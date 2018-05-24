package ceiba.parking.persistencia.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceiba.parking.persistencia.entity.VehiculoEntity;

@Repository
public interface VehiculoRepositorioJPA extends JpaRepository<VehiculoEntity, Long>{

	VehiculoEntity findByplaca(String placa);

	int countBytipo(String tipo);
}
