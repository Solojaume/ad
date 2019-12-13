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
    private static List getAll() {
        List<Clientes> clientes =  entityManager.createQuery("from Categoria order by Id", 
        		Clientes.class).getResultList();
        return clientes;
    }
    
    public static void showAll() {
    	List<Clientes> clientes=getAll();
    	for (Clientes cliente : clientes)
    		System.out.printf("%3d %s %n %s \n" , cliente.getIdCliente(), cliente.getNombre(),cliente.getDni(),cliente.getTelefono());    	
    	
    }
    
    public static void insert() {
    	Clientes cliente = new Clientes();
    	cliente.setNombre("cat " + LocalDateTime.now());
		iniciarTransicion(cliente);
		ejecutar();
    }
    
    public static void edit(){
    	Clientes categoria = search();
    	System.out.println("Imtroduce el nuevo nombre");
    	categoria.setNombre(tcl.nextLine());
    	iniciarTransicion(categoria);
		ejecutar();
    }
    
    public static void show() {
    	Clientes categoria = search();
    	System.out.println(categoria);//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    	
    }
    
    public static void delete() {
    	Clientes categoria=search();
    	entityManager.getTransaction().begin();
    	entityManager.remove(categoria);
    	ejecutar();
    }
    
	private static Clientes search() {
		System.out.println("Introduce id de la categoria a buscar");
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

	private static void iniciarTransicion(Clientes cliente) {
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);		
	}
	
	
	private static void ejecutar() {
		entityManager.getTransaction().commit();		
	}
    
	public static void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
