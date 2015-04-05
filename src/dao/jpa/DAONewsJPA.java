package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import core.News;
import core.User;
import dao.DAONews;

public class DAONewsJPA implements DAONews {

	@Override
	public News get(int id) 
	{
		return id > -1 ? DAOJPA.getManager().find(News.class, id) : null;
	}

	@Override
	public void save(News news) {
		if (news.getId()==-1)
		{
			DAOJPA.getManager().persist(news);
			DAOJPA.commit();
		}
		else
		{
			throw new IllegalArgumentException("News already persistent");
		}
	}

	@Override
	public List<News> loadAll() {
		long numOfNews = this.count();
		
		ArrayList<News> newsList = new ArrayList<News>((int)numOfNews);
		
		for(int i = 1; i <= numOfNews ; i++ )
			newsList.add(get(i));
		
		return newsList;
	}

	@Override
	public long count() {
		return ((Long) DAOJPA.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM news")
				.getSingleResult()).longValue();
	}

	@Override
	public List<News> loadLasts(int n) 
	{
		int numOfNews = (int)count();
		ArrayList<News> newsList = new ArrayList<News>(n);
		
		for(int i = 0; (i < n)&&(numOfNews-i>0) ; i++ )
			newsList.add(get(numOfNews-i));
		
		return newsList;
	}

}
