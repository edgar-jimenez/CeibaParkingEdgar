package ceiba.parking.unitaria;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.testdatabuilder.VehiculoTestDataBuilder;


public class VehiculoTest {
	
	private static final String PLACA = "DAS223";
	private static final String TIPO = "carro";
	private static final int CILINDRAJE = 180;
	
	@Test
	public void crearVehiculoTest() {
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
				conPlaca(PLACA).
				conTipo(TIPO).
				conCilindraje(CILINDRAJE);
		// act
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();

		// assert
		assertTrue( (PLACA == vehiculo.getPlaca()) && 
						(TIPO==vehiculo.getTipo()) && 
							(CILINDRAJE==vehiculo.getCilindraje()) );
	}
}
