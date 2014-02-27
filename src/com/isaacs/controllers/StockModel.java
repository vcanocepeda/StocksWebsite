package com.isaacs.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class StockModel
  implements Serializable
{
  private static final long serialVersionUID = 7263786731505063384L;
  private String code;
  private String name;
  private Integer selectedMarket;
  private Boolean readOnlyName;
  
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Integer getSelectedMarket()
  {
    return this.selectedMarket;
  }
  
  public void setSelectedMarket(Integer selectedMarket)
  {
    this.selectedMarket = selectedMarket;
  }
  
  public Boolean getReadOnlyName()
  {
    return this.readOnlyName;
  }
  
  public void setReadOnlyName(Boolean readOnlyService)
  {
    this.readOnlyName = readOnlyService;
  }
}
