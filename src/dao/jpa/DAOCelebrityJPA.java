package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import core.Celebrity;
import dao.DAOCelebrity;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;

public class DAOCelebrityJPA extends DAOJPAUnpublished implements DAOCelebrity {

	@Override
	public void save(Celebrity celebrity) throws Exception {
		if (celebrity.getId() == -1)
		{
			DAOJPAUnpublished.getManager().persist(celebrity);
			DAOJPAUnpublished.commit();
		}
		else
		{
			throw new IllegalArgumentException("This celebrity is already saved in the database");
		}
	}
	
	public void saveToPublish(Celebrity celebrity) throws Exception {
		if (celebrity.getId() == -1)
		{
			DAOJPAPublished.getManager().persist(celebrity);
			DAOJPAPublished.commit();
		}
		else
		{
			throw new IllegalArgumentException("This celebrity is already saved in the database");
		}
	}

	@Override
	public void remove(Celebrity Celebrity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long count() {
		return ((Long) DAOJPAPublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM celebrity")
				.getSingleResult()).longValue();
	}

	@Override
	public boolean exists(String lastName,String firstName) {
		return (DAOJPAPublished
				.getManager()
				.createQuery(
						"SELECT c FROM Celebrity c WHERE c:firstName LIKE :firstName AND c:lastName LIKE :lastName")
				.setParameter("firstName", firstName)
				.setParameter("lastName",lastName)
				.getResultList().size() > 0);
	}

	@Override
	public Celebrity get(int code) {
		return code>=0?DAOJPAPublished.getManager().find(Celebrity.class,code):null;
	}

	@Override
	public List<Celebrity> loadLasts(int n) {
		int numOfCelebrities = (int)count();
		ArrayList<Celebrity> celebritiesList = new ArrayList<Celebrity>(n);
		
		for(int i = 0; (i < n)&&(numOfCelebrities-i>0) ; i++ )
			celebritiesList.add(get(numOfCelebrities-i));
		
		return celebritiesList;
	}

	@Override
	public ArrayList<Celebrity> loadAllNotPublished() {
		long numOfCelebrities = this.countUnpublished();
		
		ArrayList<Celebrity> celebrityList = new ArrayList<Celebrity>((int)numOfCelebrities);
		
		for(int i = 1; i <= numOfCelebrities ; i++ )
			celebrityList.add(getNotPublished(i));
		
		return celebrityList;
	}

	public Celebrity getNotPublished(int code) {
		return code>=0?DAOJPAUnpublished.getManager().find(Celebrity.class,code):null;
	}

	@Override
	public long countUnpublished() {
		return ((Long) DAOJPAUnpublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM celebrity")
				.getSingleResult()).longValue();
	}

}
