package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import core.Award;
import core.Celebrity;
import core.Movie;
import core.MovieUpdate;
import dao.DAOAward;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.DAOMovieUpdate;
import dao.jpa.DAOAwardJPA;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;
import dao.jpa.DAOmovieUpdateJPA;


public class BeanMovieDetails {
	private Movie movie = null;
	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOMovieUpdate daoMovieUpdate = new DAOmovieUpdateJPA();
	private DAOAward daoAward = new DAOAwardJPA();
	private List<Celebrity> celebrities;
	private List<Integer> actorsId;
	private List<Integer> producersId;
	private List<Award>	awards;
	private FacesContext fc = FacesContext.getCurrentInstance();
	private Boolean isDisplayable;
	private MovieUpdate movieUpdate;
	
	public BeanMovieDetails()
	{
		if (isModification(fc))
		{
			
		}
		else
		{
			Movie m = daoMovie.get(getMovieCode(FacesContext.getCurrentInstance()));
			daoMovie.rankPlusOne(m);
			m.setRank(m.getRank()+1);
			setMovie(m);
			movieUpdate = new MovieUpdate(movie);
			
			isDisplayable = this.movie==null?false:daoMovie.isDisplayable(this.movie);
			
			if (isDisplayable)
			{
				celebrities = daoCelebrity.loadLasts((int)daoCelebrity.count());
				actorsId = new ArrayList<Integer>();
				for(Celebrity c : movie.getActors())
					actorsId.add(c.getId());
				
				producersId = new ArrayList<Integer>();
				for(Celebrity c : movie.getProducers())
					producersId.add(c.getId());		
				
				awards = daoAward.get(movie);
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
		setMovie(daoMovie.get(getMovieCode(FacesContext.getCurrentInstance())));
		return "";
	}
	
	public String update() throws Exception
	{
		MovieUpdate mU = new MovieUpdate(this.movie);
		daoMovieUpdate.save(mU);
		return "index.xhtml";
	}

	public Boolean getIsDisplayable() {
		return isDisplayable;
	}

	public void setIsDisplayable(Boolean isDisplayable) {
		this.isDisplayable = isDisplayable;
	}

	public MovieUpdate getMovieUpdate() {
		return movieUpdate;
	}

	public void setMovieUpdate(MovieUpdate movieUpdate) {
		this.movieUpdate = movieUpdate;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}
}
