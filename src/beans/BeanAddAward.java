package beans;

import java.util.Date;
import java.util.List;

import core.Award;
import core.Celebrity;
import core.Movie;
import dao.DAOAward;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.DAOAwardJPA;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;

public class BeanAddAward {
	private List<Movie> 	movies;
	private List<Celebrity>	celebrities;
	private int 		selectedMovieId;
	private int			selectedCelebrityId;
	private Date		awardDate;
	private String		awardName;
	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOAward daoAward = new DAOAwardJPA();
	
	public BeanAddAward()
	{
		movies = daoMovie.loadAllPublished();
		celebrities = daoCelebrity.loadAll();
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public int getSelectedMovieId() {
		return selectedMovieId;
	}

	public void setSelectedMovieId(int selectedMovieId) {
		this.selectedMovieId = selectedMovieId;
	}

	public Date getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public List<Celebrity> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(List<Celebrity> celebrities) {
		this.celebrities = celebrities;
	}

	public int getSelectedCelebrityId() {
		return selectedCelebrityId;
	}

	public void setSelectedCelebrityId(int selectedCelebrityId) {
		this.selectedCelebrityId = selectedCelebrityId;
	}

	public String addAward()
	{
		Celebrity c = daoCelebrity.get(selectedCelebrityId);
		Movie m = daoMovie.get(selectedMovieId);
		daoAward.save(new Award(c,m,this.awardName,this.awardDate));
		return "index";
	}
}
