package ceiba.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
