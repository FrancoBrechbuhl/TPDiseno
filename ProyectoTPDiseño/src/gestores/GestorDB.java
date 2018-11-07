package gestores;

import java.sql.*;
import logica.*;
import logica.util.EstadoClasificacion;
import logica.util.EstadoGrupoResolucion;

import java.util.List;
import java.util.ArrayList;

public class GestorDB {
	private Connection connection;
	public GestorDB() {
		this.connection = null;
	}
	
	public void connectDatabase() {
			try {	
				// We register the PostgreSQL driver
				// Registramos el driver de PostgresSQL
				try { 
				    Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException ex) {
				    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
				}
				
				// Database connect
				// Conectamos con la base de datos
				connection = DriverManager.getConnection(
				        "jdbc:postgresql://localhost/postgres",
				        "postgres", "TF135");
				boolean valido = connection.isValid(50000);
				if(valido) {
					System.out.println("Test DB OK");
				}
				else {
					System.out.println("Test DB fail");
				}
			}
			catch (java.sql.SQLException sqle) {
				System.out.println("Error al conectarse a la BD");
				System.out.println(sqle.getSQLState());
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
				
	}
	
	public void cerrarConexion() {
		try {
			this.connection.close();
		}
		catch (java.sql.SQLException sqle) {
			System.out.println("Error al cerrar la conexion de la BD");
		}
	}
	
	public List<Clasificacion> seleccionarClasificaciones() {
		List<Clasificacion> lista = new ArrayList<Clasificacion>();
			try{
				Clasificacion cAux;
				GrupoResolucion grAux;
				int idClas;
				String sql = "SELECT C.idClasificacion, C.nombre, C.estado, C.descripcion, G.idGrupo, G.nombre, G.estado, G.descripcion FROM CLASIFICACION C, GRUPOTIENEASIGNADACLASIFICACION GTAC, GRUPORESOLUCION G WHERE C.idClasificacion = GTAC.idClasificacion AND GTAC.idGrupo = G.idGrupo ORDER BY C.idClasificacion DESC;";
				ResultSet resultadoClasificaciones;
				Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
				resultadoClasificaciones = sentencia.executeQuery(sql);
				
				resultadoClasificaciones.next();
				
				cAux = new Clasificacion();
				cAux.setIdClasificacion(resultadoClasificaciones.getInt(1));
				idClas = resultadoClasificaciones.getInt(1);
				cAux.setNombre(resultadoClasificaciones.getString(2));
				cAux.setEstado(EstadoClasificacion.valueOf(resultadoClasificaciones.getString(3)));
				cAux.setDescripcion(resultadoClasificaciones.getString(4));
				
				grAux = new GrupoResolucion();
				grAux.setCodigo(resultadoClasificaciones.getInt(5));
				grAux.setNombre(resultadoClasificaciones.getString(6));
				grAux.setEstado(EstadoGrupoResolucion.valueOf(resultadoClasificaciones.getString(7)));
				grAux.setDescripcion(resultadoClasificaciones.getString(8));
				cAux.agregarGrupo(grAux);
				lista.add(cAux);
				
				while(resultadoClasificaciones.next()) {
					if(idClas == resultadoClasificaciones.getInt(1)) {
						grAux = new GrupoResolucion();
						grAux.setCodigo(resultadoClasificaciones.getInt(5));
						grAux.setNombre(resultadoClasificaciones.getString(6));
						grAux.setEstado(EstadoGrupoResolucion.valueOf(resultadoClasificaciones.getString(7)));
						grAux.setDescripcion(resultadoClasificaciones.getString(8));
						cAux.agregarGrupo(grAux);
					}
					else {
						cAux = new Clasificacion();
						cAux.setIdClasificacion(resultadoClasificaciones.getInt(1));
						idClas = resultadoClasificaciones.getInt(1);
						cAux.setNombre(resultadoClasificaciones.getString(2));
						cAux.setEstado(EstadoClasificacion.valueOf(resultadoClasificaciones.getString(3)));
						cAux.setDescripcion(resultadoClasificaciones.getString(4));
						
						grAux = new GrupoResolucion();
						grAux.setCodigo(resultadoClasificaciones.getInt(5));
						grAux.setNombre(resultadoClasificaciones.getString(6));
						grAux.setEstado(EstadoGrupoResolucion.valueOf(resultadoClasificaciones.getString(7)));
						grAux.setDescripcion(resultadoClasificaciones.getString(8));
						cAux.agregarGrupo(grAux);
						lista.add(cAux);
					}
				}
			}
			catch(java.sql.SQLException sqle) {
				System.out.println("Error al seleccionar");
				sqle.printStackTrace();
			}
		return lista;
	}
	
	public Usuario seleccionarUsuario(String userName) {
		Usuario us = new Usuario();
		try{
			GrupoResolucion gr;
			Direccion dir;
			String sql = "SELECT E.nroLegajo, E.nombre, E.telefonoDirecto, E.telefonoInterno, E.cargo, D.calle, D.numero, D.piso, D.oficina, D.ciudad, D.provincia, U.nombreUsuario, U.password, G.idGrupo, G.nombre, G.estado, G.descripcion FROM EMPLEADO E, USUARIO U, GRUPORESOLUCION G, DIRECCION D WHERE E.idDireccion = D.idDireccion AND E.nombreUsuario = U.nombreUsuario AND U.idGrupo = G.idGrupo;";
			ResultSet resultadoUsuario;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			resultadoUsuario = sentencia.executeQuery(sql);
			
			while(resultadoUsuario.next()) {
				if(userName.equals(resultadoUsuario.getString(12))) {
					gr = new GrupoResolucion(resultadoUsuario.getInt(14), resultadoUsuario.getString(15), EstadoGrupoResolucion.valueOf(resultadoUsuario.getString(16)), resultadoUsuario.getString(17));
					dir = new Direccion(resultadoUsuario.getString(6), resultadoUsuario.getString(7), resultadoUsuario.getString(8),resultadoUsuario.getString(9), resultadoUsuario.getString(10), resultadoUsuario.getString(11));
					us.setNombreUsuario(resultadoUsuario.getString(12));
					us.setPassword(resultadoUsuario.getString(13));
					us.setGrupo(gr);
					us.setUbicacion(dir);
				}
			}
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		
		return us;
	}
	
	public boolean existeEmpleado(String legajo) {
		boolean existe = false;
		try{
			String sql = "SELECT nroLegajo FROM EMPLEADO;";
			ResultSet resultadoEmpleado;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			resultadoEmpleado = sentencia.executeQuery(sql);
			
			while(resultadoEmpleado.next()) {
				if(legajo.equals(resultadoEmpleado.getString(1))) {
					existe = true;
				}
			}
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		return existe;
	}
	
	public int devolverSecuencia() {
		int nroTicket = -1;
		try{
			String sql = "SELECT currval('" + '"' + "seqTicket" + '"' + "');";
			ResultSet resultadoSecuencia;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			resultadoSecuencia = sentencia.executeQuery(sql);
			
			resultadoSecuencia.next();
			
			nroTicket = resultadoSecuencia.getInt(1) + 1;
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		return nroTicket;
	}
	
	public Empleado consultaEmpleado(String legajo) {
		Empleado emp = new Empleado();
		try{
			Direccion dir;
			String sql = "SELECT E.nroLegajo, E.nombre, E.telefonoDirecto, E.telefonoInterno, E.cargo, D.calle, D.numero, D.piso, D.oficina, D.ciudad, D.provincia FROM EMPLEADO E, DIRECCION D WHERE E.idDireccion = D.idDireccion;";
			ResultSet resultadoEmpleado;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			resultadoEmpleado = sentencia.executeQuery(sql);
			
			while(resultadoEmpleado.next()) {
				dir = new Direccion(resultadoEmpleado.getString(6), resultadoEmpleado.getString(7), resultadoEmpleado.getString(8), resultadoEmpleado.getString(9), resultadoEmpleado.getString(10), resultadoEmpleado.getString(11));
				emp.setNroLegajo(resultadoEmpleado.getString(1));
				emp.setNombre(resultadoEmpleado.getString(2));
				emp.setTelefonoDirecto(resultadoEmpleado.getString(3));
				emp.setTelefonoInterno(resultadoEmpleado.getString(4));
				emp.setDescripcionCargo(resultadoEmpleado.getString(5));
				emp.setUbicacion(dir);
			}
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		return emp;
	}
	
	public Clasificacion seleccionarClasificaciones(String clas) {
		Clasificacion clasific = new Clasificacion();
		try{
			String sql = "SELECT idClasificacion, nombre, estado, descripcion FROM CLASIFICACION;";
			ResultSet resultadoClasificacion;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			resultadoClasificacion = sentencia.executeQuery(sql);
			
			while(resultadoClasificacion.next()) {
				clasific.setIdClasificacion(resultadoClasificacion.getInt(1));
				clasific.setNombre(resultadoClasificacion.getString(2));
				clasific.setEstado(EstadoClasificacion.valueOf(resultadoClasificacion.getString(3)));
				clasific.setDescripcion(resultadoClasificacion.getString(4));
			}
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		return clasific;
	}
	
	public void guardarTicket(Ticket t) {
		try{
			String sql = "INSERT INTO TICKET VALUES (" + t.getNroTicket() + ',' + t.getDescripcion() + ',' + t.getObservaciones() + ',' + t.getFechaApertura() + ',' + "'null'" + ',' + t.getDemandante().getNroLegajo() + ");";  
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			sentencia.executeQuery(sql);
			
			System.out.println("Funciono");
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
	}
	
	public Ticket recuperarTicket(int nroTicket) {
		return null;
	}
	
	public GrupoResolucion recuperarGrupo(String groupName) {
		return null;
	}
	
	public void seleccionar() {
		try{
			String sql = "SELECT nroTicket FROM Ticket;";
			ResultSet resultado;
			ResultSetMetaData res;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			resultado = sentencia.executeQuery(sql);
			res = resultado.getMetaData();
			
			while(resultado.next()) {
				System.out.println("Dato: "+resultado.getString(1));
			}
			
		
			System.out.println("Salio bien");
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		
	}
	
	public void insertar() {
		try {
			PreparedStatement sent = this.connection.prepareStatement("INSERT INTO TICKET VALUES (1, 'Ticket1', 'ObsTicket1', '2018-10-30', null, 24088);");
			sent.execute();
			sent.close();
			
			System.out.println("Salio bien");
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al insertar");
			sqle.printStackTrace();
		}
	}
}

