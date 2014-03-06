package com.isaacs.dao.impl;

import com.isaacs.dao.MarketDao;
import com.isaacs.model.Market;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MarketDaoHibernateImpl implements Serializable, MarketDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204732623661515600L;
	static Logger logger = Logger.getLogger(MarketDaoHibernateImpl.class);
	private EntityManagerFactory emf;
	private EntityManager em;

	public MarketDaoHibernateImpl() {
		
	}
	
	@PostConstruct
	public void CreateEntityManager() {
		Properties prop = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream(
				"jdbc.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.emf = Persistence.createEntityManagerFactory("StockWebService",
				prop);
		this.em = this.emf.createEntityManager();
		logger.info("EntityManager created: em " + this.em.toString());
	}
	

	public void save(Market market) {
		try {
			this.em.getTransaction().begin();
			if (!this.em.contains(market)) {
				this.em.persist(market);

				this.em.flush();
			}
			this.em.getTransaction().commit();
			logger.info("EntityMarket saved: market " + market.getCode());
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void update(Market market) {
	}

	public void delete(Market Market) {
	}

	public Market findByMarketCode(String marketCode) {
		return null;
	}

	public List<Market> getMarketList() {
		List<Market> listMarkets = null;
		try {
			listMarkets = this.em.createQuery("SELECT m FROM Market m")
					.getResultList();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			logger.error(e);
		}
		return listMarkets;
	}
	
	@PreDestroy
	public void CloseEntityManager() {
		System.out.println("PreDestroy");
		this.em.close();
		logger.info("EntityManager destroyed: em " + this.em.toString());
	}
}
