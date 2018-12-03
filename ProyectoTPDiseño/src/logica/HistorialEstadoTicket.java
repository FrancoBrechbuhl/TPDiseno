package logica;
import logica.util.*;
import java.time.LocalDateTime;

public class HistorialEstadoTicket {
	LocalDateTime fechaInicio;
	LocalDateTime fechaFinal;
	EstadoTicket estado;
	Ticket ticket;
	Usuario actor;
	
	public HistorialEstadoTicket() {
		
	}

	public HistorialEstadoTicket(LocalDateTime fechaInicio, EstadoTicket estado, Ticket t, Usuario actor) {
		super();
		this.fechaInicio = fechaInicio;
		this.estado = estado;
		this.actor = actor;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public EstadoTicket getEstado() {
		return estado;
	}

	public void setEstado(EstadoTicket estado) {
		this.estado = estado;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Usuario getActor() {
		return actor;
	}

	public void setActor(Usuario actor) {
		this.actor = actor;
	};
	
	
	

}
