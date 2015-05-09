package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import core.Celebrity;
import core.CelebrityUpdate;
import core.Movie;
import core.MovieUpdate;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.DAOMovieUpdate;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;

public class DAOMovieJPA extends DAOJPAPublished implements DAOMovie {

	static private DAOMovieJPA instance = null;
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOMovieUpdate daoMovieUpdate = new DAOmovieUpdateJPA();

	static public DAOMovieJPA getInstance() {
		if (instance == null)
			instance = new DAOMovieJPA();

		return instance;
	}

	@Override
	public Movie get(int code) {
		Movie m = code >= 0 ? DAOJPAPublished.getManager().find(Movie.class,
				code) : null;
		return m;
	}

	@Override
	public void save(Movie movie) throws Exception {

		if (movie.getId() == -1) {
			
			if (movie.getDirector()==null)
				throw new IllegalArgumentException("Movie must have a director!");
			if ((movie.getDirector().getId() == -1)) {
				daoCelebrity.save(movie.getDirector());
			}

			if (movie.getActors() != null) {
				for (Celebrity c : movie.getActors()) {
					if (c.getId() == -1)
						daoCelebrity.save(c);
				}
			}

			if (movie.getProducers() != null) {
				for (Celebrity c : movie.getProducers()) {
					if (c.getId() == -1)
						daoCelebrity.save(c);
				}
			}
			DAOJPAPublished.getManager().persist(movie);
			DAOJPAPublished.commit();
			
			//daoMovieUpdate.save(new MovieUpdate(movie));
			
		} else {
			throw new IllegalArgumentException(
					"This movie is already saved in the database");
		}

		/*
		 * DAOJPAPublished.getManager().persist(movie);
		 * DAOJPAPublished.getManager().persist(new MovieUpdate(movie));
		 * DAOJPAPublished.commit();
		 */
	}

	@Override
	public void removeFromUnpublished(Movie movie) {
		/*
		if (movie.getId() >= 0) {
			DAOJPAUnpublished.getManager().remove(movie);
			DAOJPAUnpublished.commit();

		} else
			throw new IllegalArgumentException("Article pas persistant");
			*/
	}

	@Override
	public long count() {
		return ((Long) DAOJPAPublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM movies")
				.getSingleResult()).longValue();
	}

	@Override
	public boolean exists(String movieTitle) {
		return (DAOJPAPublished.getManager()
				.createQuery("SELECT m FROM Movie m WHERE m:title LIKE :title")
				.setParameter("title", movieTitle).getResultList().size() > 0);
	}

	@Override
	public List<Movie> loadLasts(int n) {
		int numOfMovies = (int) count();
		ArrayList<Movie> movieList = new ArrayList<Movie>(n);

		for (int i = 0; (i < n) && (numOfMovies - i > 0); i++)
			movieList.add(get(numOfMovies - i));

		return movieList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> loadAll() {
		/*
		long numOfMovies = this.count();

		ArrayList<Movie> movieList = new ArrayList<Movie>((int) numOfMovies);

		for (int i = 1; i <= numOfMovies; i++)
			movieList.add(get(i));

		return movieList;
		*/
		return DAOJPAPublished.getManager().createNativeQuery("SELECT * FROM `movies` WHERE `id` NOT IN (SELECT `movieId` FROM `movieupdates`)",Movie.class).getResultList();
	}

	@Override
	public void saveToPublish(Movie movie) throws Exception {
		DAOJPAPublished.getManager().persist(movie);
		DAOJPAPublished.commit();
	}

	@Override
	public Celebrity getDirector(int code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Celebrity> getActors(int code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Celebrity> getProducers(int code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieUpdate> loadAllNotPublished() {
		/*
		 * return DAOJPAUnpublished.getManager()
		 * .createQuery("SELECT m FROM Movie m", Movie.class) .getResultList();
		 */
		// Récupération des ID non publiés :
		/*
		ArrayList<Movie> res = new ArrayList<Movie>();
		List<MovieUpdate> list = daoMovieUpdate.loadAll();
		for (MovieUpdate movieUpdate : list) {
			res.add(this.get(movieUpdate.getMovieBeingUpdated().getId()));
		}
		return res;
		*/
		return DAOJPAPublished.getManager().createNativeQuery("SELECT * FROM `movieupdates`",MovieUpdate.class).getResultList();
	}

	public Movie getNotPublished(int i) {
		/*
		Movie m = i >= 0 ? DAOJPAUnpublished.getManager().find(Movie.class, i)
				: null;
		return m;
		*/
		return null;
	}

	@Override
	public long countUnpublished() {
		/*
		return ((Long) DAOJPAUnpublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM movies")
				.getSingleResult()).longValue();
				*/
		return 0;
	}

	@Override
	public void publish(Movie movie) throws Exception {
		/*
		if (movie.getDirector() != null)
			if (daoCelebrity.get(movie.getDirector().getId()) == null) {
				// daoCelebrity.saveToPublish(movie.getDirector());
			}

		if (movie.getActors() != null)
			for (Celebrity actor : movie.getActors())
				if (daoCelebrity.get(actor.getId()) == null)
					daoCelebrity.saveToPublish(actor);

		if (movie.getProducers() != null)
			for (Celebrity producer : movie.getProducers())
				if (daoCelebrity.get(producer.getId()) == null)
					daoCelebrity.saveToPublish(producer);

		// Methode pour update
		if (this.get(movie.getId()) != null) {
			// update
			this.updatePublish(movie);
		} else {
			// save
			this.saveToPublish(movie);
		}

		this.removeFromUnpublished(movie);
		*/
		MovieUpdate mU = daoMovieUpdate.get(movie);
		movie.setCost(mU.getCost());
		movie.setDirector(mU.getDirector());
		movie.setPosterURI(mU.getPosterURI());
		movie.setReleaseDate(mU.getReleaseDate());
		
		daoMovieUpdate.remove(movie);
		this.remove(movie);
		this.updatePublish(movie);
	}

	@Override
	public void updatePublish(Movie movie) throws Exception{
		if (movie.getId() == -1) {
			if ((movie.getDirector() != null)
					&& (movie.getDirector().getId() == -1)) {
				daoCelebrity.save(movie.getDirector());
			}

			if (movie.getActors() != null) {
				for (Celebrity c : movie.getActors()) {
					if (c.getId() == -1)
						daoCelebrity.save(c);
				}
			}

			if (movie.getProducers() != null) {
				for (Celebrity c : movie.getProducers()) {
					if (c.getId() == -1)
						daoCelebrity.save(c);
				}
			}
			DAOJPAPublished.getManager().persist(movie);
			DAOJPAPublished.commit();
			
			
		} else {
			throw new IllegalArgumentException(
					"This movie is already saved in the database");
		}
	}

	@Override
	public Boolean isDisplayable(Movie movie) {
		if (movie.getId() >= 0) {
			// CelebrityUpdate cU =
			// DAOJPAPublished.getManager().createNamedQuery("SELECT cu FROM CelebrityUpdate WHERE cu.celebrityId = :celebrityId",CelebrityUpdate.class).setParameter("celebrityId",celebrity.getId()).getSingleResult();
			return (daoMovieUpdate.exists(movie.getId()) == 0);
		}
		return false;
	}

	@Override
	public List<Movie> search(String searchString) {
		String[] searchStrings 	= searchString.split(" ");
		String[] searchFields 	= new String[]{"m.title","m.synopsis"};
		
		
		String query = "SELECT m FROM Movie m WHERE ";
			for(String field : searchFields)
				for(int i = 0;i<searchStrings.length;i++)
					query+=field+" LIKE :searchString"+String.valueOf(i)+"\n OR ";
		
		query=query.substring(0,query.length()-3);
		
		//System.out.println(query);

		Query searchQuery = DAOJPAPublished.getManager().createQuery(query,Movie.class);
		
		for(int i = 0;i<searchStrings.length;i++)
			searchQuery=searchQuery.setParameter("searchString"+String.valueOf(i),"%"+searchStrings[i]+"%");
		
		return searchQuery.getResultList();
	}

	@Override
	public List<Movie> loadAllPublished() 
	{
		return DAOJPAPublished.getManager().createNativeQuery("SELECT * FROM `movies` WHERE `id` NOT IN (SELECT `movieId` FROM `movieupdates`)",Movie.class).getResultList();
	}

	@Override
	public void remove(Movie movie) {
		if (movie.getId()>0)
		{
			DAOJPAPublished.getManager().remove(movie);
			DAOJPAPublished.commit();
			movie.setId(-1);
		}
			
		
	}

	@Override
	public void rankPlusOne(Movie movie) {
		DAOJPAPublished.getManager().createNativeQuery("UPDATE `movies` SET `rank` = "+movie.getRank()+1+" WHERE `movies`.`id` = "+movie.getId())
			.executeUpdate();
		DAOJPAPublished.commit();
	}
}
