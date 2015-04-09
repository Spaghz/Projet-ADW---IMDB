package test.jpa;

import static org.junit.Assert.assertEquals;
import java.sql.Date;
import org.junit.Before;
import core.Movie;
import dao.DAOMovie;
import dao.jpa.DAOJPA;
import dao.jpa.DAOMovieJPA;

public class Test_DAOMovie {
	private DAOMovie 	dao;
	private Movie		movie1;
	
	@org.junit.Test
	public void test() {
		testMovie();
	}
	
	@Before
	public void init() {
		movie1 = new Movie("Star Wars", new Date(5),500000);
		dao = DAOMovieJPA.getInstance();
		DAOJPA.viderBase();
	}
	
	public void testMovie()
	{
		// Rien dans la table
		assertEquals(0,dao.count());
		
		// Upload d'un objet
		DAOMovieJPA.getInstance().save(movie1);
		
		// Un objet
		assertEquals(1,dao.count());
	}
}
