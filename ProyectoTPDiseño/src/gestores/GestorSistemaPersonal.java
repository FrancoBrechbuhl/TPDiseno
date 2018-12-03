package gestores;

public class GestorSistemaPersonal {

	public GestorSistemaPersonal(){};

 public boolean consultaEmpleado(String legajo) {
	 GestorDB gestorDB = new GestorDB();
	 boolean existe = gestorDB.existeEmpleado(legajo);

	 return existe;
 }
}
