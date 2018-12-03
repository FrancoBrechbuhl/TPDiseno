package logica;
import java.time.LocalDateTime;

public class HistorialClasificacion {
	
	LocalDateTime fechaInicio;
	LocalDateTime fechaFin;
	Clasificacion clasificacion;
	Usuario actor;
	
<<<<<<< HEAD
	public HistorialClasificacion() {
		
	}

	public HistorialClasificacion(LocalDateTime fechaInicio, Clasificacion clasificacion, Ticket t, Usuario actor) {
		super();
		this.fechaInicio = fechaInicio;
		this.clasificacion = clasificacion;
		this.actor = actor;
	}


=======
	public void HistorialClasificacion() {}
>>>>>>> Nacho

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Usuario getActor() {
		return actor;
	}

	public void setActor(Usuario actor) {
		this.actor = actor;
	};
	
	

}
