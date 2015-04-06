package beans;

import java.io.Serializable;

import core.User;
import dao.DAOUser;
import dao.jpa.DAOUserJPA;

public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User 	user;
	private DAOUser daoUser;
	private String 	emailOrUsername;
	
	public BeanLogin()
	{
		user = new User();
		daoUser = new DAOUserJPA();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmailOrUsername() {
		return emailOrUsername;
	}

	public void setEmailOrUsername(String emailOrUsername) {
		this.emailOrUsername = emailOrUsername;
	}

	public String login()
	{
		return "";
	}
}
