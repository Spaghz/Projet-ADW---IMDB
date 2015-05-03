package dao;

import java.util.List;

import core.Movie;
import core.MovieUpdate;

public interface DAOMovieUpdate {
	MovieUpdate get(Movie movie);
	long exists(int movieCode);
	void save(MovieUpdate movieUpdate) throws Exception;
	List<MovieUpdate> loadAll();	
	void remove(Movie movie);
	void remove(MovieUpdate movieUpdate);
}
