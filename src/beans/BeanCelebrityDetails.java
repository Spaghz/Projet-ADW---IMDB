package beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Convert;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import core.Celebrity;
import dao.DAOCelebrity;
import dao.jpa.DAOCelebrityJPA;

@ManagedBean(name="beanCelebrityDetails")
@RequestScoped
public class BeanCelebrityDetails {
	private Celebrity celebrity = null;
	private DAOCelebrity dao = new DAOCelebrityJPA();
	
	public BeanCelebrityDetails()
	{
		setCelebrity(dao.get(getCelebrityCode(FacesContext.getCurrentInstance())));
	}
	
	public int getCelebrityCode(FacesContext fc){
		 
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return Integer.parseInt(params.get("id"));
	}
	
	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}
	
	public String loadCelebrity()
	{
		setCelebrity(dao.get(getCelebrityCode(FacesContext.getCurrentInstance())));
		return "testttttttttttt.xhtml";
	}
}
