package com.isaacs.dao.factories;

import com.isaacs.dao.StockDao;
import com.isaacs.listeners.EMFServletContextListener;
import com.isaacs.dao.impl.StockDaoHibernateImpl;
//import com.isaacs.dao.impl.StockDaoRestXmlImpl;
// Look if we could get a parent factory Abstract Factory for all tDaos

public class StockDaoFactory implements DaoTypes{
	
	private static String stockDao = "";//new DaoType().getDaoType();
	
	//use marketDao method to get object of type marketDao 
	public static StockDao getStockDao(){
	      if (stockDao == "") 
	    	  stockDao = DaoType[EMFServletContextListener.getIntDaoSelected()];
	      if(stockDao.equalsIgnoreCase(DaoType[0])){
	         return new StockDaoHibernateImpl();
	      } else if(stockDao.equalsIgnoreCase(DaoType[1])){
	         return new StockDaoHibernateImpl(); //StockDaoRestXmlImpl
	      } 
	      return null;
	   }
	
}