package ceiba.parking.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ceiba.parking.dominio.Calendario;

public class CalendarioTest {
	
	
	private static final int ANNO = 2018;
	private static final int MES = 4;
	private static final int DIA_DOMINGO = 20;
	private static final int DIA_LUNES = 21;
	private static final int DIA_MARTES = 23;
	
	@InjectMocks
	private Calendario calendario;
	
	@Before
	public void setup() {
		 MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void validarFechaDomingoTest() {		
		// arrange
		calendario.setFecha(ANNO,MES,DIA_DOMINGO);
		// act
		boolean resultado = calendario.validarFecha();
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void validarFechaLunesTest() {		
		// arrange
		calendario.setFecha(ANNO,MES,DIA_LUNES);
		// act
		boolean resultado = calendario.validarFecha();
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void validarFechaMartesTest() {		
		// arrange
		calendario.setFecha(ANNO,MES,DIA_MARTES);
		// act
		boolean resultado = calendario.validarFecha();
		// assert
		assertFalse(resultado);
	}
	
	@Test
	public void getFechaTest() {		
		// arrange
		calendario.setFecha(ANNO,MES,DIA_MARTES);
		Calendar cal=Calendar.getInstance();
		cal.set(ANNO,MES,DIA_MARTES);
		// act
		boolean resultado = (cal.getTime().equals(calendario.getFecha()));
		// assert
		assertTrue(resultado);
	}
}
