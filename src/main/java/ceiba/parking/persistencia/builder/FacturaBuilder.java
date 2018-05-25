package ceiba.parking.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import ceiba.parking.dominio.Factura;
import ceiba.parking.persistencia.entity.FacturaEntity;

public class FacturaBuilder{
	
	private FacturaBuilder() {
		
	}
	
	public static Factura deFacturaEntityADominio(FacturaEntity facturaEntity) {
		Factura factura = null;
		if(facturaEntity != null) {
			factura = new Factura(
					facturaEntity.getId(),
					facturaEntity.getEntrada(),
					facturaEntity.getSalida(),
					facturaEntity.getTiempo(),
					facturaEntity.getValor(),
					facturaEntity.getVehiculoPlaca(),
					facturaEntity.getVehiculoTipo());
		}
		return factura;
	}
	
	public static FacturaEntity deFacturaAEntity(Factura factura) {
		return new FacturaEntity(
				factura.getEntrada(),
				factura.getSalida(),
				factura.getTiempo(),
				factura.getValor(),
				factura.getVehiculoPlaca(),
				factura.getVehiculoTipo());
	}
	
	public static List<Factura> deFacturasEntityADominio(List<FacturaEntity> listFacturas){
		List<Factura> factura=new ArrayList<>();
		for (FacturaEntity facturaEntity : listFacturas) {
			factura.add(deFacturaEntityADominio(facturaEntity));
		}
		return factura;
	}
}
