package beans;

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


public class BeanMovieDetails {
	private Movie movie = null;
	private DAOMovie dao = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private List<Celebrity> celebrities;
	private List<Integer> actorsId;
	private List<Integer> producersId;
	private FacesContext fc = FacesContext.getCurrentInstance();
	private Boolean isDisplayable;
	
	public BeanMovieDetails()
	{
		if (isModification(fc))
		{
			
		}
		else
		{
			setMovie(dao.get(getMovieCode(FacesContext.getCurrentInstance())));
			
			isDisplayable = this.movie==null?false:dao.isDisplayable(this.movie);
			
			if (isDisplayable)
			{
				celebrities = daoCelebrity.loadLasts((int)daoCelebrity.count());
				actorsId = new ArrayList<Integer>();
				for(Celebrity c : movie.getActors())
					actorsId.add(c.getId());
				
				producersId = new ArrayList<Integer>();
				for(Celebrity c : movie.getProducers())
					producersId.add(c.getId());				
			}
		}
		

	}
	
	public int getMovieCode(FacesContext fc){
		 
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return Integer.parseInt(params.get("id"));
	}
	
	public Boolean isModification(FacesContext fc)
	{
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return (((params.get("modification"))!=null)&&(params.get("modification")=="true"));
	}
	
	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie Movie) {
		this.movie = Movie;
	}
	
	public List<Celebrity> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(List<Celebrity> celebrities) {
		this.celebrities = celebrities;
	}

	public List<Integer> getActorsId() {
		return actorsId;
	}

	public void setActorsId(List<Integer> actorsId) {
		this.actorsId = actorsId;
	}

	public List<Integer> getProducersId() {
		return producersId;
	}

	public void setProducersId(List<Integer> producersId) {
		this.producersId = producersId;
	}

	public String loadMovie()
	{
		setMovie(dao.get(getMovieCode(FacesContext.getCurrentInstance())));
		return "";
	}
	
	public String update()
	{
		System.out.println(this.movie);
		return "";
	}

	public Boolean getIsDisplayable() {
		return isDisplayable;
	}

	public void setIsDisplayable(Boolean isDisplayable) {
		this.isDisplayable = isDisplayable;
	}
}
