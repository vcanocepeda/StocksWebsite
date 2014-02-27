package com.isaacs.controllers;

import com.isaacs.dao.impl.MarketDaoHibernateImpl;
import com.isaacs.model.Market;
import java.io.PrintStream;
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
  
  public void prepareModel()
  {
    System.out.println("Prueba");
  }
  
  public void createMarket()
  {
    Market market = new Market(this.marketModel.getCode(), this.marketModel.getCity());
    this.marketDao.save(market);
  }
  
  public List<Market> getMarketList()
  {
    return this.marketDao.getMarketList();
  }
  
  public String navigateToCreateMarket()
  {
    return "/Markets/createMarket.xhtml";
  }
}
