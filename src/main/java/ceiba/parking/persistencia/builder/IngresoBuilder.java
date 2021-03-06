package ceiba.parking.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import ceiba.parking.dominio.Ingreso;
import ceiba.parking.persistencia.entity.IngresoEntity;

public class IngresoBuilder {
	
	private IngresoBuilder() {
		
	}
	
	public static Ingreso deIngresoEntityADominio(IngresoEntity ingresoEntity) {
		Ingreso ingreso = null;
		if(ingresoEntity != null) {
			ingreso = new Ingreso(
					ingresoEntity.getId(),
					ingresoEntity.getFecha(),
					VehiculoBuilder.convertirADominio(ingresoEntity.getVehiculo()));
		}
		return ingreso;
	}
	
	public static IngresoEntity deIngresoAEntity(Ingreso ingreso) {
		return new IngresoEntity(
				ingreso.getFecha(),
				VehiculoBuilder.convertirAEntity(ingreso.getVehiculo()));
	}
	
	public static List<Ingreso> deIngresosEntityADominio(List<IngresoEntity> listIgresos){
		List<Ingreso> ingresos=new ArrayList<>();
		for (IngresoEntity ingresoEntity : listIgresos) {
			ingresos.add(deIngresoEntityADominio(ingresoEntity));
		}
		return ingresos;
	}
}
