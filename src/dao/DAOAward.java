package dao;

import java.util.List;
import core.Award;
import core.Celebrity;
import core.Movie;

public interface DAOAward {
	List<Award> get(Movie m,Celebrity c);
	List<Award> get(Movie m);
	void save(Award a);
}
