package gestores;
import logica.Clasificacion;

public class GestorClasificacion {

	public GestorClasificacion() {};

	public Clasificacion seleccionarClasificaciones(String clas) {
		Clasificacion c;
		GestorDB gestorDB = new GestorDB();
		c = gestorDB.seleccionarClasificaciones(clas);
		return c;

	}

}
