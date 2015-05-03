package test.jpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import core.Celebrity;
import dao.DAOCelebrity;
import dao.DAOCelebrityUpdate;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOCelebrityUpdateJPA;

public class Test_Celebrity {
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOCelebrityUpdate daoCelebrityUpdate = new DAOCelebrityUpdateJPA();
	private SeedDB seeder = new SeedDB();
	
	@org.junit.Test
	public void test() {
		testCelebrities();
	}

	private void testCelebrities() {
		//seeder.seedDB();
		
		Celebrity c1 = daoCelebrity.get(1);
		
		assertNotNull(c1);
		
		assertFalse(daoCelebrity.isDisplayable(c1));
		
		List<Celebrity> unpublishedCelebrity = daoCelebrity.loadAll();
		
		assertNotNull(unpublishedCelebrity);
		
		assertFalse(unpublishedCelebrity.size()==0);
		
		List<Celebrity> searchRes = daoCelebrity.search("scarlett");
	}
	
	
}
