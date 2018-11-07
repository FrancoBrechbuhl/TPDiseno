package logica;

public class Empleado {
	
	String nroLegajo;
	String nombre;
	String telefonoInterno;
	String telefonoDirecto;
	String descripcionCargo;
	Direccion ubicacion;
	
	public Empleado(String nroLegajo, String nombre, String telefonoInterno, String telefonoDirecto,
			String descripcionCargo, Direccion ubicacion) {
		super();
		this.nroLegajo = nroLegajo;
		this.nombre = nombre;
		this.telefonoInterno = telefonoInterno;
		this.telefonoDirecto = telefonoDirecto;
		this.descripcionCargo = descripcionCargo;
		this.ubicacion = ubicacion;
	}
	
	public Empleado() {}

	public String getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefonoInterno() {
		return telefonoInterno;
	}

	public void setTelefonoInterno(String telefonoInterno) {
		this.telefonoInterno = telefonoInterno;
	}

	public String getTelefonoDirecto() {
		return telefonoDirecto;
	}

	public void setTelefonoDirecto(String telefonoDirecto) {
		this.telefonoDirecto = telefonoDirecto;
	}

	public String getDescripcionCargo() {
		return descripcionCargo;
	}

	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
	}

	public Direccion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Direccion ubicacion) {
		this.ubicacion = ubicacion;
	};
	
	
	

}
