package serpis.ad.dao;
import serpis.ad.*;
import serpis.ad.model.Cliente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;


public class ClienteDao {
	private static Scanner tcl= new Scanner(System.in);
     
    
    public static void showAll() {
    	List<Cliente> clientes=getAll();
    	for (Cliente cliente : clientes)
    		System.out.printf("%3d %s \n" , cliente.getIdCliente(), cliente.getNombre());    	
    }
    
    public static void insert() {
    	Cliente cliente = new Cliente();
    	cliente.setNombre("cat " + LocalDateTime.now());
		transicion(cliente);
    }
    
//    public static void edit(){
//    	Clientes cliente = search();
//    	System.out.println("Introduce el nuevo nombre");
//    	cliente.setNombre(tcl.nextLine());    
//    	transicion(cliente);
//    }
    public static void edit(){
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la cliente a buscar");
		Long id=Long.parseLong(tcl.nextLine());
    	Cliente cliente = entityManager.find(Cliente.class, id);
    	entityManager.getTransaction().begin();
    	System.out.println("Introduce el nuevo nombre");
    	cliente.setNombre(tcl.nextLine());   
    	entityManager.getTransaction().commit();
    	entityManager.close();

    }


    public static void show() {
    	Cliente cliente = search();
		System.out.printf("%3d %s \n" , cliente.getIdCliente(), cliente.getNombre());    	
//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    }
    
    public static void delete() {
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	Cliente cliente=search();
//    	System.out.println("Introduce id de la cliente a borrar");
//		Long id=Long.parseLong(tcl.nextLine());
//    	Cliente cliente=entityManager.find(Cliente.class, id);
    	entityManager.getTransaction().begin();
    	entityManager.remove(cliente);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Cliente search() {
		System.out.println("Introduce id de la cliente a buscar");
		int b=Integer.parseInt(tcl.nextLine());
		List<Cliente> clientes=getAll();
		Cliente cliente = new Cliente();
		for (Cliente clienteA : clientes)
			if (clienteA.getIdCliente() == b) {
				cliente=clienteA;
				break;
			}
		return cliente;
	}

	private static void transicion(Cliente cliente) {
		EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();	
		entityManager.close();
	}
	
	private static List getAll() {
	    EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
	    List<Cliente> clientes =  entityManager.createQuery("from Clientes order by id_cliente", 
	     		Cliente.class).getResultList();
	    entityManager.close();
	    return clientes; 
	 }
}
