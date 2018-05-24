package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Usuario;

public class SQLHelper {
	
	
	public static Usuario sqlUsuario(int idUsuario, Connection connection) throws SQLException{

		PreparedStatement consulta = connection
				.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
		consulta.setInt(1, idUsuario);
		ResultSet resultado = consulta.executeQuery();
		resultado.next();
		String nick = "de " + resultado.getString("nick");
		Usuario usuario = new Usuario (resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("contrasenia"), nick, resultado.getString("imagen_de_perfil"));
		
		return usuario;
	}
	
}
