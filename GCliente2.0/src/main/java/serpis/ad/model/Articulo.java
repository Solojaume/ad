package serpis.ad.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Articulo {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private BigDecimal precio;
	@ManyToOne
	@JoinColumn(name = "articulo_id",
	foreignKey = @ForeignKey(name = "pedidolinea_ibfk_2"))
	private Categoria categoria;
	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
