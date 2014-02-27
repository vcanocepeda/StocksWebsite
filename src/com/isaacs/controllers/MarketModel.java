package com.isaacs.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MarketModel
  implements Serializable
{
  private static final long serialVersionUID = 6063853573648861967L;
  private String code;
  private String city;
  private Boolean readOnlyName;
  
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
  
  public Boolean getReadOnlyName()
  {
    return this.readOnlyName;
  }
  
  public void setReadOnlyName(Boolean readOnlyName)
  {
    this.readOnlyName = readOnlyName;
  }
}
