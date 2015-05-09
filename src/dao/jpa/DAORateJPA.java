package dao.jpa;

import javax.persistence.NoResultException;

import core.Movie;
import core.Rate;
import core.User;
import dao.DAORate;
import dao.jpa.managers.DAOJPAPublished;

public class DAORateJPA implements DAORate {

	@Override
	public double getRate(Movie m) {
		return getRate(m.getId());
	}

	@Override
	public double getRate(Movie m, User u) {
		return getRate(m.getId(),u.getId());
	}

	@Override
	public void save(Rate r) {
		if (!hasRated(r.getIdUser(), r.getIdMovie()))
		{
			if (r.getIdMovie()!=-1)
				if (r.getIdUser()!=-1)
				{
					DAOJPAPublished.getManager().persist(r);
					DAOJPAPublished.commit();
				}
		}
		else
		{
			// Update
			DAOJPAPublished.getManager().createQuery("UPDATE Rate SET rate = :newRate WHERE idMovie = :idMovie AND idUser = :idUser")
					.setParameter("newRate", r.getRate())
					.setParameter("idMovie", r.getIdMovie())
					.setParameter("idUser", r.getIdUser())
					.executeUpdate();
			DAOJPAPublished.commit();
		}
	}

	@Override
	public Boolean hasRated(User u, Movie m) {
		return hasRated(u.getId(),m.getId());
	}

	@Override
	public Boolean hasRated(int idUser, int idMovie) {
		try
		{
			Rate r = DAOJPAPublished.getManager().createQuery("SELECT r FROM Rate r WHERE r.idMovie = :idMovie AND r.idUser = :idUser",Rate.class)
					.setParameter("idMovie",idMovie)
					.setParameter("idUser",idUser)
					.getSingleResult();			
		}
		catch (Exception e)
		{
			return false;
		}

		
		return true;
	}

	@Override
	public double getRate(int idMovie) {
		try
		{
			return (double)DAOJPAPublished.getManager().createQuery("SELECT AVG(r.rate) FROM Rate r WHERE r.idMovie = :idMovie").setParameter("idMovie",idMovie).getSingleResult();	
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	@Override
	public double getRate(int idMovie, int idUser) {
		try
		{
			return (double)DAOJPAPublished.getManager().createQuery("SELECT r.rate FROM Rate r WHERE r.idMovie = :idMovie AND r.idUser = :idUser")
					.setParameter("idMovie",idMovie)
					.setParameter("idUser",idUser)
					.getFirstResult();
		}
		catch (Exception e)
		{
			return 0;
		}
	}

}
