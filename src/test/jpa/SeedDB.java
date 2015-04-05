package test.jpa;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;

import core.News;
import core.User;
import dao.DAONews;
import dao.DAOUser;
import dao.jpa.DAOJPA;
import dao.jpa.DAONewsJPA;
import dao.jpa.DAOUserJPA;

public class SeedDB {
	
	private DAONews daoNews;
	private DAOUser daoUser;
	private User 	user1;
	
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
		user1 = new User("admin", "admin", "justinbieber@bieber.com","Justin","Bieber", new java.util.Date(), "",true,true);
	}
	
	public void seedDB()
	{
		DAOJPA.viderBase();
		seedUsers();
		seedNews();
	}
	
	public void seedUsers()
	{
		daoUser.save(user1);
	}
	
	public void seedNews()
	{
		for (int i = 1 ; i <= 5 ; i++)
		{
			daoNews.save(new News(user1,"title"+i,"content"+i,"",new Date()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}
