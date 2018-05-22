package ceiba.parking.persistencia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="vehiculo")
public class VehiculoEntity {
	
	@Id
	@GeneratedValue
	@Column(name="id_vehiculo")
	private Long id;
	
	@Column(unique=true)
	private String placa;
		
	private String tipo;
	
	private int cilindraje;
	
	
	public VehiculoEntity() {
	}


	public VehiculoEntity(String placa, String tipo, int cilindraje) {
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getCilindraje() {
		return cilindraje;
	}


	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
}
