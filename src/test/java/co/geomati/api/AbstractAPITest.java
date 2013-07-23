package co.geomati.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import co.geomati.olakease.api.ApplicationListener;
import co.geomati.olakease.api.ProjectsResource;

public abstract class AbstractAPITest extends JerseyTest {

	private static Server server;

	@BeforeClass
	public static void startDBMS() throws Exception {
		server = Server.createTcpServer();
		if (server.start() == null) {
			throw new Exception("Cannot start server");
		}
		new ApplicationListener().contextInitialized(null);
	}

	@AfterClass
	public static void stopDBMS() throws Exception {
		server.stop();
		new ApplicationListener().contextDestroyed(null);
	}

	@Before
	public void clearDB() {
		EntityManager entityManager = ApplicationListener.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String[] cleaningSQL = new String[] { "DELETE FROM Assignment",
				"DELETE FROM Project", "DELETE FROM Developer" };
		for (String sql : cleaningSQL) {
			entityManager.createQuery(sql).executeUpdate();
		}
		transaction.commit();
	}

	@Override
	protected Application configure() {
		ResourceConfig resourceConfig = new ResourceConfig(
				ProjectsResource.class);
		resourceConfig.packages("co.geomati.olakease.api;"
				+ "org.codehaus.jackson.jaxrs");
		return resourceConfig;
	}

}
