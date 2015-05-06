package test.jpa;

import java.util.Date;

import core.Award;
import core.Celebrity;
import core.Movie;
import dao.DAOAward;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.DAOAwardJPA;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;
import dao.jpa.managers.DAOJPAPublished;
import de.svenjacobs.loremipsum.LoremIpsum;

public class Test_Award {
	private DAOAward daoAward = new DAOAwardJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOMovie daoMovie = new DAOMovieJPA();
	
	@org.junit.Test
	public void test() {
		testAwards();
	}

	@SuppressWarnings("deprecation")
	private void testAwards() 
	{
		DAOJPAPublished.viderBase();
		
		Celebrity 	c1 = new Celebrity("Clint","Eastwood",new LoremIpsum().getWords(150),new Date(1930,5,31),"");
		Movie m1 = new Movie("Star Wars : La menace fantôme", new Date(12,6,1999), 50000000, new LoremIpsum().getWords(150));
		
		Award a = new Award(c1, m1,"oscar",new Date());

		try {
			daoCelebrity.save(c1);
			daoMovie.save(m1);
			daoAward.save(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
