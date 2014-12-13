package com.isaacs.controllers;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.faces.bean.ViewScoped;

import com.isaacs.model.Stock;

@Named
@ViewScoped
public class StockModel implements Serializable {
	private static final long serialVersionUID = 7263786731505063384L;
	private Integer id;
	private String code;
	private String name;
	private Integer selectedMarket;
	private Stock selectedStock;
	private List<Stock> stocksList;	
	private Boolean readOnlyName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSelectedMarket() {
		return this.selectedMarket;
	}

	public void setSelectedMarket(Integer selectedMarket) {
		this.selectedMarket = selectedMarket;
	}

	public Stock getSelectedStock() {
		return selectedStock;
	}

	public void setSelectedStock(Stock selectedStock) {
		this.selectedStock = selectedStock;
	}

	public List<Stock> getStocksList() {
		return stocksList;
	}

	public void setStocksList(List<Stock> stocksList) {
		this.stocksList = stocksList;
	}

	public Boolean getReadOnlyName() {
		return this.readOnlyName;
	}

	public void setReadOnlyName(Boolean readOnlyService) {
		this.readOnlyName = readOnlyService;
	}
}
