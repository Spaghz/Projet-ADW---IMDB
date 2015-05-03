package beans;

import java.util.List;

import core.Celebrity;
import core.Movie;

public class BeanSearch {
	private String searchString;

	public BeanSearch()
	{
		
	}
	
	public String performSearch()
	{
		return "";
	}
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public List<Celebrity> performSearchCelebrity()
	{
		return null;
	}
	
	public List<Movie> performSearchMovie()
	{
		return null;
	}
}
