package core;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "celebrityupdates")
public class CelebrityUpdate {
	@Column(name = "updateNumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private	int		id = -1;		
	
	@OneToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="celebrityId") 	
	private Celebrity celebrityBeingUpdated=null;
	
	public CelebrityUpdate()
	{
		
	}
	
	public CelebrityUpdate(Celebrity c)
	{
		celebrityBeingUpdated = c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Celebrity getCelebrityBeingUpdated() {
		return celebrityBeingUpdated;
	}

	public void setCelebrityBeingUpdated(Celebrity celebrityBeingUpdated) {
		this.celebrityBeingUpdated = celebrityBeingUpdated;
	}
}
