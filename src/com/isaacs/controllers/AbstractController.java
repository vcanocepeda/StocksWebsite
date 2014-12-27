package com.isaacs.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


public abstract class AbstractController implements Serializable {
	
	//varia - start
	public static final String QUESTION_MARK = "?";
	public static final String AMPERSAND = "&";
	public static final String SEMICOLON = ";";
	public static final String FACES_REDIRECT_TRUE = "faces-redirect=true";
	public static final String INCLUDE_VIEW_PARAMS_TRUE = "includeViewParams=true";
	public static final String I18N_BUNDLE = "i18nBundle";
	private static final String TIME_PATTERN = "timePattern";
	private static final String DATE_PATTERN = "datePattern";
	private static final String DATE_TIME_SEPARATOR = "dateTimeSeparator";
	//varia - end
	
	//views - start
	public static final String MARKETS_LIST_VIEW = "/Markets/listMarkets";
	public static final String MARKETS_MODIFY_VIEW = "/Markets/modifyMarket";
	public static final String MARKETS_CREATE_VIEW = "/Markets/createMarket";
	//views - end
		
	
	//request parameters - start
	public static final String CLEAR_SESSION_REQUEST_PARAMETER = "clearSession";
	public static final String INFO_MESSAGE_KEY_REQUEST_PARAMETER = "infoMessageKey";
	//request parameters - end

	protected AbstractController() {
		super();
	}

	/**
	 * Adds a <code>faces-redirect=true</code> expression to the specified <code>outcome</code>.
	 * 
	 * @param outcome  a navigation outcome.
	 * @return  a modified navigation outcome with an added <code>faces-redirect=true</code> command.
	 */
	protected String appendFacesRedirect(String outcome) {
		StringBuilder modifiedOutcome = new StringBuilder(outcome);
		
		if(!outcome.endsWith(QUESTION_MARK)) {
			modifiedOutcome.append(QUESTION_MARK);
		}
		
		if(!outcome.contains(FACES_REDIRECT_TRUE)) {
			modifiedOutcome.append(FACES_REDIRECT_TRUE);
		}
		
		return modifiedOutcome.toString();
  }
	
		
	protected void addFatalMessage(String messageKey) {
		addMessage(FacesMessage.SEVERITY_FATAL, messageKey);
	}
	
	protected void addErrorMessage(String messageKey) {
		addMessage(FacesMessage.SEVERITY_ERROR, messageKey);
	}
	
	protected void addWarningMessage(String messageKey) {
		addMessage(FacesMessage.SEVERITY_WARN, messageKey);
	}

	protected void addInfoMessage(String messageKey) {
		addMessage(FacesMessage.SEVERITY_INFO, messageKey);
	}

	protected void addMessage(FacesMessage.Severity severity, String messageKey) {
		if(messageKey != null) {			
			String message = getPropertyFromResourceBundle(messageKey);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
		}
	}	

	protected void markFieldsAsInvalid(String messageKey, String... fieldIds) {
  	for(String fieldId : fieldIds) {
  	  UIComponent field = findChild(FacesContext.getCurrentInstance().getViewRoot(), fieldId);
  	  if(field instanceof UIInput) {
  	  	UIInput inputField = (UIInput)field;
  	  	inputField.setValid(false);
  	  }
  	}
    addErrorMessage(messageKey);
  }

	private UIComponent findChild(UIComponent component, String componentId) {
		UIComponent theChild = null;
		
		String parentId = component.getId();
		if(parentId.equals(componentId)) {
			theChild = component;
		}else {
			for(UIComponent child : component.getChildren()) {
				theChild = findChild(child, componentId);
			}
		}
		
		return theChild;
	}

	protected String getRequestParameter(String parameterName) {
		HttpServletRequest httpRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return httpRequest.getParameter(parameterName);
	}
	
	protected Integer getIntegerRequestParameter(String parameterName) {
		Integer intParameter = null;
		HttpServletRequest httpRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String stringParameter = httpRequest.getParameter(parameterName);
		try {
			if(stringParameter != null) {				
				intParameter = Integer.parseInt(stringParameter);
			}
		}catch(Throwable t) {
			t.printStackTrace();
		}
		return intParameter;
	}

	/**
	 * Returns true if the current request carries a parameter called <code>clearSession</code>, false otherwise.
	 *
	 * @return true if the current request carries a parameter called <code>clearSession</code>, false otherwise.
	 */
	protected Boolean getClearSessionRequestParameter() {
		Boolean clearSession = false;
		
		if(getRequestParameter(CLEAR_SESSION_REQUEST_PARAMETER) != null) {
			clearSession = true;
		}
		
		return clearSession;
	}
	
	protected String getPropertyFromResourceBundle(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, I18N_BUNDLE);
		return resourceBundle.getString(key);
	}
	
	protected String getDatePattern() {
		return getPropertyFromResourceBundle(DATE_PATTERN);		
	}
	
	protected String getTimePattern() {
		return getPropertyFromResourceBundle(TIME_PATTERN);
	}
	
	protected String getDateTimeSeparator() {
		return getPropertyFromResourceBundle(DATE_TIME_SEPARATOR);
	}
	
	protected String getDateTimeFormat() {
		return getDatePattern() +  getDateTimeSeparator() + getTimePattern();
	}
	
}
