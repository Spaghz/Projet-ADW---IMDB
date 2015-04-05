package dao.jpa;
/*
import core.User;
import dao.DAOUserPicture;

public class DAOUserPictureJPA implements DAOUserPicture {

	@Override
	public UserPicture get(int code) {
		return code>=0?DAOJPA.getManager().find(UserPicture.class,code):null;
	}

	@Override
	public void save(UserPicture userPicture) throws Exception {
		if (userPicture.getIdPicture() == -1)
		{
			if (userPicture.getUser().getId() != -1)
				DAOJPA.getManager().persist(userPicture);
			else
				throw new IllegalArgumentException("This picture belongs to a user that is not persistent");
		}
		else
			throw new IllegalArgumentException("This picture is already persistent");
	}

	@Override
	public void remove(UserPicture userPicture) {
		if (userPicture.getIdPicture() >= 0) {
			DAOJPA.getManager().remove(userPicture);
			DAOJPA.commit();
			userPicture.setIdPicture(-1);
			userPicture.getUser().getUserPictures().remove(userPicture);
		}
		else 
			throw new IllegalArgumentException("User picture not persistent");

	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
*/