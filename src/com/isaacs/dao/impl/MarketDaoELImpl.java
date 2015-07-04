package com.isaacs.dao.impl;

import com.isaacs.dao.GenericDao;
import com.isaacs.model.Market;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MarketDaoELImpl extends GenericDaoHibernateImpl<Market, String>  implements GenericDao<Market, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204732623661515600L;


	public MarketDaoELImpl() {
		super();
	}
	
//	@PostConstruct
//	public void CreateEntityManager() {
		//em = entityManagerFactory.createEntityManager();
//		logger.info("EntityManager created: em " + em.toString());
//	}
	
	@PreDestroy
	public void CloseEntityManager() {
		em.close();
		logger.info("EntityManager destroyed: em " + em.toString());
	}

}
