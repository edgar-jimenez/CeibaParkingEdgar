package ceiba.parking.testdatabuilder;

import java.util.Date;

import ceiba.parking.dominio.Ingreso;
import ceiba.parking.dominio.Vehiculo;

public class IngresoTestDataBuilder {
	
	private Date fecha;
	private Vehiculo vehiculo;
	
	public IngresoTestDataBuilder(Vehiculo vehiculo) {
		this.fecha = new Date();;
		this.vehiculo = vehiculo;
	}
	
	public IngresoTestDataBuilder conFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}
	
	public Ingreso build() {
		return new Ingreso(this.fecha,this.vehiculo);
	}
}
