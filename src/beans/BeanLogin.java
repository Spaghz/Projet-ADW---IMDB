package beans;

import java.io.Serializable;

import core.User;
import dao.DAOUser;
import dao.jpa.DAOUserJPA;

public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private DAOUser daoUser;
	
	public BeanLogin()
	{
		user = new User();
		daoUser = new DAOUserJPA();
	}

	private String login()
	{
		return "";
	}
}
