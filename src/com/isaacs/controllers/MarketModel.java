package com.isaacs.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.isaacs.model.Market;

@Named
@RequestScoped
public class MarketModel
  implements Serializable
{
  private static final long serialVersionUID = 6063853573648861967L;
  private Integer id;
  private String code;
  private String city;
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
  
  public List<Market> getListMarkets()
  {
    return this.marketsList;
  }
  
  public void setListMarkets(List<Market> markets)
  {
    this.marketsList = markets;
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
