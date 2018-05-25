package ceiba.parking.integracion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import ceiba.parking.dominio.Ingreso;
import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.dominio.Vigilante;
import ceiba.parking.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VigilanteTestIntegracion {
	
	private TestRestTemplate restTemplate = new TestRestTemplate();
 
	@LocalServerPort
	private int localServerPort; 
	
	@Mock
	private Vigilante vigilante;
	
	@Test
	public void agregarIngresoTest() {

		Vehiculo vehiculo=new VehiculoTestDataBuilder().conPlaca("xxx").conTipo("Moto").conCilindraje(180).build();

		ResponseEntity<Ingreso> responseEntityIngreso= restTemplate.postForEntity("http://localhost:"+localServerPort+"/Vigilante/ingreso",vehiculo,Ingreso.class);
		
		assertEquals(HttpStatus.OK, responseEntityIngreso.getStatusCode());
	}

	@Test
	public void salidaPorPlacaTest() {

		Mockito.when(vigilante.salidaDeVehiculo(1L)).thenReturn(true);
		
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:"+localServerPort+"/Vigilante/salida/{id}",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                String.class,
                1);

        System.out.println("resultado :"+ result);
        assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	
	@Test
	public void listarIngresosTest() {
		ResponseEntity<Object[]> responseTest = restTemplate.getForEntity("http://localhost:"+localServerPort+"/Vigilante/listarIngresos",Object[].class);
		assertEquals(HttpStatus.OK, responseTest.getStatusCode());
		assertEquals(1,responseTest.getBody().length);
	}
	
	@Test
	public void listarFacturasTest() {
		ResponseEntity<Object[]> responseTest = restTemplate.getForEntity("http://localhost:"+localServerPort+"/Vigilante/listarFacturas",Object[].class);
		assertEquals(HttpStatus.OK, responseTest.getStatusCode());
	}
}
