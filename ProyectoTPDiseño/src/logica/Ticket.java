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
	
	
	

}
