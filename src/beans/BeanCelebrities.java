package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import core.Celebrity;
import dao.DAOCelebrity;
import dao.jpa.DAOCelebrityJPA;

@ManagedBean(name="beanCelebrities")
public class BeanCelebrities {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private DAOCelebrity 	daoCelebrityJPA = new DAOCelebrityJPA();
	private List<Celebrity>	celebritiesToDisplay;
	
	
	public BeanCelebrities()
	{
		super();
		setCelebritiesToDisplay(loadCelebrities(5));
	}

	public ArrayList<Celebrity> loadCelebrities(int n)
	{
		return (ArrayList<Celebrity>)daoCelebrityJPA.loadLasts(n);
	}

	public List<Celebrity> getCelebritiesToDisplay() {
		return celebritiesToDisplay;
	}

	public void setCelebritiesToDisplay(List<Celebrity> celebritiesToDisplay) {
		this.celebritiesToDisplay = celebritiesToDisplay;
	}

}
