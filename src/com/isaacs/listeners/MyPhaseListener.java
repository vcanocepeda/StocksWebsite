package com.isaacs.listeners;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MyPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 7219091759039246457L;

	@Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
      //Phases of JSF  PhaseId.
      // return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // Do your job here which should run right before the RENDER_RESPONSE.
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        // Do your job here which should run right after the RENDER_RESPONSE.
    	FacesContext fc = event.getFacesContext();

        // Check to see if they are on the login page.
        boolean loginPage = 
        		fc.getViewRoot().getViewId().lastIndexOf("login") > -1 ? true : false;
//        if (!loginPage && !loggedIn()) {
//            NavigationHandler nh = fc.getApplication().getNavigationHandler();
//            nh.handleNavigation(fc, null, "logout"); */
 //       }
    }
    
//    private boolean loggedIn() {
//        return LoginController.loggedIn().booleanValue()c;
//    }

}