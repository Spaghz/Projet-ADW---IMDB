package test.jpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import core.Celebrity;
import core.Movie;
import dao.DAOCelebrity;
import dao.DAOCelebrityUpdate;
import dao.DAOMovie;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOCelebrityUpdateJPA;
import dao.jpa.DAOMovieJPA;
import dao.jpa.managers.DAOJPAPublished;
import de.svenjacobs.loremipsum.LoremIpsum;

public class Test_Celebrity {
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOCelebrityUpdate daoCelebrityUpdate = new DAOCelebrityUpdateJPA();
	private DAOMovie daoMovie = new DAOMovieJPA();

	
	@org.junit.Test
	public void test() {
		testCelebrities();
	}

	private void testCelebrities() {
		DAOJPAPublished.viderBase();
		
		Celebrity 	c1 = new Celebrity("Clint","Eastwood",new LoremIpsum().getWords(150),new Date(1930,5,31),""),
				c2 = new Celebrity("Christoph","Waltz",new LoremIpsum().getWords(250),new Date(1956,10,4),""),
				c3 = new Celebrity("Robert","Downey Jr.",new LoremIpsum().getWords(150),new Date(1965,4,4),""),
				c4 = new Celebrity("Scarlett","Johanson",new LoremIpsum().getWords(350),new Date(1984,11,22),""),
				c5 = new Celebrity("Ethan","Hawke",new LoremIpsum().getWords(154),new Date(1970,11,6),""),
				c6 = new Celebrity("Uma","Thurman",new LoremIpsum().getWords(250),new Date(1970,4,29),""),
				c7 = new Celebrity("George","Lucas",new LoremIpsum().getWords(321),new Date(),""),
				c8 = new Celebrity("Liam","Neeson",new LoremIpsum().getWords(211),new Date(),""),
				c9 = new Celebrity("Ewan","McGregor",new LoremIpsum().getWords(121),new Date(),""),
				c10 = new Celebrity("Carrie","Fisher",new LoremIpsum().getWords(121),new Date(),""),
				c11 = new Celebrity("Jacques","Chirac",new LoremIpsum().getWords(121),new Date(),"");
								
		
		
		Movie 	m1 = new Movie("Star Wars : La menace fantôme", new Date(12,6,1999), 50000000, new LoremIpsum().getWords(150)),
				m2 = new Movie("Harry Potter 1", new Date(12,6,2002), 20000000, new LoremIpsum().getWords(150)),
				m3 = new Movie("Harry Potter 2", new Date(12,6,2004), 50000000, new LoremIpsum().getWords(150)),
				m4 = new Movie("Harry Potter 3", new Date(12,6,2006), 50000000, new LoremIpsum().getWords(150)),
				m5 = new Movie("Harry Potter 4", new Date(12,6,2007), 50000000, new LoremIpsum().getWords(150));
		
		m1.addProducer(c1);
		m1.addProducer(c2);
		m2.addProducer(c1);
		
		try {
			m1.setDirector(c7);
			m2.setDirector(c7);
			daoMovie.save(m1);
			daoMovie.save(m2);

			List<Movie> movieProduced = daoCelebrity.getMovieProduced(c1);
			List<Movie> movieStarred = daoCelebrity.getMovieStarred(c1);
			List<Movie> movieDirected = daoCelebrity.getMovieDirected(c7);
			System.out.println("test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
