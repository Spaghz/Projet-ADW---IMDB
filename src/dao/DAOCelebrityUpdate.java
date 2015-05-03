package dao;

import java.util.List;

import core.Celebrity;
import core.CelebrityUpdate;

public interface DAOCelebrityUpdate 
{
	long count(int celebrityCode);
	void save(CelebrityUpdate celebrity) throws Exception;	
	List<CelebrityUpdate> loadAll();
}
