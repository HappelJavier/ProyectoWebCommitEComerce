package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.example.model.Computadora2;
import com.example.model.Producto;
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
	
public static void cargarProductos(Connection connection, Model template) throws SQLException {
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos;");
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
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
		
	public static void nuevaPc(Connection connection) throws SQLException {
			
		PreparedStatement insertPC = connection.prepareStatement("INSERT INTO pc (mother,micro,gpu,disco,ram1,ram2,ram3,ram4) VALUES(null,null,null,null,null,null,null,null);");
		insertPC.executeUpdate();
		PreparedStatement consultaPC = connection.prepareStatement("SELECT FROM pc;");
		ResultSet resultadoPC = consultaPC.executeQuery();
		resultadoPC.next();
		
		
		
	}

	public static Computadora2 cargarPc(Connection connection, Model template) throws SQLException {
		
		PreparedStatement consultaPC = connection.prepareStatement("SELECT * FROM pc WHERE id=2;");
		ResultSet resultadoPC = consultaPC.executeQuery();
		resultadoPC.next();
		int id = resultadoPC.getInt("id");
		int mother = resultadoPC.getInt("mother");
		int micro = resultadoPC.getInt("micro");
		int gpu = resultadoPC.getInt("gpu");
		int disco = resultadoPC.getInt("disco");
		int fuente = resultadoPC.getInt("fuente");
		int ram1 = resultadoPC.getInt("ram1");
		int ram2 = resultadoPC.getInt("ram2");
		int ram3 = resultadoPC.getInt("ram3");
		int ram4 = resultadoPC.getInt("ram4");
		
		if(mother!=0) {SQLHelper.cargarProducto(connection, mother, template, "mother");}
		if(micro!=0) {SQLHelper.cargarProducto(connection, micro, template, "micro");}
		if(gpu!=0) {SQLHelper.cargarProducto(connection, gpu, template, "gpu");}
		if(disco!=0) {SQLHelper.cargarProducto(connection, disco, template, "disco");}
		if(fuente!=0) {SQLHelper.cargarProducto(connection, fuente, template, "fuente");}
		if(ram1!=0) {SQLHelper.cargarProducto(connection, ram1, template, "ram1");}
		if(ram2!=0) {SQLHelper.cargarProducto(connection, ram2, template, "ram2");}
		if(ram3!=0) {SQLHelper.cargarProducto(connection, ram3, template, "ram3");}
		if(ram4!=0) {SQLHelper.cargarProducto(connection, ram4, template, "ram4");}
		
		Computadora2 pc = new Computadora2 (id, mother, micro, gpu, 
				disco, fuente, ram1, ram2, ram3, ram4);
		
		template.addAttribute("pc", pc);
		boolean bRam2 ;
		boolean bRam3 ;
		boolean bRam4 ;
		
		if(ram1!=0 && ram2==0|| ram1!=0 && ram3!=0 || ram1!=0 && ram3==0 && ram4!=0) {
			bRam2 = true;
		} else {
			bRam2 = false;
		}
		
		if(ram1!=0 && ram2!=0 && ram3==0|| ram1!=0 && ram2!=0 && ram4!=0) {
			bRam3 = true;
		} else {
			bRam3 = false;
		}
		if(ram1!=0 && ram2!=0 && ram3!=0 && ram4==0) {
			bRam4 = true;
		} else {
			bRam4 = false;
		}
		template.addAttribute("bram2", bRam2);
		template.addAttribute("bram3", bRam3);
		template.addAttribute("bram4", bRam4);
		
		return pc;
	}
	
	public static void cargarProducto(Connection connection, int idProducto, Model template) throws SQLException {
		PreparedStatement consulta= connection.prepareStatement("SELECT * FROM productos WHERE id=?;");
		consulta.setInt(1, idProducto);
		ResultSet resultado= consulta.executeQuery();
		resultado.next();
		int id = resultado.getInt("id");
		String tipo= resultado.getString("tipo");
		String marca= resultado.getString("marca");
		String modelo= resultado.getString("modelo");
		String socketcpu= resultado.getString("socketcpu");
		String tiporam= resultado.getString("tiporam");
		String pci = resultado.getString("pci");
		String sata = resultado.getString("sata");
		String velocidad = resultado.getString("velocidad");
		String tamanio = resultado.getString("tamanio");
		String rendimiento = resultado.getString("rendimiento");
		int consumo = resultado.getInt("consumo");
		int precio = resultado.getInt("precio");
		String urlimagen = resultado.getString("urlimagen");
		Producto motherboard = new Producto (id, tipo, marca, modelo, 
		socketcpu, tiporam, pci, sata, velocidad, tamanio, 
		rendimiento, consumo, precio, urlimagen);
		template.addAttribute(tipo, motherboard);
	}
	
	public static void cargarProducto(Connection connection, int idProducto, Model template, String nombre) throws SQLException {
		PreparedStatement consulta= connection.prepareStatement("SELECT * FROM productos WHERE id=?;");
		consulta.setInt(1, idProducto);
		ResultSet resultado= consulta.executeQuery();
		resultado.next();
		int id = resultado.getInt("id");
		String tipo= resultado.getString("tipo");
		String marca= resultado.getString("marca");
		String modelo= resultado.getString("modelo");
		String socketcpu= resultado.getString("socketcpu");
		String tiporam= resultado.getString("tiporam");
		String pci = resultado.getString("pci");
		String sata = resultado.getString("sata");
		String velocidad = resultado.getString("velocidad");
		String tamanio = resultado.getString("tamanio");
		String rendimiento = resultado.getString("rendimiento");
		int consumo = resultado.getInt("consumo");
		int precio = resultado.getInt("precio");
		String urlimagen = resultado.getString("urlimagen");
		Producto Producto = new Producto (id, tipo, marca, modelo, 
		socketcpu, tiporam, pci, sata, velocidad, tamanio, 
		rendimiento, consumo, precio, urlimagen);
		template.addAttribute(nombre, Producto);
	}
	
}
