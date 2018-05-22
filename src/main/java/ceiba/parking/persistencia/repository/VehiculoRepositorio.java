package ceiba.parking.persistencia.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.persistencia.builder.VehiculoBuilder;
import ceiba.parking.persistencia.entity.VehiculoEntity;
import ceiba.parking.persistencia.repository.jpa.VehiculoRepositorioJPA;

@Repository
public class VehiculoRepositorio {
	@Autowired
	private VehiculoRepositorioJPA vehiculoRepositorioJPA;
	
	public List<VehiculoEntity> consultarVehiculos(){
		return  vehiculoRepositorioJPA.findAll();
	}
	
	public Vehiculo consultarPorPlaca(String placa) {
			return VehiculoBuilder.convertirADominio(vehiculoRepositorioJPA.findByplaca(placa));
	}
	
	public VehiculoEntity registro(VehiculoEntity vehiculo) {
		return vehiculoRepositorioJPA.save(vehiculo);
	}
	
	public void eliminarVehiculo(Vehiculo vehiculo) {
		vehiculoRepositorioJPA.delete(VehiculoBuilder.convertirAEntity(vehiculo));
	}
	
	
	public int contarPorTipo(String tipo) {
		return vehiculoRepositorioJPA.countBytipo(tipo);
	}

}
