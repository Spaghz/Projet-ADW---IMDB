package beans;

import java.util.Map;

import javax.faces.context.FacesContext;

import core.Movie;
import dao.DAOMovie;
import dao.jpa.DAOMovieJPA;


public class BeanMovieDetails {
	private Movie movie = null;
	private DAOMovie dao = new DAOMovieJPA();
	
	public BeanMovieDetails()
	{
		setMovie(dao.get(getMovieCode(FacesContext.getCurrentInstance())));
	}
	
	public int getMovieCode(FacesContext fc){
		 
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return Integer.parseInt(params.get("id"));
	}
	
	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie Movie) {
		this.movie = Movie;
	}
	
	public String loadMovie()
	{
		setMovie(dao.get(getMovieCode(FacesContext.getCurrentInstance())));
		return "";
	}	
}
