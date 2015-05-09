package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import core.Celebrity;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;

public class BeanAddCelebrity {
	private Celebrity c = new Celebrity();
	private DAOMovie daoMovie = new DAOMovieJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	
	public BeanAddCelebrity() {
	}

	public Celebrity getC() {
		return c;
	}

	public void setC(Celebrity c) {
		this.c = c;
	}
	
	public String addCelebrity()
	{
		try
		{
			if (c.getFirstName().equals(""))
				throw new Exception("Please enter a valid first name");
			
			if (c.getLastName().equals(""))
				throw new Exception("Please enter a valid last name");
			
			if (c.getBirthDate()==null)
				throw new Exception("Please enter a valid birthdate");
			
			if (c.getBiography().equals(""))
				throw new Exception("Please enter a valid biography");
			
			c.setPictureURI("");
			
			daoCelebrity.save(c);
						
			return "index.xhtml";
		}
		catch (Exception e)
		{
			 FacesMessage message = new FacesMessage();
			 message.setSeverity(FacesMessage.SEVERITY_ERROR);
			 message.setSummary(e.getMessage());
			 message.setDetail(e.getMessage());
			 FacesContext.getCurrentInstance().addMessage(null, message);
			 return "";
		}
	}
}
