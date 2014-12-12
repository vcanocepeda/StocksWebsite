package com.isaacs.controllers;

import com.isaacs.dao.impl.MarketDaoHibernateImpl;
import com.isaacs.dao.impl.StockDaoHibernateImpl;
import com.isaacs.model.Market;
import com.isaacs.model.Stock;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CMRStockController
  implements Serializable
{
  private static final long serialVersionUID = -1111720015205329057L;
  @Inject
  private StockModel stockModel;
  @Inject
  private StockDaoHibernateImpl stockDao;
  @Inject
  private MarketDaoHibernateImpl marketDao;
  
  public void prepareModel()
  {
    System.out.println("Prueba");
  }
  
  public List<Stock> getStockList()
  {
    return this.stockDao.getStockList();
  }
  
  public List<Market> getMarketList()
  {
    return this.marketDao.getMarketList();
  }
  
  public void createStock()
  {
    Stock stock = new Stock(this.stockModel.getCode(), this.stockModel.getName(), 
      this.stockModel.getSelectedMarket());
    this.stockDao.save(stock);
  }
  
  public String navigateToCreateStock()
  {
    return "/Stocks/createStock.xhtml";
  }
  
/* 
 * We need getStockFromId
 * public String navigateToCreateMarket()
  {
    return "/Markets/createMarket.xhtml";
  } */
}
