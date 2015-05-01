package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

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
	private List<Movie>		movies;
	private ArrayList<Celebrity>	celebrities;
	
	public BeanAdminChanges()
	{
		movies = daoMovie.loadAllNotPublished();
		//celebrities = daoCelebrity.loadAllNotPublished();
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public ArrayList<Celebrity> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(ArrayList<Celebrity> celebrities) {
		this.celebrities = celebrities;
	}
	
	public String publish() throws Exception
	{
		  int idToPublish = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idToPublish"));
		  Movie movieToPublish = daoMovie.getNotPublished(idToPublish);
		  daoMovie.saveToPublish(movieToPublish);
		  return "";
	}
}
