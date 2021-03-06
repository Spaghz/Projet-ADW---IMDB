package beans;

import java.io.Serializable;
import java.security.SecureRandom;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import utils.StringUtil;
import core.User;
import dao.DAOUser;
import dao.jpa.DAOUserJPA;


public class BeanSubscribe implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user;
	private DAOUser daoUser;
	
	public BeanSubscribe()
	{
		user = new User();
		daoUser = new DAOUserJPA();
		
	}
	
	public String subscribe()
	{	
		daoUser.save(user);
		FacesMessage subscriptionMessage = new FacesMessage("Subscription done!");
		FacesContext.getCurrentInstance().addMessage(null, subscriptionMessage);
		return("Subscription done!");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
