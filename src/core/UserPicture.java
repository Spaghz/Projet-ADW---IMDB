package core;

/*
@Entity
@Table(name = "userpictures")
public class UserPicture 
{
	@Column(name = "idPicture")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private	int		idPicture = -1;	
	
	@Column(name="pictureLink")
	private String pictureLink ="";
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private	User user = null;
	
	public UserPicture(){
	}
	
	public UserPicture(User user)
	{
		this.user = user;
		user.addPicture(this);
	}
	
	public UserPicture(String link)
	{
		pictureLink = link;
	}

	public int getIdPicture() {
		return idPicture;
	}

	public void setIdPicture(int idPicture) {
		this.idPicture = idPicture;
	}

	public User getUser() {
		return user;
	}

	public void setIdUser(User idUser) {
		this.user = idUser;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}
	
	
}
*/
