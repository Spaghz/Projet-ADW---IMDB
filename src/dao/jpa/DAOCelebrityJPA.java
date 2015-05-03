package dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import core.Celebrity;
import core.CelebrityUpdate;
import core.Movie;
import dao.DAOCelebrity;
import dao.DAOCelebrityUpdate;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;

public class DAOCelebrityJPA extends DAOJPAUnpublished implements DAOCelebrity {
	
	private DAOCelebrityUpdate daoCelebrityUpdate = new DAOCelebrityUpdateJPA();
	
	@Override
	public void save(Celebrity celebrity) throws Exception {
		/*
		if (celebrity.getId() == -1)
		{
			DAOJPAUnpublished.getManager().persist(celebrity);
			DAOJPAUnpublished.commit();
		}
		else
		{
			throw new IllegalArgumentException("This celebrity is already saved in the database");
		}
		*/
		DAOJPAPublished.getManager().persist(celebrity);
		DAOJPAPublished.getManager().persist(new CelebrityUpdate(celebrity));
		DAOJPAPublished.commit();
	}
	
	public void saveToPublish(Celebrity celebrity) throws Exception {
		DAOJPAPublished.getManager().persist(celebrity);
		DAOJPAPublished.commit();
	}

	@Override
	/*
	public void removeFromUnpublished(Celebrity Celebrity) {
		if (Celebrity.getId() >= 0) {
			DAOJPAUnpublished.getManager().remove(Celebrity);
			DAOJPAUnpublished.commit();
			
		} else
			throw new IllegalArgumentException("Article pas persistant");
	}*/

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Celebrity> loadAll() {
		/*
		long numOfCelebrities = this.countUnpublished();
		
		ArrayList<Celebrity> celebrityList = new ArrayList<Celebrity>((int)numOfCelebrities);
		
		for(int i = 1; i <= numOfCelebrities ; i++ )
			celebrityList.add(getNotPublished(i));
		
		return celebrityList;
		*/
		// Récupération des ID non publiés :
		return DAOJPAPublished.getManager().createNativeQuery("SELECT * FROM `celebrity`",Celebrity.class).getResultList();
	}

	/*
	public Celebrity getNotPublished(int code) 
	{
		return code>=0?DAOJPAUnpublished.getManager().find(Celebrity.class,code):null;
	}

	@Override
	public long countUnpublished() {
		return ((Long) DAOJPAUnpublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM celebrity")
				.getSingleResult()).longValue();
	}*/

	@Override
	public Boolean isDisplayable(Celebrity celebrity) {
		if (celebrity.getId()>=0)
		{
			//CelebrityUpdate cU = DAOJPAPublished.getManager().createNamedQuery("SELECT cu FROM CelebrityUpdate WHERE cu.celebrityId = :celebrityId",CelebrityUpdate.class).setParameter("celebrityId",celebrity.getId()).getSingleResult();
			return (daoCelebrityUpdate.count(celebrity.getId())==0);
		}
		return false;
	}

	@Override
	public Celebrity getNotPublished(int code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFromUnpublished(Celebrity celebrity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long countUnpublished() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Celebrity> search(String searchString) {
		return DAOJPAPublished.getManager().createQuery(""
				+ "SELECT c FROM Celebrity c "
				+ "WHERE c.firstName LIKE :searchString "
				+ "OR c.lastName LIKE :searchString "
				+ "OR c.biography LIKE :searchString ",Celebrity.class)
				.setParameter("searchString","%"+searchString+"%")
				.getResultList();
	}

}
