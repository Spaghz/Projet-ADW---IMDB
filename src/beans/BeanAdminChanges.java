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
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;

public class BeanAdminChanges implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430432842893316585L;

	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private List<Movie> movies;
	private List<Celebrity> celebrities;

	public BeanAdminChanges() {
		movies = daoMovie.loadAllNotPublished();
		celebrities = daoCelebrity.loadAll();
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Celebrity> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(List<Celebrity> celebrities) {
		this.celebrities = celebrities;
	}

	public void publish() throws Exception {
		int idToPublish = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("idToPublish"));
		daoMovie.publish(daoMovie.get(idToPublish));/*
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String refreshpage = facesContext.getViewRoot().getViewId();
		ViewHandler viewHandler = facesContext.getApplication()
				.getViewHandler();
		UIViewRoot viewroot = viewHandler.createView(facesContext, refreshpage);
		viewroot.setViewId(refreshpage);
		facesContext.setViewRoot(viewroot);*/
	}

}
