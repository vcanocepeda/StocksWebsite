package com.isaacs.dao.impl;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.NoResultException;

import com.isaacs.dao.GenericDao;
import com.isaacs.model.Market;
import com.isaacs.model.Stock;

public class StockDaoHibernateImpl extends GenericDaoHibernateImpl<Market, String>  implements GenericDao<Market, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8890073456388862102L;

	public StockDaoHibernateImpl() {
		super();
	}
	
	public Stock findByCode(String code) {
		Stock stock = null;
		
		try {
			stock = (Stock) this.em.createQuery("SELECT s FROM Stock s " +
              "WHERE s.code = :code")
					.setParameter("code", code).getSingleResult();
		    } catch (NoResultException e) {
		     
		    }
		return stock;
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
	
	@Override
	@PreDestroy
	public void CloseEntityManager() {
		this.em.close();
		logger.info("EntityManager destroyed: em " + this.em.toString());
	}

}