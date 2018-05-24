package ceiba.parking.persistencia.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceiba.parking.persistencia.entity.FacturaEntity;

@Repository
public interface FacturaRepositorioJPA extends JpaRepository<FacturaEntity,Long>{

}
