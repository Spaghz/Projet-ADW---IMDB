package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rates")
public class Rate {
	@Column(name = "idUser")
	@Id
	private	int		idUser = -1;
	
	@Column(name = "idMovie")
	@Id
	private	int		idMovie = -1;	
	
	@Column(name="rate")
	private double rate;

	public Rate() {
	}
	
	public Rate(User u,Movie m) {
		this.idUser = u.getId();
		this.idMovie = m.getId();
		this.rate = 0;
	}
	
	public Rate(User u,Movie m, double rate) {
		this.idUser = u.getId();
		this.idMovie = m.getId();
		this.rate = rate;
	}

	public Rate(int idUser, int idMovie, double rate) {
		this.idUser = idUser;
		this.idMovie = idMovie;
		this.rate = rate;
	}

	public Rate(int idUser, int idMovie) {
		this.idUser = idUser;
		this.idMovie = idMovie;
		this.rate = 0;
	}
	
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
