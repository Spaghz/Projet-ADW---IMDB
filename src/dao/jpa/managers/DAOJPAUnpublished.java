package dao.jpa.managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DAOJPAUnpublished extends DAOJPAManager{
	/*
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	private static EntityTransaction tx = null;

	public static EntityManager getManager() {
		if (em == null) {
			emf = Persistence.createEntityManagerFactory("imdb");
			em = emf.createEntityManager();
			tx = em.getTransaction();
		}
		if (!tx.isActive()) {
			tx.begin();
		}
		return em;
	}

	public static void commit() {
		em.flush();
		//em.clear();
		tx.commit();
	}

	public static void rollback() {
		tx.rollback();
	}

	public static void close() {
		em.close();
		em = null;
		emf.close();
		emf = null;
	}
	
	public static void viderBase() {
		getManager().createQuery("DELETE FROM Movie").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE movies AUTO_INCREMENT = 1").executeUpdate();
		getManager().createQuery("DELETE FROM User").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
		getManager().createQuery("DELETE FROM Rights").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE rights AUTO_INCREMENT = 1").executeUpdate();
		getManager().createQuery("DELETE FROM News").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE news AUTO_INCREMENT = 1").executeUpdate();
		getManager().createQuery("DELETE FROM Celebrity").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE celebrity AUTO_INCREMENT = 1").executeUpdate();
		}
		*/
}