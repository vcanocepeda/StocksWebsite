package com.isaacs.controllers;

import com.isaacs.dao.impl.MarketDaoHibernateImpl;
import com.isaacs.dao.impl.MarketDaoRestXmlImpl;
import com.isaacs.model.Market;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
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
  
  public void prepareModel()
  {
    System.out.println("Prueba");
  }
  
  public void createMarket()
  {
    Market market = new Market(this.marketModel.getCode(), this.marketModel.getCity());
    this.marketDao.save(market);
  //final Entity<Market> entity = Entity.entity(market, MediaType.APPLICATION_XML);
    //Response response = this.root.request().post(entity, Response.class);
   // Form form = new Form().param("customer", "Bill")
   //         .param("product", "IPhone 5") 
   //         .param("CC", "4444 4444 4444 4444"); 
  }
  
  public List<Market> getMarketList()
  {
    return this.marketDao.getMarketList();
  }
  
  public String navigateToCreateMarket()
  {
//	Market market = this.marketDaoRest.findByMarketCode("NYSE");
	List<Market> markets = this.marketDaoRest.getMarketList();
    return "/Markets/createMarket.xhtml";
  }
}
