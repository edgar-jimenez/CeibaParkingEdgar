package ceiba.parking.dominio;

public class Factura {

	private Long id;
	
	private String entrada;
	
	private String salida;
	
	private String tiempo;
	
	private String valor;
	
	private String vehiculoPlaca;
	
	private String vehiculoTipo;	

	public Factura(String entrada, String salida, String tiempo, String valor, String vehiculoPlaca,
			String vehiculoTipo) {
		this.entrada = entrada;
		this.salida = salida;
		this.tiempo = tiempo;
		this.valor = valor;
		this.vehiculoPlaca = vehiculoPlaca;
		this.vehiculoTipo = vehiculoTipo;
	}

	public Factura(Long id, String entrada, String salida, String tiempo, String valor, String vehiculoPlaca,
			String vehiculoTipo) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.tiempo = tiempo;
		this.valor = valor;
		this.vehiculoPlaca = vehiculoPlaca;
		this.vehiculoTipo = vehiculoTipo;
	}

	public String getEntrada() {
		return entrada;
	}

	public String getSalida() {
		return salida;
	}

	public Long getId() {
		return id;
	}

	public String getTiempo() {
		return tiempo;
	}

	public String getValor() {
		return valor;
	}

	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}

	public String getVehiculoTipo() {
		return vehiculoTipo;
	}
}
