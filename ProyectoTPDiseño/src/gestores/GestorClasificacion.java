package gestores;
import logica.Clasificacion;

public class GestorClasificacion {

	public GestorClasificacion() {};

	public Clasificacion seleccionarClasificaciones(String clas) {
		Clasificacion c;
		GestorDB gestorDB = new GestorDB();
		gestorDB.connectDatabase();
		c = gestorDB.seleccionarClasificaciones(clas);
		gestorDB.cerrarConexion();
		return c;

	}

}
