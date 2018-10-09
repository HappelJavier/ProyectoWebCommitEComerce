package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.model.Usuario;

public class Usuarios {
	
	public static void insertarUsuarios (Connection connection, String nombre, String apellido, String nick, String contrasenia, String mail, boolean admin) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO usuarios(nombre, apellido, nick, contrasenia, mail, administrador) VALUES( ?, ?, ?, ?, ?, ?); ");
		consulta.setString(1, nombre);
		consulta.setString(2, apellido);
		consulta.setString(3, nick);
		consulta.setString(4, contrasenia);
		consulta.setString(5, mail);
		consulta.setBoolean(6, admin);
		consulta.executeUpdate();
	}
	
	public static void cargarListadoUsuarios (Connection connection, Model template) throws SQLException {
		PreparedStatement consultaUsuarios = connection.prepareStatement("SELECT * FROM usuarios ORDER BY id ASC;");

		ResultSet resultadoUsuarios = consultaUsuarios.executeQuery();

		ArrayList<Usuario> listadoUsuarios = new ArrayList<Usuario>();

		while ( resultadoUsuarios.next() ) {
			int idUsuario = resultadoUsuarios.getInt("id");
			String nombreUsuario = resultadoUsuarios.getString("nombre");
			String contrasenia = resultadoUsuarios.getString("contrasenia");
			String nick = resultadoUsuarios.getString("nick");
			String mail = resultadoUsuarios.getString("mail");
			String idpc = resultadoUsuarios.getString("idpc");
			String imagen_de_perfil = resultadoUsuarios.getString("imagen_de_perfil");
			Boolean administrador = resultadoUsuarios.getBoolean("administrador");
			Usuario y = new Usuario (idUsuario, nombreUsuario, contrasenia, nick, mail, idpc, imagen_de_perfil, administrador);
			listadoUsuarios.add(y);	
		}

		template.addAttribute("listadoUsuarios", listadoUsuarios);

	}
	
	public static void cargarUsuario(Connection connection, int id, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM usuarios WHERE id = ?;");
		consulta.setInt(1, id);
		
		ResultSet resultado = consulta.executeQuery();

		while ( resultado.next() ) {
			String nombre = resultado.getString("nombre");
			String apellido = resultado.getString("apellido");
			String nick = resultado.getString("nick");
			String contrasenia = resultado.getString("contrasenia");
			String mail = resultado.getString("mail");
			String idpc = resultado.getString("idpc");
			String imagen_de_perfil = resultado.getString("imagen_de_perfil");
			Boolean administrador = resultado.getBoolean("administrador");
			template.addAttribute("id", id);
			template.addAttribute("nombre", nombre);
			template.addAttribute("apellido", apellido);
			template.addAttribute("nick", nick);
			template.addAttribute("contrasenia", contrasenia);
			template.addAttribute("mail", mail);
			template.addAttribute("idpc", idpc);
			template.addAttribute("imagen_de_perfil", imagen_de_perfil);
			template.addAttribute("administrador", administrador);
		}
	}
	
	public static boolean intentarLogearse(HttpSession session, String nick, String contrasenia, Connection connection) throws SQLException{

	
		PreparedStatement consulta = connection
				.prepareStatement("SELECT * FROM usuarios WHERE nick = ? AND contrasenia = ? ");
		consulta.setString(1, nick);
		consulta.setString(2, contrasenia);
		ResultSet resultado = consulta.executeQuery();
		
		
		if (resultado.next() ){
			int id = resultado.getInt("id");
			session.setAttribute("id", id);
			String codigo = UUID.randomUUID().toString();
			session.setAttribute("codigo-autorizacion", codigo); //sesion id
			PreparedStatement consulta2 = connection
					.prepareStatement("UPDATE usuarios SET codigo = ? WHERE nick = ?");
			consulta2.setString(1, codigo);
			consulta2.setString(2, nick);
			consulta2.executeUpdate();
			
			
			return true;
		} else {
			return false;
		}
	
	}
	
	public static Usuario usuarioLogeado(HttpSession session, Connection connection) throws SQLException{
		String codigo =  (String)session.getAttribute("codigo-autorizacion");
		
		if (codigo != null){
		
			PreparedStatement consulta = connection
					.prepareStatement("SELECT * FROM usuarios WHERE codigo = ?");
			consulta.setString(1, codigo);
			ResultSet resultado = consulta.executeQuery();
			
			if (resultado.next()){
				int id = resultado.getInt("id");
				session.setAttribute("id", id);
				Usuario logueado = new Usuario (resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("contrasenia"), resultado.getString("nick"), resultado.getString("imagen_de_perfil"), resultado.getBoolean("administrador"));
				
				return logueado;
			
			} else {
				return null;
			}

		} else {
			return null;
		}
	
	}
	
	public static void cerrarSesion(HttpSession session, Connection connection) throws SQLException{
		
		String codigo = (String)session.getAttribute("codigo-autorizacion");
		session.removeAttribute("codigo-autorizacion");
		


		PreparedStatement consulta = connection
				.prepareStatement("UPDATE usuarios SET codigo = null WHERE codigo = ?");
		consulta.setString(1, codigo);
		consulta.executeUpdate();

	}
	
}
