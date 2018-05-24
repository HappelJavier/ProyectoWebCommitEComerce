package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.example.model.Comentario;
import com.example.model.Subcomentario;
import com.example.model.Usuario;


public class ComentariosHelper {

	
	public static void cargarComentarios(Connection connection, int id, Model template) throws SQLException {
		ArrayList<Comentario> listadoComentarios= new ArrayList<Comentario>();
		PreparedStatement consulta2 = connection.prepareStatement("SELECT * FROM comentarios WHERE id_producto_padre = ? AND id_comentario_padre = '0';");
		consulta2.setInt(1, id);
		ResultSet resultado2 = consulta2.executeQuery();
		
		while ( resultado2.next() ) {
			
			int id2 = resultado2.getInt("id");
			int id_usuario_emisor = resultado2.getInt("id_usuario_emisor");
			int id_usuario_receptor = resultado2.getInt("id_usuario_receptor");
			int id_producto_padre = resultado2.getInt("id_producto_padre");
			int id_comentario_padre = resultado2.getInt("id_comentario_padre");
			String fecha = "El " + resultado2.getString("fecha");
			String contenido = resultado2.getString("contenido");
			
			Usuario usuario = SQLHelper.sqlUsuario(id_usuario_emisor, connection );
			
			template.addAttribute("nick", usuario.getNick());
			template.addAttribute("fecha", fecha);
			
			
			PreparedStatement consulta3 = connection.prepareStatement("SELECT * FROM comentarios WHERE id_comentario_padre = ? ;");
			consulta3.setInt(1, id2);
			ResultSet resultado3 = consulta3.executeQuery();
			ArrayList<Subcomentario> subcomentarios = new ArrayList<Subcomentario>();
		
			while (resultado3.next() ) {
				int id3 = resultado3.getInt("id");
				int id_usuario_emisor3 = resultado3.getInt("id_usuario_emisor");
				int id_usuario_receptor3 = resultado3.getInt("id_usuario_receptor");
				int id_producto_padre3 = resultado3.getInt("id_producto_padre");
				int id_comentario_padre3 = resultado3.getInt("id_comentario_padre");
				String fecha3 = resultado3.getString("fecha");
				String contenido3 = resultado3.getString("contenido");
				Subcomentario y = new Subcomentario(id3, id_usuario_receptor3, id_usuario_emisor3, id_producto_padre3, id_comentario_padre3, fecha3, contenido3);
				subcomentarios.add(y);	
				
			}
		
			Comentario x = new Comentario (id2, id_usuario_receptor, id_usuario_emisor, id_producto_padre, id_comentario_padre, fecha, contenido, subcomentarios);
			listadoComentarios.add(x);	
			
		}
		
		template.addAttribute("listadoComentarios", listadoComentarios);
	}
	
	
}
