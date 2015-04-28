package test.jpa;

import java.util.Date;

import org.junit.Before;

import core.Celebrity;
import core.News;
import core.User;
import dao.DAOCelebrity;
import dao.DAONews;
import dao.DAOUser;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAONewsJPA;
import dao.jpa.DAOUserJPA;
import dao.jpa.managers.DAOJPAPublished;
import de.svenjacobs.loremipsum.LoremIpsum;

public class SeedDB {
	
	private DAONews daoNews;
	private DAOUser daoUser;
	private DAOCelebrity daoCelebrity;
	private User 	adminUser1,proUser1,proUser2;
	
	@org.junit.Test
	public void test() 
	{
		seedDB();
	}
	
	@Before
	public void init() 
	{
		daoNews = new DAONewsJPA();
		daoUser = new DAOUserJPA();
		daoCelebrity = new DAOCelebrityJPA();
		adminUser1 	= new User("admin", "admin", "justinbieber@bieber.com","Justin","Bieber", new java.util.Date(), "",true,true);
		proUser1	= new User("user1","mdp1","a@a.com","Élie","Yaffa",new java.util.Date(),"",false,true);
		proUser2	= new User("user2","mdp2","b@b.com","Kaaris","Sevran",new java.util.Date(),"",false,true);
	}
	
	public void seedDB()
	{
		DAOJPAPublished.viderBase();
		seedUsers();
		seedNews();
		seedCelebrities();
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
		Celebrity 	c1 = new Celebrity("Clint","Eastwood",new LoremIpsum().getWords(50),new Date(1930,5,31),""),
					c2 = new Celebrity("Christoph","Waltz",new LoremIpsum().getWords(50),new Date(1956,10,4),"");
		
		try {
			daoCelebrity.saveToPublish(c1);
			daoCelebrity.saveToPublish(c2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
