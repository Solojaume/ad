package serpis.ad;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Clientes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	private String dni;
	private String nombre;
	private BigDecimal telefono;
	public Long getId_cliente() {
		return id_cliente;
	}
	
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public String getDni() {
		
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getTelefono() {
		return telefono;
	}
	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}
	
}