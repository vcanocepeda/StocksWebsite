package com.isaacs.controllers;

import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import com.isaacs.dao.impl.UserDaoHibernateImpl;
import com.isaacs.model.User;

@Named
@SessionScoped
public class LoginController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2668555881118974729L;
	@Inject
	private LoginModel loginModel;
	@Inject
	private UserDaoHibernateImpl userDao;
	
	 public LoginModel getLoginModel() {
			return this.loginModel;
		  }

	public LoginController() {
		super();
	}

	public String checkCredentials() {
		// TODO: check the credentials at the database
		// if they match and are not expired make the user navigate to its home
		// page
		// if they don't mach make the user navigate to the incorrectCredentials
		// page
		// if they are expired make the user navigate to the expiredCredentials
		// page
		// also check that the user has a role; if the user doesn't have a role
		// the redirecto to userWithoutCredentials view
		String result = "";
		String username = getLoginModel().getUserName();
		String password = getLoginModel().getPassword();
		User user = this.userDao.findUser(username, password);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (user != null) {
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.setAttribute("login", user);
			result = appendFacesRedirect("/Markets/listMarkets");
		} else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login no correcto", "PrimeFaces Rocks."));
		}
		return result;
	}
	
	//Create a Enum with the tabIndex and webPages
	public void tabIsChanged(TabChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		  Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		  TabView tabView = (TabView) event.getComponent();
		  String activeIndexValue = params.get(tabView.getClientId(context) + "_tabindex");
		  // doesnt workreturn appendFacesRedirect(MARKETS_CREATE_VIEW);
	}

}
