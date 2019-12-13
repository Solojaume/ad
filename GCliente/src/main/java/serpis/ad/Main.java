package serpis.ad;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
//	public static void main(String[] args) {
//		Clientes cliente = new Clientes();
//		cliente.setNombre("Pepe");
//		cliente.setDni("3566554");
//		cliente.setTelefono(BigDecimal.valueOf(65487875));
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(cliente);
//		List<Clientes> clientes = entityManager.createQuery("from Clientes order by id_cliente", Clientes.class).getResultList();
//		showAll(clientes);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//	}
//	public static void showAll(List <Clientes> clientes) {
//    	for (Clientes cliente : clientes)
//    		System.out.printf("%3d %s %n %s \n" , cliente.getIdCliente(), cliente.getNombre(),cliente.getDni(),cliente.getTelefono());    	
//    }
	
	public static void main(String[] args) {
		Scanner tcl = new Scanner(System.in);
		Paquetes paquete= new Paquetes();
		System.out.println("Introduce id cliente:");
		paquete.setIdCliente(tcl.nextLong());
		paquete.setCantidad(12L);
		paquete.setPrecio(120L);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(paquete);
		List<Paquetes> paquetes = entityManager.createQuery("from Paquetes order by id_paquete", Paquetes.class).getResultList();
		showAll(paquetes);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	
	}
	public static void showAll(List <Paquetes> paquetes) {
    	for (Paquetes paquete : paquetes)
    		System.out.printf("%3d %3d %3d %3d \n" ,paquete.getIdPaquete(), paquete.getIdCliente(), paquete.getPrecio(),paquete.getCantidad());    	
    	   
    }
	
    
}
	
