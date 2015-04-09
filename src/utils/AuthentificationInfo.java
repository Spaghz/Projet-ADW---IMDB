package utils;

public class AuthentificationInfo {
	private byte[] salt;
	private String encryptedPassword;
	
	public AuthentificationInfo(byte[] salt,String encryptedPassword)
	{
		this.salt = salt;
		this.encryptedPassword = encryptedPassword;
	}

	public byte[] getSalt() {
		return salt;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}
}
