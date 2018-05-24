package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.example.model.Usuario;

public class UsuarioHelper {
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
