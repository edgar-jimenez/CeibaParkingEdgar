package ceiba.parking.persistencia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="factura")
public class FacturaEntity {

	@Id
	@GeneratedValue
	@Column(name="id_factura")
	private Long id;
	
	private String entrada;
	
	private String salida;
	
	private String tiempo;
	
	private String valor;
	
	private String vehiculoPlaca;
	
	private String vehiculoTipo;

	public FacturaEntity() {
	}

	public FacturaEntity(String entrada, String salida, String tiempo, String valor, String vehiculoPlaca,
			String vehiculoTipo) {
		this.entrada = entrada;
		this.salida = salida;
		this.tiempo = tiempo;
		this.valor = valor;
		this.vehiculoPlaca = vehiculoPlaca;
		this.vehiculoTipo = vehiculoTipo;
	}

	public Long getId() {
		return id;
	}
	
	public String getEntrada() {
		return entrada;
	}

	public String getSalida() {
		return salida;
	}

	public String getTiempo() {
		return tiempo;
	}

	public String getValor() {
		return valor;
	}

	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}

	public String getVehiculoTipo() {
		return vehiculoTipo;
	}

}
