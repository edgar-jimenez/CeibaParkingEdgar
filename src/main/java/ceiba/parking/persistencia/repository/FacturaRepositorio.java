package ceiba.parking.persistencia.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ceiba.parking.dominio.Factura;
import ceiba.parking.persistencia.builder.FacturaBuilder;
import ceiba.parking.persistencia.repository.jpa.FacturaRepositorioJPA;

@Repository
public class FacturaRepositorio {

	@Autowired
	private FacturaRepositorioJPA facturaRepositorioJPA;
	
	public List<Factura> consultarFacturas(){
		return FacturaBuilder.deFacturasEntityADominio(facturaRepositorioJPA.findAll());
	}
	
	public Factura registrarFactura(Factura factura) {
		return FacturaBuilder.deFacturaEntityADominio(facturaRepositorioJPA.save(FacturaBuilder.deFacturaAEntity(factura)));
	}
}
