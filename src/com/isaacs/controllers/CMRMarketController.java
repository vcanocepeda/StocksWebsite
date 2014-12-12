package com.isaacs.controllers;

import com.isaacs.dao.impl.MarketDaoHibernateImpl;
import com.isaacs.dao.impl.MarketDaoRestXmlImpl;
import com.isaacs.model.Market;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@Named
@SessionScoped
@URLMappings (
		  mappings={
			@URLMapping(id="listMarkets", pattern="/Markets/listMarkets.xhtml", viewId="/Markets/listMarkets.xhtml"),
		    @URLMapping(id="modifyMarket", pattern="/Markets/modifyMarket.xhtml", viewId="/Markets/modifyMarket.xhtml")    
		  }
		)
public class CMRMarketController
  implements Serializable
{
  private static final long serialVersionUID = 1851141508761707284L;
  @Inject
  private MarketModel marketModel;
  @Inject
  private MarketDaoHibernateImpl marketDao;
  @Inject
  private MarketDaoRestXmlImpl marketDaoRest;
  
  
  public MarketModel getMarketModel() {
	return this.marketModel;
  }
  
  public List<Market> getMarketsList()
  {
    return this.marketModel.getListMarkets();
  }
  
  @URLAction(mappingId="listMarkets")
  public void prepareListMarkets()
  {
	  //Get the DAOS by JNDI
//		List<Market> markets = this.marketDaoRest.getMarketList();
	    List<Market> markets = this.marketDao.getMarketList();
		this.marketModel.setListMarkets(markets);
  }
  
  
  @URLAction(mappingId="modifyMarket")
  public void prepareModifyMarket()
  {
	//Get the code parameter
    String code = "NYSE";
    String id = FacesContext.getCurrentInstance().getExternalContext().
    				getRequestParameterMap().get("id");
    // Employee employee = (Employee)entityManager.find("Market", employeeId);
	Market market = this.marketDao.findByMarketCode(code);
	this.marketModel.setId(market.getId());
    this.marketModel.setCity(market.getCity());
    this.marketModel.setCode(market.getCode());
  }
  
  public void createMarket()
  {
    Market market = new Market(this.marketModel.getCode(), this.marketModel.getCity());
    this.marketDaoRest.save(market); 
  }
 
  
  public String navigateToCreateMarket()
  {
//	Market market = this.marketDaoRest.findByMarketCode("NYSE");
//	List<Market> markets = this.marketDaoRest.getMarketList();
    return "/Markets/createMarket.xhtml";
  }
  /* // Obtain our environment naming context
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");

	// Look up our data source
		DataSource ds = (DataSource)
  		envCtx.lookup("jdbc/EmployeeDB");

	// Allocate and use a connection from the pool
		Connection conn = ds.getConnection();
	... use this connection to access the database ...
	conn.close(); */
}
