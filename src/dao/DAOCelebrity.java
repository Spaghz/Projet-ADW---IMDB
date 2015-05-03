package dao;

import java.util.ArrayList;
import java.util.List;

import core.Celebrity;
import core.News;

public interface DAOCelebrity {
	Celebrity get(int code);

	Celebrity getNotPublished(int code);
	
	Boolean isDisplayable(Celebrity celebrity);

	void save(Celebrity celebrity) throws Exception;

	void removeFromUnpublished(Celebrity celebrity);

	long count();

	long countUnpublished();

	boolean exists(String lastName, String firstName);

	List<Celebrity> loadLasts(int n);

	void saveToPublish(Celebrity c1) throws Exception;

	List<Celebrity> loadAll();
	
	List<Celebrity> search(String searchString);
}
