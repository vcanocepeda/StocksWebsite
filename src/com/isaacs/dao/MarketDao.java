package com.isaacs.dao;

import com.isaacs.model.Market;
import java.util.List;

public abstract interface MarketDao
{
  public abstract void save(Market paramMarket);
  
  public abstract void update(Market paramMarket);
  
  public abstract void delete(Market paramMarket);
  
  public abstract Market findByMarketCode(String paramString);
  
  public abstract List<Market> getMarketList();
}
