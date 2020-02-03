package serpis.ad.dao;
import serpis.ad.model.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class CategoriaHelper {
	private static Scanner tcl= new Scanner(System.in);
	
    private static List getAll() {
	    EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
        List<Categoria> categorias =  entityManager.createQuery("from Categoria order by Id", 
        		Categoria.class).getResultList();
	    entityManager.close();
        return categorias;
    }
    
    public static void showAll() {
    	List<Categoria> categorias=getAll();
    	for (Categoria categoria : categorias)
			System.out.printf("%3d %s %n", categoria.getId(), categoria.getNombre());
    	
    }
    
    public static void insert() {
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	Categoria categoria = new Categoria();
		categoria.setNombre("cat " + LocalDateTime.now());
		entityManager.getTransaction().begin();
    	entityManager.persist(categoria);	
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
    public static void edit(){
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la categoria a buscar");
		Long id=Long.parseLong(tcl.nextLine());
    	Categoria categoria =  entityManager.find(Categoria.class, id);
		entityManager.getTransaction().begin();
    	System.out.println("Introduce el nuevo nombre:");
    	categoria.setNombre(tcl.nextLine());
    	entityManager.getTransaction().commit();
    	entityManager.close();   
    }
    
    public static void show() {
    	Categoria categoria = search();
    	System.out.println(categoria);//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    	
    }
    
    public static void delete() {	    
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
//    	Categoria categoria =search();
    	System.out.println("Introduce id de la categoria a buscar");
		Long id=Long.parseLong(tcl.nextLine());
    	Categoria categoria=entityManager.find(Categoria.class, id);
    	entityManager.getTransaction().begin();
    	entityManager.remove(categoria);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Categoria search() {
		System.out.println("Introduce id de la categoria a buscar");
		int b=Integer.parseInt(tcl.nextLine());
		List<Categoria> categorias=getAll();
		Categoria categoria = new Categoria();
		for (Categoria categoriaA : categorias)
			if (categoriaA.getId() == b) {
				categoria=categoriaA;
				break;
			}
		return categoria;
	}

	
	
}