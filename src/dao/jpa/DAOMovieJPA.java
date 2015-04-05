package dao.jpa;

import core.Movie;
import dao.DAOMovie;

public class DAOMovieJPA extends DAOJPA implements DAOMovie {
	
	static private DAOMovieJPA instance = null;

	static public DAOMovieJPA getInstance() {
		if (instance == null)
			instance = new DAOMovieJPA();

		return instance;
	}

	@Override
	public Movie get(int code) 
	{
		return code>=0?DAOJPA.getManager().find(Movie.class,code):null;
	}

	@Override
	public void save(Movie movie)
	{
		if (movie.getId() == -1)
		{
			DAOJPA.getManager().persist(movie);
			DAOJPA.commit();
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
			DAOJPA.getManager().remove(movie);
			DAOJPA.commit();
			movie.setId(-1);
		}
	}

	@Override
	public long count() {
		return ((Long) DAOJPA.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM movies")
				.getSingleResult()).longValue();
	}
}
