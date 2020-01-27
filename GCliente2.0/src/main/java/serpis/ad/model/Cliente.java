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
	private Long id;
//	private String dni;
	private String nombre;
//	private BigDecimal telefono;
	public Long getId() {
		return id;
	}
	@OneToMany(cascade = CascadeType.ALL, targetEntity =  Pedido.class, orphanRemoval = true )

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}