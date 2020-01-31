package serpis.ad;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import serpis.ad.dao.ArticuloDao;
import serpis.ad.dao.CategoriaDao;
import serpis.ad.dao.ClienteDao;
import serpis.ad.dao.ContainerEntitityManager;
import serpis.ad.dao.PedidoDao;

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
			System.out.println("5-Linea Pedido");
			System.out.println("6-Salir");
			
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
					ClienteDao.insert();
					break;
				case 2:
					ClienteDao.showAll();
					break;
				case 3:
					ClienteDao.edit();
					break;
				case 4:
					ClienteDao.show();
					break;
				case 5:
					ClienteDao.delete();
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
					ArticuloDao.insert();
					break;
				case 2:
					ArticuloDao.showAll();
					break;
				case 3:
					ArticuloDao.edit();
					break;
				case 4:
					ArticuloDao.show();
					break;
				case 5:
					ArticuloDao.delete();
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
					PedidoDao.insert();
					break;
				case 2:
					PedidoDao.showAll();
					break;
				case 3:
					PedidoDao.edit();
					break;
				case 4:
					PedidoDao.show();
					break;
				case 5:
					PedidoDao.delete();
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
	
