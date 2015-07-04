package com.isaacs.dao.factories;

import com.isaacs.dao.GenericDao;
import com.isaacs.listeners.JsfServletContextListener;
import com.isaacs.model.Market;
import com.isaacs.dao.impl.MarketDaoELImpl;
import com.isaacs.dao.impl.MarketDaoRestNewImpl;

public class GenericDaoFactory { //implements DaoTypes{
	
	private static DaoTypes daoSelected = null;
			
	//use marketDao method to get object of type marketDao 
	public static GenericDao getGenericDao(Class clazz){
	    //  if (daoVar == "") 
	    //	  daoVar = DaoType[JsfServletContextListener.getIntDaoSelected()];
		if (daoSelected == null) {
				int i = JsfServletContextListener.getIntDaoSelected();
				daoSelected = DaoTypes.values()[i];
			}
	      if(daoSelected == DaoTypes.HIBERNATE){
	    	  if (clazz.isAssignableFrom(Market.class)) //We need Enums
	    		  return new MarketDaoELImpl();
	      } else if(daoSelected == DaoTypes.RESTXML){
	    	  if (clazz.isAssignableFrom(Market.class))
	    		  return new MarketDaoRestNewImpl();
	      } 
	      return null;
	   }
	
}