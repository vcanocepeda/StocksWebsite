package com.isaacs.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
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
		
		User user = (User) req.getSession().getAttribute("login");
		if (user != null) {						
			// User is logged so just continue request.
			chain.doFilter(request, response);
		} else {
			// User is not logged in, so redirect to login.
			request.getRequestDispatcher("/login.xhtml").forward(request, response);		
		} 		
	}
		
	public void destroy() {
	}
}