package serpis.ad.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import serpis.ad.model.Articulo;
import serpis.ad.model.Categoria;

public class ArticuloHelper {
private static Scanner tcl= new Scanner(System.in);
	
    private static List getAll() {
	    EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
        List<Articulo> articulos =  entityManager.createQuery("from Articulo order by Id", 
        		Articulo.class).getResultList();
	    entityManager.close();
        return articulos;
    }
    
    public static void showAll() {
    	List<Articulo> articulos=getAll();
    	for (Articulo articulo : articulos)
			System.out.printf("%3d %s %s \n", articulo.getId(), articulo.getNombre(),articulo.getCategoria().getNombre());
    	
    	
    }
    
    public static void insert() {
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	Articulo articulo = new Articulo();
		articulo.setNombre("Art " + LocalDateTime.now());
    	System.out.println("Introduce el nuevo precio:");
    	articulo.setPrecio(new BigDecimal(tcl.nextLine()));
    	System.out.println("Introduce la id de la categoria:");
    	Categoria cat=entityManager.find(Categoria.class, Long.parseLong(tcl.nextLine()));
    	articulo.setCategoria(cat);

		entityManager.getTransaction().begin();
    	entityManager.persist(articulo);	
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
    public static void edit(){
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la articulo a buscar");
		Long id=Long.parseLong(tcl.nextLine());
    	Articulo articulo =  entityManager.find(Articulo.class, id);
		entityManager.getTransaction().begin();
    	System.out.println("Introduce el nuevo nombre:");
    	articulo.setNombre(tcl.nextLine());
    	
    	System.out.println("Introduce el nuevo precio:");
    	articulo.setPrecio(new BigDecimal(tcl.nextLine()));
    	System.out.println("Introduce la id de la categoria:");
    	articulo.setCategoria(entityManager.find(Categoria.class, Long.parseLong(tcl.nextLine())));
    	entityManager.getTransaction().commit();
    	entityManager.close();   
    }
    
    public static void show() {
    	Articulo articulo = search();
    	System.out.println(articulo);//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    	
    }
    
    public static void delete() {	    
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la ARTICULO a buscar");
		Long id=Long.parseLong(tcl.nextLine());
    	Articulo articulo=entityManager.find(Articulo.class, id);
    	entityManager.getTransaction().begin();
    	entityManager.remove(articulo);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Articulo search() {
		System.out.println("Introduce id de la ARTICULO a buscar");
		int b=Integer.parseInt(tcl.nextLine());
		List<Articulo> categorias=getAll();
		Articulo categoria = new Articulo();
		for (Articulo categoriaA : categorias)
			if (categoriaA.getId() == b) {
				categoria=categoriaA;
				break;
			}
		return categoria;
	}
}
