package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import core.Celebrity;
import core.Movie;
import core.MovieUpdate;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.DAOMovieUpdate;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;
import dao.jpa.DAOmovieUpdateJPA;

public class BeanAdminChanges implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430432842893316585L;

	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOMovieUpdate daoMovieUpdate = new DAOmovieUpdateJPA();
	private List<MovieUpdate> movies;
	private List<Celebrity> celebrities;

	public BeanAdminChanges() {
		movies = daoMovie.loadAllNotPublished();
		celebrities = daoCelebrity.loadAll();
	}



	public List<MovieUpdate> getMovies() {
		return movies;
	}



	public void setMovies(List<MovieUpdate> movies) {
		this.movies = movies;
	}



	public List<Celebrity> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(List<Celebrity> celebrities) {
		this.celebrities = celebrities;
	}

	public String publish() throws Exception {
		int idToPublish = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("idToPublish"));
		daoMovie.publish(daoMovie.get(idToPublish));
		return "index.xhtml";
	}
	
	public String discard() throws Exception
	{
		try
		{
			// Discard = supprimer simplement la ligne de MoviesUpdates
			int idToPublish = Integer.parseInt(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap()
					.get("idToPublish"));
			
			daoMovieUpdate.remove(daoMovie.get(idToPublish));
			return "index.xhtml";		
		}
		catch (Exception E)
		{
			System.err.println(E);
		}
		return "index.xhtml";

	}

}
