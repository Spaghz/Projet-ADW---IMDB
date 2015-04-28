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
@Table(name = "celebrity")
public class Celebrity {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private	int		id = -1;	
	
	@Column(name = "firstName")
	private String 	firstName;	
	
	@Column(name = "lastName")
	private String 	lastName;	
	
	@Column(name = "biography")
	private String 	biography;	
	
	@Column(name = "birthDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date	birthDate;	
	
	@Column(name = "pictureURI")
	private String 	pictureURI;		
	
	@Column(name = "rank")
	private int 	rank=0;
	
	public Celebrity(){}

	public Celebrity(String firstName, String lastName, String biography,
			Date birthDate, String pictureURI) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography = biography;
		this.birthDate = birthDate;
		this.pictureURI = pictureURI;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPictureURI() {
		return pictureURI;
	}

	public void setPictureURI(String pictureURI) {
		this.pictureURI = pictureURI;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}	
	
	
}
