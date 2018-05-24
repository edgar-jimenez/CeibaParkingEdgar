package ceiba.parking.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:m:s a");
	
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
	
	@SuppressWarnings("deprecation")
	@Test
	public void getFechaTest() {		
		// arrange
		calendario.setFecha(ANNO,MES,DIA_MARTES);
		Calendar cal=Calendar.getInstance();
		cal.set(ANNO,MES,DIA_MARTES);
		// act
		// assert
		assertEquals(cal.getTime().getMinutes(),calendario.getFecha().getMinutes());
	}
	
	@Test
	public void calcularTiempoTest1() {		
		// arrange		 
        Date fechaInicial=null;
        Date fechaFinal=null;
		try {
			fechaInicial = dateFormat.parse("2018-04-22 1:00:00 AM");
			fechaFinal = dateFormat.parse("2018-04-23 7:50:00 AM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		calendario.calcularTiempo(fechaInicial, fechaFinal);
		// act
		boolean resultado = (calendario.getDiaDeParqueo() == 1 && 
				calendario.getHoraDeParqueo() == 6 &&
				calendario.getMinutosDeParqueo() == 50);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void calcularTiempoTest2() {		
		// arrange		 
        Date fechaInicial=null;
        Date fechaFinal=null;
		try {
			fechaInicial = dateFormat.parse("2018-04-22 12:00:00 PM");
			fechaFinal = dateFormat.parse("2018-04-25 7:50:00 PM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		calendario.calcularTiempo(fechaInicial, fechaFinal);
		// act
		boolean resultado = (calendario.getDiaDeParqueo() == 3 && 
				calendario.getHoraDeParqueo() == 7 &&
				calendario.getMinutosDeParqueo() == 50);
		// assert
		assertTrue(resultado);
	}
	
	@Test
	public void calcularTiempoTest3() {		
		// arrange		 
        Date fechaInicial=null;
        Date fechaFinal=null;
		try {
			fechaInicial = dateFormat.parse("2018-04-22 8:00:00 AM");
			fechaFinal = dateFormat.parse("2018-04-22 7:15:00 PM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		calendario.calcularTiempo(fechaInicial, fechaFinal);
		// act
		boolean resultado = (calendario.getDiaDeParqueo() == 0 && 
				calendario.getHoraDeParqueo() == 11 &&
				calendario.getMinutosDeParqueo() == 15);
		// assert
		assertTrue(resultado);
	}
}
