package dao;

import java.util.List;

import core.Celebrity;
import core.News;

public interface DAOCelebrity {
	Celebrity	get		(int code);
	void 	save	(Celebrity celebrity) throws Exception;
	void 	remove	(Celebrity celebrity);
	long 	count	();
	boolean	exists	(String lastName,String firstName);
	List<Celebrity>	loadLasts(int n);
	void saveToPublish(Celebrity c1) throws Exception;
}
