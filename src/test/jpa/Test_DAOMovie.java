package test.jpa;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.constraints.AssertFalse;

import org.junit.Before;

import core.Movie;
import core.MovieUpdate;
import dao.DAOMovie;
import dao.DAOMovieUpdate;
import dao.jpa.DAOMovieJPA;
import dao.jpa.DAOmovieUpdateJPA;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;

public class Test_DAOMovie {
	private DAOMovie 	daoMovie;
	private DAOMovieUpdate daoMovieUpdate;
	private Movie		movie1;
	private SeedDB		seedDB;
	
	@org.junit.Test
	public void test() {
		try {
			testMovie();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before
	public void init() {
		movie1 = new Movie("Star Wars", new Date(5),500000,"");
		daoMovie = DAOMovieJPA.getInstance();
		daoMovieUpdate = new DAOmovieUpdateJPA();
		seedDB = new SeedDB();
	}
	
	@SuppressWarnings("unused")
	public void testMovie() throws Exception
	{
		DAOJPAPublished.viderBase();
		
		daoMovie.save(movie1);
		
		Movie m = daoMovie.get(1);
		
		assertFalse(daoMovie.isDisplayable(m));
		
		daoMovie.publish(m);
		
		daoMovieUpdate.save(new MovieUpdate(m));
		
		List<Movie> res = daoMovie.search("star wars");

	}
}
