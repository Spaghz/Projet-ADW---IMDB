package dao;

import java.util.List;

import core.Movie;
import core.MovieUpdate;

public interface DAOMovieUpdate {
	MovieUpdate get(Movie movie);
	MovieUpdate get(int movieId);
	long exists(int movieCode);
	void save(MovieUpdate movieUpdate) throws Exception;
	List<MovieUpdate> loadAll();	
	void remove(Movie movie);
	void remove(MovieUpdate movieUpdate);
	void remove(int movieId);
}
