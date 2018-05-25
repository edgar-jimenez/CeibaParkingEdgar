package ceiba.parking.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

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
import ceiba.parking.persistencia.repository.IngresoRepositorio;
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
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	
	@InjectMocks
	private Vigilante vigilante;
	
	@Mock
	private Calendario diaActual;
	
	@Mock
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Mock
	private IngresoRepositorio ingresoRepositorio;
	
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
	
	@Test
	public void cobroMotoCilindrajeMayorTest1() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(0, 10,0, CILINDRAJE_MAYOR) == 6000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroMotoCilindrajeMayorTest2() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(0, 8,0, CILINDRAJE_MAYOR) == 6000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroMotoCilindrajeMayorTest3() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(1, 3,0, CILINDRAJE_MAYOR) == 7500);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroMotoCilindrajeMayorTest4() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(1, 3,15, CILINDRAJE_MAYOR) == 8000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroMotoCilindrajeMenorTest1() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(0, 10,0, CILINDRAJE_MENOR) == 4000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroMotoCilindrajeMenorTest2() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(0, 8,0, CILINDRAJE_MENOR) == 4000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroMotoCilindrajeMenorTest3() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(1, 3,0, CILINDRAJE_MENOR) == 5500);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroMotoCilindrajeMenorTest4() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroMoto(1, 3,20, CILINDRAJE_MENOR) == 6000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroCarroTest1() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroCarro(0, 10,0) == 8000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroCarroTest2() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroCarro(0, 8,0) == 8000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroCarroTest3() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroCarro(1, 3,0) == 11000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void cobroCarroTest4() {
		// arrange
		// act
		boolean resultado=(vigilante.cobroCarro(1, 3,20) == 12000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void calcularCobroCarroTest() {
		// arrange
		Mockito.when(vehiculoMock.getTipo()).thenReturn("Carro");
		// act
		boolean resultado=(vigilante.calcularCobro(1, 3,0, vehiculoMock) == 11000);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void calcularCobroMotoCilindrajeMenorTest() {
		// arrange
		Mockito.when(vehiculoMock.getTipo()).thenReturn("Moto");
		Mockito.when(vehiculoMock.getCilindraje()).thenReturn(CILINDRAJE_MENOR);
		// act
		boolean resultado=(vigilante.calcularCobro(1, 3,0, vehiculoMock) == 5500);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void calcularCobroMotoCilindrajeMayorTest() {
		// arrange
		Mockito.when(vehiculoMock.getTipo()).thenReturn("Moto");
		Mockito.when(vehiculoMock.getCilindraje()).thenReturn(CILINDRAJE_MAYOR);
		// act
		boolean resultado=(vigilante.calcularCobro(1, 3,0, vehiculoMock) == 7500);
		// assert
		assertTrue(resultado);
	}
	
}
