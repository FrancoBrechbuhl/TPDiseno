package logica;

public class Direccion {
	
	String calle;
	String numero;
	String piso;
	String oficina;
	String ciudad;
	String provincia;

	public Direccion() {
		
	}

	public Direccion(String calle, String numero, String piso, String oficina, String ciudad, String provincia) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.oficina = oficina;
		this.ciudad = ciudad;
		this.provincia = provincia;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	};
	
	

}
