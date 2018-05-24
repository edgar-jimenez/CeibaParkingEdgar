package ceiba.parking.unitaria;

import static org.junit.Assert.assertTrue;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.parking.ParkingApplication;
import ceiba.parking.persistencia.entity.VehiculoEntity;
import ceiba.parking.persistencia.repository.jpa.VehiculoRepositorioJPA;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ParkingApplication.class)
@DataJpaTest
public class VehiculoRepositorioTest {
	
	private static final String PLACA = "DAS223";
	private static final String TIPO = "carro";
	private static final int CILINDRAJE = 180;

	@Autowired
	private TestEntityManager  entityManager;
	
	@Autowired
	private VehiculoRepositorioJPA vehiculoRepositorioJPA;
	
	@Test
	private void findByplaca() {
		// arrange
		VehiculoEntity vehiculoEntity = new VehiculoEntity(PLACA, TIPO, CILINDRAJE);
		entityManager.persist(vehiculoEntity);
		entityManager.flush();
		
		// act
		VehiculoEntity vehiculoEntity1 = vehiculoRepositorioJPA.findByplaca(PLACA);
		boolean comparacion=(vehiculoEntity.equals(vehiculoEntity1));
		// assert
		assertTrue(comparacion);
	}
}
