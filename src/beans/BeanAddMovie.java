package beans;

import java.io.Serializable;
import java.util.List;

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
		if (movie.getTitle()!=null)
			if (movie.getReleaseDate()!=null)
				try {
					movie.setDirector(daoCelebrity.get(movie.getDirector().getId()));
					daoMovie.save(movie);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return "index.xhtml";
	}
	

}
