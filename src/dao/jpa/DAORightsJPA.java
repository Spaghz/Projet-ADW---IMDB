package dao.jpa;

import javax.persistence.NoResultException;

import core.Rights;
import core.User;
import dao.DAORights;

public class DAORightsJPA implements DAORights {

	@Override
	public Rights get(int code) 
	{
		return code > -1 ? DAOJPA.getManager().find(Rights.class, code) : null;
	}

	@Override
	public Rights get(User user) {
		try
		{
			return (Rights)DAOJPA.getManager().createQuery("select r from Rights r where r.idUser = :idUser").setParameter("idUser",user.getId()).getSingleResult();		
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	@Override
	public void save(Rights rights) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Rights rights) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean isAdmin(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isPro(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
