package ceiba.parking.persistencia.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import ceiba.parking.persistencia.entity.FacturaEntity;

public interface FacturaRepositorioJPA extends JpaRepository<FacturaEntity,Long>{

}
