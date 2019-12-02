package serpis.ad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class CategoriaDao {
//	protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
	private static EntityManagerFactory entityManagerFactory=HibernateMain.entityManagerFactory;
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static Scanner tcl= new Scanner(System.in);
    private static List getAll() {
        List<Categoria> categorias =  entityManager.createQuery("from Categoria order by Id", 
        		Categoria.class).getResultList();
        return categorias;
    }
    
    public static void showAll() {
    	List<Categoria> categorias=getAll();
    	for (Categoria categoria : categorias)
			System.out.printf("%3d %s %n", categoria.getId(), categoria.getNombre());
    	
    }
    
    public static void insert() {
    	Categoria categoria = new Categoria();
		categoria.setNombre("cat " + LocalDateTime.now());
		iniciarTransicion(categoria);
		ejecutar();
    }
    
    public static void edit(){
    	Categoria categoria = search();
    	System.out.println("Imtroduce el nuevo nombre");
    	categoria.setNombre(tcl.nextLine());
    	iniciarTransicion(categoria);
		ejecutar();
    }
    
    public static void show() {
    	Categoria categoria = search();
    	System.out.println(categoria);//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    	
    }
    public static void delete() {
    	Categoria categoria=search();
    	entityManager.getTransaction().begin();
    	entityManager.remove(categoria);
    	ejecutar();
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

	private static void iniciarTransicion(Categoria categoria) {
		entityManager.getTransaction().begin();
		entityManager.persist(categoria);		
	}
	
	
	private static void ejecutar() {
		entityManager.getTransaction().commit();		
	}
    
	public static void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
}