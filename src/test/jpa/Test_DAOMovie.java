package test.jpa;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.constraints.AssertFalse;

import org.junit.Before;

import core.Celebrity;
import core.Movie;
import core.MovieUpdate;
import dao.DAOMovie;
import dao.DAOMovieUpdate;
import dao.jpa.DAOMovieJPA;
import dao.jpa.DAOmovieUpdateJPA;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;
import de.svenjacobs.loremipsum.LoremIpsum;

public class Test_DAOMovie {
	private DAOMovie 	daoMovie;
	private DAOMovieUpdate daoMovieUpdate;
	private Movie		movie1,movie2;
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
		daoMovie = DAOMovieJPA.getInstance();
		daoMovieUpdate = new DAOmovieUpdateJPA();
		seedDB = new SeedDB();
	}
	
	@SuppressWarnings("unused")
	public void testMovie() throws Exception
	{
		/*
		DAOJPAPublished.viderBase();
		seedDB = new SeedDB();
		seedDB.seedUsers();
		
		movie1 = new Movie("Star Wars", new Date(5),500000,"");
		movie2 = new Movie("Star Wars 2", new Date(5),500000,"");
		Celebrity 	c1 = new Celebrity("George","Lucas",new LoremIpsum().getWords(321),new Date(),""),
					c2 = new Celebrity("Liam","Neeson",new LoremIpsum().getWords(211),new Date(),""),
					c3 = new Celebrity("Ewan","McGregor",new LoremIpsum().getWords(121),new Date(),""),
					c4 = new Celebrity("Carrie","Fisher",new LoremIpsum().getWords(121),new Date(),"");
		
		movie1.setDirector(c1);
		movie1.addActor(c2);
		movie1.addActor(c3);
		movie1.addProducer(c4);
		daoMovie.save(movie1);
		
		Movie m = daoMovie.get(1);
		
		MovieUpdate mu1 = new MovieUpdate(movie1);
		daoMovieUpdate.save(mu1);
		
		daoMovie.rankPlusOne(movie1);

		
		List<Movie> res = daoMovie.search("star wars");
		*/
		
		

	}
}
