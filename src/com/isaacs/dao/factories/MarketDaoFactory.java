package com.isaacs.dao.factories;

import com.isaacs.dao.MarketDao;
import com.isaacs.listeners.JsfServletContextListener;
import com.isaacs.dao.impl.MarketDaoHibernateImpl;
import com.isaacs.dao.impl.MarketDaoRestXmlImpl;

public class MarketDaoFactory implements DaoTypes{
	
	private static String marketDao = "";//new DaoType().getDaoType();
	
	//use marketDao method to get object of type marketDao 
	public static MarketDao getMarketDao(){
	      if (marketDao == "") 
	    	  marketDao = DaoType[JsfServletContextListener.getIntDaoSelected()];
	      if(marketDao.equalsIgnoreCase(DaoType[0])){
	         return new MarketDaoHibernateImpl();
	      } else if(marketDao.equalsIgnoreCase(DaoType[1])){
	         return new MarketDaoRestXmlImpl();
	      } 
	      return null;
	   }
	
}