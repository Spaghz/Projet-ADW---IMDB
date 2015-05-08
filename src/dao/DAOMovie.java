package dao;

import java.util.List;

import core.Celebrity;
import core.Movie;
import core.MovieUpdate;

public interface DAOMovie {
	Movie get(int code);

	Movie getNotPublished(int code);

	void save(Movie movie) throws Exception;

	void saveToPublish(Movie movie) throws Exception;
	
	void publish(Movie movie) throws Exception;

	void removeFromUnpublished(Movie movie);
	
	void remove(Movie movie);

	long count();

	long countUnpublished();

	boolean exists(String name);

	List<Movie> loadLasts(int n);

	List<Movie> loadAll();

	List<MovieUpdate> loadAllNotPublished();
	
	List<Movie> loadAllPublished();
	
	List<Movie> search(String searchString);

	Celebrity getDirector(int code);

	List<Celebrity> getActors(int code);

	List<Celebrity> getProducers(int code);

	void updatePublish(Movie m) throws Exception;
	
	Boolean isDisplayable(Movie movie);
	
	void rankPlusOne(Movie movie);
}
