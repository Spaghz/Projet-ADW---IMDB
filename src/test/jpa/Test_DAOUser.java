package test.jpa;

import static org.junit.Assert.*;

import java.sql.Date;

import jdk.nashorn.internal.ir.ThrowNode;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import core.Movie;
import core.Rights;
import core.User;
import dao.DAOMovie;
import dao.DAORights;
import dao.DAOUser;
import dao.jpa.DAOJPA;
import dao.jpa.DAOMovieJPA;
import dao.jpa.DAORightsJPA;
import dao.jpa.DAOUserJPA;

public class Test_DAOUser {
	private DAOUser 		daoUser = new DAOUserJPA();
	private DAORights		daoRights = new DAORightsJPA();
	private User			user1,user2;
	
	@org.junit.Test
	public void test() {
		testUser();
	}
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		user1 = new User("Jean-Onche","lolpw","email@email.com", "Liam", "Neeson", new java.util.Date(15,2,1950),"pic1.jpg",false,false);
		user2 = new User("Jean-Onche2","lolpw","email@email.com","lol","xd",new java.util.Date(12,3,1975),"pic2.jpg",false,false);
	}
	
	public void testUser()
	{
		DAOJPA.viderBase();

		assertTrue(daoUser.count()==0);
		
		daoUser.save(user1);
		
		assertFalse(user1.getId()==-1);
		
		assertTrue(daoUser.count()==1);
		
		Rights r = daoRights.get(user1);
		
		assertNotNull(daoUser.get("Jean-Onche"));
		
		assertNull(daoUser.get("Inexistant"));
		
		r.getId();
	}
}
