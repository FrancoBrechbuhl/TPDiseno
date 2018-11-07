package logica;

import logica.util.*;

public class GrupoResolucion {
	private int codigo;
	private String nombre;
	private EstadoGrupoResolucion estado;
	private String descripcion;
	
	public GrupoResolucion() {
		
	}

	public GrupoResolucion(int codigo, String nombre, EstadoGrupoResolucion estado, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
		this.descripcion = descripcion;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoGrupoResolucion getEstado() {
		return estado;
	}

	public void setEstado(EstadoGrupoResolucion estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
