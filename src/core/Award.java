package core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "award")
public class Award {
	@ManyToOne
	@JoinColumn(name="idCelebrity")
	@Id
	private Celebrity celebrity;
	
	@ManyToOne
	@JoinColumn(name="idMovie")
	@Id
	private Movie movie;
	
	@Column(name = "awardName")
	@Id
	private	String	awardName;
	
	@Column(name = "awardDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date	awardDate;


	public Award() {
	}


	public Award(Celebrity celebrity, Movie movie, String awardName,
			Date awardDate) {
		this.celebrity = celebrity;
		this.movie = movie;
		this.awardName = awardName;
		this.awardDate = awardDate;
	}


	public Celebrity getCelebrity() {
		return celebrity;
	}


	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public String getAwardName() {
		return awardName;
	}


	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}


	public Date getAwardDate() {
		return awardDate;
	}


	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}
	
	
	
}