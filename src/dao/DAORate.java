package dao;

import core.Movie;
import core.Rate;
import core.User;

public interface DAORate {
	void save(Rate r);
	double getRate(Movie m);
	double getRate(int idMovie);
	double getRate(Movie m,User u);
	double getRate(int idMovie,int idUser);
	Boolean hasRated(User u,Movie m);
	Boolean hasRated(int idUser,int idMovie);
}
