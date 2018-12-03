package logica;
import java.time.LocalDateTime;
import java.util.List;

public class Ticket {
	
	int nroTicket;
	String descripcion;
	LocalDateTime fechaApertura;
	String observaciones;
	long tiempoEnMesa;
	List<Intervencion> intervenciones;
	Usuario actorMesa;
	List<HistorialEstadoTicket> historialEstadoTicket;
	Empleado demandante;
	List<HistorialClasificacion> historialClasificacion;
	
	public Ticket(int nroTicket, String descripcion, LocalDateTime fechaApertura, String observaciones, long tiempoEnMesa, List<Intervencion> intervenciones, Usuario actorMesa, List<HistorialEstadoTicket> historialEstadoTicket, Empleado demandante, List<HistorialClasificacion> historialClasificacion) {
		super();
		this.nroTicket = nroTicket;
		this.descripcion = descripcion;
		this.fechaApertura = fechaApertura;
		this.observaciones = observaciones;
		this.tiempoEnMesa = tiempoEnMesa;
		this.intervenciones = intervenciones;
		this.actorMesa = actorMesa;
		this.historialEstadoTicket = historialEstadoTicket;
		this.demandante = demandante;
		this.historialClasificacion = historialClasificacion;
	}

	public Ticket() {
		
	}
	
	public int getNroTicket() {
		return nroTicket;
	}
	public void setNroTicket(int nroTicket) {
		this.nroTicket = nroTicket;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDateTime getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public long getTiempoEnMesa() {
		return tiempoEnMesa;
	}
	public void setTiempoEnMesa(long tiempoEnMesa) {
		this.tiempoEnMesa = tiempoEnMesa;
	}
	public List<Intervencion> getIntervenciones() {
		return intervenciones;
	}
	public void setIntervenciones(List<Intervencion> intervenciones) {
		this.intervenciones = intervenciones;
	}
	public Usuario getActorMesa() {
		return actorMesa;
	}
	public void setActorMesa(Usuario actorMesa) {
		this.actorMesa = actorMesa;
	}
	public List<HistorialEstadoTicket> getHistorialEstadoTicket() {
		return historialEstadoTicket;
	}
	public void setHistorialEstadoTicket(List<HistorialEstadoTicket> historialEstadoTicket) {
		this.historialEstadoTicket = historialEstadoTicket;
	}
	public Empleado getDemandante() {
		return demandante;
	}
	public void setDemandante(Empleado demandante) {
		this.demandante = demandante;
	}
	public List<HistorialClasificacion> getHistorialClasificacion() {
		return historialClasificacion;
	}
	public void setHistorialClasificacion(List<HistorialClasificacion> historialClasificacion) {
		this.historialClasificacion = historialClasificacion;
	}
	
	public void agregarHistorialTicket(HistorialEstadoTicket ht) {
		historialEstadoTicket.add(ht);
	}
	
	public void agregarHistorialClasificacion(HistorialClasificacion hc) {
		historialClasificacion.add(hc);
	}
	
	public void agregarIntervencion(Intervencion i) {
		intervenciones.add(i);
	}
}



