package ceiba.parking.dominio;

import java.util.Date;

public class Ingreso {
		
	private Date fecha;
	
	private Vehiculo vehiculo;

	public Ingreso(Date fecha, Vehiculo vehiculo) {
		this.fecha = fecha;
		this.vehiculo = vehiculo;
	}	

	public Date getFecha() {
		return fecha;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
}
