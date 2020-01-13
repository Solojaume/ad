package serpis.ad.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ContainerEntitityManager {
	protected static EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
	public static void close() {
		entityManagerFactory.close();
	}

}
