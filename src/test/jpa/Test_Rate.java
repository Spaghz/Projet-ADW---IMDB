package test.jpa;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Rate;
import dao.DAORate;
import dao.jpa.DAORateJPA;

public class Test_Rate {
	private DAORate daoRate = new DAORateJPA();
	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		daoRate.save(new Rate(1, 1, 2));
		
		assertTrue(daoRate.hasRated(1, 1));
		assertFalse(daoRate.hasRated(1, 2));
		
		assertNotEquals(0,daoRate.getRate(1));

		
	}

}
