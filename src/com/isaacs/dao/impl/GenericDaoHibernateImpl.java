package com.isaacs.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;

import com.isaacs.dao.GenericDao;
import com.isaacs.listeners.JsfServletContextListener;

public abstract class GenericDaoHibernateImpl<E, K extends Serializable> implements GenericDao<E, K> {

	protected static Logger logger = null;
	protected EntityManager em;
    protected Class<? extends E> daoType;
    // we have DaoType here
    @SuppressWarnings("unchecked")
	public GenericDaoHibernateImpl() {
		daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
		logger = Logger.getLogger(daoType);
		CreateEntityManager();
	}
    
    private void CreateEntityManager() {
    	this.em = JsfServletContextListener.createEntityManager();
    	logger.info("EntityManager created: em " + em.toString());
    }
    
    public abstract void CloseEntityManager();

	
	
	@Override
	public String save(E entity) throws Exception{
		String error = "";
		try {
			em.getTransaction().begin();
			if (!em.contains(entity)) {
				em.persist(entity);

				em.flush();
			}
			em.getTransaction().commit();		
			// we have to write entity namelogger.info("EntityMarket saved: entity " + entity.getClass());
			logger.info("Entity saved: entity " + entity.getClass());
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.error(e);
			throw e;
		}
		return error;
	}
	
	@Override
	public String update(E entity) throws Exception{
		String error = "";
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.flush();
			em.getTransaction().commit();
			logger.info("Entity updated: market " + entity.getClass());
		} catch (Exception e) {
			em.getTransaction().rollback();
			//e.printStackTrace();
			logger.error(e);
			throw e;
		}
		return error;
	}

	@Override
	public String delete(E entity) {
		String error = "";
		return error;
	}
	
	@Override
	public E find(K key) {
		E entity = null;
		try {
			entity = em.find(daoType, key);
		} catch (NoResultException e) {

		} 
		return entity;
	}

	@Override
	public List<E> list() throws Exception{
		List<E> listEntities = null;
		try {
			listEntities = em.createQuery("SELECT m FROM " + daoType.getSimpleName() + " m")
					.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.error(e);
			throw e;
		}
		return listEntities;
	}
	
	// Try to implement session factory
		//private SessionFactory sessionFactory;
		// class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">
	    //<property name="dataSource" ref="dataSource" />
	    //<property name="annotatedClasses" >
}
