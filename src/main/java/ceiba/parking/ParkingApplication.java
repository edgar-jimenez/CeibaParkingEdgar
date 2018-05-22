package ceiba.parking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ceiba.parking.persistencia.entity.VehiculoEntity;
import ceiba.parking.persistencia.repository.jpa.VehiculoRepositorioJPA;

@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner setup(VehiculoRepositorioJPA vehiculoRepository) {
//		return args -> {
//			vehiculoRepository.save(new VehiculoEntity("CKT180","Carro",180));
//			vehiculoRepository.save(new VehiculoEntity("AFK123","Carro",180));
//			vehiculoRepository.save(new VehiculoEntity("WMK321","Moto",180));
//		};
//	}
}
