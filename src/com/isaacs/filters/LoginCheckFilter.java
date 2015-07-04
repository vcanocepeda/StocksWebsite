package com.isaacs.filters;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.isaacs.model.User;

@WebFilter(filterName = "LoginCheckFilter",
urlPatterns = {"/Markets/*","/Stocks/*"},
initParams = {
    @WebInitParam(name = "mood", value = "awake")})
public class LoginCheckFilter implements Filter {
	
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession(false);
		
		User user = (User) req.getSession().getAttribute("login");
		if (user != null) {						
			// User is logged so just continue request.
			chain.doFilter(request, response);
		} else {
			// User is not logged in, so redirect to login.
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Session has expired", "PrimeFaces Rocks."));
			request.getRequestDispatcher("/login.xhtml").forward(request, response);		
		} 		
	}
		
	 @Override
	 public void destroy() {
		 // If you have assigned any expensive resources as field of
	     // this Filter class, then you could clean/close them here.
	 }
}