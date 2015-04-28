package beans;

import java.io.Serializable;

import core.Movie;

public class BeanAddMovie implements Serializable{

	private static final long serialVersionUID = 1L;
	private Movie movie;
	
	public String addMovie()
	{
		return null;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	

}
