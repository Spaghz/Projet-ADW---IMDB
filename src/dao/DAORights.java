package dao;

import core.Rights;
import core.User;

public interface DAORights {
	Rights	get		(int code);
	Rights	get		(User user);
	void 	save	(Rights rights) throws Exception;
	void 	remove	(Rights rights);
	Boolean isAdmin (User user);
	Boolean isPro	(User user);
	long 	count	();
}
