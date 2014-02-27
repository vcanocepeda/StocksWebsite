package com.isaacs.dao;

import com.isaacs.model.Stock;
import java.util.List;

public abstract interface StockDao
{
  public abstract void save(Stock paramStock);
  
  public abstract void update(Stock paramStock);
  
  public abstract void delete(Stock paramStock);
  
  public abstract Stock findByStockCode(String paramString);
  
  public abstract List<Stock> getStockList();
}
