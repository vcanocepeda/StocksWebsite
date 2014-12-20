package com.isaacs.controllers;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

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
		if (user != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.setAttribute("login", user);
			result = appendFacesRedirect("/Markets/listMarkets.xhtml");
		}
		return result;
	}

}
