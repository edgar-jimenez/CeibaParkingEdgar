package ceiba.parking.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.parking.ParkingApplication;
import ceiba.parking.persistencia.entity.VehiculoEntity;
import ceiba.parking.persistencia.repository.jpa.VehiculoRepositorioJPA;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParkingApplication.class)
@DataJpaTest
public class VehiculoRepositorioTest {
	
	private static final String PLACA1 = "DAS223";
	private static final String PLACA2 = "DAx223";
	private static final String TIPO_CARRO = "Carro";
	private static final String TIPO_MOTO = "Moto";
	private static final int CILINDRAJE = 180;

	@Autowired
	private TestEntityManager  entityManager;
	
	@Autowired
	private VehiculoRepositorioJPA vehiculoRepositorioJPA;
	
	@Test
	public void findByplacaTest() {
		// arrange
		VehiculoEntity vehiculoEntity = new VehiculoEntity(PLACA1, TIPO_CARRO, CILINDRAJE);
		entityManager.persist(vehiculoEntity);
		entityManager.flush();
		
		// act
		VehiculoEntity vehiculoEntity1 = vehiculoRepositorioJPA.findByplaca(PLACA1);
		boolean comparacion=(vehiculoEntity.equals(vehiculoEntity1));
		// assert
		assertTrue(comparacion);
	}
	
	@Test
	public void countBytipoCarroTest() {
		// arrange
		VehiculoEntity vehiculoEntity1 = new VehiculoEntity(PLACA1, TIPO_CARRO, CILINDRAJE);
		VehiculoEntity vehiculoEntity2 = new VehiculoEntity(PLACA2, TIPO_CARRO, CILINDRAJE);
		entityManager.persist(vehiculoEntity1);

		entityManager.persist(vehiculoEntity2);
		entityManager.flush();
		
		// act
		int numeroDeVehiculos = vehiculoRepositorioJPA.countBytipo(TIPO_CARRO);
		// assert
		assertEquals(2, numeroDeVehiculos);
	}
	
	@Test
	public void countBytipoMotoTest() {
		// arrange
		VehiculoEntity vehiculoEntity1 = new VehiculoEntity(PLACA1, TIPO_MOTO, CILINDRAJE);
		VehiculoEntity vehiculoEntity2 = new VehiculoEntity(PLACA2, TIPO_CARRO, CILINDRAJE);
		entityManager.persist(vehiculoEntity1);

		entityManager.persist(vehiculoEntity2);
		entityManager.flush();
		
		// act
		int numeroDeVehiculos = vehiculoRepositorioJPA.countBytipo(TIPO_CARRO);
		// assert
		assertEquals(1,numeroDeVehiculos);
	}
}
