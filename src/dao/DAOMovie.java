package dao;

import java.util.List;

import core.Movie;

public interface DAOMovie 
{
	Movie	get		(int code);
	void 	save	(Movie movie) throws Exception;
	void 	remove	(Movie movie);
	long 	count	();
	boolean	exists	(String name);
	List<Movie> loadLasts(int n);
	List<Movie> loadAll();
}
