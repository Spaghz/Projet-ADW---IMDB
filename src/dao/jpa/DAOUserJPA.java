package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import core.Rights;
import core.User;
import dao.DAOUser;
import dao.jpa.managers.DAOJPAPublished;

public class DAOUserJPA implements DAOUser {

	static private DAOUserJPA instance = null;

	static public DAOUserJPA getInstance() {
		if (instance == null)
			instance = new DAOUserJPA();

		return instance;
	}

	public User getByCode(int code) {
		User 	user 		= code > -1 ? DAOJPAPublished.getManager().find(User.class, code) : null;
		
		if (user==null)
			return null;
		
		Rights 	userRights 	= DAOJPAPublished.getManager().find(Rights.class,code);
		
		user.setIsAdmin(userRights.getIsAdmin());
		user.setIsPro(userRights.getIsPro());
		
		return user;
	}

	public void save(User user) {
		if ((user.getId() == -1)&&(!nicknameExists(user.getNickName()))&&(!emailExists(user.getEmail())))
		{
			DAOJPAPublished.getManager().persist(user);
			DAOJPAPublished.commit();
			
			DAOJPAPublished.getManager().persist(new Rights(user));
			DAOJPAPublished.commit();
			
		} else
			throw new IllegalArgumentException(
					"User is already in the database");
	}

	@Override
	public void remove(User user) {

	}

	public long count() {
		return ((Long) DAOJPAPublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM users")
				.getSingleResult()).longValue();
	}

	public boolean nicknameExists(String nickName) {
		return (DAOJPAPublished
				.getManager()
				.createQuery(
						"SELECT u FROM User u WHERE u.nickName LIKE :nickName")
				.setParameter("nickName", nickName).getResultList().size() > 0);
	}
	
	public boolean emailExists(String email)
	{
		return (DAOJPAPublished
				.getManager()
				.createQuery(
						"SELECT u FROM User u WHERE u.email LIKE :email")
				.setParameter("email", email).getResultList().size() > 0);		
	}

	public List<User> loadAll() {
		long numOfUsers = this.count();
		
		ArrayList<User> users = new ArrayList<User>((int)numOfUsers);
		
		for(int i = 1; i <= numOfUsers ; i++ )
			users.add(getByCode(i));
		
		return users;
	}


	public User getByUsername(String nickName) 
	{
		try
		{
			User user = (User)DAOJPAPublished.getManager().createQuery("select u from User u where u.nickName = :nickName").setParameter("nickName",nickName).getSingleResult();
			
			if (user==null)
				return null;
			
			Rights 	userRights 	= DAOJPAPublished.getManager().find(Rights.class,user.getId());
			
			user.setIsAdmin(userRights.getIsAdmin());
			user.setIsPro(userRights.getIsPro());
			
			return user;
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	@Override
	public User getByEmail(String email) {
		try
		{
			User user = (User)DAOJPAPublished.getManager().createQuery("select u from User u where u.email = :email").setParameter("email",email).getSingleResult();
		
			if (user==null)
				return null;
			
			Rights 	userRights 	= DAOJPAPublished.getManager().find(Rights.class,user.getId());
			
			user.setIsAdmin(userRights.getIsAdmin());
			user.setIsPro(userRights.getIsPro());
			
			return user;
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	public Object getAuthentificationInfo(int code) 
	{
		try
		{
			return DAOJPAPublished.getManager().createQuery("select u.salt,u.password from User u where u.id = :id").setParameter("id",code).getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	@Override
	public int getCodeByUsername(String username) {
		try
		{
			return (int)DAOJPAPublished.getManager().createQuery("select u.id from User u where u.nickName = :nickName").setParameter("nickName",username).getSingleResult();
		}
		catch (NoResultException e)
		{
			return -1;
		}
	}

	@Override
	public int getCodeByEmail(String email) {
		try
		{
			return (int)DAOJPAPublished.getManager().createQuery("select u.id from User u where u.email = :email").setParameter("email",email).getSingleResult();
		}
		catch (NoResultException e)
		{
			return -1;
		}
	}
}
