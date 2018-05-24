package ceiba.parking.integracion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import ceiba.parking.dominio.Ingreso;
import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VigilanteTestIntegracion {

	private TestRestTemplate restTemplate = new TestRestTemplate();
 
	@LocalServerPort
	private int localServerPort; 
	
	@Test
	public void retrieveDetailsForCourse() {

		Vehiculo vehiculo=new VehiculoTestDataBuilder().conPlaca("xxx").conTipo("Moto").conCilindraje(180).build();

		ResponseEntity<Ingreso> responseEntityIngreso= restTemplate.postForEntity("http://localhost:"+localServerPort+"/Vigilante/ingreso",vehiculo,Ingreso.class);
		
		assertEquals(HttpStatus.OK, responseEntityIngreso.getStatusCode());
	}

}
