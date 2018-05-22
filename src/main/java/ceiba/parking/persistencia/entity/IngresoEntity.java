package ceiba.parking.persistencia.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="ingreso")
public class IngresoEntity {

	@Id
	@GeneratedValue
	@Column(name="id_ingreso")
	private Long id;
	
	private Date fecha;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="ID_VEHICULO",referencedColumnName="id_vehiculo")
	private VehiculoEntity vehiculo;

	
	public IngresoEntity() {
	}


	public IngresoEntity(Date fecha, VehiculoEntity vehiculo) {
		this.fecha = fecha;
		this.vehiculo = vehiculo;
	}

	public IngresoEntity(Long id, Date fecha, VehiculoEntity vehiculo) {
		this.id = id;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}
	
}
