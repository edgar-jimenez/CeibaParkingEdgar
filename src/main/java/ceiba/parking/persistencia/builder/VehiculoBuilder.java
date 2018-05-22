package ceiba.parking.persistencia.builder;

import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.persistencia.entity.VehiculoEntity;

public class VehiculoBuilder {
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo = null;
		if(vehiculoEntity != null) {
			vehiculo = new Vehiculo(
					vehiculoEntity.getId(),
					vehiculoEntity.getPlaca(),
					vehiculoEntity.getTipo(), 
					vehiculoEntity.getCilindraje());
		}
		return vehiculo;
	}
	
	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		vehiculoEntity.setTipo(vehiculo.getTipo());
		vehiculoEntity.setCilindraje(vehiculo.getCilindraje());
		return vehiculoEntity; 
	}
}
