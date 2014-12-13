package com.isaacs.controllers;

import com.isaacs.dao.impl.MarketDaoHibernateImpl;
import com.isaacs.dao.impl.StockDaoHibernateImpl;
import com.isaacs.model.Market;
import com.isaacs.model.Stock;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
@URLMappings(mappings = {
		@URLMapping(id = "listStocks", pattern = "/Stocks/listStocks.xhtml", viewId = "/Stocks/listStocks.xhtml"),
		@URLMapping(id = "modifyStock", pattern = "/Stocks/modifyStock.xhtml", viewId = "/Stocks/modifyStock.xhtml") })
public class CMRStockController implements Serializable {
	private static final long serialVersionUID = -1111720015205329057L;
	@Inject
	private StockModel stockModel;
	@Inject
	private StockDaoHibernateImpl stockDao;
	@Inject
	private MarketDaoHibernateImpl marketDao;

	public StockModel getStockModel() {
		return this.stockModel;
	}

	@URLAction(mappingId = "listStocks")
	public void prepareListStocks() {
		// Get the DAOS by JNDI
		// List<Stock> stocks = this.stockDaoRest.getMarketList();
		List<Stock> stocks = this.stockDao.getStockList();
		this.stockModel.setStocksList(stocks);
	}

	@URLAction(mappingId = "modifyStock")
	public void prepareModifyStock() {
		// Get the code parameter
		String code = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("stockCode");
		Stock stock = this.stockDao.findByStockCode(code);

		if (stock == null) {
			// this.cMRMarketUserQueryModel.setNonExistingMarketUserQuery(true);
		} else {
			this.stockModel.setSelectedStock(stock);
			this.stockModel.setId(stock.getId());
			this.stockModel.setCode(stock.getCode());
			this.stockModel.setName(stock.getName());
		}
	}

	public List<Stock> getStockList() {
		return this.stockDao.getStockList();
	}

	public List<Market> getMarketList() {
		return this.marketDao.getMarketList();
	}

	public void createStock() {
		Stock stock = new Stock(this.stockModel.getCode(),
				this.stockModel.getName(), this.stockModel.getSelectedMarket());
		this.stockDao.save(stock);
	}
	
	public String modifyStock()
	  { 
		Stock stock = this.stockModel.getSelectedStock();
		stock.setCode(this.stockModel.getCode());
	    stock.setName(this.stockModel.getName());
	    this.stockDao.update(stock);      
	    return "/Stocks/listStocks.xhtml";
	  }

	public String navigateToCreateStock() {
		return "/Stocks/createStock.xhtml";
	}

	/*
	 * We need getStockFromId public String navigateToCreateMarket() { return
	 * "/Markets/createMarket.xhtml"; }
	 */
}
