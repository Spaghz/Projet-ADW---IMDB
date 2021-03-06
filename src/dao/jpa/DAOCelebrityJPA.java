package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import core.Award;
import core.Celebrity;
import core.CelebrityUpdate;
import core.Movie;
import dao.DAOCelebrity;
import dao.DAOCelebrityUpdate;
import dao.DAOMovie;
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
		// R�cup�ration des ID non publi�s :
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
		String[] searchStrings 	= searchString.split(" ");
		String[] searchFields 	= new String[]{"c.firstName","c.lastName"};
		
		
		String query = "SELECT c FROM Celebrity c WHERE ";
			for(String field : searchFields)
				for(int i = 0;i<searchStrings.length;i++)
					query+=field+" LIKE :searchString"+String.valueOf(i)+"\n OR ";
		
		query=query.substring(0,query.length()-3);
		
		//System.out.println(query);

		Query searchQuery = DAOJPAPublished.getManager().createQuery(query,Celebrity.class);
		
		for(int i = 0;i<searchStrings.length;i++)
			searchQuery=searchQuery.setParameter("searchString"+String.valueOf(i),"%"+searchStrings[i]+"%");
		
		return searchQuery.getResultList();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Movie> getMovieProduced(Celebrity c) {
		List<Integer> idMovies = DAOJPAPublished.getManager().createNativeQuery("SELECT `idMovie` FROM `produce` WHERE `idCelebrity` = "+c.getId()).getResultList();
		List<Movie> res = new ArrayList<Movie>();
		for(Integer i : idMovies)
		{
			res.add(new DAOMovieJPA().get(i));
		}
		return res;
	}

	@Override
	public List<Movie> getMovieStarred(Celebrity c) {
		List<Integer> idMovies = DAOJPAPublished.getManager().createNativeQuery("SELECT `idMovie` FROM `play` WHERE `idCelebrity` = "+c.getId()).getResultList();
		
		List<Movie> res = new ArrayList<Movie>();
		for(Integer i : idMovies)
		{
			res.add(new DAOMovieJPA().get(i));
		}
		return res;

	}

	@Override
	public List<Movie> getMovieDirected(Celebrity c) {
		return DAOJPAPublished.getManager().createQuery("SELECT m FROM Movie m WHERE m.director = :c",Movie.class).setParameter("c",c).getResultList();
	}

	@Override
	public List<Award> getAwards(Celebrity c) {
		return DAOJPAPublished.getManager().createQuery("SELECT a FROM Award a WHERE a.celebrity = :c",Award.class).setParameter("c",c).getResultList();
	}

}
