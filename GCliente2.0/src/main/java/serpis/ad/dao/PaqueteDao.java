package serpis.ad.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import serpis.ad.*;
import serpis.ad.model.Paquete;

public class PaqueteDao {
	private static Scanner tcl= new Scanner(System.in);
	
	public static void showAll() {
    	List<Paquete> paquetes=getAll();
    	for (Paquete paquete : paquetes)
    		System.out.printf("%3d %d %d %d \n" ,paquete.getIdPaquete(), paquete.getIdCliente(),paquete.getPrecio(),paquete.getCantidad());    	
    }
    
    public static void insert() {
    	Paquete paquete = new Paquete();
    	paquete.setPrecio(1L);
    	paquete.setCantidad(12L);
    	paquete.setIdCliente(9L);
		transicion(paquete);
    }
    
    public static void edit(){
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la cliente a buscar");
		Long id=Long.parseLong(tcl.nextLine());
    	Paquete paquetes = entityManager.find(Paquete.class, id);
    	entityManager.getTransaction().begin();
    	System.out.println("Introduce el nuevo precio");
    	paquetes.setPrecio(Long.parseLong(tcl.nextLine()));   
    	entityManager.getTransaction().commit();
    	entityManager.close();

    }
    
    public static void show() {
    	Paquete paquete = search();
		System.out.printf("%3d %d %d %d \n" ,paquete.getIdPaquete(), paquete.getIdCliente(),paquete.getPrecio(),paquete.getCantidad());    	
//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    }
    
    public static void delete() {
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	Paquete paquete=search();
    	entityManager.getTransaction().begin();
    	entityManager.remove(paquete);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Paquete search() {
		System.out.println("Introduce id de la paquete a buscar");
		int b=Integer.parseInt(tcl.nextLine());
		List<Paquete> paquetes=getAll();
		Paquete paquete = new Paquete();
		for (Paquete paqueteA : paquetes)
			if (paqueteA.getIdCliente() == b) {
				paquete=paqueteA;
				break;
			}
		return paquete;
	}

	private static void transicion(Paquete cliente) {
		EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();	
		entityManager.close();
	}
	
	private static List getAll() {
	    EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
	    List<Paquete> paquetes =  entityManager.createQuery("from Paquetes order by id_paquete", 
	    		Paquete.class).getResultList();
	    entityManager.close();
	    return paquetes; 
	 }

}
