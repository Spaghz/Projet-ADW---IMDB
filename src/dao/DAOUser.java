package dao;
import java.util.List;

import core.User;

public interface DAOUser {
	User			getByCode				(int code);
	User			getByUsername			(String nickName);
	User			getByEmail				(String email);
	int				getCodeByUsername		(String username);
	int				getCodeByEmail			(String email);
	Object			getAuthentificationInfo	(int code);
	List<User> 		loadAll					();
	void 			save					(User user);
	void 			remove					(User user);
	long 			count					();
	boolean 		nicknameExists			(String nickName);
	boolean 		emailExists				(String email);
}
