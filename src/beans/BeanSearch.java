package beans;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import core.Celebrity;
import core.Movie;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;

public class BeanSearch {

	private List<Celebrity> celebrityResults = null;
	private List<Movie> movieResults = null;
	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private FacesContext fc = FacesContext.getCurrentInstance();
	private String searchString;
	private int numOfMovies,numOfCelebs;

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public BeanSearch() {

	}

	public List<Celebrity> getCelebrityResults() {
		return celebrityResults;
	}

	public void setCelebrityResults(List<Celebrity> celebrityResults) {
		this.celebrityResults = celebrityResults;
	}

	public List<Movie> getMovieResults() {
		return movieResults;
	}

	public void setMovieResults(List<Movie> movieResults) {
		this.movieResults = movieResults;
	}

	public String performSearch() {
		if ((searchString != null) && (!searchString.equals(""))) {
			celebrityResults = daoCelebrity.search(searchString);
			movieResults = daoMovie.search(searchString);
		}
		numOfCelebs = celebrityResults.size();
		numOfMovies = movieResults.size();
		return"searchResult";
	}

	public int getNumOfMovies() {
		return numOfMovies;
	}

	public void setNumOfMovies(int numOfMovies) {
		this.numOfMovies = numOfMovies;
	}

	public int getNumOfCelebs() {
		return numOfCelebs;
	}

	public void setNumOfCelebs(int numOfCelebs) {
		this.numOfCelebs = numOfCelebs;
	}
	
	
}
