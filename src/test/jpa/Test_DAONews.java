package test.jpa;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;
import core.News;
import dao.DAONews;
import dao.jpa.DAOJPA;
import dao.jpa.DAONewsJPA;

public class Test_DAONews {
	private DAONews 	dao = new DAONewsJPA();
	private News		news1;
	
	@org.junit.Test
	public void test() {
		testNews();
	}
	
	public void testNews()
	{
		//DAOJPA.viderBase();
		assertEquals(0,dao.count());
	}
	
}
