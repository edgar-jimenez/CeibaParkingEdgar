package ceiba.parking.dominio;

import java.util.Date;

public class Ingreso {
	
	private Long id;
	
	private Date fecha;
	
	private Vehiculo vehiculo;
	
	public Ingreso() {
	}

	public Ingreso(Date fecha, Vehiculo vehiculo) {
		this.fecha = fecha;
		this.vehiculo = vehiculo;
	}	
	
	public Ingreso(Long id, Date fecha, Vehiculo vehiculo) {
		this.id = id;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public Long getId() {
		return id;
	}
	
}
