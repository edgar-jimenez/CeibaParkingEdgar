package ceiba.parking.dominio.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ceiba.parking.dominio.Factura;
import ceiba.parking.dominio.Ingreso;
import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.dominio.Vigilante;

@RestController
public class VigilanteController {
	
	@Autowired
	private Vigilante vigilante;
	
	@RequestMapping(value = "/Vigilante/ingreso", method = RequestMethod.POST)
   	public ResponseEntity<Ingreso> agregarIngreso(@RequestBody Vehiculo vehiculo) {
		return new ResponseEntity<>(vigilante.ingresoDeVehiculo(vehiculo), HttpStatus.OK);
   	}
	
	@RequestMapping(value = "/Vigilante/salida/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> salidaPorPlaca(@PathVariable("id") Long id){
    	vigilante.salidaDeVehiculo(id);
		return new ResponseEntity<>("Vehiculo eliminado", HttpStatus.OK);
	}
	
	@RequestMapping(value="/Vigilante/listarIngresos", method=RequestMethod.GET)
	public ResponseEntity<List<Ingreso>> listarIngresos(){
		return new ResponseEntity<>(vigilante.listaIngresos(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/Vigilante/listarFacturas", method=RequestMethod.GET)
	public ResponseEntity<List<Factura>> listarFacturas(){
		return new ResponseEntity<>(vigilante.listaFacturas(), HttpStatus.OK);
	}
	
}
