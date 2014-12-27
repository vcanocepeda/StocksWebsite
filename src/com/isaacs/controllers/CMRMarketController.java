package com.isaacs.controllers;

import com.isaacs.dao.MarketDao;
import com.isaacs.model.Market;
import com.isaacs.dao.factories.MarketDaoFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@Named
@SessionScoped
@URLMappings(mappings = {
		@URLMapping(id = "listMarkets", pattern = "/Markets/listMarkets", viewId = "/Markets/listMarkets.xhtml"),
		@URLMapping(id = "modifyMarket", pattern = "/Markets/modifyMarket", viewId = "/Markets/modifyMarket.xhtml"),
		@URLMapping(id = "createMarket", pattern = "/Markets/createMarket", viewId = "/Markets/createMarket.xhtml")})
public class CMRMarketController extends AbstractController {
	private static final long serialVersionUID = 1851141508761707284L;
	@Inject
	private MarketModel marketModel;

	/*@Inject private MarketDaoHibernateImpl marketDao;
	  private MarketDaoRestXmlImpl marketDaoRest;
	  We don't need to inject it */
	
	private static MarketDao marketDao = null;

	public MarketModel getMarketModel() {
		return this.marketModel;
	}
	
	@PostConstruct
	public void setMarketDao() {
		// Get the DAOS by JNDI
		// MarketDaoFactory factory = new MarketDaoFactory();
		if (marketDao == null)
			marketDao = MarketDaoFactory.getMarketDao();
	}

	@URLAction(mappingId = "listMarkets")
	public void prepareListMarkets() {
		List<Market> markets = marketDao.getMarketList();
		this.marketModel.setMarketsList(markets);
	}

	@URLAction(mappingId = "modifyMarket")
	public void prepareModifyMarket() {
		// Get the code parameter
		String code = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("marketCode");
		Market market = marketDao.findByMarketCode(code);

		if (market == null) {
			// this.cMRMarketUserQueryModel.setNonExistingMarketUserQuery(true);
		} else {
			this.marketModel.setSelectedMarket(market);
			this.marketModel.setId(market.getId());
			this.marketModel.setCity(market.getCity());
			this.marketModel.setCode(market.getCode());
		}
	}

	public void createMarket() {
		Market market = new Market(this.marketModel.getCode(),
				this.marketModel.getCity());
		marketDao.save(market);
	}

	public String modifyMarket() {
		Market market = this.marketModel.getSelectedMarket();
		market.setCode(this.marketModel.getCode());
		market.setCity(this.marketModel.getCity());
		marketDao.update(market);
		return MARKETS_LIST_VIEW;
	}

	public String navigateToCreateMarket() {
		return MARKETS_CREATE_VIEW; //Maybe we need appendFacesRedirect
	}

	// We need to place the outcomes on the abstract controllers
	public String navigateToModifyMarket() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> map = facesContext.getExternalContext()
				.getRequestParameterMap();
		String result = appendFacesRedirect(MARKETS_MODIFY_VIEW);
		result += "&marketCode=" + map.get("marketCode");
		return result;
	}
}
