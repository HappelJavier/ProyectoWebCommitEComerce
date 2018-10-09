package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.model.Juego;
import com.example.model.Producto;
import com.example.model.Usuario;


public class Productos {
	
	public static void cargarListadoJuegos(Connection connection, Model template) throws SQLException {
		PreparedStatement consultaJuegos = connection.prepareStatement("SELECT * FROM juegos ORDER BY id ASC;");

		ResultSet resultadoJuegos = consultaJuegos.executeQuery();

		ArrayList<Juego> listadoJuegos= new ArrayList<Juego>();

		while ( resultadoJuegos.next() ) {
			int idJuego = resultadoJuegos.getInt("id");
			String nombreJuego = resultadoJuegos.getString("nombre");
			String desarrollador = resultadoJuegos.getString("desarrollador");
			String fecha = resultadoJuegos.getString("fecha");
			String plataforma = resultadoJuegos.getString("plataforma");
			String genero = resultadoJuegos.getString("genero");
			int rencpu1 = resultadoJuegos.getInt("rencpu1");
			int rencpu2 = resultadoJuegos.getInt("rencpu2");
			int rengpu1 = resultadoJuegos.getInt("rengpu1");
			int rengpu2 = resultadoJuegos.getInt("rengpu2");
			int vram1 = resultadoJuegos.getInt("vram1");
			int vram2 = resultadoJuegos.getInt("vram2");
			int ram1 = resultadoJuegos.getInt("ram1");
			int ram2 = resultadoJuegos.getInt("ram2");
			int precio = resultadoJuegos.getInt("precio");
			String urlimagenJuego = resultadoJuegos.getString("urlimagen");
			
			Juego z = new Juego (idJuego, nombreJuego, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagenJuego);
			listadoJuegos.add(z);	
		}

		template.addAttribute("listadoJuegos", listadoJuegos);
	}
	
	public static void insertarJuego(Connection connection, String nombre, String desarrollador, String fecha, String plataforma, String genero, int rencpu1, int rencpu2, int rengpu1, int rengpu2, int vram1, int vram2, int ram1, int ram2, int precio, String urlimagen) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO juegos(nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		consulta.setString(1, nombre);
		consulta.setString(2, desarrollador);
		consulta.setString(3, fecha);
		consulta.setString(4, plataforma);
		consulta.setString(5, genero);
		consulta.setInt(6, rencpu1);
		consulta.setInt(7, rencpu2);
		consulta.setInt(8, rengpu1);
		consulta.setInt(9, rengpu2);
		consulta.setInt(10, vram1);
		consulta.setInt(11, vram2);
		consulta.setInt(12, ram1);
		consulta.setInt(13, ram2);
		consulta.setInt(14, precio);
		consulta.setString(15, urlimagen);

		consulta.executeUpdate();
	}
	
	public static void insertarJuego(Connection connection, Juego juego) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO juegos(nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		consulta.setString(1, juego.getNombre());
		consulta.setString(2, juego.getDesarrollador());
		consulta.setString(3, juego.getFecha());
		consulta.setString(4, juego.getPlataforma());
		consulta.setString(5, juego.getGenero());
		consulta.setInt(6, juego.getRencpu1());
		consulta.setInt(7, juego.getRencpu2());
		consulta.setInt(8, juego.getRengpu1());
		consulta.setInt(9, juego.getRengpu2());
		consulta.setInt(10, juego.getVram1());
		consulta.setInt(11, juego.getVram2());
		consulta.setInt(12, juego.getRam1());
		consulta.setInt(13, juego.getRam2());
		consulta.setInt(14, juego.getPrecio());
		consulta.setString(15, juego.getUrlimagen());

		consulta.executeUpdate();
	}

	public static void cargarJuego(Connection connection, int id, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM juegos WHERE id = ?;");
		consulta.setInt(1, id);
		
		ResultSet resultado = consulta.executeQuery();

		while ( resultado.next() ) {
			String nombre = resultado.getString("nombre");
			String desarrollador = resultado.getString("desarrollador");
			String fecha = resultado.getString("fecha");
			String plataforma = resultado.getString("plataforma");
			String genero = resultado.getString("genero");
			int rencpu1 = resultado.getInt("rencpu1");
			int rencpu2 = resultado.getInt("rencpu2");
			int rengpu1 = resultado.getInt("rengpu1");
			int rengpu2 = resultado.getInt("rengpu2");
			int vram1 = resultado.getInt("vram1");
			int vram2 = resultado.getInt("vram2");
			int ram1 = resultado.getInt("ram1");
			int ram2 = resultado.getInt("ram2");
			int precio = resultado.getInt("precio");
			String urlimagenJuego = resultado.getString("urlimagen");
			template.addAttribute("id", id);
			template.addAttribute("nombre", nombre);
			template.addAttribute("desarrollador", desarrollador);
			template.addAttribute("fecha", fecha);
			template.addAttribute("plataforma", plataforma);
			template.addAttribute("genero", genero);
			template.addAttribute("rencpu1", rencpu1);
			template.addAttribute("rencpu2", rencpu2);
			template.addAttribute("rengpu1", rengpu1);
			template.addAttribute("rengpu2", rengpu2);
			template.addAttribute("vram1", vram1);
			template.addAttribute("vram2", vram2);
			template.addAttribute("ram1", ram1);
			template.addAttribute("ram2", ram2);
			template.addAttribute("precio", precio);
			template.addAttribute("urlimagen", urlimagenJuego);
		}
	}
	
	public static void cargarListadoComponentes(Connection connection, Model template) throws SQLException {
		PreparedStatement consultaProductos = connection.prepareStatement("SELECT * FROM productos ORDER BY id ASC;");

		ResultSet resultadoProductos = consultaProductos.executeQuery();

		ArrayList<Producto> listadoProductos = new ArrayList<Producto>();

		while ( resultadoProductos.next() ) {
			int idProducto = resultadoProductos.getInt("id");
			String tipo = resultadoProductos.getString("tipo");
			String marca = resultadoProductos.getString("marca");
			String modelo = resultadoProductos.getString("modelo");
			String socketcpu = resultadoProductos.getString("socketcpu");
			String tiporam = resultadoProductos.getString("tiporam");
			String pci = resultadoProductos.getString("pci");
			String sata = resultadoProductos.getString("sata");
			String velocidad = resultadoProductos.getString("velocidad");
			String tamanio = resultadoProductos.getString("tamanio");
			String rendimiento = resultadoProductos.getString("rendimiento");
			int consumo = resultadoProductos.getInt("consumo");
			int precio = resultadoProductos.getInt("precio");
			String urlimagenProducto = resultadoProductos.getString("urlimagen");
			Producto x = new Producto (idProducto, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagenProducto);
			listadoProductos.add(x);	
		}

		template.addAttribute("listadoProductos", listadoProductos);
	}
	
	public static void insertarComponente(Connection connection, String tipo, String marca, String modelo, String socketcpu, String tiporam, String pci, String sata, String velocidad, String tamanio, String rendimiento, String consumo, int precio, String urlimagen) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ");
		consulta.setString(1, tipo);
		consulta.setString(2, marca);
		consulta.setString(3, modelo);
		consulta.setString(4, socketcpu);
		consulta.setString(5, tiporam);
		consulta.setString(6, pci);
		consulta.setString(7, sata);
		consulta.setString(8, velocidad);
		consulta.setString(9, tamanio);
		consulta.setString(10, rendimiento);
		consulta.setString(11, consumo);
		consulta.setInt(12, precio);
		consulta.setString(13, urlimagen);

		consulta.executeUpdate();
	}
	
	public static void cargarComponente(Connection connection, int id, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE id = ?;");
		consulta.setInt(1, id);
		
		ResultSet resultado = consulta.executeQuery();

		while ( resultado.next() ) {
			String tipo = resultado.getString("tipo");
			String marca = resultado.getString("marca");
			String modelo = resultado.getString("modelo");
			String socketcpu = resultado.getString("socketcpu");
			String tiporam = resultado.getString("tiporam");
			String pci = resultado.getString("pci");
			String sata = resultado.getString("sata");
			String velocidad = resultado.getString("velocidad");
			String tamanio = resultado.getString("tamanio");
			String rendimiento = resultado.getString("rendimiento");
			String consumo = resultado.getString("consumo");
			int precio = resultado.getInt("precio");
			String urlimagen = resultado.getString("urlimagen");
			boolean oferta = resultado.getBoolean("enoferta");
			template.addAttribute("id", id);
			template.addAttribute("tipo", tipo);
			template.addAttribute("marca", marca);
			template.addAttribute("modelo", modelo);
			template.addAttribute("socketcpu", socketcpu);
			template.addAttribute("tiporam", tiporam);
			template.addAttribute("pci", pci);
			template.addAttribute("sata", sata);
			template.addAttribute("velocidad", velocidad);
			template.addAttribute("tamanio", tamanio);
			template.addAttribute("rendimiento", rendimiento);
			template.addAttribute("consumo", consumo);
			template.addAttribute("precio", precio);
			template.addAttribute("urlimagen", urlimagen);
			template.addAttribute("oferta", oferta);
		}
	}
	
	public static void crearListadoOrdenado(HttpSession session,Model template, Connection connection, int offset) throws SQLException{
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta.setInt(1, offset);
		ResultSet resultado = consulta.executeQuery();

		ArrayList<Producto> listadoProductos = new ArrayList<Producto>();

		while ( resultado.next() ) {
			int id = resultado.getInt("id");
			String tipo = resultado.getString("tipo");
			String marca = resultado.getString("marca");
			String modelo = resultado.getString("modelo");
			String socketcpu = resultado.getString("socketcpu");
			String tiporam = resultado.getString("tiporam");
			String pci = resultado.getString("pci");
			String sata = resultado.getString("sata");
			String velocidad = resultado.getString("velocidad");
			String tamanio = resultado.getString("tamanio");
			String rendimiento = resultado.getString("rendimiento");
			int consumo = resultado.getInt("consumo");
			int precio = resultado.getInt("precio");
			String urlimagen = resultado.getString("urlimagen");
			
			
			Producto x = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
			listadoProductos.add(x);	
		}

		template.addAttribute("listadoProductos", listadoProductos);
		
	}
	
	public static void crearListadoEspecifico(HttpSession session,Model template, Connection connection, int offset, String tiposql) throws SQLException{
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		if (logeado == null) {
			
			template.addAttribute("esta-logeado", false);
			
		} else {
			template.addAttribute("esta-logeado", true);
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
			template.addAttribute("nick", logeado.getNick());
			
				if (logeado.isAdministrador() == true) {
					
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
					template.addAttribute("admin", "administrar");
				}
		}
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta.setString(1, tiposql);
		consulta.setInt(2, offset);
		ResultSet resultado = consulta.executeQuery();

		ArrayList<Producto> listadoProductos = new ArrayList<Producto>();

		while ( resultado.next() ) {
			int id = resultado.getInt("id");
			String tipo = resultado.getString("tipo");
			String marca = resultado.getString("marca");
			String modelo = resultado.getString("modelo");
			String socketcpu = resultado.getString("socketcpu");
			String tiporam = resultado.getString("tiporam");
			String pci = resultado.getString("pci");
			String sata = resultado.getString("sata");
			String velocidad = resultado.getString("velocidad");
			String tamanio = resultado.getString("tamanio");
			String rendimiento = resultado.getString("rendimiento");
			int consumo = resultado.getInt("consumo");
			int precio = resultado.getInt("precio");
			String urlimagen = resultado.getString("urlimagen");
			
			
			Producto x = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
			listadoProductos.add(x);	
		}

		template.addAttribute("listadoProductos", listadoProductos);
		
	}
	
	public static boolean CheckearCantidad(HttpSession session,Model template, Connection connection, int offset) throws SQLException{
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos ORDER BY tipo LIMIT 8 OFFSET ?;");
		
		consulta.setInt(1, offset + 8);
		
		
		ResultSet resultado = consulta.executeQuery();
		if (resultado.next()){
			return true;
			
		} else {
			return false;
		}
	}	
	
	public static boolean CheckearCantidadEspecifica(HttpSession session,Model template, Connection connection, int offset, String tiposql) throws SQLException{
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
		
		consulta.setString(1, tiposql);
		
		consulta.setInt(2, offset + 8);
		
		
		ResultSet resultado = consulta.executeQuery();
		if (resultado.next()){
			return true;
			
		} else {
			return false;
		}
	}
	
	
	public static void checkearHeader(Usuario logeado, Model template) throws SQLException{
		if (logeado == null) {
			template.addAttribute("estaLogeado", false);
		} else {
			template.addAttribute("estaLogeado", true);
		
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
			template.addAttribute("nick", logeado.getNick());
			
				if (logeado.isAdministrador() == true) {
					
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
				}
		}
	}
	
public static void crearListadoOfertas(HttpSession session,Model template, Connection connection, int offset) throws SQLException{
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE enoferta = ? ORDER BY tipo LIMIT 4 OFFSET ?;");
		consulta.setBoolean(1, true);
		consulta.setInt(2, offset);
		ResultSet resultado = consulta.executeQuery();

		ArrayList<Producto> listadoProductos = new ArrayList<Producto>();

		while ( resultado.next() ) {
			int id = resultado.getInt("id");
			String tipo = resultado.getString("tipo");
			String marca = resultado.getString("marca");
			String modelo = resultado.getString("modelo");
			String socketcpu = resultado.getString("socketcpu");
			String tiporam = resultado.getString("tiporam");
			String pci = resultado.getString("pci");
			String sata = resultado.getString("sata");
			String velocidad = resultado.getString("velocidad");
			String tamanio = resultado.getString("tamanio");
			String rendimiento = resultado.getString("rendimiento");
			int consumo = resultado.getInt("consumo");
			int precio = resultado.getInt("precio");
			String urlimagen = resultado.getString("urlimagen");
			
			
			Producto x = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
			listadoProductos.add(x);	
		}

		template.addAttribute("listadoProductos", listadoProductos);
		
	}
	
}
	
	
	

