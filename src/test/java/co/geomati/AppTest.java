package co.geomati;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import co.geomati.olakease.persistence.Project;

public class AppTest {

	@Test
	public void testname() throws Exception {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("olakease");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Project feedback = new Project();
		feedback.setId(211);
		feedback.setName("olakease");
		feedback.setDescription("Resource management");
		entityManager.persist(feedback);
		transaction.commit();
		entityManager.close();
		emf.close();
	}
}
