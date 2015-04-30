package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import utils.StringUtil;
import core.User;
import dao.DAOUser;
import dao.jpa.DAOUserJPA;

@ManagedBean(name="beanLogin")
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int		userUniqueId;
	private User 	user =  null;
	private DAOUser daoUser = new DAOUserJPA();
	private String 	inputLogin;
	private String	inputPassword;
	
	public BeanLogin()
	{
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
		// Check if user input email or username
		if (StringUtil.isEmailAddress(inputLogin))
			userUniqueId = daoUser.getCodeByEmail(inputLogin);
		else
			userUniqueId = daoUser.getCodeByUsername(inputLogin);
		
		if (userUniqueId==-1)
			return "Authentification failed!";

		// Retrieving salt & encrypted password
		Object[] authInfos = (Object[]) daoUser.getAuthentificationInfo(userUniqueId);
		
		// String comparison for password
		if ((StringUtil.getEncryptedPassword((byte[])authInfos[0],this.inputPassword)).equals((String)(authInfos[1])))
		{
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", daoUser.getByCode(userUniqueId));
			user = daoUser.getByCode(userUniqueId);
			inputLogin = null;
			inputPassword = null;
			return "Authentification successful!";
		}

		return("Authentification failed!");
	}
	
	public String disconnect()
	{
		user=null;
		return"";
	}
	
	public Boolean canAddMovies()
	{
		return user==null?false:user.getIsPro();
	}
}
