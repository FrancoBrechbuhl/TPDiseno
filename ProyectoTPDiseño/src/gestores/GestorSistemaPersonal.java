package gestores;

public class GestorSistemaPersonal {
	
	public GestorSistemaPersonal(){};
	
 public boolean consultaEmpleado(String legajo) {
	 GestorDB gestorDB = new GestorDB();
	 gestorDB.connectDatabase();
	 boolean existe = gestorDB.existeEmpleado(legajo);
	 gestorDB.cerrarConexion();
	 
	 return existe;
 }
}
