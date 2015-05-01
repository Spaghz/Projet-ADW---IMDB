package dao.jpa;

import javax.persistence.NoResultException;

import core.Rights;
import core.User;
import dao.DAORights;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;

public class DAORightsJPA implements DAORights {

	@Override
	public Rights get(int code) 
	{
		return code > -1 ? DAOJPAPublished.getManager().find(Rights.class, code) : null;
	}

	@Override
	public Rights get(User user) {
		try
		{
			return (Rights)DAOJPAPublished.getManager().createQuery("select r from Rights r where r.idUser = :idUser").setParameter("idUser",user.getId()).getSingleResult();		
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	@Override
	public void save(Rights rights) throws Exception {
		DAOJPAPublished.getManager().persist(rights);
		DAOJPAPublished.commit();
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
