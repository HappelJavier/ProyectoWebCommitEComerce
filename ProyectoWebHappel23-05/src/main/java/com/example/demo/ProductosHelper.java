package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.model.Producto;
import com.example.model.Usuario;


public class ProductosHelper {
	
	public static void crearListado(HttpSession session,Model template, Connection connection) throws SQLException{
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		if (logeado == null) {
			template.addAttribute("header", "header");
		} else {
			template.addAttribute("header", "headerLogeado");
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
			template.addAttribute("nick", logeado.getNick());
			
				if (logeado.isAdministrador() == true) {
					
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
					template.addAttribute("admin", "administrar");
				}
		}

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos ORDER BY tipo;");

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
			String consumo = resultado.getString("consumo");
			int precio = resultado.getInt("precio");
			String urlimagen = resultado.getString("urlimagen");
			
			
			Producto x = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
			listadoProductos.add(x);	
		}

		template.addAttribute("listadoProductos", listadoProductos);
		connection.close();
		
		
	}
	
	public static void checkearHeader(Usuario logeado, Model template) throws SQLException{
		if (logeado == null) {
			template.addAttribute("header", "header");
		} else {
			template.addAttribute("header", "headerLogeado");
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
			template.addAttribute("nick", logeado.getNick());
			
				if (logeado.isAdministrador() == true) {
					
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
				}
		}
	}
	
	
	
}
