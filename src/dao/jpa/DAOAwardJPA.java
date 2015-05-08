package dao.jpa;

import java.util.List;

import core.Award;
import core.Celebrity;
import core.Movie;
import dao.DAOAward;
import dao.jpa.managers.DAOJPAPublished;

public class DAOAwardJPA implements DAOAward {

	@Override
	public List<Award> get(Movie m, Celebrity c) {
		return DAOJPAPublished.getManager().createQuery("SELECT a FROM Award a WHERE a.celebrity = :celebrity AND a.movie = :movie",Award.class)
				.setParameter("celebrity",c)
				.setParameter("movie",m)
				.getResultList();
	}

	@Override
	public List<Award> get(Movie m) {
		return DAOJPAPublished.getManager().createQuery("SELECT a FROM Award a WHERE a.movie = :movie",Award.class)
				.setParameter("movie",m)
				.getResultList();
	}

	@Override
	public void save(Award a) {
		if ((a.getMovie()!=null)&&(a.getCelebrity()!=null)&&(!a.getAwardName().isEmpty()))
		{
			if ((a.getMovie().getId()!=-1)&&(a.getCelebrity().getId()!=-1))
			{
				DAOJPAPublished.getManager().persist(a);
				DAOJPAPublished.commit();
			}
			else
			{
				throw new IllegalArgumentException("Movie and/or celebrity need to be persisted first");
			}
		}
	}

}
