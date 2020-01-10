package serpis.ad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;


public class ClienteDao {
	private static Scanner tcl= new Scanner(System.in);
 
    
    public static void showAll() {
    	List<Clientes> clientes=getAll();
    	for (Clientes cliente : clientes)
    		System.out.printf("%3d %s %s %s \n" , cliente.getIdCliente(), cliente.getNombre(),cliente.getDni(),cliente.getTelefono());    	
    }
    
    public static void insert() {
    	Clientes cliente = new Clientes();
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
    	EntityManager entityManager = Main.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la cliente a buscar");
		Long id=Long.parseLong(tcl.nextLine());
    	Clientes cliente = entityManager.find(Clientes.class, id);
    	entityManager.getTransaction().begin();
    	System.out.println("Introduce el nuevo nombre");
    	cliente.setNombre(tcl.nextLine());   
    	entityManager.getTransaction().commit();
    	entityManager.close();

    }


    public static void show() {
    	Clientes cliente = search();
		System.out.printf("%3d %s %s %s \n" , cliente.getIdCliente(), cliente.getNombre(),cliente.getDni(),cliente.getTelefono());    	
//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    }
    
    public static void delete() {
    	EntityManager entityManager = Main.entityManagerFactory.createEntityManager();
    	Clientes cliente=search();
    	entityManager.getTransaction().begin();
    	entityManager.remove(cliente);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Clientes search() {
		System.out.println("Introduce id de la cliente a buscar");
		int b=Integer.parseInt(tcl.nextLine());
		List<Clientes> clientes=getAll();
		Clientes cliente = new Clientes();
		for (Clientes clienteA : clientes)
			if (clienteA.getIdCliente() == b) {
				cliente=clienteA;
				break;
			}
		return cliente;
	}

	private static void transicion(Clientes cliente) {
		EntityManager entityManager = Main.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();	
		entityManager.close();
	}
	
	private static List getAll() {
	    EntityManager entityManager = Main.entityManagerFactory.createEntityManager();
	    List<Clientes> clientes =  entityManager.createQuery("from Clientes order by id_cliente", 
	     		Clientes.class).getResultList();
	    entityManager.close();
	    return clientes; 
	 }
}
