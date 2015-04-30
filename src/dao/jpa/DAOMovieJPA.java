package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import core.Movie;
import core.News;
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

	@Override
	public List<Movie> loadLasts(int n) {
		int numOfMovies = (int)count();
		ArrayList<Movie> movieList = new ArrayList<Movie>(n);
		
		for(int i = 0; (i < n)&&(numOfMovies-i>0) ; i++ )
			movieList.add(get(numOfMovies-i));
		
		return movieList;
	}

	@Override
	public List<Movie> loadAll() {
		long numOfMovies = this.count();
		
		ArrayList<Movie> movieList = new ArrayList<Movie>((int)numOfMovies);
		
		for(int i = 1; i <= numOfMovies ; i++ )
			movieList.add(get(i));
		
		return movieList;
	}
}
