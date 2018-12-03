package gestores;

import java.sql.*;
import logica.*;
import logica.util.*;

import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class GestorDB {
	private Connection connection;
	public GestorDB() {
		this.connection = null;
	}

	private void connectDatabase() {
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

	private void cerrarConexion() {
		try {
			this.connection.close();
		}
		catch (java.sql.SQLException sqle) {
			System.out.println("Error al cerrar la conexion de la BD");
		}
	}

	public List<Clasificacion> seleccionarClasificaciones() {
		this.connectDatabase();
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
		this.cerrarConexion();
		return lista;
	}

	public Usuario seleccionarUsuario(String userName) {
		this.connectDatabase();
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
			System.out.println("Salio bien");
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		this.cerrarConexion();
		return us;
	}

	public boolean existeEmpleado(String legajo) {
		this.connectDatabase();
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

			System.out.println("Salio bien");
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		this.cerrarConexion();
		return existe;
	}

	public int devolverSecuencia() {
		this.connectDatabase();
		int nroTicket = -1;
		try{
			String sql = "SELECT nextval('" + '"' + "seqticket" + '"' + "');";
			ResultSet resultadoSecuencia;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultadoSecuencia = sentencia.executeQuery(sql);

			resultadoSecuencia.next();

			nroTicket = resultadoSecuencia.getInt(1);
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		this.cerrarConexion();
		return nroTicket;
	}

	public Empleado consultaEmpleado(String legajo) {
		this.connectDatabase();
		Empleado emp = new Empleado();
		try{
			Direccion dir;
			String sql = "SELECT E.nroLegajo, E.nombre, E.telefonoDirecto, E.telefonoInterno, E.cargo, D.calle, D.numero, D.piso, D.oficina, D.ciudad, D.provincia FROM EMPLEADO E, DIRECCION D WHERE E.idDireccion = D.idDireccion AND E.nroLegajo = '" + legajo + "';";
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
		this.cerrarConexion();
		return emp;
	}

	public Clasificacion seleccionarClasificaciones(String clas) {
		this.connectDatabase();
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
		this.cerrarConexion();
		return clasific;
	}

	public void guardarTicket(Ticket t, boolean update) {
		this.connectDatabase();
		try{
			if(!update) {
				Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				PreparedStatement actualizacion;
				ResultSet resultadoEstado;
				ResultSet resultadoClasificacion;
				String hora = "'" + t.getFechaApertura().getHour() + ':' + t.getFechaApertura().getMinute() + ':' + t.getFechaApertura().getSecond() + "'";
				String fecha = "'" + t.getFechaApertura().getYear() + '-' + t.getFechaApertura().getMonthValue() + '-' + t.getFechaApertura().getDayOfMonth() + "'";
				String desc = "'" + t.getDescripcion() + "'";
				String obs = "'" + t.getObservaciones() + "'";
				String nroL = "'" + t.getDemandante().getNroLegajo() + "'";

				String sql = "INSERT INTO TICKET VALUES (" + t.getNroTicket() + ',' + desc + ',' + obs + ',' + fecha + ',' + hora + ", 0," + nroL + ");";
				actualizacion = this.connection.prepareStatement(sql);
				actualizacion.executeUpdate();

				HistorialEstadoTicket het = t.getHistorialEstadoTicket().get(t.getHistorialEstadoTicket().size() - 1);
				String estT = "'" + het.getEstado().toString() + "'";
				String sql2 = "SELECT idEstado FROM ESTADO WHERE nombre = " + estT + ";";
				resultadoEstado = sentencia.executeQuery(sql2);

				resultadoEstado.next();
				int idEstado = resultadoEstado.getInt(1);
				String userName = "'" + het.getActor().getNombreUsuario() + "'";
				String nextValor = "('" + '"' + "idasignado" + '"' + "')";	//('"idasignado"')
				hora = "'" + het.getFechaInicio().getHour() + ':' + het.getFechaInicio().getMinute() + ':' +het.getFechaInicio().getSecond() + "'";
				fecha = "'" + het.getFechaInicio().getYear() + '-' + het.getFechaInicio().getMonthValue() + '-' + het.getFechaInicio().getDayOfMonth() + "'";


				String sql3 = "INSERT INTO TicketEstaEnEstado VALUES (nextval" + nextValor + ',' + fecha + ',' + hora + ", null, null, " + idEstado + ',' + t.getNroTicket() + ',' + userName + ");";
				actualizacion = this.connection.prepareStatement(sql3);
				actualizacion.executeUpdate();

				HistorialClasificacion hec = t.getHistorialClasificacion().get(t.getHistorialClasificacion().size() - 1);
				String clasT = "'" + hec.getClasificacion().getNombre() + "'";
				String sql4 = "SELECT idClasificacion FROM CLASIFICACION WHERE nombre = " + clasT + ';';
				resultadoClasificacion = sentencia.executeQuery(sql4);

				resultadoClasificacion.next();
				int idClasificacion = resultadoClasificacion.getInt(1);
				userName = "'" + hec.getActor().getNombreUsuario() + "'";
				hora = "'" + hec.getFechaInicio().getHour() + ':' + hec.getFechaInicio().getMinute() + ':' + hec.getFechaInicio().getSecond() + "'";
				fecha = "'" + hec.getFechaInicio().getYear() + '-' + hec.getFechaInicio().getMonthValue() + '-' + hec.getFechaInicio().getDayOfMonth() + "'";
				nextValor = "('" + '"' + "idclasificado" + '"' + "')";	//('"idclasificado"')

				String sql5 = "INSERT INTO ClasificacionPerteneceATicket VALUES (nextval" + nextValor + ',' + fecha + ',' + hora + ", null, null, " + idClasificacion + ',' + t.getNroTicket() + ',' + userName + ");";
				actualizacion = this.connection.prepareStatement(sql5);
				actualizacion.executeUpdate();

				userName = "'" + t.getActorMesa().getNombreUsuario() + "'";
				String sql6 = "INSERT INTO UsuarioCreaTicket VALUES (" + userName + ',' + t.getNroTicket() + ");";
				actualizacion = this.connection.prepareStatement(sql6);
				actualizacion.executeUpdate();
			}
			else {
				Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				PreparedStatement actualizacion;
				ResultSet resultadoEstado;
				ResultSet resultadoClasificacion;
				String hora = "'" + t.getFechaApertura().getHour() + ':' + t.getFechaApertura().getMinute() + ':' + t.getFechaApertura().getSecond() + "'";
				String fecha = "'" + t.getFechaApertura().getYear() + '-' + t.getFechaApertura().getMonthValue() + '-' + t.getFechaApertura().getDayOfMonth() + "'";
				String desc = "'" + t.getDescripcion() + "'";
				String obs = "'" + t.getObservaciones() + "'";
				String nroL = "'" + t.getDemandante().getNroLegajo() + "'";

				String sql = "UPDATE TICKET SET descripcion = " + desc + ',' + "observaciones = " + obs + ',' + "fechaApertura = " + fecha + ',' + "horaApertura = " + hora + ',' + "tiempoEnMesa = " + t.getTiempoEnMesa() + ',' + "nroLegajo = " + nroL + " WHERE nroTicket = " + t.getNroTicket() + ';';
				actualizacion = this.connection.prepareStatement(sql);
				actualizacion.executeUpdate();

				HistorialEstadoTicket het = t.getHistorialEstadoTicket().get(t.getHistorialEstadoTicket().size() - 1);
				String estT = "'" + het.getEstado().toString() + "'";
				String sql2 = "SELECT idEstado FROM ESTADO WHERE nombre = " + estT + ";";
				resultadoEstado = sentencia.executeQuery(sql2);

				resultadoEstado.next();
				int idEstado = resultadoEstado.getInt(1);
				String userName = "'" + het.getActor().getNombreUsuario() + "'";
				hora = "'" + het.getFechaInicio().getHour() + ':' + het.getFechaInicio().getMinute() + ':' +het.getFechaInicio().getSecond() + "'";
				fecha = "'" + het.getFechaInicio().getYear() + '-' + het.getFechaInicio().getMonthValue() + '-' + het.getFechaInicio().getDayOfMonth() + "'";


				String sql3 = "UPDATE TicketEstaEnEstado SET fechaInicio = " + fecha + ',' + "horaInicio = " + hora + ',' +"idEstado = " + idEstado + ',' + "nombreUsuario = " + userName + " WHERE nroTicket = " + t.getNroTicket() + ';';
				actualizacion = this.connection.prepareStatement(sql3);
				actualizacion.executeUpdate();

				HistorialClasificacion hec = t.getHistorialClasificacion().get(t.getHistorialClasificacion().size() - 1);
				String clasT = "'" + hec.getClasificacion().getNombre() + "'";
				String sql4 = "SELECT idClasificacion FROM CLASIFICACION WHERE nombre = " + clasT + ';';
				resultadoClasificacion = sentencia.executeQuery(sql4);

				resultadoClasificacion.next();
				int idClasificacion = resultadoClasificacion.getInt(1);
				userName = "'" + hec.getActor().getNombreUsuario() + "'";
				hora = "'" + hec.getFechaInicio().getHour() + ':' + hec.getFechaInicio().getMinute() + ':' + hec.getFechaInicio().getSecond() + "'";
				fecha = "'" + hec.getFechaInicio().getYear() + '-' + hec.getFechaInicio().getMonthValue() + '-' + hec.getFechaInicio().getDayOfMonth() + "'";

				String sql5 = "UPDATE ClasificacionPerteneceATicket SET fechaInicio = " + fecha + ',' + "horaInicio = " + hora + ',' + "idClasificacion = " + idClasificacion + ',' + "nombreUsuario = " + userName + " WHERE nroTicket = " + t.getNroTicket() + ';';
				actualizacion = this.connection.prepareStatement(sql5);
				actualizacion.executeUpdate();

				userName = "'" + t.getActorMesa().getNombreUsuario() + "'";
				String sql6 = "UPDATE UsuarioCreaTicket SET nombreUsuario = " + userName + " WHERE nroTicket = " + t.getNroTicket() + ';';
				actualizacion = this.connection.prepareStatement(sql6);
				actualizacion.executeUpdate();
			}
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al guardar");
			sqle.printStackTrace();
		}
		this.cerrarConexion();
	}

	public Ticket recuperarTicket(int nroTicket) {
		this.connectDatabase();
		Ticket t = new Ticket();
		try{
			Empleado emp;
			Usuario user;
			Direccion dir;
			GrupoResolucion gr;
			Clasificacion clas;
			HistorialEstadoTicket het;
			HistorialClasificacion hc;
			List<HistorialEstadoTicket> listaEstado = new ArrayList<HistorialEstadoTicket>();
			List<HistorialClasificacion> listaClasificacion = new ArrayList<HistorialClasificacion>();
			String sql = "SELECT T.descripcion, T.observaciones, T.fechaApertura, T.horaApertura, T.tiempoEnMesa, E.nroLegajo, E.nombre, E.telefonoDirecto, E.telefonoInterno, E.cargo, D.calle, D.numero, D.piso, D.oficina, D.ciudad, D.provincia, U.nombreUsuario, U.password, G.idGrupo, G.nombre, G.estado, G.descripcion FROM TICKET T, EMPLEADO E, DIRECCION D, USUARIO U, GRUPORESOLUCION G, UsuarioCreaTicket UCT WHERE T.nroLegajo = E.nroLegajo AND E.idDireccion = D.idDireccion AND T.nroTicket = UCT.nroTicket AND UCT.nombreUsuario = U.nombreUsuario AND U.idGrupo = G.idGrupo AND T.nroTicket = " + nroTicket + ';';
			ResultSet resultadoTicket;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultadoTicket = sentencia.executeQuery(sql);

			resultadoTicket.next();
			t.setNroTicket(nroTicket);
			t.setDescripcion(resultadoTicket.getString(1));
			t.setObservaciones(resultadoTicket.getString(2));
			t.setFechaApertura(this.castearFechaYHora(resultadoTicket.getString(3), resultadoTicket.getString(4)));
			t.setTiempoEnMesa(resultadoTicket.getInt(5));
			dir = new Direccion(resultadoTicket.getString(11), resultadoTicket.getString(12), resultadoTicket.getString(13), resultadoTicket.getString(14), resultadoTicket.getString(15), resultadoTicket.getString(16));
			emp = new Empleado(resultadoTicket.getString(6), resultadoTicket.getString(7), resultadoTicket.getString(8), resultadoTicket.getString(9), resultadoTicket.getString(10), dir);
			t.setDemandante(emp);

			gr = new GrupoResolucion(resultadoTicket.getInt(19), resultadoTicket.getString(20), EstadoGrupoResolucion.valueOf(resultadoTicket.getString(21)), resultadoTicket.getString(22));
			user = new Usuario(resultadoTicket.getString(17), resultadoTicket.getString(18), gr);
			t.setActorMesa(user);

			String sql2 = "SELECT TEEE.fechaInicio, TEEE.horaInicio, TEEE.fechaFin, TEEE.horaFin, E.nombre, U.nombreUsuario, U.password, G.idGrupo, G.nombre, G.estado, G.descripcion FROM TicketEstaEnEstado TEEE, USUARIO U, GRUPORESOLUCION G, ESTADO E WHERE TEEE.nombreUsuario = U.nombreUsuario AND TEEE.idEstado = E.idEstado AND U.idGrupo = G.idGrupo AND TEEE.nroTicket = " + nroTicket + ';';
			resultadoTicket = sentencia.executeQuery(sql2);

			while(resultadoTicket.next()) {
				gr = new GrupoResolucion(resultadoTicket.getInt(8), resultadoTicket.getString(9), EstadoGrupoResolucion.valueOf(resultadoTicket.getString(10)), resultadoTicket.getString(11));
				user = new Usuario(resultadoTicket.getString(6), resultadoTicket.getString(7), gr);
				het = new HistorialEstadoTicket(this.castearFechaYHora(resultadoTicket.getString(1), resultadoTicket.getString(2)), EstadoTicket.valueOf(resultadoTicket.getString(5)), t, user);
				listaEstado.add(het);
			}
			t.setHistorialEstadoTicket(listaEstado);

			String sql3 = "SELECT CPAT.fechaInicio, CPAT.horaInicio, CPAT.fechaFin, CPAT.horaFin, C.idClasificacion, C.nombre, C.estado, C.descripcion, U.nombreUsuario, U.password, G.idGrupo, G.nombre, G.estado, G.descripcion FROM ClasificacionPerteneceATicket CPAT, CLASIFICACION C, USUARIO U, GRUPORESOLUCION G WHERE CPAT.idClasificacion = C.idClasificacion AND CPAT.nombreUsuario = U.nombreUsuario AND U.idGrupo = G.idGrupo AND CPAT.nroTicket = " + nroTicket + ';';
			resultadoTicket = sentencia.executeQuery(sql3);

			while(resultadoTicket.next()) {
				gr = new GrupoResolucion(resultadoTicket.getInt(11), resultadoTicket.getString(12), EstadoGrupoResolucion.valueOf(resultadoTicket.getString(13)), resultadoTicket.getString(14));
				user = new Usuario(resultadoTicket.getString(9), resultadoTicket.getString(10), gr);
				List<Clasificacion> listaC = this.seleccionarClasificaciones();
				clas = new Clasificacion();
				for(Clasificacion c : listaC) {
					if(c.getNombre().equals(resultadoTicket.getString(6))) {
						clas = c;
					}
				}
				hc = new HistorialClasificacion(this.castearFechaYHora(resultadoTicket.getString(1), resultadoTicket.getString(2)), clas, t, user);
				listaClasificacion.add(hc);
			}
			t.setHistorialClasificacion(listaClasificacion);
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		this.cerrarConexion();
		return t;
	}

	public GrupoResolucion recuperarGrupo(String groupName) {
		this.connectDatabase();
		GrupoResolucion gr = new GrupoResolucion();
		try{
			String sql = "SELECT idGrupo, estado, descripcion FROM GRUPORESOLUCION WHERE nombre = '" + groupName + "';";
			ResultSet resultadoGrupo;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultadoGrupo = sentencia.executeQuery(sql);

			resultadoGrupo.next();

			gr.setNombre(groupName);
			gr.setCodigo(resultadoGrupo.getInt(1));
			gr.setEstado(EstadoGrupoResolucion.valueOf(resultadoGrupo.getString(2)));
			gr.setDescripcion(resultadoGrupo.getString(3));
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		this.cerrarConexion();
		return gr;
	}

	private LocalDateTime castearFechaYHora(String fecha, String hora ) {
		int a, me, d, h, mi, s;
		a = Integer.parseInt(fecha.substring(0, 4));
		me = Integer.parseInt(fecha.substring(5, 7));
		d = Integer.parseInt(fecha.substring(8));
		h = Integer.parseInt(hora.substring(0, 2));
		mi = Integer.parseInt(hora.substring(3, 5));
		s= Integer.parseInt(hora.substring(6));

		LocalDateTime fechaYHora = LocalDateTime.of(a, me, d, h, mi, s);
		return fechaYHora;
	}
}
