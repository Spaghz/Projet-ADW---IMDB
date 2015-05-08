package core;

import java.security.SecureRandom;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import utils.StringUtil;

@Entity
@Table(name = "users")
public class User {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private	int		id = -1;	
	
	@Column(name = "nickName")
	@NotNull(message = "Please enter a nickname.")
	@Size(min=3, max=64, message="Username must contain between 3 and 64 characters.")
	private String nickName;
	
	@Column(name ="password")
	@NotNull(message = "Please enter a valid password.")
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message="Please enter a valid password")
	private String password;
	
	@Column(name="email")
	@NotNull(message ="Please enter your email address.")
	@Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Merci de saisir une adresse mail valide" )
	private String email;
	
	@Column(name = "firstName")
	@NotNull(message = "Please enter your first name")
	@Size(min=3, max=60, message="First name must contain between 3 and 60 characters.")
	private String firstName;
	
	@Column(name = "lastName")
	@NotNull(message = "Please enter your your last name.")
	@Size(min=3, max=60, message="Last name must contain between 3 and 60 characters.")
	private String lastName;
	
	@Column(name="birthDate")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Please enter your birthdate")
	private Date birthDate;

	@Column(name="pictureURI")
	private String pictureURI;
	
	@Transient
	private Boolean isAdmin = false;
	
	@Transient
	private Boolean isPro	= true;
	
	@Column(name="salt")
	private byte[] salt;
	
	public User()
	{
		// Salt & Password generation

	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public User(String nickName,String password,String email,String firstName,String lastName,Date birthDate,String pictureURI,Boolean isAdmin,Boolean isPro)
	{
		testBirthDate(birthDate);
		
		testFirstName(firstName);
		
		testLastName(lastName);
		
		testNickName(nickName);

		this.nickName = nickName;
		this.email=email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.pictureURI = pictureURI;
		this.setIsAdmin(isAdmin);
		this.setIsPro(isPro);
		this.password = password;
		
		// Salt & Password generation

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPictureURI() {
		return pictureURI;
	}

	public void setPictureURI(String pictureURI) {
		this.pictureURI = pictureURI;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String _nickName) {
		this.nickName = _nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String _firstName) {
		this.firstName = _firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String _lastName) {
		this.lastName = _lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public byte[] getSalt() {
		return salt;
	}

	private void testNickName(String nickName) {
		if (nickName.length()==0)
			throw new IllegalArgumentException("User must enter a nickname");
	}

	private void testLastName(String lastName) {
		if (lastName.length()==0)
			throw new IllegalArgumentException("User must enter a last name");
	}

	private void testFirstName(String firstName) {
		if (firstName.length()==0)
			throw new IllegalArgumentException("User must enter a first name");
	}

	private void testBirthDate(Date birthDate) {
		if (birthDate.after(new Date()))
			throw new IllegalArgumentException("User can not be born after current date");
	}	
	
}
