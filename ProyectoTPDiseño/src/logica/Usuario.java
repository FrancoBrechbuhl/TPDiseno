package logica;


public class Usuario extends Empleado {
	String nombreUsuario;
	String password;
	GrupoResolucion grupo;
	
	public Usuario(String nombreUsuario, String password, GrupoResolucion grupo) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.grupo = grupo;
	}
	
<<<<<<< HEAD
	public Usuario() {
		
	}
=======
	public Usuario() {};
>>>>>>> Nacho

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GrupoResolucion getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoResolucion grupo) {
		this.grupo = grupo;
	}
	
	
	

}
