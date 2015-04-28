package dao.jpa;

import dao.jpa.managers.DAOJPAManager;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;

public class DAOCoreClasses {
	DAOJPAManager 	publishManager 		= new DAOJPAPublished(),
					unpublishedManager	= new DAOJPAUnpublished();
}
