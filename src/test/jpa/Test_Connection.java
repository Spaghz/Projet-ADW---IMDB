package test.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class Test_Connection {

	@Test
	public void test() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");
		emf.createEntityManager();
	}

}
