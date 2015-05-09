package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import core.Celebrity;
import core.Movie;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;

public class BeanAddMovie implements Serializable{

	private static final long serialVersionUID = 1L;
	private Movie movie;
	private List<Celebrity> celebrities;
	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	
	
	public BeanAddMovie() {
		celebrities = daoCelebrity.loadAll();
		movie = new Movie();
		movie.setDirector(new Celebrity());
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Celebrity> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(List<Celebrity> celebrities) {
		this.celebrities = celebrities;
	}
	
	public String addMovie()
	{
		try
		{
			if (movie.getTitle()==null)
				throw new Exception("Movie title can't be empty!");
			
			if (movie.getReleaseDate()==null)
				throw new Exception("Please enter a release date.");
			
			movie.setDirector(daoCelebrity.get(movie.getDirector().getId()));
			daoMovie.save(movie);
			
			return "index.xhtml";
		}
		catch (Exception e)
		{
			 FacesMessage message = new FacesMessage();
			 message.setSeverity(FacesMessage.SEVERITY_ERROR);
			 message.setSummary(e.getMessage());
			 message.setDetail(e.getMessage());
			 FacesContext.getCurrentInstance().addMessage(null, message);
			 return "";
		}
	}
	

}
