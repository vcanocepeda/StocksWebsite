package com.isaacs.dao.impl;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.isaacs.listeners.JsfServletContextListener;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.isaacs.dao.GenericDao;
import com.isaacs.model.Market;

public class MarketDaoRestNewImpl implements Serializable, GenericDao<Market, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -288077746076465581L;
	static final String url = JsfServletContextListener
			.getUrlWebservice() + "/MarketService";
	static Logger logger = Logger.getLogger(MarketDaoHibernateImpl.class);

	@Override
	public String save(Market market) throws Exception{
		String result ="";
		String urlAddMarket = url + "/addMarketXml";
		try {
			Client client = ClientBuilder.newClient();
			
			Invocation saveBook = client.target(urlAddMarket).request()
					.buildPost(Entity.entity(market, MediaType.APPLICATION_XML));
			Response response = saveBook.invoke();
			result = response.readEntity(String.class);
			logger.info("EntityMarket saved: market " + market.getCode() +
					" on " + url);
		} catch ( Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public String update(Market paramMarket) throws Exception{
		String result ="";
		String urlAddMarket = url + "/updateMarketXml";
		try {
			Client client = ClientBuilder.newClient();
			
			Invocation updateBook = client.target(urlAddMarket).request()
					.buildPost(Entity.entity(paramMarket, MediaType.APPLICATION_XML));
			Response response = updateBook.invoke();
			result = response.readEntity(String.class);
			logger.info("EntityMarket updated: market " + paramMarket.getCode() +
					" on " + url);
		} catch ( Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public String delete(Market paramMarket) {
		String error ="";
		return error;
	}

	@Override
	public Market find(String code) {
		String urlGetMarket = url + "/getMarketXml/" + code;
		Market market = null;

		try {
			URL obj = new URL(urlGetMarket);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			System.out.println("\nSending 'GET' request to URL : " + urlGetMarket);
			int responseCode = con.getResponseCode();	
			System.out.println("Response Code : " + responseCode);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Market.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			market = (Market) jaxbUnmarshaller.unmarshal(con.getInputStream());
			
			/* // 2. Fetch book by id
				Book book2 = client.target(REST_SERVICE_URL).path("/{bookId}")
				.resolveTemplate("bookId", bookId).request().get(Book.class);
			*/
			/* ClientResponse response = r.get(ClientResponse.class);
    			EntityTag e = response.getEntityTag();
    			String entity = response.getEntity(String.class); */
		} catch (Exception e) {
			// TODO: handle exception
		}

		return market;
	}

	@Override
	public List<Market> list() throws Exception {
		// We don't have any client but since java-rs 2.0 we have one.. 
		// We could use the popular Jersey too
		// We have to configure marketService URL and add params in every method
		List<Market> markets = null;
		String urlGetMarkets = url + "/getMarketsXml";
		try {
			Client client = ClientBuilder.newClient();
			GenericType<List<Market>> listmarkets = new GenericType<List<Market>>() {};
			markets = client.target(urlGetMarkets)
				.request(MediaType.APPLICATION_XML).get(listmarkets);
		} catch ( javax.ws.rs.NotFoundException e) {
			throw e;
		}
		return markets;
	}
/* We have to try here with a List<Market>		
		Response response = client.target(url)
				.request(MediaType.APPLICATION_XML).get();
		Market bookWrapper = response.readEntity(Market.class); */


	/*
	 * Jersey request String uri = "http://localhost:8080/jd/rest/emp/getEmp";
	 * EmpRequest request = new EmpRequest(); //set id as 1 for OK response
	 * request.setId(2); request.setName("PK"); try{ Client client =
	 * Client.create(); WebResource r=client.resource(uri); ClientResponse
	 * response =
	 * r.type(MediaType.APPLICATION_XML).post(ClientResponse.class,request );
	 * System.out.println(response.getStatus()); if(response.getStatus() ==
	 * 200){ EmpResponse empResponse = response.getEntity(EmpResponse.class);
	 * System.out.println(empResponse.getId() + "::"+empResponse.getName());
	 * }else{ ErrorResponse exc = response.getEntity(ErrorResponse.class);
	 * System.out.println(exc.getErrorCode());
	 * System.out.println(exc.getErrorId()); } }catch(Exception e){
	 * System.out.println(e.getMessage()); }
	 */
	
	
}
