package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.example.model.Producto;


public class Cargar {
	
	public static Producto producto(Connection connection, Model template, int id) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE id=?;");
		consulta.setInt(1, id);
		ResultSet resultado = consulta.executeQuery();
		Producto producto = WhileLoop.productoBuilder(resultado, template);
		return producto;
	}
	
	public static void mothers(Connection connection, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='motherboard';");
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMothers", listadoProductos);
	}
	
	public static void motherMarca(Connection connection, Model template, String brand) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='motherboard' AND marca = ?;");
		consulta.setString(1, brand);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMothers", listadoProductos);
	}
	
	public static void motherSocket(Connection connection, Model template, String socket) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='motherboard' AND socketcpu = ?;");
		consulta.setString(1, socket);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMothers", listadoProductos);
	}
	
	public static void motherRam(Connection connection, Model template, String ddr) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='motherboard' AND tiporam = ? ;");
		consulta.setString(1, ddr);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMothers", listadoProductos);
	}
	
	public static void motherRamSocket(Connection connection, Model template, String ddr, String socket) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='motherboard' AND tiporam = ? AND socketcpu = ?;");
		consulta.setString(1, ddr);
		consulta.setString(2, socket);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMothers", listadoProductos);
	}
	
	public static void motherRamMarca(Connection connection, Model template, String ddr, String brand) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='motherboard' AND tiporam = ? AND marca = ?;");
		consulta.setString(1, ddr);
		consulta.setString(2, brand);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMothers", listadoProductos);
	}
	
	
	public static void micros(Connection connection, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='microprocesador';");
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMicros", listadoProductos);
	}
	
	public static void microMarca(Connection connection, Model template, String brand) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='microprocesador' AND marca = ?;");
		consulta.setString(1, brand);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMicros", listadoProductos);
	}
	
	public static void microSocket(Connection connection, Model template, String socket) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='microprocesador' AND socketcpu = ?;");
		consulta.setString(1, socket);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoMicros", listadoProductos);
	}
	
	
	public static void gpu(Connection connection, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='placa de video';");
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoGpus", listadoProductos);
	}
	
	public static void gpuMarca(Connection connection, Model template, String brand) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='placa de video' AND marca = ?;");
		consulta.setString(1, brand);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoGpus", listadoProductos);
	}
	
	public static void rams(Connection connection, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='memoria';");
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoRams", listadoProductos);
	}
	
	public static void ramSocket(Connection connection, Model template, String ddr) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='memoria' AND tiporam = ?;");
		consulta.setString(1, ddr);
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoRams", listadoProductos);
	}
	
	
	
	public static void discos(Connection connection, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='disco duro';");
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoDiscos", listadoProductos);
	}

	
	public static void fuentes(Connection connection, Model template) throws SQLException {
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE tipo='fuente';");
		ResultSet resultado = consulta.executeQuery();
		ArrayList<Producto> listadoProductos= new ArrayList<Producto>();
		WhileLoop.ArrayListBuilder(resultado, template, listadoProductos);
		template.addAttribute("listadoFuentes", listadoProductos);
	}
	
}
