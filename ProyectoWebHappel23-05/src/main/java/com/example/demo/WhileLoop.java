package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.example.model.Producto;


public class WhileLoop {
	
	public static void ArrayListBuilder(ResultSet resultado, Model template, ArrayList<Producto> listadoProductos) throws SQLException {
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
	}
	
	public static Producto productoBuilder(ResultSet resultado, Model template) throws SQLException {
		resultado.next();
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
		return x;
	}
	
}
