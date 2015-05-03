package dao.jpa;

import java.util.List;

import core.CelebrityUpdate;
import dao.DAOCelebrityUpdate;
import dao.jpa.managers.DAOJPAPublished;

public class DAOCelebrityUpdateJPA implements DAOCelebrityUpdate {

	@Override
	public long count(int celebrityCode) {
		return ((Long) DAOJPAPublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM `celebrityUpdates` WHERE `celebrityId` = ".concat(String.valueOf(celebrityCode)))
				.getSingleResult()).longValue();
	}

	@Override
	public void save(CelebrityUpdate celebrity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CelebrityUpdate> loadAll() {
		return DAOJPAPublished.getManager().createQuery("SELECT cU FROM CelebrityUpdate cU",CelebrityUpdate.class).getResultList();
	}

}
