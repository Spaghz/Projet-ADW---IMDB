package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Constraint;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import dao.DAOCelebrity;
import dao.jpa.DAOCelebrityJPA;

@Entity
@Table(name = "movies")
public class Movie {
	@Transient
	DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	
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
	
	@Column(name="synopsis")
	private String synopsis;
	
	@Column(name="posterURI")
	private String posterURI="";
	
	@Column(name="rank")
	private int rank;
	
	@OneToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="idDirector") 	
	private Celebrity director=null;

	@ManyToMany
	@JoinTable(name = "play",
		joinColumns = { 
			@JoinColumn(table = "movies", name = "idMovie", referencedColumnName = "id") },
		inverseJoinColumns = { 
			@JoinColumn(table = "celebrity", name = "idCelebrity", referencedColumnName = "id") })
	private List<Celebrity> actors = new ArrayList<Celebrity>();

	@ManyToMany
	@JoinTable(name = "produce",
		joinColumns = { 
			@JoinColumn(table = "movies", name = "idMovie", referencedColumnName = "id") },
		inverseJoinColumns = { 
			@JoinColumn(table = "celebrity", name = "idCelebrity", referencedColumnName = "id") })
	private List<Celebrity> producers=new ArrayList<Celebrity>();

	public Movie()
	{
	}
	
	public Movie(String title, Date releaseDate,double cost)
	{
		this.title = title;
		this.releaseDate = releaseDate;
		this.cost = cost;
	}
	
	public Movie(String title, Date releaseDate,double cost,String synopsis)
	{
		this.title = title;
		this.releaseDate = releaseDate;
		this.cost = cost;
		this.synopsis = synopsis;
	}
	
	public Movie(int id,String title, Date releaseDate,double cost,String posterURI,int idDirector,String synopsis,int rank)
	{
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.cost = cost;
		this.posterURI = posterURI;
		this.director = daoCelebrity.getNotPublished(idDirector);
		if (this.director==null)
			this.director = daoCelebrity.get(idDirector);
		this.synopsis = synopsis;		
		this.rank = rank;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Celebrity getDirector() {
		return director;
	}

	public void setDirector(Celebrity director) {
		this.director = director;
	}

	public List<Celebrity> getActors() {
		return actors;
	}

	public void setActors(List<Celebrity> actors) {
		this.actors = actors;
	}

	public List<Celebrity> getProducers() {
		return producers;
	}

	public void setProducers(List<Celebrity> producers) {
		this.producers = producers;
	}	
	
	public void addProducer(Celebrity c)
	{
		producers.add(c);
	}
	
	public void addActor(Celebrity c)
	{
		actors.add(c);
	}
	
	public String getTrimmedSynopsis()
	{
		return synopsis.substring(0,50).concat("...");
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", releaseDate="
				+ releaseDate + ", cost=" + cost + ", synopsis=" + synopsis
				+ ", posterURI=" + posterURI + ", rank=" + rank + ", director="
				+ director + ", actors=" + actors + ", producers=" + producers
				+ "]";
	}
	

}
