package dao.jpa;

import java.util.List;

import core.Award;
import core.Celebrity;
import core.Movie;
import dao.DAOAward;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.managers.DAOJPAPublished;

public class DAOAwardJPA implements DAOAward {
	
	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();

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
	public void save(Award a) throws Exception {
		if ((a.getMovie()!=null)&&(a.getCelebrity()!=null)&&(!a.getAwardName().isEmpty()))
		{
			if (a.getMovie().getId()==-1)
				daoMovie.save(a.getMovie());
			
			if (a.getCelebrity().getId()==-1)
				daoCelebrity.save(a.getCelebrity());
			

				DAOJPAPublished.getManager().persist(a);
				DAOJPAPublished.commit();

		}
	}

}
