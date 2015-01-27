package com.isaacs.dao;

import com.isaacs.model.Market;
import java.util.List;

public abstract interface MarketDao
{
  public abstract String save(Market paramMarket);
  
  public abstract String update(Market paramMarket);
  
  public abstract String delete(Market paramMarket);
  
  public abstract Market findByMarketCode(String paramString);
  
  public abstract List<Market> getMarketList();
}
