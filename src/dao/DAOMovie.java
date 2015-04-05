package dao;

import core.Movie;

public interface DAOMovie 
{
	Movie	get		(int code);
	void 	save	(Movie movie) throws Exception;
	void 	remove	(Movie movie);
	long 	count	();
}
