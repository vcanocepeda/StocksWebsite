package com.isaacs.dao.impl;

import com.isaacs.dao.MarketDao;
import com.isaacs.model.Market;
import com.isaacs.listeners.JsfServletContextListener;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class MarketDaoHibernateImpl implements Serializable, MarketDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204732623661515600L;
	static Logger logger = Logger.getLogger(MarketDaoHibernateImpl.class);
	private EntityManager em;

	public MarketDaoHibernateImpl() {
		this.em = JsfServletContextListener.createEntityManager();
		logger.info("EntityManager created: em " + this.em.toString());
	}
	
	@PostConstruct
	public void CreateEntityManager() {
		this.em = JsfServletContextListener.createEntityManager();
		logger.info("EntityManager created: em " + this.em.toString());
	}
	

	public String save(Market market) {
		String error = "";
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
			error = e.toString();
			logger.error(e);
		}
		return error;
	}

	public String update(Market market) {
		String error = "";
		try {
			this.em.getTransaction().begin();
			this.em.merge(market);
			this.em.flush();
			this.em.getTransaction().commit();
			logger.info("EntityMarket updated: market " + market.getCode());
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			logger.error(e);
		}
		return error;
	}

	public String delete(Market Market) {
		String error = "";
		return error;
	}

	public Market findByMarketCode(String marketCode) {
		Market market = null;
		
		try {
			market = (Market) this.em.createQuery("SELECT m FROM Market m " +
              "WHERE m.code = :code")
					.setParameter("code", marketCode).getSingleResult();
		    } catch (NoResultException e) {
		     
		    }
		return market;
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
		this.em.close();
		logger.info("EntityManager destroyed: em " + this.em.toString());
	}
}
