package serpis.ad;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import serpis.ad.dao.ClienteDao;
import serpis.ad.dao.ContainerEntitityManager;
import serpis.ad.dao.PaqueteDao;

public class Main {
	
	public static void main(String[] args) {
		Scanner tcl= new Scanner(System.in);
		int o,bool=1;
		while(bool==1) {
			System.out.println("Selecciona la tabla:");
			System.out.println("1-Cliente");
			System.out.println("2-Paquete");
			System.out.println("3-Salir");
			
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
					PaqueteDao.insert();
					break;
				case 2:
					PaqueteDao.showAll();
					break;
				case 3:
					PaqueteDao.edit();
					break;
				case 4:
					PaqueteDao.show();
					break;
				case 5:
					PaqueteDao.delete();
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
	
