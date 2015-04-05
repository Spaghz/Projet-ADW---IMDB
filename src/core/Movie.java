package core;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "movies")
public class Movie {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private	int		id = -1;	
	
	@Column(name = "title")
	private String 			title;
	
	@Column(name = "releaseDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date			releaseDate;
	
	@Column(name="cost")
	private double	cost;
	
	/*
	private ArrayList<User> directors;
	private ArrayList<User>	actors;
	private ArrayList<User>	productors;
	private String			poster;	
	*/

	public Movie()
	{
	}
	
	public Movie(String title, Date releaseDate,double cost)
	{
		this.title = title;
		this.releaseDate = releaseDate;
		this.cost = cost;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
