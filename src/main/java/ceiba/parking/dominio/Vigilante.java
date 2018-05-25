package ceiba.parking.dominio;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceiba.parking.dominio.exception.IngresoException;
import ceiba.parking.persistencia.repository.FacturaRepositorio;
import ceiba.parking.persistencia.repository.IngresoRepositorio;
import ceiba.parking.persistencia.repository.VehiculoRepositorio;

@Service
public class Vigilante {

	public static final String VEHICULO_NO_AUROTIZADO = "placa inicia con A dia no autorizado";
	public static final String CUPO_NO_DISPONIBLE = "Cupos no disponibles";
	public static final String VEHICULO_NO_REGISTRADO = "Vehiculo no se ecuentra en el parqueadero";
	public static final String VEHICULO_MOTO = "Moto";
	public static final String VEHICULO_CARRO = "Carro";
	public static final int CARROS_TOPE=20;
	public static final int MOTOS_TOPE=10;
	public static final int COSTO_HORA_MOTO=500;
	public static final int COSTO_DIA_MOTO=4000;
	public static final int COSTO_HORA_CARRO=1000;
	public static final int COSTO_DIA_CARRO=8000;
	public static final int COSTO_ADD_POR_CILINDRAJE=2000;
	
	private Calendario diaActual;	
	private VehiculoRepositorio vehiculoRepositorio;	
	private IngresoRepositorio ingresoRepositorio;
	private FacturaRepositorio facturaRepositorio;
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd h:mm:s");

	@Autowired
	public Vigilante(
			Calendario diaActual,
			VehiculoRepositorio vehiculoRepositorio,
			IngresoRepositorio ingresoRepositorio,
			FacturaRepositorio facturaRepositorio) {
		this.diaActual = diaActual;
		this.vehiculoRepositorio = vehiculoRepositorio;
		this.ingresoRepositorio = ingresoRepositorio;
		this.facturaRepositorio = facturaRepositorio;
	}
	
	public Ingreso ingresoDeVehiculo(Vehiculo vehiculo) {
		if (validarCupo(vehiculo)) {
			if (entradaValida(vehiculo.getPlaca())) {
				Ingreso ingreso = new Ingreso(diaActual.getFechanueva(),vehiculo);
				return ingresoRepositorio.registrarIngreso(ingreso);
			}else throw new IngresoException(VEHICULO_NO_AUROTIZADO);
		}else throw new IngresoException(CUPO_NO_DISPONIBLE);		
	}
	
	public boolean salidaDeVehiculo(Long id) {
		Ingreso ingreso = ingresoRepositorio.buscarPorId(id);
		Factura factura;
		
		if (validarVehiculo(ingreso.getVehiculo().getPlaca())) {
			
			diaActual.calcularTiempo(ingreso.getFecha(), diaActual.getFechanueva());
			
			double valor=calcularCobro(
					diaActual.getDiaDeParqueo(),
					diaActual.getHoraDeParqueo(),
					diaActual.getMinutosDeParqueo(),
					ingreso.getVehiculo());
			factura = new Factura(
					formatoFecha.format(ingreso.getFecha()),
					formatoFecha.format(diaActual.getFechanueva()),
					"Dias: "+diaActual.getDiaDeParqueo()+
					" Horas: "+diaActual.getHoraDeParqueo()+
					" minutos: "+diaActual.getMinutosDeParqueo(),
					"$ "+valor,
					ingreso.getVehiculo().getPlaca(),
					ingreso.getVehiculo().getTipo()); 
			
			facturaRepositorio.registrarFactura(factura);
			ingresoRepositorio.eliminarIngreso(ingreso.getId());			
		}else throw new IngresoException(VEHICULO_NO_REGISTRADO);
		return true;
	}

	public double calcularCobro(int dias,int horas,int minutos,Vehiculo vehiculo) {
		int valorDias=0;
		int valorHoras=0;
		int valorMinutos=0;
		
		int costoDia=0;
		int costoHora=0;
		int costPorCilindraje=0;
	
		if (vehiculo.getTipo().equals(VEHICULO_MOTO)) {
			costoDia=COSTO_DIA_MOTO;
			costoHora=COSTO_HORA_MOTO;
			costPorCilindraje=COSTO_ADD_POR_CILINDRAJE;
			
		}
		
		if (vehiculo.getTipo().equals(VEHICULO_CARRO)) {
			costoDia=COSTO_DIA_CARRO;
			costoHora=COSTO_HORA_CARRO;
		}
		
		valorDias=dias*costoDia;
		
		if (minutos>10) {
			valorMinutos=costoHora;
		}
		
		if (horas<9) {
			valorHoras=costoHora*horas;
		}else valorHoras=costoDia;
		
		if(vehiculo.getCilindraje()>500) {
			return valorDias+valorHoras+valorMinutos+costPorCilindraje;
		}else return valorDias+valorHoras+valorMinutos;
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

	public boolean motoCilindrajeMayorA500(int cilindraje) {
		return (cilindraje>500);
	}
	
	public List<Ingreso> listaIngresos(){
		return ingresoRepositorio.consultarIngresos();
	}
	
	public List<Factura> listaFacturas(){
		return facturaRepositorio.consultarFacturas();
	}
}
