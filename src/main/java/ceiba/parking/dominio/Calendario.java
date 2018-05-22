package ceiba.parking.dominio;

import java.util.Calendar;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class Calendario {
	
	private Calendar fecha =Calendar.getInstance();

	public boolean validarFecha() {
		return (Calendar.SUNDAY==fecha.get(Calendar.DAY_OF_WEEK) 
				|| Calendar.MONDAY==fecha.get(Calendar.DAY_OF_WEEK));
	}
	
	public void setFecha(int anno,int mes,int dia) {
		this.fecha.set(anno, mes, dia);
	}

	public Date getFecha() {
		return fecha.getTime();
	}

}
