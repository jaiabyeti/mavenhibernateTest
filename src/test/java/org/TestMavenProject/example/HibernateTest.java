package org.TestMavenProject.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	private SessionFactory sessionFactory;
	private Session session = null;

	@Before
	public void before() {

		sessionFactory = createSessionFactory();
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

	}

	@Test
	public void testSetup() {
		String str = "I am done with Junit Setup";
		assertEquals("I am done with Junit Setup", str);

		// storing the objects for the test in the database
//		
//		session.save(hero);
//		SuperHeroRepository heroRepository = new SuperHeroRepository(session);
//		List<SuperHero> heroes = heroRepository.loadBy(superpower);
//		assertNotNull(heroes);
//		assertEquals(1, heroes.size());
	}

	@After
	public void after() {
		session.close();
		sessionFactory.close();
	}

	private SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

}
