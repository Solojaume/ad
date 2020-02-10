package serpis.ad.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;


import javax.persistence.*;
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="`fecha`")
	private LocalDateTime fecha;
	@ManyToOne
	@JoinColumn(name = "cliente",
			foreignKey = @ForeignKey(name = "pedido_ibfk_1")
	)
	private Cliente cliente;
	
	private BigDecimal importe;
	@OneToMany(mappedBy ="pedido",cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private List<PedidoLinea> pedidoLinea = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public List<PedidoLinea> getPedidosLineas() {
		return pedidoLinea;
	}
	
	public void setPedidosLineas(List<PedidoLinea> pedidoLinea) {
		this.pedidoLinea = pedidoLinea;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	private void preGetImporte() {
		importe = BigDecimal.ZERO;
		for (PedidoLinea pedidoLinea : pedidoLinea) {
			importe = importe.add(pedidoLinea.getImporte());
		}
	}
	
	public BigDecimal getImporte() {
		preGetImporte();
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}
