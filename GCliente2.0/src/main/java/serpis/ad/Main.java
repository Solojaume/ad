package serpis.ad;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import serpis.ad.dao.ArticuloHelper;
import serpis.ad.dao.CategoriaHelper;
import serpis.ad.dao.ClienteHelper;
import serpis.ad.dao.ContainerEntitityManager;
import serpis.ad.dao.PedidoHelper;

public class Main {
	
	public static void main(String[] args) {
		Scanner tcl= new Scanner(System.in);
		int o,bool=1;
		while(bool==1) {
			System.out.println("Selecciona la tabla:");
			System.out.println("1-Cliente");
			System.out.println("2-Categoria");
			System.out.println("3-Articulo");
			System.out.println("4-Pedido");
//			System.out.println("5-Linea Pedido");
			System.out.println("5-Salir");
			
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
					ClienteHelper.insert();
					break;
				case 2:
					ClienteHelper.showAll();
					break;
				case 3:
					ClienteHelper.edit();
					break;
				case 4:
					ClienteHelper.show();
					break;
				case 5:
					ClienteHelper.delete();
				default:
					break;
				}
			}
			else if(o==2) {
				System.out.println("Selecciona la opción");
				System.out.println("1-Insertar");
				System.out.println("2-Ver todos");
				System.out.println("3-Editar");
				System.out.println("4-Ver uno");
//				System.out.println("5-Eliminar");

				o=Integer.parseInt(tcl.nextLine());
			  switch (o) {
				case 1:
					CategoriaHelper.insert();
					break;
				case 2:
					CategoriaHelper.showAll();
					break;
				case 3:
					CategoriaHelper.edit();
					break;
				case 4:
					CategoriaHelper.show();
					break;
				case 5:
					CategoriaHelper.delete();
				default:
					break;
			  }
			}
			else if(o==3) {
				System.out.println("Selecciona la opción");
				System.out.println("1-Insertar");
				System.out.println("2-Ver todos");
				System.out.println("3-Editar");
				System.out.println("4-Ver uno");
				System.out.println("5-Eliminar");

				o=Integer.parseInt(tcl.nextLine());
			  switch (o) {
				case 1:
					ArticuloHelper.insert();
					break;
				case 2:
					ArticuloHelper.showAll();
					break;
				case 3:
					ArticuloHelper.edit();
					break;
				case 4:
					ArticuloHelper.show();
					break;
				case 5:
					ArticuloHelper.delete();
				default:
					break;
			  }
			}
			else if(o==4) {
				System.out.println("Selecciona la opción");
				System.out.println("1-Insertar");
				System.out.println("2-Ver todos");
				System.out.println("3-Editar");
				System.out.println("4-Ver uno");
				System.out.println("5-Eliminar");

				o=Integer.parseInt(tcl.nextLine());
			  switch (o) {
				case 1:
					PedidoHelper.insert();
					break;
				case 2:
					PedidoHelper.showAll();
					break;
				case 3:
					PedidoHelper.edit();
					break;
				case 4:
					PedidoHelper.show();
					break;
				case 5:
					PedidoHelper.delete();
					break;
					
				default:
					break;
			  }
			}
			else {
				bool=0;
				System.exit(0);
			}
		}
		
				
		ContainerEntitityManager.close();
		

	}
}
	
