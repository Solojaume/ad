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
	@GeneratedValue
	private Long id;
	
//	@Column(name="`fecha`")
	private LocalDateTime fecha;
	@ManyToOne
	@JoinColumn(name = "client_id",
			foreignKey = @ForeignKey(name = "pedido_ibfk_1")
	)
	private Cliente cliente;
	
	private BigDecimal importe;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoLinea> pl = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}
