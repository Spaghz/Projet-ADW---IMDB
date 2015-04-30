package dao;

import java.util.List;

import core.News;

public interface DAONews {
	News 		get(int id);
	void 		save(News news);
	void 		saveToPublish(News news);
	List<News> 	loadAll();
	List<News>	loadLasts(int n);
	long		count();
}
