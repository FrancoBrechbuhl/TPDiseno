package gestores;
import logica.*;

import logica.Sesion;

public class GestorUsuario {

	public GestorUsuario() {};

	public Sesion setUsuarioLogueado(Usuario usr) {
	Sesion sesion = new Sesion(usr);
	return sesion;
	}

	public Usuario getUser(Sesion s) {
		return s.getUser();
	}
}
