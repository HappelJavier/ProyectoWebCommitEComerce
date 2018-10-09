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


public class Comentarios {

	
	public static void insertarComentario(Connection connection, int id, Usuario logeado, String fecha, String comentarionuevo) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("INSERT INTO comentarios(id_producto_padre ,id_comentario_padre ,id_usuario_emisor ,id_usuario_receptor ,fecha ,contenido) VALUES(?, '0', ?, '0', ? , ?)");
		consulta.setInt(1, id);
		consulta.setInt(2, logeado.getId());
		consulta.setString(3, fecha);
		consulta.setString(4, comentarionuevo);
		consulta.executeUpdate();
	}
	
	public static void cargarComentarios(Connection connection, int id, Model template) throws SQLException {
		ArrayList<Comentario> listadoComentarios= new ArrayList<Comentario>();
		PreparedStatement comentarios = connection.prepareStatement("SELECT * FROM comentarios WHERE id_producto_padre = ? AND id_comentario_padre = '0' ORDER BY fecha;");
		comentarios.setInt(1, id);
		ResultSet resultComentarios = comentarios.executeQuery();
		
		while ( resultComentarios.next() ) {
			
			int idComentario = resultComentarios.getInt("id");
			int idEmisor = resultComentarios.getInt("id_usuario_emisor");
			int idReceptor = resultComentarios.getInt("id_usuario_receptor");
			int idProductoPadre = resultComentarios.getInt("id_producto_padre");
			int idComentarioPadre = resultComentarios.getInt("id_comentario_padre");
			String fecha =resultComentarios.getString("fecha");
			String contenido = resultComentarios.getString("contenido");
			PreparedStatement usuarioEmisor = connection.prepareStatement("SELECT * FROM usuarios WHERE id = ? ;");
			usuarioEmisor.setInt(1, idEmisor);
			ResultSet resultEmisor = usuarioEmisor.executeQuery();
			resultEmisor.next();
			String nombreUsuario=resultEmisor.getString("nick");
			String fotoPerfil=resultEmisor.getString("imagen_de_perfil");
			
			Usuario usuario = SQLHelper.sqlUsuario(idEmisor, connection );
			
			
			
			PreparedStatement subcomentarios = connection.prepareStatement("SELECT * FROM comentarios WHERE id_comentario_padre = ? ORDER BY fecha;");
			subcomentarios.setInt(1, idComentario);
			ResultSet resultSubcomentarios = subcomentarios.executeQuery();
			ArrayList<Subcomentario> listadoSubcomentarios = new ArrayList<Subcomentario>();
		
			while (resultSubcomentarios.next() ) {
				int idSubcomentario = resultSubcomentarios.getInt("id");
				int idSubEmisor = resultSubcomentarios.getInt("id_usuario_emisor");
				PreparedStatement usuarioSubEmisor = connection.prepareStatement("SELECT * FROM usuarios WHERE id = ? ;");
				usuarioSubEmisor.setInt(1, idSubEmisor);
				ResultSet resultSubEmisor = usuarioSubEmisor.executeQuery();
				resultSubEmisor.next();
				String nombreSubUsuario=resultSubEmisor.getString("nick");
				String fotoSubPerfil=resultSubEmisor.getString("imagen_de_perfil");
				int idSubReceptor = resultSubcomentarios.getInt("id_usuario_receptor");
				int idSubProductoPadre = resultSubcomentarios.getInt("id_producto_padre");
				int idSubComentarioPadre = resultSubcomentarios.getInt("id_comentario_padre");
				String subFecha = resultSubcomentarios.getString("fecha");
				String subContenido = resultSubcomentarios.getString("contenido");
				Subcomentario y = new Subcomentario(idSubcomentario, idSubEmisor, idSubReceptor,  idSubProductoPadre, idSubComentarioPadre, subFecha, subContenido, nombreSubUsuario, fotoSubPerfil);
				listadoSubcomentarios.add(y);	
				
			}
		
			Comentario x = new Comentario (idComentario, idEmisor, idReceptor,  idProductoPadre, idComentarioPadre, fecha, contenido, listadoSubcomentarios, nombreUsuario, fotoPerfil);
			listadoComentarios.add(x);	
			
		}
		
		template.addAttribute("listadoComentarios", listadoComentarios);
	}
	
	
}
