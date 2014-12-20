package com.isaacs.dao.impl;

import com.isaacs.dao.UserDao;
import com.isaacs.model.User;
import com.isaacs.listeners.EMFServletContextListener;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserDaoHibernateImpl implements Serializable, UserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204732623661515600L;
	static Logger logger = Logger.getLogger(MarketDaoHibernateImpl.class);
	private EntityManager em;

	public UserDaoHibernateImpl() {
		
	}
	
	@PostConstruct
	public void CreateEntityManager() {
		this.em = EMFServletContextListener.createEntityManager();
		logger.info("EntityManager created: em " + this.em.toString());
	}

	public User findUser(String username, String password) {
		User user = null;
		
		try {
			user = this.em.createQuery("SELECT u FROM User u " +
              "WHERE u.username = :username and u.password = :password", User.class)
					.setParameter("username", username).setParameter("password", password)
					.getSingleResult();
		    } catch (NoResultException e) {
		     
		    }
		return user;
	}
	
	@PreDestroy
	public void CloseEntityManager() {
		this.em.close();
		logger.info("EntityManager destroyed: em " + this.em.toString());
	}
}
