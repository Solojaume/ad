package serpis.ad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sun.tools.doclint.Entity;

public class HibernateMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sepis.ad.hibernate");
	    Categoria categoria=new Categoria();
	    
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.persist(categoria);
	    entityManager.close();
	    
	    entityManagerFactory.close();
	    
	}

}
