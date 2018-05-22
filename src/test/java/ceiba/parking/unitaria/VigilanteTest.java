package ceiba.parking.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.parking.dominio.Calendario;
import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.dominio.Vigilante;
import ceiba.parking.persistencia.repository.VehiculoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class VigilanteTest {
	
	private static final String PLACA_SIN_A = "CKT223";
	private static final String PLACA_CON_A = "AKT223";
	private static final String TIPO_CARRO = "Carro";
	private static final String TIPO_MOTO = "Moto";
	private static final String TIPO_NO_VALIDO = "XXXX";
	private static final int CILINDRAJE_MAYOR = 510;
	private static final int CILINDRAJE_MENOR = 180;
	public static final String VEHICULO_NO_AUROTIZADO = "placa inicia con A dia no autorizado";
	public static final String CUPO_NO_DISPONIBLE = "Cupos no disponibles";
	public static final String VEHICULO_NO_REGISTRADO = "Vehiculo no se ecuentra en el parqueadero";
	
	@InjectMocks
	private Vigilante vigilante;
	
	@Mock
	private Calendario diaActual;
	
	@Mock
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Mock
	private Vehiculo vehiculoMock;
	
	@Test
	public void esPlacaIniciaConATest() {
		assertTrue(vigilante.esPlacaIniciaConA(PLACA_CON_A));
	}
	
	@Test
	public void noEsPlacaIniciaConATest() {
		assertFalse(vigilante.esPlacaIniciaConA(PLACA_SIN_A));
	}
	
	@Test
	public void validarVehiculoTest() {
		// arrange
		Mockito.when(vehiculoRepositorio.consultarPorPlaca(PLACA_CON_A)).thenReturn(vehiculoMock);
		// act
		boolean resultado=vigilante.validarVehiculo(PLACA_CON_A);
		//assert
		assertTrue(resultado);
	}
	
	@Test
	public void noValidarVehiculoTest() {
		// arrange
		Mockito.when(vehiculoRepositorio.consultarPorPlaca(PLACA_CON_A)).thenReturn(null);
		// act
		boolean resultado=vigilante.validarVehiculo(PLACA_CON_A);
		//assert
		assertFalse(resultado);
	}
	
	@Test
	public void validarCupoCarroTest() {
		// arrange
		Mockito.when(vehiculoRepositorio.contarPorTipo(TIPO_CARRO)).thenReturn(15);
		Mockito.when(vehiculoMock.getTipo()).thenReturn(TIPO_CARRO);
		// act
		boolean resultado=vigilante.validarCupo(vehiculoMock);
		//assert
		assertTrue(resultado);
	}
	
	@Test
	public void noValidarCupoCarroTest() {
		// arrange
		Mockito.when(vehiculoMock.getTipo()).thenReturn(TIPO_CARRO);
		Mockito.when(vehiculoRepositorio.contarPorTipo(TIPO_CARRO)).thenReturn(20);
		// act
		boolean resultado=vigilante.validarCupo(vehiculoMock);
		//assert
		assertFalse(resultado);
	}
	
	@Test
	public void validarCupoMotoTest() {
		// arrange
		Mockito.when(vehiculoMock.getTipo()).thenReturn(TIPO_MOTO);
		Mockito.when(vehiculoRepositorio.contarPorTipo(TIPO_MOTO)).thenReturn(9);
		// act
		boolean resultado=vigilante.validarCupo(vehiculoMock);
		//assert
		assertTrue(resultado);
	}
	
	@Test
	public void noValidarCupoMotoTest() {
		// arrange
		Mockito.when(vehiculoMock.getTipo()).thenReturn(TIPO_MOTO);
		Mockito.when(vehiculoRepositorio.contarPorTipo(TIPO_MOTO)).thenReturn(10);
		// act
		boolean resultado=vigilante.validarCupo(vehiculoMock);
		//assert
		assertFalse(resultado);
	}
	
	@Test
	public void noValidarCupoTest() {
		// arrange
		Mockito.when(vehiculoMock.getTipo()).thenReturn(TIPO_NO_VALIDO);
		// act
		boolean resultado=vigilante.validarCupo(vehiculoMock);
		//assert
		assertFalse(resultado);
	}
	
	@Test
	public void motoCilindrajeMayorA500Test() {
		// arrange
		
		// act
		boolean resultado=vigilante.motoCilindrajeMayorA500(CILINDRAJE_MAYOR);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void motoCilindrajeMenorA500Test() {
		// arrange
		
		// act
		boolean resultado=vigilante.motoCilindrajeMayorA500(CILINDRAJE_MENOR);
		// assert
		assertFalse(resultado);
	}
	
	@Test
	public void entradaValidaPlacaATest() {
		// arrange
		Mockito.when(diaActual.validarFecha()).thenReturn(true);
		// act
		boolean resultado=vigilante.entradaValida(PLACA_CON_A);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void entradaValidaSinPlacaATest() {
		// arrange
		// act
		boolean resultado=vigilante.entradaValida(PLACA_SIN_A);
		// assert
		assertTrue(resultado);
	}
}
