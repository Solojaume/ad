package serpis.ad;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Paquete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_paquete;
	private Long precio;
	private Long cantidad;

	private Long id_cliente;
	
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_paquete() {
		return id_paquete;
	}
	public void setId_paquete(Long id_paquete) {
		this.id_paquete = id_paquete;
	}
	public Long getPrecio() {
		return precio;
	}
	public void setPrecio(Long precio) {
		this.precio = precio;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
