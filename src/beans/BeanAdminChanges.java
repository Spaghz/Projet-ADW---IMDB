package beans;

import java.io.Serializable;
import java.util.ArrayList;
import core.Celebrity;
import core.Movie;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;

public class BeanAdminChanges implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430432842893316585L;

	private DAOMovie 				daoMovie = new DAOMovieJPA();
	private DAOCelebrity 			daoCelebrity = new DAOCelebrityJPA();
	private ArrayList<Movie>		movies;
	private ArrayList<Celebrity>	celebrities;
	
	public BeanAdminChanges()
	{
		movies = (ArrayList<Movie>) daoMovie.loadAll();
		celebrities = daoCelebrity.loadAllNotPublished();
	}
	
}
