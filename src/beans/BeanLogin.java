package beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;

import utils.StringUtil;
import core.User;
import dao.DAOUser;
import dao.jpa.DAOUserJPA;

public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int		userUniqueId;
	private User 	user;
	private DAOUser daoUser;
	private String 	inputLogin;
	private String	inputPassword;
	
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
		return inputLogin;
	}

	public void setEmailOrUsername(String emailOrUsername) {
		this.inputLogin = emailOrUsername;
	}

	public String getPassword() {
		return inputPassword;
	}

	public void setPassword(String password) {
		this.inputPassword = password;
	}

	public String login()
	{
		int code;
		// Check if user input email or username
		if (StringUtil.isEmailAddress(inputLogin))
			userUniqueId = daoUser.getCodeByEmail(inputLogin);
		else
			userUniqueId = daoUser.getCodeByUsername(inputLogin);
		
		if (user==null)
		{
			return "Authentification failed!";
		}
		else
		{
			// String comparison for password
			if ((StringUtil.getEncryptedPassword(user.getSalt(),this.inputPassword)).equals(user.getPassword()))
			{
				System.out.println("successfull!");
			}
			else
				System.out.println("not successful!");
			return "Authentification successful!";
		}
	}
}
