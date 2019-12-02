package serpis.ad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateMain {
	protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
	public static void main(String[] args) {
		
		
		Scanner tcl= new Scanner(System.in);
		int o;
		System.out.println("Selecciona la tabla:");
		System.out.println("1-Categoria");
		System.out.println("2-Articulo");
		o=Integer.parseInt(tcl.nextLine());
		if(o==1) {
			System.out.println("Selecciona la opción");
			System.out.println("1-Insertar");
			System.out.println("2-Ver todos");
			System.out.println("3-Editar");
			System.out.println("4-Ver uno");
			System.out.println("5-Eliminar");

			o=Integer.parseInt(tcl.nextLine());
			switch (o) {
			case 1:
				CategoriaDao.insert();
				break;
			case 2:
				CategoriaDao.showAll();
				break;
			case 3:
				CategoriaDao.edit();
				break;
			case 4:
				CategoriaDao.show();
				break;
			case 5:
				CategoriaDao.delete();
			default:
				break;
			}
		}
				
//		Categoria categoria = new Categoria();
//		categoria.setNombre("cat " + LocalDateTime.now());
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(categoria);
//		
//		List<Categoria> categorias = entityManager.createQuery("from Categoria order by Id", Categoria.class).getResultList();
//		show(categorias);
//		entityManager.getTransaction().commit();
//		entityManager.close();
		CategoriaDao.close();
		entityManagerFactory.close();
		

	}
	
	private static void show(List<Categoria> categorias) {
		for (Categoria categoria : categorias)
			System.out.printf("%3d %s %n", categoria.getId(), categoria.getNombre());		
	}

}
