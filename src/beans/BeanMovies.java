package beans;

import java.io.Serializable;
import java.util.List;

import core.Movie;
import dao.DAOMovie;
import dao.jpa.DAOMovieJPA;

public class BeanMovies implements Serializable {

	private static final long serialVersionUID = -5918672837122707274L;
	private List<Movie> listOfMovies;
	private DAOMovie	dao = new DAOMovieJPA();
	
	public BeanMovies()
	{
		listOfMovies = dao.loadAllPublished();
	}

	public List<Movie> getListOfMovies() {
		return listOfMovies;
	}

	public void setListOfMovies(List<Movie> listOfMovies) {
		this.listOfMovies = listOfMovies;
	}
	
	

}
