package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rights")
public class Rights {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private	int		id = -1;	
	
	@Column(name = "idUser")
	private	int		idUser = -1;	
	
	@Column(name = "isAdmin")
	private	Boolean	isAdmin = false;
	
	@Column(name = "isPro")
	private	Boolean	isPro = false;	
	
	public Rights()
	{
	}
	
	public Rights(User u)
	{
		if (u.getId() == -1)
			throw new IllegalArgumentException("User hasn't been uploaded yet! Please give a registered user to this function");
		
		this.idUser = u.getId();
		this.isAdmin = u.getIsAdmin();
		this.isPro = u.getIsPro();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsPro() {
		return isPro;
	}

	public void setIsPro(Boolean isPro) {
		this.isPro = isPro;
	}
	
	
}
