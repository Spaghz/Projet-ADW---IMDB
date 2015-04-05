package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import core.Rights;
import core.User;
import dao.DAOUser;

public class DAOUserJPA implements DAOUser {

	static private DAOUserJPA instance = null;

	static public DAOUserJPA getInstance() {
		if (instance == null)
			instance = new DAOUserJPA();

		return instance;
	}

	public User get(int code) {
		return code > -1 ? DAOJPA.getManager().find(User.class, code) : null;
	}

	public void save(User user) {
		if ((user.getId() == -1)&&(!nicknameExists(user.getNickName())))
		{
			DAOJPA.getManager().persist(user);
			DAOJPA.commit();
			
			DAOJPA.getManager().persist(new Rights(user));
			DAOJPA.commit();
			
		} else
			throw new IllegalArgumentException(
					"User is already in the database");
	}

	@Override
	public void remove(User user) {

	}

	public long count() {
		return ((Long) DAOJPA.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM users")
				.getSingleResult()).longValue();
	}

	public boolean nicknameExists(String nickName) {
		return (DAOJPA
				.getManager()
				.createQuery(
						"SELECT u FROM User u WHERE u.nickName LIKE :nickName")
				.setParameter("nickName", nickName).getResultList().size() > 0);
	}
	
	public boolean emailExists(String email)
	{
		return (DAOJPA
				.getManager()
				.createQuery(
						"SELECT u FROM User u WHERE u.email LIKE :email")
				.setParameter("email", email).getResultList().size() > 0);		
	}

	public List<User> loadAll() {
		long numOfUsers = this.count();
		
		ArrayList<User> users = new ArrayList<User>((int)numOfUsers);
		
		for(int i = 1; i <= numOfUsers ; i++ )
			users.add(get(i));
		
		return users;
	}


	public User get(String nickName) 
	{
		try
		{
			return (User)DAOJPA.getManager().createQuery("select u from User u where u.nickName = :nickName").setParameter("nickName",nickName).getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
}
