package gestores;

public class GestorSistemaPersonal {
	
	public GestorSistemaPersonal(){};
	
<<<<<<< HEAD
 public boolean consultaEmpleado(String legajo) {
=======
 public boolean obtenerE(String legajo) {
>>>>>>> Nacho
	 GestorDB gestorDB = new GestorDB();
		
		gestorDB.connectDatabase();
		boolean existe = gestorDB.existeEmpleado(legajo);
		gestorDB.cerrarConexion();
		
		return true;
 }

<<<<<<< HEAD
}
=======
}
>>>>>>> Nacho
