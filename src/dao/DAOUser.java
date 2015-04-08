package dao;
import java.util.List;

import core.User;

public interface DAOUser {
	User		get				(int code);
	User		get				(String nickName);
	User		getByEmail		(String email);
	List<User> 	loadAll			();
	void 		save			(User user);
	void 		remove			(User user);
	long 		count			();
	boolean 	nicknameExists	(String nickName);
	boolean 	emailExists		(String email);
}
