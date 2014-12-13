package com.isaacs.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import com.isaacs.model.Market;

@Named
@ViewScoped
public class MarketModel
  implements Serializable
{
  private static final long serialVersionUID = 6063853573648861967L;
  private Integer id;
  private String code;
  private String city;
  private Market selectedMarket;
  private List<Market> marketsList;
  private Boolean readOnlyName;
  
  public Integer getId() {
	return id;
  }

  public void setId(Integer id) {
	this.id = id;
  }
 
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public List<Market> getMarketsList()
  {
    return this.marketsList;
  }
  
  public void setMarketsList(List<Market> markets)
  {
    this.marketsList = markets;
  }
  
  public Market getSelectedMarket() {
	return selectedMarket;
  }

  public void setSelectedMarket(Market selectedMarket) {
	this.selectedMarket = selectedMarket;
  }
  
  public Boolean getReadOnlyName()
  {
    return this.readOnlyName;
  }
  
  public void setReadOnlyName(Boolean readOnlyName)
  {
    this.readOnlyName = readOnlyName;
  }
}
