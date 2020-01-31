package serpis.ad.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import serpis.ad.model.Categoria;
import serpis.ad.model.Cliente;
import serpis.ad.model.Pedido;

public class PedidoDao {

private static Scanner tcl= new Scanner(System.in);
	
    private static List getAll() {
	    EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
        List<Pedido> categorias =  entityManager.createQuery("from Pedido order by Id", 
        		Pedido.class).getResultList();
	    entityManager.close();
        return categorias;
    }
    
    public static void showAll() {
    	List<Pedido> pedidos=getAll();
    	for (Pedido pedido : pedidos)
			System.out.printf("%3d %s %t %n", pedido.getId(), pedido.getFecha(),pedido.getCliente().getNombre());
    	
    }
    
    public static void insert() {
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	Pedido pedido = new Pedido();
		pedido.setFecha( LocalDateTime.now());
		System.out.println("Introduce la id del cliente al que quieres asociar el pedido:");
    	Cliente cli=entityManager.find(Cliente.class, Long.parseLong(tcl.nextLine()));
    	pedido.setCliente(cli);
    	int b=1;
    	PedidoLineaDao.insert(pedido);
        
    	while(b==1) {
    		System.out.println("Quieres añadir otra linea de pedido?");
    		System.out.println("1-Sí");
    		System.out.println("2-No");
    		if(Integer.parseInt(tcl.nextLine())==1)
    			PedidoLineaDao.insert(pedido);
    		else
    			b=0;
    		
    	}
		entityManager.getTransaction().begin();
    	entityManager.persist(pedido);	
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
//    public static void edit(){
//    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
//    	System.out.println("Introduce id de la categoria a buscar");
//		Long id=Long.parseLong(tcl.nextLine());
//		Pedido pedido =  entityManager.find(Pedido.class, id);
//		entityManager.getTransaction().begin();
//    	System.out.println("Introduce el nuevo nombre:");
//    	pedido.set(tcl.nextLine());
//    	entityManager.getTransaction().commit();
//    	entityManager.close();   
//    }
    
    public static void show() {
    	Pedido categoria = search();
    	System.out.println(categoria);//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    	
    }
    
    public static void delete() {	    
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la categoria a buscar");
		Long id=Long.parseLong(tcl.nextLine());
		Pedido categoria=entityManager.find(Pedido.class, id);
    	entityManager.getTransaction().begin();
    	entityManager.remove(categoria);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Pedido search() {
		System.out.println("Introduce id de la categoria a buscar");
		int b=Integer.parseInt(tcl.nextLine());
		List<Pedido> pedidos=getAll();
		Pedido pedido = new Pedido();
		for (Pedido categoriaA : pedidos)
			if (categoriaA.getId() == b) {
				pedido=categoriaA;
				break;
			}
		return pedido;
	}


}
