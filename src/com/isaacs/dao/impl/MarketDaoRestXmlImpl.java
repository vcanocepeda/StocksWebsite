package com.isaacs.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.ext.
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.isaacs.dao.MarketDao;
import com.isaacs.model.Market;

public class MarketDaoRestXmlImpl implements Serializable, MarketDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -288077746076465581L;
	
	private static final String url = "http://localhost:9001/MarketService/getMarketsXml";

	@Override
	public void save(Market market) {
		try {

			File file = new File("C:\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Market.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(market, file);
			jaxbMarshaller.marshal(market, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Market paramMarket) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Market paramMarket) {
		// TODO Auto-generated method stub

	}

	@Override
	public Market findByMarketCode(String code) {
		String url = "http://localhost:9001/MarketService/getMarketXml/NYSE";

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		StringBuffer response;
		Market market = null;

		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			// BufferedReader in = new BufferedReader(new InputStreamReader(
			// con.getInputStream()));
			// String inputLine;
			// response = new StringBuffer();
			// while ((inputLine = in.readLine()) != null) {
			// response.append(inputLine);
			// }
			// in.close();

			// print result
			// System.out.println(response.toString());

			JAXBContext jaxbContext = JAXBContext.newInstance(Market.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			market = (Market) jaxbUnmarshaller.unmarshal(con.getInputStream());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return market;
	}

	@Override
	public List<Market> getMarketList() {
		// We don't have any client but since java-rs 2.0 we have one.. We could use the popular Jersey too
		// We have to configure marketService URL and add params in every method
		Client client = ClientBuilder.newClient();
		GenericType<List<Market>> listmarkets = new GenericType<List<Market>>() {};
		List<Market> markets = client.target(url)
				.request(MediaType.APPLICATION_XML).get(listmarkets);
		
		return markets;

/* We have to try here with a List<Market>		
		Response response = client.target(url)
				.request(MediaType.APPLICATION_XML).get();
		Market bookWrapper = response.readEntity(Market.class); */
/*		Market market = client.target("http://example.com/customers")
				.queryParam("name", "Bill Burke")
				.request().get(Market.class); */
		
		// Example Client client = ClientFactory.newClient();
		/*WebTarget target = client.target("http://example.com/shop");
		Form form = new Form().param("customer", "Bill")
		.param("product", "IPhone 5")
		.param("CC", "4444 4444 4444 4444");
		Response response = target.request().post(Entity.form(form));
		assert response.getStatus() == 200;
		Order order = response.readEntity(Order.class); */
	}

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
