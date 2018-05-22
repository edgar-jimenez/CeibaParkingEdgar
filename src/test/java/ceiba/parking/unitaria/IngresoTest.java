package ceiba.parking.unitaria;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.mockito.Mock;

import ceiba.parking.dominio.Calendario;
import ceiba.parking.dominio.Ingreso;
import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.testdatabuilder.IngresoTestDataBuilder;
import ceiba.parking.testdatabuilder.VehiculoTestDataBuilder;

public class IngresoTest {
	
	@Mock
	Calendario fechadia;
	
	@Mock
	Vehiculo vehiculo;
	
	@Test
	public void crearVehiculoTest() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, 15, 8);
		Date fecha=calendar.getTime();
		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder(vehiculo).conFecha(fecha);
		Ingreso ingreso = ingresoTestDataBuilder.build();

		// act
		
		// assert
		assertTrue( fecha == ingreso.getFecha() && vehiculo==ingreso.getVehiculo() );
	}
}
