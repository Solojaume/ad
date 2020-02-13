package serpis.ad.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import serpis.ad.model.Categoria;
import serpis.ad.model.Cliente;
import serpis.ad.model.Pedido;
import serpis.ad.model.PedidoLinea;

public class PedidoHelper {

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
    	for (Pedido pedido : pedidos) {
			System.out.printf("%3d %s %s %s %n", pedido.getId(), pedido.getFecha(),pedido.getCliente().getNombre(),pedido.getImporte().toString());
    	    PedidoLineaHelper.showAll(pedido);
    	}
    	
    }
    
    public static void insert() {
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	Pedido pedido = new Pedido();
		pedido.setFecha( LocalDateTime.now());
		System.out.println("Introduce la id del cliente al que quieres asociar el pedido:");
    	Cliente cli=entityManager.find(Cliente.class, Long.parseLong(tcl.nextLine()));
    	pedido.setCliente(cli);
    	
    	int b=1;
    	PedidoLineaHelper.crear(pedido);
        
    	while(b==1) {
    		System.out.println("Quieres añadir otra linea de pedido?");
    		System.out.println("1-Sí");
    		System.out.println("2-No");
    		if(Integer.parseInt(tcl.nextLine())==1)
    			PedidoLineaHelper.crear(pedido);
    		else
    			b=0;
    		
    	}
    	
    	
    	pedido.setImporte(pedido.getImporte());
		entityManager.getTransaction().begin();
    	entityManager.persist(pedido);	
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
    public static void edit(){
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id de la pedido a buscar");
		Long id=Long.parseLong(tcl.nextLine());
		Pedido pedido =  entityManager.find(Pedido.class, id);
		pedido.setFecha( LocalDateTime.now());
		System.out.println("Introduce la id del cliente al que quieres asociar el pedido:");
    	Cliente cli=entityManager.find(Cliente.class, Long.parseLong(tcl.nextLine()));
    	pedido.setCliente(cli);
    	System.out.println("Que quieres hacer?");
    	System.out.println("1- Añadir Linea Pedido");
    	System.out.println("2- Eliminar Linea Pedido");
    	System.out.println("3- Volver al menu principal");
    	PedidoLineaHelper.showAll(pedido);
    	int o=Integer.parseInt(tcl.nextLine());
    	if(o==1)
    		PedidoLineaHelper.crear(pedido);
    	else if(o==2)
    		PedidoLineaHelper.delete();
    	else;
    	
    	pedido.setImporte(pedido.getImporte());
		entityManager.getTransaction().begin();
    	
    	entityManager.getTransaction().commit();
    	entityManager.close();   
    }
    
    public static void show() {
    	Pedido pedido = search();
    	System.out.printf("%3d %s %s %s %n", pedido.getId(), pedido.getFecha(),pedido.getCliente().getNombre(),pedido.getImporte().toString());
	    for (PedidoLinea linp:pedido.getPedidosLineas()) {
	    	System.out.println("        "+linp);
	    }//Si en persistence.xml se le dice que hibernate muestre los comandos el los mostrara
    	
    }
    
    public static void delete() {	    
    	EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	System.out.println("Introduce id del pedido a buscar");
		Long id=Long.parseLong(tcl.nextLine());
		Pedido pedido=entityManager.find(Pedido.class, id);
    	entityManager.getTransaction().begin();
    	entityManager.remove(pedido);
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
    
	private static Pedido search() {
		System.out.println("Introduce id de la pedido a buscar");
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
