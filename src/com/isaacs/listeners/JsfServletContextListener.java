package com.isaacs.listeners;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JsfServletContextListener implements ServletContextListener {

	private static EntityManagerFactory emf;
	private static Integer IntDaoSelected = null;
	private static String urlWebservice = null;

	@Override//no haria falta en el contexto quizas si (generic no inicializa)
	public void contextInitialized(ServletContextEvent event) {
		Properties prop = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream(
				"jdbc.properties");
		try {
			prop.load(in);

			emf = Persistence.createEntityManagerFactory("StockWebService",
					prop);
			if (IntDaoSelected == null) {
				IntDaoSelected = Integer.parseInt(prop.getProperty("dao.type"));
			}
			if (urlWebservice == null) {
				urlWebservice = prop.getProperty("url.webservice");
			}
		} catch (IOException | NumberFormatException e) {
			// NumberFormatException is a RuntimeException and we don´t need to
			// catch it but since we are using Java 7 we can catch several exc with |
			e.printStackTrace();
		}
	}

	// We need an error control here

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		emf.close();
	}

	public static EntityManager createEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}

		return emf.createEntityManager();
	}

	public static Integer getIntDaoSelected() {
		return IntDaoSelected;
	}
	
	public static String getUrlWebservice() {
		return urlWebservice;
	}

}