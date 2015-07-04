package com.isaacs.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

//import javax.validation.constraints.NotNull;

/**
 * Model for the <code>loginForm.xhtml</code> view.
 *
 * @author Comunytek.
 */
@Named
@RequestScoped
public class LoginModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5869785507313454742L;
	private String userName;
	private String password;

	public LoginModel() {
		super();
	}

	// @NotNull
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// @NotNull
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
