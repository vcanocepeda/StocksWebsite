package com.isaacs.dao.impl;

import javax.annotation.PreDestroy;
import javax.persistence.NoResultException;

import com.isaacs.dao.GenericDao;
import com.isaacs.model.Market;

public class MarketDaoHibernateImpl extends GenericDaoHibernateImpl<Market, String>  implements GenericDao<Market, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204732623661515600L;


	public MarketDaoHibernateImpl() {
		super();
	}
	
	@Override
	@PreDestroy
	public void CloseEntityManager() {
		em.close();
		logger.info("EntityManager destroyed: em " + em.toString());
	}
	
	public Market findByCode(String code) {
		Market market = null;
		
		try {
			market = (Market) this.em.createQuery("SELECT m FROM Market m " +
              "WHERE m.code = :code")
					.setParameter("code", code).getSingleResult();
		    } catch (NoResultException e) {
		     
		    }
		return market;
	}

}
