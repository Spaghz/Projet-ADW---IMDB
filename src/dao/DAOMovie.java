package dao;

import java.util.List;

import core.Celebrity;
import core.Movie;

public interface DAOMovie {
	Movie get(int code);

	Movie getNotPublished(int code);

	void save(Movie movie) throws Exception;

	void saveToPublish(Movie movie) throws Exception;

	void remove(Movie movie);

	long count();

	long countUnpublished();

	boolean exists(String name);

	List<Movie> loadLasts(int n);

	List<Movie> loadAll();

	List<Movie> loadAllNotPublished();

	Celebrity getDirector(int code);

	List<Celebrity> getActors(int code);

	List<Celebrity> getProducers(int code);
}
