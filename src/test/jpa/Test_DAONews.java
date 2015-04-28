package test.jpa;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;
import core.News;
import dao.DAONews;
import dao.jpa.DAONewsJPA;
import dao.jpa.managers.DAOJPAPublished;

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
