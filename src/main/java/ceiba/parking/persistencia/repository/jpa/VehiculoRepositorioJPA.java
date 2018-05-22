package ceiba.parking.persistencia.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import ceiba.parking.persistencia.entity.VehiculoEntity;

public interface VehiculoRepositorioJPA extends JpaRepository<VehiculoEntity, Long>{

	VehiculoEntity findByplaca(String placa);

	int countBytipo(String tipo);
}
