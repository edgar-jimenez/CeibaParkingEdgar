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

import ceiba.parking.dominio.Ingreso;
import ceiba.parking.dominio.Vehiculo;
import ceiba.parking.dominio.Vigilante;
import ceiba.parking.persistencia.repository.IngresoRepositorio;
import ceiba.parking.persistencia.repository.VehiculoRepositorio;

@RestController
public class VigilanteController {
	
	@Autowired
	private Vigilante vigilante;
		
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private IngresoRepositorio ingresoRepositorio;
	
	@RequestMapping(value = "/Vigilante/ingreso", method = RequestMethod.POST)
   	public ResponseEntity<Ingreso> agregarIngreso(@RequestBody Vehiculo vehiculo) {
		return new ResponseEntity<>(vigilante.ingresoDeVehiculo(vehiculo), HttpStatus.OK);
   	}
	
	@RequestMapping(value="/Vigilante/listar", method=RequestMethod.GET)
	public ResponseEntity<List<Ingreso>> listarIngresos(){
		return new ResponseEntity<>(vigilante.listaIngresos(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Vigilante/salida/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> salidaPorPlaca(@PathVariable("id") Long id){		
    	vigilante.salidaDeVehiculo(id);
		return new ResponseEntity<>("Vehiculo eliminado", HttpStatus.OK);
	}
	
	
//	@RequestMapping(value = "/vehiculo/{placa}", method = RequestMethod.GET)
//	public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable("placa") String placa) {
//		return new ResponseEntity<>(vehiculoRepositorio.consultarPorPlaca(placa), HttpStatus.OK);
//	}
//    
//    @RequestMapping(value = "/vehiculo", method = RequestMethod.POST)
//   	public ResponseEntity<VehiculoEntity> agregarVehiculo(@RequestBody VehiculoEntity vehiculo) {
//		return new ResponseEntity<>(vehiculoRepositorio.registro(vehiculo), HttpStatus.OK);
//   	}
//  
//    
//    @RequestMapping(value = "/vehiculotipo/{tipo}", method = RequestMethod.GET)
//	public ResponseEntity<Integer> contarPorTipo(@PathVariable("tipo") String tipo){
//		return new ResponseEntity<>(vehiculoRepositorio.contarPorTipo(tipo),HttpStatus.OK);
//	}
}
