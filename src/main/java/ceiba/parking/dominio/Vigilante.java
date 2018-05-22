package ceiba.parking.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceiba.parking.dominio.exception.IngresoException;
import ceiba.parking.persistencia.repository.IngresoRepositorio;
import ceiba.parking.persistencia.repository.VehiculoRepositorio;

@Service
public class Vigilante {

	public static final String VEHICULO_NO_AUROTIZADO = "placa inicia con A dia no autorizado";
	public static final String CUPO_NO_DISPONIBLE = "Cupos no disponibles";
	public static final String VEHICULO_NO_REGISTRADO = "Vehiculo no se ecuentra en el parqueadero";
	public static final int CARROS_TOPE=20;
	public static final int MOTOS_TOPE=10;
	
	private Calendario diaActual;
	
	private VehiculoRepositorio vehiculoRepositorio;
	
	private IngresoRepositorio ingresoRepositorio;

	@Autowired
	public Vigilante(Calendario diaActual, VehiculoRepositorio vehiculoRepositorio,
			IngresoRepositorio ingresoRepositorio) {
		this.diaActual = diaActual;
		this.vehiculoRepositorio = vehiculoRepositorio;
		this.ingresoRepositorio = ingresoRepositorio;
	}
	
	public Ingreso ingresoDeVehiculo(Vehiculo vehiculo) {
		if (validarCupo(vehiculo)) {
			if (entradaValida(vehiculo.getPlaca())) {
				Ingreso ingreso = new Ingreso(diaActual.getFecha(),vehiculo);
				return ingresoRepositorio.registrarIngreso(ingreso);
			}else throw new IngresoException(VEHICULO_NO_AUROTIZADO);
		}else throw new IngresoException(CUPO_NO_DISPONIBLE);		
	}
	
	public void salidaDeVehiculo(Long id) {
		Ingreso ingreso = ingresoRepositorio.buscarPorId(id);
		if (validarVehiculo(ingreso.getVehiculo().getPlaca())) {
			ingresoRepositorio.eliminarIngreso(id);			
		}else throw new IngresoException(VEHICULO_NO_REGISTRADO);
	}
	
	public boolean validarVehiculo(String placa) {
		return (vehiculoRepositorio.consultarPorPlaca(placa)!=null);
	}
	
	public boolean entradaValida(String placa) {
		if(!esPlacaIniciaConA(placa)) {
			return true;
		}else {
			return (diaActual.validarFecha());
		}
	}
	
	public boolean esPlacaIniciaConA(String placa) {
		return ( placa.charAt(0)=='A' );
	}
	
	public boolean validarCupo(Vehiculo vehiculo) {
		if (vehiculo.getTipo().equals("Carro")) {
			return (vehiculoRepositorio.contarPorTipo(vehiculo.getTipo()) < 20);
		}
		if (vehiculo.getTipo().equals("Moto")) {
			return (vehiculoRepositorio.contarPorTipo(vehiculo.getTipo()) < 10);
		}else return false;
	}
	
	public List<Ingreso> listaIngresos(){
		return ingresoRepositorio.consultarIngresos();
	}

	public boolean motoCilindrajeMayorA500(int cilindraje) {
		return (cilindraje>500);
	}
}
