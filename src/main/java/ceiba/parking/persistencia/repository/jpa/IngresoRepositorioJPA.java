package ceiba.parking.persistencia.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import ceiba.parking.persistencia.entity.IngresoEntity;

public interface IngresoRepositorioJPA extends JpaRepository<IngresoEntity,Long>{

}
