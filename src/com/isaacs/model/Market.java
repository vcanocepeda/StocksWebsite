package com.isaacs.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="market")
public class Market
{
  private Integer id;
  private String code = "";
  private String city = "";
  private List<Stock> stocks = null;
  
  public Market() {}
  
  public Market(String code, String city)
  {
    setCode(code);
    setCity(city);
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", unique=true, nullable=false)
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  @Column(name="code", nullable=false, length=45)
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  @Column(name="city", length=45)
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  @OneToMany(mappedBy="market", cascade={javax.persistence.CascadeType.ALL})
  public List<Stock> getStocks()
  {
    return this.stocks;
  }
  
  public void setStocks(List<Stock> stocks)
  {
    this.stocks = stocks;
  }
}