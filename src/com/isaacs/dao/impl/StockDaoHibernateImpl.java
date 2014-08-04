// We have to change the methods (Copy the ones from MarketDaoHibernate...)
package com.isaacs.dao.impl;

import com.isaacs.dao.StockDao;
import com.isaacs.model.Market;
import com.isaacs.model.Stock;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public class StockDaoHibernateImpl implements Serializable, StockDao {
	private static final long serialVersionUID = 8890073456388862102L;
	static Logger logger = Logger.getLogger(StockDaoHibernateImpl.class);
	private EntityManagerFactory emf;
	private EntityManager em;

	public StockDaoHibernateImpl() {
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

	public void save(Stock stock) {
		try {
			this.em.getTransaction().begin();
			if (!this.em.contains(stock)) {
				this.em.persist(stock);

				this.em.flush();
			}
			this.em.getTransaction().commit();
			logger.info("EntityMarket saved: stock " + stock.getCode());

		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void update(Stock stock) {
	}

	public void delete(Stock stock) {
	}

	public Stock findByStockCode(String stockCode) {
		return null;
	}

	public List<Stock> getStockList() {
		List<Stock> listStocks = null;
		try {
			listStocks = this.em.createQuery("SELECT s FROM Stock s")
					.getResultList();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
		}
		return listStocks;
	}

	public List<Market> getMarketList() {
		List<Market> listMarkets = null;
		try {
			listMarkets = this.em.createQuery("SELECT m FROM Market m")
					.getResultList();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
		}
		return listMarkets;
	}

}