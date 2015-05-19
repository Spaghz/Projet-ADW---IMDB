package beans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import core.Award;
import core.Celebrity;
import core.Movie;
import dao.DAOCelebrity;
import dao.jpa.DAOCelebrityJPA;

@ManagedBean(name="beanCelebrityDetails")
@RequestScoped
public class BeanCelebrityDetails {
	private Celebrity celebrity = null;
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private List<Movie> movieProduced;
	private List<Movie> movieDirected;
	private List<Movie> movieStarred;
	private List<Award> awardGot;
	
	public BeanCelebrityDetails()
	{
		setCelebrity(daoCelebrity.get(getCelebrityCode(FacesContext.getCurrentInstance())));
		movieDirected = daoCelebrity.getMovieDirected(this.celebrity);
		movieProduced = daoCelebrity.getMovieProduced(this.celebrity);
		movieStarred = daoCelebrity.getMovieStarred(this.celebrity);
		awardGot = daoCelebrity.getAwards(this.celebrity);
	}
	
	public int getCelebrityCode(FacesContext fc){
		 
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return Integer.parseInt(params.get("id"));
	}
	
	
	public List<Movie> getMovieProduced() {
		return movieProduced;
	}

	public void setMovieProduced(List<Movie> movieProduced) {
		this.movieProduced = movieProduced;
	}

	public List<Movie> getMovieDirected() {
		return movieDirected;
	}

	public void setMovieDirected(List<Movie> movieDirected) {
		this.movieDirected = movieDirected;
	}

	public List<Movie> getMovieStarred() {
		return movieStarred;
	}

	public void setMovieStarred(List<Movie> movieStarred) {
		this.movieStarred = movieStarred;
	}

	public List<Award> getAwardGot() {
		return awardGot;
	}

	public void setAwardGot(List<Award> awardGot) {
		this.awardGot = awardGot;
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}
	
	public String loadCelebrity()
	{
		setCelebrity(daoCelebrity.get(getCelebrityCode(FacesContext.getCurrentInstance())));
		return "";
	}
}
