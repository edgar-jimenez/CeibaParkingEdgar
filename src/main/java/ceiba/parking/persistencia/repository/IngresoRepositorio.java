package ceiba.parking.persistencia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ceiba.parking.dominio.Ingreso;
import ceiba.parking.persistencia.builder.IngresoBuilder;
import ceiba.parking.persistencia.entity.IngresoEntity;
import ceiba.parking.persistencia.repository.jpa.IngresoRepositorioJPA;


@Repository
public class IngresoRepositorio {
	
	@Autowired
	private IngresoRepositorioJPA ingresoRepositorioJPA;
	
	public List<Ingreso> consultarIngresos(){
		return IngresoBuilder.deIngresosEntityADominio(ingresoRepositorioJPA.findAll());
	}
	
	public Ingreso registrarIngreso(Ingreso ingreso) {
		return IngresoBuilder.deIngresoEntityADominio(
				ingresoRepositorioJPA.save(IngresoBuilder.deIngresoAEntity(ingreso)));
	}
	
	public void eliminarIngreso(Long id) {
		ingresoRepositorioJPA.deleteById(id);
	}
	
	public Ingreso buscarPorId(Long id) {
		Optional<IngresoEntity> ingresoEntity =ingresoRepositorioJPA.findById(id);
		if (ingresoEntity.isPresent()) {
			return IngresoBuilder.deIngresoEntityADominio(ingresoEntity.get());
		}else return null;
	}
	

}
