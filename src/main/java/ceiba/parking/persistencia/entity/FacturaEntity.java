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

	public FacturaEntity(Long id, String entrada, String salida, String tiempo, String valor, String vehiculoPlaca,
			String vehiculoTipo) {
		this.id = id;
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

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}

	public void setVehiculoPlaca(String vehiculoPlaca) {
		this.vehiculoPlaca = vehiculoPlaca;
	}

	public String getVehiculoTipo() {
		return vehiculoTipo;
	}

	public void setVehiculoTipo(String vehiculoTipo) {
		this.vehiculoTipo = vehiculoTipo;
	}
}
