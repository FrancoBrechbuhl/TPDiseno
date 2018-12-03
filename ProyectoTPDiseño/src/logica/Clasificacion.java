package logica;

import gestores.*;
import logica.util.*;
import java.util.List;
import java.util.ArrayList;

public class Clasificacion {
	private int idClasificacion; 
	private String nombre;
	private String descripcion;
	private EstadoClasificacion estado;
	private List<GrupoResolucion> grupos;
	
	public Clasificacion() {
		this.grupos = new ArrayList<GrupoResolucion>();
	}
	
	public Clasificacion(int idClasificacion, String nombre) {
		this.idClasificacion = idClasificacion;
		this.nombre = nombre;
	}
	
	public Clasificacion(int idClasificacion, String nombre, String descripcion, EstadoClasificacion estado, List<GrupoResolucion> grupos) {
		this.idClasificacion = idClasificacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.grupos = grupos;
	}

	public int getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(int idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoClasificacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoClasificacion estado) {
		this.estado = estado;
	}

	public List<GrupoResolucion> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoResolucion> grupos) {
		this.grupos = grupos;
	}
	
	public void agregarGrupo(GrupoResolucion gr) {
		this.grupos.add(gr);
	}
	
}
