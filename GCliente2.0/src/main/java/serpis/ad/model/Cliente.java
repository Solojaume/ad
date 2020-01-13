package serpis.ad.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Cliente {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	private String dni;
	private String nombre;
	private BigDecimal telefono;
	public Long getIdCliente() {
		return id_cliente;
	}
	@OneToMany(cascade = CascadeType.ALL, targetEntity =  Paquete.class, orphanRemoval = true )

	public void setIdCliente(Long id_cliente) {
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