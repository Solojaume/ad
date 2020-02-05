package serpis.ad.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.persistence.EntityManager;

import serpis.ad.model.Articulo;
import serpis.ad.model.Categoria;
import serpis.ad.model.Pedido;
import serpis.ad.model.PedidoLinea;

public class PedidoLineaHelper {
	private static Scanner tcl= new Scanner(System.in);


	public static void crear(Pedido pedido) {
		EntityManager entityManager = ContainerEntitityManager.entityManagerFactory.createEntityManager();
    	PedidoLinea pedidoLinea = new PedidoLinea(pedido);
    	System.out.println("Introduce el id del articulo:");
    	Long id=Long.parseLong(tcl.nextLine());
    	Articulo articulo =  entityManager.find(Articulo.class, id);
    	pedidoLinea.setArticulo(articulo);
	    System.out.println("Introduce la unidades del producto:");
	    pedidoLinea.setUnidades(new BigDecimal(tcl.nextLine()));
    	pedidoLinea.setPrecio(articulo.getPrecio());
    	pedidoLinea.setImporte(pedidoLinea.getPrecio().multiply(pedidoLinea.getUnidades()));   	
    	entityManager.close();
		
	}

}
