package dao.jpa;

import java.util.List;

import core.CelebrityUpdate;
import core.Movie;
import core.MovieUpdate;
import dao.DAOMovieUpdate;
import dao.jpa.managers.DAOJPAPublished;

public class DAOmovieUpdateJPA implements DAOMovieUpdate {

	@Override
	public long exists(int movieCode) {
		return ((Long) DAOJPAPublished.getManager()
				.createNativeQuery("SELECT COUNT(*) FROM `movieUpdates` WHERE `movieId` = ".concat(String.valueOf(movieCode)))
				.getSingleResult()).longValue();
	}

	@Override
	public void save(MovieUpdate movieUpdate) throws Exception {
		DAOJPAPublished.getManager().persist(movieUpdate);
		DAOJPAPublished.commit();
	}

	@Override
	public List<MovieUpdate> loadAll() {
		return DAOJPAPublished.getManager().createQuery("SELECT mU FROM MovieUpdate mU",MovieUpdate.class).getResultList();
	}

	public void remove(Movie movie) {
		if (movie.getId()!=-1)
		{
			// Récupération de la ligne movieUpdate correspondante
			//DAOJPAPublished.getManager().remove(this.get(movie));
			//DAOJPAPublished.commit();
			DAOJPAPublished.getManager().createQuery("DELETE FROM MovieUpdate AS mU WHERE mU.movieBeingUpdated = :movieId",MovieUpdate.class).setParameter("movieId",movie).executeUpdate();
			DAOJPAPublished.commit();
		}
	}

	@Override
	public void remove(MovieUpdate movieUpdate) {
		if (movieUpdate.getId()!=-1)
			DAOJPAPublished.getManager().remove(movieUpdate);
	}

	@Override
	public MovieUpdate get(Movie movie) {
		return (movie.getId()>0)?DAOJPAPublished.getManager().createQuery("SELECT mU FROM MovieUpdate mU WHERE mU.movieBeingUpdated = :movieId",MovieUpdate.class).setParameter("movieId",movie).getSingleResult():null;
	
	}

	@Override
	public MovieUpdate get(int movieId) {
		if (movieId>0)
		{
			return (MovieUpdate) DAOJPAPublished.getManager().createNativeQuery("SELECT * FROM `movieupdates` WHERE `movieId` = :movieId",MovieUpdate.class)
					.setParameter("movieId",movieId)
					.getSingleResult();
		}
		return null;
	}

	@Override
	public void remove(int movieId) {
		DAOJPAPublished.getManager().createQuery("DELETE FROM MovieUpdate AS mU WHERE mU.movieBeingUpdated = :movieId",MovieUpdate.class).setParameter("movieId",movieId).executeUpdate();
		DAOJPAPublished.commit();
	}

}
