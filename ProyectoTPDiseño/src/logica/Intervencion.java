package logica;
import logica.util.*;
import java.time.LocalDateTime;

public class Intervencion {
	
	Ticket ticket;
	LocalDateTime fechaAsignacion;
	LocalDateTime fechaInicioTrabajo;
	LocalDateTime fechaFinal;
	long tiempoTrabajado;
	EstadoIntervencion estado;
	String Observaciones;
	Usuario actor;
	
	public Intervencion() {
		
	}
	
	public Intervencion(Ticket ticket, LocalDateTime fechaAsignacion, LocalDateTime fechaInicioTrabajo,
			LocalDateTime fechaFinal, long tiempoTrabajado, EstadoIntervencion estado, String observaciones) {
		super();
		this.ticket = ticket;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaInicioTrabajo = fechaInicioTrabajo;
		this.fechaFinal = fechaFinal;
		this.tiempoTrabajado = tiempoTrabajado;
		this.estado = estado;
		Observaciones = observaciones;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public LocalDateTime getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public LocalDateTime getFechaInicioTrabajo() {
		return fechaInicioTrabajo;
	}

	public void setFechaInicioTrabajo(LocalDateTime fechaInicioTrabajo) {
		this.fechaInicioTrabajo = fechaInicioTrabajo;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public long getTiempoTrabajado() {
		return tiempoTrabajado;
	}

	public void setTiempoTrabajado(long tiempoTrabajado) {
		this.tiempoTrabajado = tiempoTrabajado;
	}

	public EstadoIntervencion getEstado() {
		return estado;
	}

	public void setEstado(EstadoIntervencion estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

	public Usuario getActor() {
		return actor;
	}

	public void setActor(Usuario actor) {
		this.actor = actor;
	}

}
