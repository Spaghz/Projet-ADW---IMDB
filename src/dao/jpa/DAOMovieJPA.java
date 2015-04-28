package dao.jpa;

import core.Movie;
import dao.DAOMovie;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;

public class DAOMovieJPA extends DAOJPAPublished implements DAOMovie {
	
	static private DAOMovieJPA instance = null;

	static public DAOMovieJPA getInstance() {
		if (instance == null)
			instance = new DAOMovieJPA();

		return instance;
	}

	@Override
	public Movie get(int code) 
	{
		return code>=0?DAOJPAPublished.getManager().find(Movie.class,code):null;
	}

	@Override
	public void save(Movie movie)
	{
		if (movie.getId() == -1)
		{
			DAOJPAUnpublished.getManager().persist(movie);
			DAOJPAUnpublished.commit();
		}
		else
		{
			throw new IllegalArgumentException("This movie is already saved in the database");
		}
	}

	@Override
	public void remove(Movie movie) 
	{
		if (movie.getId() != -1)
		{
			DAOJPAPublished.getManager().remove(movie);
			DAOJPAPublished.commit();
			movie.setId(-1);
		}
	}

	@Override
	public long count() {
		return ((Long) DAOJPAPublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM movies")
				.getSingleResult()).longValue();
	}

	@Override
	public boolean exists(String movieTitle) {
		return (DAOJPAPublished
				.getManager()
				.createQuery(
						"SELECT m FROM Movie m WHERE m:title LIKE :title")
				.setParameter("title", movieTitle).getResultList().size() > 0);
	}
}
