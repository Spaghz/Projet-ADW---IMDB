package core;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "movieupdates")
public class MovieUpdate {
	@Column(name = "updateNumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id = -1;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "movieId")
	private Movie movieBeingUpdated = null;
	
	@Column(name = "title")
	private String 			title;
	
	@Column(name = "releaseDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date			releaseDate;
	
	@Column(name="cost")
	private double	cost;
	
	@Column(name="synopsis")
	private String synopsis;
	
	@Column(name="posterURI")
	private String posterURI="";
	
	@OneToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="idDirector") 	
	private Celebrity director=null;

	public MovieUpdate() {

	}

	public MovieUpdate(Movie movieBeingUpdated) {
		this.movieBeingUpdated = movieBeingUpdated;
		this.title = movieBeingUpdated.getTitle();
		this.releaseDate = movieBeingUpdated.getReleaseDate();
		this.cost = movieBeingUpdated.getCost();
		this.synopsis = movieBeingUpdated.getSynopsis();
		this.posterURI = movieBeingUpdated.getPosterURI();
		this.director = movieBeingUpdated.getDirector();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovieBeingUpdated() {
		return movieBeingUpdated;
	}

	public void setMovieBeingUpdated(Movie movieBeingUpdated) {
		this.movieBeingUpdated = movieBeingUpdated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getPosterURI() {
		return posterURI;
	}

	public void setPosterURI(String posterURI) {
		this.posterURI = posterURI;
	}

	public Celebrity getDirector() {
		return director;
	}

	public void setDirector(Celebrity director) {
		this.director = director;
	}

}
