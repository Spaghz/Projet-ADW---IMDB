package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import core.News;
import dao.jpa.DAONewsJPA;

public class BeanNews implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DAONewsJPA 		daoNewsJPA = new DAONewsJPA();
	private List<News>	newsToDisplay;
	
	public BeanNews()
	{
		super();
		setNewsToDisplay(loadLastNews(5));
	}

	public ArrayList<News> loadLastNews(int n)
	{
		return (ArrayList<News>) daoNewsJPA.loadLasts(n);
	}

	public List<News> getNewsToDisplay() {
		return newsToDisplay;
	}

	public void setNewsToDisplay(ArrayList<News> newsToDisplay) {
		this.newsToDisplay = newsToDisplay;
	}
}
