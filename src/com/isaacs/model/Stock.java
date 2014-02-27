package com.isaacs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class Stock
{
  private Integer id;
  private String code = "";
  private String name = "";
  private Market market = null;
  private Integer idmarket;
  
  public Stock() {}
  
  public Stock(String code, String name, Integer idmarket)
  {
    setCode(code);
    setName(name);
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
  
  @Column(name="name", length=45)
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  @ManyToOne
  @JoinColumn(name="idmarket", nullable=false)
  public Market getMarket()
  {
    return this.market;
  }
  
  public void setMarket(Market market)
  {
    this.market = market;
  }
}