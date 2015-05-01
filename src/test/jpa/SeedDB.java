package test.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import core.Celebrity;
import core.Movie;
import core.News;
import core.User;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.DAONews;
import dao.DAOUser;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;
import dao.jpa.DAONewsJPA;
import dao.jpa.DAOUserJPA;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;
import de.svenjacobs.loremipsum.LoremIpsum;

public class SeedDB {
	
	private DAONews daoNews;
	private DAOUser daoUser;
	private DAOCelebrity daoCelebrity;
	private DAOMovie daoMovie;
	private User 	adminUser1,proUser1,proUser2;
	
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
	
	@org.junit.Test
	public void test() 
	{
		//List<Movie> movies = DAOJPAUnpublished.getManager().createQuery("SELECT m FROM Movie m",Movie.class).getResultList();
		seedDB();
	}
	
	@Before
	public void init() 
	{
		daoNews = new DAONewsJPA();
		daoUser = new DAOUserJPA();
		daoCelebrity = new DAOCelebrityJPA();
		daoMovie = new DAOMovieJPA();
		adminUser1 	= new User("admin", "admin", "justinbieber@bieber.com","Justin","Bieber", new java.util.Date(), "",true,true);
		proUser1	= new User("user1","mdp1","a@a.com","Élie","Yaffa",new java.util.Date(),"",false,true);
		proUser2	= new User("user2","mdp2","b@b.com","Kaaris","Sevran",new java.util.Date(),"",false,true);
	}
	
	public void seedDB()
	{
		DAOJPAPublished.viderBase();
		DAOJPAUnpublished.viderBase();
		seedUsers();
		seedNews();
		seedCelebrities();
		seedMovies();
	}
	
	public void seedUsers()
	{
		daoUser.save(adminUser1);
		daoUser.save(proUser1);
		daoUser.save(proUser2);
	}
	
	public void seedNews()
	{
		for (int i = 1 ; i <= 5 ; i++)
		{
			daoNews.save(new News(adminUser1,"title"+i,"content"+i,"",new Date()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	public void seedCelebrities()
	{	
		try {
			daoCelebrity.save(c1);
			daoCelebrity.save(c2);
			daoCelebrity.save(c3);
			daoCelebrity.save(c4);
			daoCelebrity.save(c5);
			daoCelebrity.save(c6);
			daoCelebrity.save(c7);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void seedMovies()
	{
		m1.setDirector(c7);
		m1.addActor(c8);
		m1.addActor(c9);
		m1.addActor(c10);
		m1.addProducer(c11);
		try {
			daoMovie.save(m1);
			daoMovie.save(m2);
			daoMovie.save(m3);
			daoMovie.save(m4);
			daoMovie.save(m5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
