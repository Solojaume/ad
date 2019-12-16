package serpis.ad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PaqueteDao {
	private static EntityManagerFactory entityManagerFactory=Main.entityManagerFactory;
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static Scanner tcl= new Scanner(System.in);
	
	public static void showAll() {
    	List<Paquetes> paquetes=getAll();
    	for (Paquetes paquete : paquetes)
    		System.out.printf("%3d %d %d %d \n" ,paquete.getIdPaquete(), paquete.getIdCliente(),paquete.getPrecio(),paquete.getCantidad());    	
    }
    
    public static void insert() {
    	Paquetes paquete = new Paquetes();
    	paquete.setPrecio(1L);
    	paquete.setCantidad(12L);
    	paquete.setIdCliente(9L);
		transicion(paquete);
    }
    
    public static void edit(){
    	Paquetes paquete = search();
    	System.out.println("Imtroduce el nuevo nombre");
    	paquete.setPrecio(tcl.nextLong());
    	paquete.setCantidad(tcl.nextLong());
    	paquete.setIdCliente(tcl.nextLong());
    	transicion(paquete);
    }
    
    public static void show() {
    	Paquetes paquete = search();
    	System.out.println(paquete);//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    }
    
    public static void delete() {
    	EntityManager entityManager = Main.entityManagerFactory.createEntityManager();
    	Paquetes paquete=search();
    	entityManager.getTransaction().begin();
    	entityManager.remove(paquete);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Paquetes search() {
		System.out.println("Introduce id de la paquete a buscar");
		int b=Integer.parseInt(tcl.nextLine());
		List<Paquetes> paquetes=getAll();
		Paquetes paquete = new Paquetes();
		for (Paquetes paqueteA : paquetes)
			if (paqueteA.getIdCliente() == b) {
				paquete=paqueteA;
				break;
			}
		return paquete;
	}

	private static void transicion(Paquetes cliente) {
		EntityManager entityManager = Main.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();	
		entityManager.close();
	}
	
	private static List getAll() {
	    EntityManager entityManager = Main.entityManagerFactory.createEntityManager();
	    List<Paquetes> paquetes =  entityManager.createQuery("from Paquetes order by id_paquete", 
	    		Paquetes.class).getResultList();
	    entityManager.close();
	    return paquetes; 
	 }

}
