package serpis.ad.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoLinea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "pedido",
	foreignKey = @ForeignKey(name = "pedidolinea_ibfk_1")
	)
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name = "articulo",
	foreignKey = @ForeignKey(name = "pedidolinea_ibfk_2")
	)
	private Articulo articulo;
	
	private BigDecimal unidades;
	private BigDecimal importe;
	private BigDecimal precio;
	
	public PedidoLinea(Pedido pedido) {
		this.pedido = pedido;
		if(pedido.getPedidosLineas() == null)
			pedido.setPedidosLineas(new ArrayList<PedidoLinea>());
		pedido.getPedidosLineas().add(this);
	}
	
	private PedidoLinea() {
		
	}
	@Override
	public String toString() {
		return "PedidoLinea [id=" + id  + ", articulo=" + articulo.getNombre() + ", unidades=" + unidades
				+ ", precio=" + precio + ", importe=" + importe  + "]";
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Pedido getIdPedido() {
		return pedido;
	}
	
	public void setIdPedido(Pedido idPedido) {
		this.pedido = idPedido;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public BigDecimal getUnidades() {
		return unidades;
	}
	
	public void setUnidades(BigDecimal unidades) {
		this.unidades = unidades;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	
}
