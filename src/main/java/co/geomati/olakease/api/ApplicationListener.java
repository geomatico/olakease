package co.geomati.olakease.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

	private static EntityManagerFactory emf;
	private static EntityManager entityManager;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		emf = Persistence.createEntityManagerFactory("olakease");
		entityManager = emf.createEntityManager();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManager != null) {
			entityManager.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
}
