package com.isaacs.controllers;

import com.isaacs.dao.GenericDao;
import com.isaacs.model.Market;
import com.isaacs.dao.factories.GenericDaoFactory;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

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
	
//	private static MarketDao marketDao = null;
	
	private static GenericDao<Market, String> marketDao = null;

	public MarketModel getMarketModel() {
		return this.marketModel;
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void setMarketDao() {
		
		if (marketDao == null)
			marketDao = GenericDaoFactory.getGenericDao(Market.class);
	}

	@URLAction(mappingId = "listMarkets")
	public void prepareListMarkets() {
		List<Market> markets = null;
		try {
			markets = marketDao.list();
		}catch(Exception e){
			if (e instanceof javax.ws.rs.ProcessingException) {
				addErrorMessage("error_connection_listmarkets");
			}
		}	
		this.marketModel.setMarketsList(markets);
	}

	@URLAction(mappingId = "modifyMarket")
	public void prepareModifyMarket() {
		// Get the code parameter
		String code = "";
		Market market = null;
		if ((this.marketModel.getSelectedMarket()) == null) {
			code = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("marketCode");
			market = marketDao.find(code);
			this.marketModel.setSelectedMarket(market);
			this.marketModel.setId(market.getId());
			this.marketModel.setCity(market.getCity());
			this.marketModel.setCode(market.getCode());
		}
	}

	public String createMarket() {
		String result = "";
		String forwardPage = appendFacesRedirect(MARKETS_LIST_VIEW);
		
		Market market = new Market(this.marketModel.getCode(),
				this.marketModel.getCity());
		try {
			result = marketDao.save(market);
		
		} catch(Exception e){
			if (e instanceof javax.ws.rs.NotFoundException) {
				addErrorMessage("error_createmarket", market.getCode());
				forwardPage = "";
			}
		}	
			
		return forwardPage;
	}

	public String modifyMarket() {
		String result = "";
		String forwardPage = appendFacesRedirect(MARKETS_LIST_VIEW);
		Market market = this.marketModel.getSelectedMarket();
		market.setCode(this.marketModel.getCode());
		market.setCity(this.marketModel.getCity());
		try {			
			result = marketDao.update(market);		
		} catch(Exception e){
			if (e instanceof javax.ws.rs.NotFoundException) {
				addErrorMessage("error_modifymarket");
				forwardPage = "";
			}
		}			
		return forwardPage;	
	}

	public String navigateToCreateMarket() {
		return appendFacesRedirect(MARKETS_CREATE_VIEW);
	}

	// We need to place the outcomes on the abstract controllers with 
	// a new method Maybe we need appendFacesRedirect(param1)
	public String navigateToModifyMarket() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> map = facesContext.getExternalContext()
				.getRequestParameterMap();
		String result = appendFacesRedirect(MARKETS_MODIFY_VIEW);
		result += "&marketCode=" + map.get("marketCode");
		return result;
	}
}
