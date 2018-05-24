package ceiba.parking.dominio;

import java.util.Calendar;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class Calendario {
	
	private Calendar fecha =Calendar.getInstance();
	
	private int diaDeParqueo;

	private int horaDeParqueo;

	private int minutosDeParqueo;

	public boolean validarFecha() {
		return (Calendar.SUNDAY==fecha.get(Calendar.DAY_OF_WEEK) 
				|| Calendar.MONDAY==fecha.get(Calendar.DAY_OF_WEEK));
	}
	
	public void calcularTiempo(Date entrada,Date salida) {
        
        int diferencia=(int) ((salida.getTime()-entrada.getTime())/1000);
        int dias=0;
        int horas=0;
        int minutos=0;
        if(diferencia>86400) {
            dias=(int)Math.floor((double)diferencia/86400);
            diferencia=diferencia-(dias*86400);
        }
        if(diferencia>3600) {
            horas=(int)Math.floor((double)diferencia/3600);
            diferencia=diferencia-(horas*3600);
        }
        if(diferencia>60) {
            minutos=(int)Math.floor((double)diferencia/60);
        }
        
        this.diaDeParqueo=dias;
        this.horaDeParqueo=horas;
        this.minutosDeParqueo=minutos;
	}
	
	public void setFecha(int anno,int mes,int dia) {
		this.fecha.set(anno, mes, dia);
	}

	public Date getFecha() {
		return fecha.getTime();
	}
	
	public Date getFechanueva() {
		fecha =Calendar.getInstance();
		return fecha.getTime();
	}

	public int getDiaDeParqueo() {
		return diaDeParqueo;
	}

	public int getHoraDeParqueo() {
		return horaDeParqueo;
	}

	public int getMinutosDeParqueo() {
		return minutosDeParqueo;
	}

}
