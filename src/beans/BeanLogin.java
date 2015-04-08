package beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import core.User;
import dao.DAOUser;
import dao.jpa.DAOUserJPA;

public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User 	user;
	private DAOUser daoUser;
	private String 	emailOrUsername;
	private String	password;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login()
	{
		// Check if user input email or username
		Pattern emailPattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
		if (emailPattern.matcher(emailOrUsername).matches())
		{
			
		}
		else
		{
			
		}
		
		
		return"";
	}
}
