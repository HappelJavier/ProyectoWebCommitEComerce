package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Juego;
import com.example.model.Producto;
import com.example.model.Usuario;

@Controller
public class ProductosController {
	
	@Autowired
	private Environment env;
	
	@GetMapping("/")
	public String index(HttpSession session, Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		
		ProductosHelper.checkearHeader(logeado, template);
		
		return "index";
	}
	
	@GetMapping("/administrar")
	public String administrar(HttpSession session,Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos;");

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
		
		return "administrar";
	}
	
	@GetMapping("/registro-producto")
	public String registro(HttpSession session,Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
		
		return "registro-producto";
	}
	
	@GetMapping("/registrar-juego")
	public String registrarJuego(@RequestParam String nombre,@RequestParam String desarrollador,@RequestParam String fecha,@RequestParam String plataforma, @RequestParam String genero,@RequestParam int rencpu1, @RequestParam int rencpu2, @RequestParam int rengpu1, @RequestParam int rengpu2, @RequestParam int vram1, @RequestParam int vram2, @RequestParam int ram1, @RequestParam int ram2, @RequestParam int precio,@RequestParam String urlimagen) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		
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
		connection.close();

		return "redirect:/registro-producto";
	}
	
	@GetMapping("/registrar-producto")
	public String registrarProducto(@RequestParam String tipo, @RequestParam String marca, @RequestParam String modelo,
			@RequestParam String socketcpu, @RequestParam String tiporam, @RequestParam String pci,
			@RequestParam String sata, @RequestParam String velocidad, @RequestParam String tamanio,
			@RequestParam String rendimiento, @RequestParam String consumo, @RequestParam int precio, @RequestParam String urlimagen) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); "
				+ "UPDATE productos SET compram = null, comphd = null, tamaniomemoria = null WHERE tipo = 'microprocesador'; "
				+ "UPDATE productos SET compcpu = null, comphd = null, velocidad = null WHERE tipo = 'placa de video'; "
				+ "UPDATE productos SET compcpu = null, compram = null, velocidad = null , rendimiento = null WHERE tipo = 'disco duro';"
				+ " UPDATE productos SET tamaniomemoria = null, velocidad = null , rendimiento = null WHERE tipo = 'motherboard';");
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

		connection.close();

		return "redirect:/registro-producto";
	}
	
	@GetMapping("/subir-imagen")
	public String subirImagen(@RequestParam String inputUrlImagen, HttpSession session) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session,connection);

		PreparedStatement consulta = connection.prepareStatement(
			"UPDATE usuarios SET imagen_de_perfil = ? WHERE id = ?");
		consulta.setString(1, inputUrlImagen);
		consulta.setInt(2, logeado.getId());
		consulta.executeUpdate();
		connection.close();
		return "redirect:/perfil/"+logeado.getNick();
	}
		
	@GetMapping("/comparar-pc")
	public String compararPc(HttpSession session,Model template, @RequestParam int micro, @RequestParam int placa, @RequestParam int memoria1, @RequestParam int memoria2, @RequestParam int mother, @RequestParam int disco, @RequestParam int juego) throws SQLException, ParseException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
		
		
		template.addAttribute("micro", micro);
		template.addAttribute("placa", placa);
		template.addAttribute("memoria1", memoria1);
		template.addAttribute("memoria2", memoria2);
		template.addAttribute("mother", mother);
		template.addAttribute("disco", disco);
		template.addAttribute("juego", juego);
		
		
		PreparedStatement consultaMother = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
		consultaMother.setInt(1, mother);
		ResultSet motherboard = consultaMother.executeQuery();
		motherboard.next();
		String tipomother = motherboard.getString("tipo");
		String marcamother = motherboard.getString("marca");
		String modelomother = motherboard.getString("modelo");
		int preciomother = motherboard.getInt("precio");
		String urlimagenmother = motherboard.getString("urlimagen");
		template.addAttribute("tipomother", tipomother);
		template.addAttribute("marcamother", marcamother);
		template.addAttribute("modelomother", modelomother);
		template.addAttribute("preciomother", preciomother);
		template.addAttribute("urlimagenmother", urlimagenmother);
		
		PreparedStatement consultaHD = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
		consultaHD.setInt(1, disco);
		ResultSet HD = consultaHD.executeQuery();
		HD.next();
		String tipoHD = HD.getString("tipo");
		String marcaHD = HD.getString("marca");
		String modeloHD = HD.getString("modelo");
		int precioHD = HD.getInt("precio");
		String urlimagenHD = HD.getString("urlimagen");
		template.addAttribute("tipoHD", tipoHD);
		template.addAttribute("marcaHD", marcaHD);
		template.addAttribute("modeloHD", modeloHD);
		template.addAttribute("precioHD", precioHD);
		template.addAttribute("urlimagenHD", urlimagenHD);
		
		
		PreparedStatement consultaPlaca = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
		consultaPlaca.setInt(1, placa);
		ResultSet renPlacaDeVideo = consultaPlaca.executeQuery();
		renPlacaDeVideo.next();
		String tipogpu = renPlacaDeVideo.getString("tipo");
		String marcagpu = renPlacaDeVideo.getString("marca");
		String modelogpu = renPlacaDeVideo.getString("modelo");
		int preciogpu = renPlacaDeVideo.getInt("precio");
		String urlimagengpu = renPlacaDeVideo.getString("urlimagen");
		template.addAttribute("tipogpu", tipogpu);
		template.addAttribute("marcagpu", marcagpu);
		template.addAttribute("modelogpu", modelogpu);
		template.addAttribute("preciogpu", preciogpu);
		template.addAttribute("urlimagengpu", urlimagengpu);
		
		String renPlacaDeVideoX = renPlacaDeVideo.getString("rendimiento");
		String renPlacaDeVideoY = renPlacaDeVideoX.replaceAll( "[^\\d]", "" );
		int renPlacaDeVideoZ= Integer.parseInt(renPlacaDeVideoY);
		template.addAttribute("renPlacaDeVideo", renPlacaDeVideoZ);
		
		String vramX = renPlacaDeVideo.getString("tamanio");
		String vramY = vramX.replaceAll( "[^\\d]", "" );
		int vramZ= Integer.parseInt(vramY);
		template.addAttribute("vram", vramZ);


		PreparedStatement consultaMicro = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
		consultaMicro.setInt(1, micro);
		ResultSet renProcesador = consultaMicro.executeQuery();
		renProcesador.next();
		String tipocpu = renProcesador.getString("tipo");
		String marcacpu = renProcesador.getString("marca");
		String modelocpu = renProcesador.getString("modelo");
		int preciocpu = renProcesador.getInt("precio");
		String urlimagencpu = renProcesador.getString("urlimagen");
		template.addAttribute("tipocpu", tipocpu);
		template.addAttribute("marcacpu", marcacpu);
		template.addAttribute("modelocpu", modelocpu);
		template.addAttribute("preciocpu", preciocpu);
		template.addAttribute("urlimagencpu", urlimagencpu);	
		String renProcesadorX = renProcesador.getString("rendimiento");
		String renProcesadorY = renProcesadorX.replaceAll( "[^\\d]", "" );
		int renProcesadorZ= Integer.parseInt(renProcesadorY);
		template.addAttribute("renProcesador", renProcesadorZ);	
		
		int rampc;
		int preciorampc2;
		
		PreparedStatement consultaRampc1 = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
		consultaRampc1.setInt(1, memoria1);
		ResultSet rampc1 = consultaRampc1.executeQuery();
		rampc1.next();
		String tiporampc1 = rampc1.getString("tipo");
		String marcarampc1 = rampc1.getString("marca");
		String modelorampc1 = rampc1.getString("modelo");
		int preciorampc1 = rampc1.getInt("precio");
		String urlimagenrampc1 = rampc1.getString("urlimagen");	
		template.addAttribute("tiporam", tiporampc1);
		template.addAttribute("marcaram", marcarampc1);
		template.addAttribute("modeloram", modelorampc1);
		template.addAttribute("precioram", preciorampc1);
		template.addAttribute("urlimagenram", urlimagenrampc1);
		String rampc1X = rampc1.getString("tamanio");
		template.addAttribute("tamanio1", rampc1X);
		String rampc1Y = rampc1X.replaceAll( "[^\\d]", "" );
		int rampc1Z= Integer.parseInt(rampc1Y);
		template.addAttribute("ram1", rampc1Z);
		
		
		if (memoria2 != 0) {
		PreparedStatement consultaRampc2 = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
		consultaRampc2.setInt(1, memoria2);
		ResultSet rampc2 = consultaRampc2.executeQuery();
		rampc2.next();
		String tiporampc2 = rampc2.getString("tipo");
		String marcarampc2 = rampc2.getString("marca");
		String modelorampc2 = rampc2.getString("modelo");
		preciorampc2 = rampc2.getInt("precio");
		String urlimagenrampc2 = rampc2.getString("urlimagen");
		template.addAttribute("tiporam2", tiporampc2);
		template.addAttribute("marcaram2", marcarampc2);
		template.addAttribute("modeloram2", modelorampc2);
		template.addAttribute("precioram2", preciorampc2);
		template.addAttribute("urlimagenram", urlimagenrampc2);
		String rampc2X = rampc2.getString("tamanio");
		template.addAttribute("tamanio2", rampc2X);
		String rampc2Y = rampc2X.replaceAll( "[^\\d]", "" );
		int rampc2Z= Integer.parseInt(rampc2Y);
		template.addAttribute("ram2pc", rampc2Z);
		rampc =rampc1Z + rampc2Z;} else {
			preciorampc2 = 0;
			rampc = rampc1Z;
		}
		
		
		int precioPC = preciomother + preciocpu + preciogpu + precioHD + preciorampc1+ preciorampc2;
		template.addAttribute("precioPC", precioPC);
		
		
		
		PreparedStatement consultaJuego = connection.prepareStatement("SELECT * FROM juegos WHERE id=? ");
		consultaJuego.setInt(1, juego);
		ResultSet juego1 = consultaJuego.executeQuery();
		juego1.next();
		//int id = juego1.getInt("id");
		String nombre = juego1.getString("nombre");
		//String desarrollador = juego1.getString("desarrollador");
		//String fecha = juego1.getString("fecha");
		//String plataforma = juego1.getString("plataforma");
		//String genero = juego1.getString("genero");
		int rencpu1 = juego1.getInt("rencpu1");
		int rencpu2 = juego1.getInt("rencpu2");
		int rengpu1 = juego1.getInt("rengpu1");
		int rengpu2 = juego1.getInt("rengpu2");
		int vram1 = juego1.getInt("vram1");
		int vram2 = juego1.getInt("vram2");
		int ram1 = juego1.getInt("ram1");
		int ram2 = juego1.getInt("ram2");
		int precio = juego1.getInt("precio");
		String urlimagen = juego1.getString("urlimagen");
		
		template.addAttribute("urlimagen", urlimagen);	
		template.addAttribute("nombre", nombre);
		template.addAttribute("precio", precio);
		//Juego x = new Juego(id, nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen);
		
		int ren = 5;
		
		if (renPlacaDeVideoZ>rengpu2) {	
			template.addAttribute("renPlacaDeVideoJuego", "La placa de video supera los requisitos recomendados del juego");
			ren += 3;
			template.addAttribute("ren", ren);
		} else if (renPlacaDeVideoZ==rengpu2) {
			template.addAttribute("renPlacaDeVideoJuego", "La placa de video esta ajustada a los requisitos recomendados del juego");
			ren += 2;
			template.addAttribute("ren", ren);
		} else {
			if (renPlacaDeVideoZ>rengpu1) {
				template.addAttribute("renPlacaDeVideoJuego", "La placa de video supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				ren += 0;
				template.addAttribute("ren", ren);
			} else if (renPlacaDeVideoZ==rengpu1) {
				template.addAttribute("renPlacaDeVideoJuego", "La placa de video esta ajustada a los requisitos minimos del juego");
				ren -= 1;
				template.addAttribute("ren", ren);
			} else {
				template.addAttribute("renPlacaDeVideoJuego", "La placa de video esta por debajo de los requisitos minimos del juego");	
				ren -= 3;
				template.addAttribute("ren", ren);
			}
		}
		
		if (vramZ>vram2) {
			template.addAttribute("vramZ", "y la cantidad de memoria de la misma supera los requisitos recomendados del juego");
			ren += 3;
			template.addAttribute("ren2", ren);
		} else if (vramZ==vram2) {
			template.addAttribute("vramZ", "y la cantidad de memoria de la misma esta ajustada a los requisitos recomendados del juego");
			ren += 2;
			template.addAttribute("ren2", ren);
		} else {
			if (vramZ>vram1) {
				template.addAttribute("vramZ", "y la cantidad de memoria de la misma supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				ren += 0;
				template.addAttribute("ren2", ren);
			} else if (vramZ==vram1) {
				template.addAttribute("vramZ", "y la cantidad de memoria de la misma esta ajustada a los requisitos minimos del juego");
				ren -= 1;
				template.addAttribute("ren2", ren);
			} else {
				template.addAttribute("vramZ", "y la cantidad de memoria de la misma esta por debajo de los requisitos minimos del juego");	
				ren -= 2;
				template.addAttribute("ren2", ren);
			}
		}
				
		if (renProcesadorZ>rencpu2) {
			template.addAttribute("renProcesadorZ", "el microprocesdor supera los requisitos recomendados del juego");
			ren += 3;
			template.addAttribute("ren1", ren);
		} else if (renProcesadorZ==rencpu2) {
			template.addAttribute("renProcesadorZ", "el microprocesdor esta ajustado a los requisitos recomendados del juego");
			ren += 2;
			template.addAttribute("ren1", ren);
		} else {
			if (renProcesadorZ>rencpu1) {
				template.addAttribute("renProcesadorZ", "el microprocesdor supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				ren += 0;
				template.addAttribute("ren1", ren);
			} else if (renProcesadorZ==rencpu1) {
				template.addAttribute("renProcesadorZ", "el microprocesdor esta ajustado a los requisitos minimos del juego");
				ren -= 1;
				template.addAttribute("ren1", ren);
			} else {
				template.addAttribute("renProcesadorZ", "el microprocesdor esta por debajo de los requisitos minimos del juego");	
				ren -= 2;
				template.addAttribute("ren1", ren);
			}
		}

		if (rampc>ram2) {
			template.addAttribute("ramZ", "la cantidad de memoria ram supera los requisitos recomendados del juego");
			ren += 3;
			template.addAttribute("ren", ren);
		} else if (rampc==ram2) {
			template.addAttribute("ramZ", "la cantidad de memoria ram esta ajustada a los requisitos recomendados del juego");
			ren += 2;
			template.addAttribute("ren3", ren);
		} else {
			if (rampc>ram1) {
				template.addAttribute("ramZ", "la cantidad de memoria ram supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				ren += 0;
				template.addAttribute("ren3", ren);
			} else if (rampc==ram1) {
				template.addAttribute("ramZ", "la cantidad de memoria ram esta ajustada a los requisitos minimos del juego");
				ren -= 1;
				template.addAttribute("ren3", ren);
			} else {
				template.addAttribute("ramZ", "la cantidad de memoria ram esta por debajo de los requisitos minimos del juego");	
				ren -= 2;
				template.addAttribute("ren3", ren);
			}
		}
		
		connection.close();
		
		return "pc-armada";
	}
	
	@GetMapping("/armar-pc-de-prueba")
	public String armarPcDePrueba(HttpSession session,Model template) throws SQLException {	
		
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
			
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Producto> listadoPlacaDeVideo = new ArrayList<Producto>();
		ArrayList<Producto> listadoMicroprocesador = new ArrayList<Producto>();
		ArrayList<Producto> listadoMemoria = new ArrayList<Producto>();
		ArrayList<Producto> listadoMotherboard = new ArrayList<Producto>();
		ArrayList<Producto> listadoDiscoDuro= new ArrayList<Producto>();
		
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
			
			
			if (tipo.equals("placa de video")) {
				Producto placa = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoPlacaDeVideo.add(placa);
			}
			
			if (tipo.equals("microprocesador")) {
				Producto micro = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoMicroprocesador.add(micro);
			}

			if (tipo.equals("memoria")) {
				Producto memoria = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoMemoria.add(memoria);
			}
			
			if (tipo.equals("motherboard")) {
				Producto mother = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoMotherboard.add(mother);
			}
			if (tipo.equals("disco duro")) {
				Producto disco = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoDiscoDuro.add(disco);
			}
		}
			
		template.addAttribute("listadoPlacaDeVideo", listadoPlacaDeVideo);
		template.addAttribute("listadoMicroprocesador", listadoMicroprocesador);
		template.addAttribute("listadoMemoria", listadoMemoria);
		template.addAttribute("listadoMotherboard", listadoMotherboard);
		template.addAttribute("listadoDiscoDuro", listadoDiscoDuro);
		
		PreparedStatement consulta2 = connection.prepareStatement("SELECT * FROM juegos;");

		ResultSet resultado2 = consulta2.executeQuery();

		ArrayList<Juego> listadoJuegos = new ArrayList<Juego>();
		
		while ( resultado2.next() ) {
			int id = resultado2.getInt("id");
			String nombre = resultado2.getString("nombre");
			String desarrollador = resultado2.getString("desarrollador");
			String fecha = resultado2.getString("fecha");
			String plataforma = resultado2.getString("plataforma");
			String genero = resultado2.getString("genero");
			int rencpu1 = resultado2.getInt("rencpu1");
			int rencpu2 = resultado2.getInt("rencpu2");
			int rengpu1 = resultado2.getInt("rengpu1");
			int rengpu2 = resultado2.getInt("rengpu2");
			int vram1 = resultado2.getInt("vram1");
			int vram2 = resultado2.getInt("vram2");
			int ram1 = resultado2.getInt("ram1");
			int ram2 = resultado2.getInt("ram2");
			int precio = resultado2.getInt("precio");
			String urlimagen = resultado2.getString("urlimagen");
			
			Juego juego = new Juego(id, nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen);
			listadoJuegos.add(juego);
			
			
		}
		

		
		template.addAttribute("listadoJuegos", listadoJuegos);
		
		connection.close();
		
		return "armarPc";
	}
		
	@GetMapping("/busqueda")
	public String busqueda(HttpSession session, Model template , @RequestParam String busqueda) throws SQLException {

		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
			
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM productos WHERE tipo LIKE LOWER (?) OR modelo LIKE LOWER (?) OR  marca LIKE LOWER (?) ;");

		consulta.setString(1, "%"+ busqueda +"%");
		consulta.setString(2, "%"+ busqueda +"%");
		consulta.setString(3, "%"+ busqueda +"%");
		
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
		
		return "listadoProductos";
	}

	@GetMapping("/listado")
	public String listado(HttpSession session,Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		ProductosHelper.crearListado(session, template, connection);
		return "listadoProductos";
	}

	@GetMapping("/modificar-producto/{id}")
	public String modificarProducto(Model template, @PathVariable int id, HttpSession session) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
		
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
		}

		connection.close();
		return "formModificar";
	}
	
	@GetMapping("/update-producto")
	public String updateProducto(Model template, HttpSession session, @RequestParam int id, @RequestParam String tipo, @RequestParam String marca, @RequestParam String modelo, @RequestParam String socketcpu, @RequestParam String tiporam, @RequestParam String pci, @RequestParam String sata, @RequestParam String velocidad, @RequestParam String tamanio, @RequestParam String rendimiento, @RequestParam String consumo, @RequestParam int precio, @RequestParam String urlimagen) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = connection.prepareStatement("UPDATE productos SET tipo = ?, marca = ?, modelo = ?, socketcpu = ?, tiporam = ?, pci = ?, sata = ?, velocidad = ?, tamanio = ?, rendimiento = ?, consumo = ?, precio = ?, urlimagen = ?  WHERE id = ? ;");

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
		consulta.setInt(14, id);
		
		
		consulta.executeUpdate();
		
		connection.close();
		
		return "redirect:/administrar";
	}
	
	@GetMapping("/eliminar-producto/{id}")
	public String eliminarProducto(Model template, @PathVariable int id) throws SQLException {
	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM productos WHERE id = ?;");

		consulta.setInt(1, id);

		consulta.executeUpdate();

		connection.close();
		return "redirect:/listado";
	}
	
	@GetMapping("/eliminar-pc/{idpc}")
	public String eliminarPc(HttpSession session, Model template, @PathVariable int idpc) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session,connection);
		
		PreparedStatement consulta = connection.prepareStatement("DELETE FROM pcusuarios WHERE id = ?;");

		consulta.setInt(1, idpc);
		
		consulta.executeUpdate();
		PreparedStatement consulta2 = connection.prepareStatement("UPDATE usuarios SET idpc = 0 WHERE id = ?;");

		consulta2.setInt(1, logeado.getId());
		
		consulta2.executeUpdate();
		
		
		connection.close();
		return "redirect:/perfil/" + logeado.getNick();
	}

	@GetMapping("/eliminar-comentario")
	public String eliminarComentario(Model template, @RequestParam int idcom, @RequestParam int id) throws SQLException {
		
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM comentarios WHERE id = ?; DELETE FROM comentarios WHERE id_comentario_padre = ?;");

		consulta.setInt(1, idcom);
		consulta.setInt(2, idcom);
		
		consulta.executeUpdate();
		
		connection.close();
		return "redirect:/detalle/" + id;
	}
	
	@GetMapping("/modificar-comentario")
	public String modificarComentario(Model template, @RequestParam int idcom, @RequestParam String comentarionuevo, @RequestParam int id) throws SQLException {
		
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("UPDATE comentarios SET contenido = ? WHERE id = ? ;");

		consulta.setString(1, comentarionuevo);
		consulta.setInt(2, idcom);
		
		consulta.executeUpdate();
		
		connection.close();
		return "redirect:/detalle/" + id;
	}
	
	@GetMapping("/placas-de-video")
	public String placasDeVideo(HttpSession session,Model template) throws SQLException {
		
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
				
		ProductosHelper.checkearHeader(logeado, template);
		
		
		PreparedStatement consulta = connection
				.prepareStatement("SELECT * FROM productos WHERE tipo = 'placa de video';");

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

		return "listadoProductos";
	}

	@GetMapping("/microprocesadores")
	public String microprocesadores(HttpSession session,Model template) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
				
		ProductosHelper.checkearHeader(logeado, template);
		
		
		PreparedStatement consulta = connection
				.prepareStatement("SELECT * FROM productos WHERE tipo = 'microprocesador';");

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

		return "listadoProductos";
	}
		
	@GetMapping("/discos-duros")
	public String discosDuros(HttpSession session,Model template) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
				
		ProductosHelper.checkearHeader(logeado, template);
		

		PreparedStatement consulta = connection
				.prepareStatement("SELECT * FROM productos WHERE tipo = 'disco duro';");

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

		return "listadoProductos";
	}
	
	@GetMapping("/motherboards")
	public String motherboards(HttpSession session,Model template) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
				
		ProductosHelper.checkearHeader(logeado, template);
		

		PreparedStatement consulta = connection
				.prepareStatement("SELECT * FROM productos WHERE tipo = 'motherboard';");

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

		return "listadoProductos";
	}

	@GetMapping("/procesar-comentario")
	public String procesarComentario(HttpSession session, Model template,@RequestParam int id, @RequestParam String comentarionuevo) throws SQLException {
			

			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
	
			Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
					
			ProductosHelper.checkearHeader(logeado, template);
			
	
			PreparedStatement consulta = connection.prepareStatement("INSERT INTO comentarios(id_producto_padre ,id_comentario_padre ,id_usuario_emisor ,id_usuario_receptor ,fecha ,contenido) VALUES(?, '0', ?, '0', '2018-05-01', ?)");

			consulta.setInt(1, id);
			consulta.setInt(2, logeado.getId());
			consulta.setString(3, comentarionuevo);
			
			consulta.executeUpdate();
			connection.close();
			
			
			
		return "redirect:/detalle/"+ id;
	}
	
	@GetMapping("/procesar-subcomentario")
	public String procesarSubomentario(HttpSession session, Model template,@RequestParam int id, @RequestParam int idcom, @RequestParam String comentarionuevo) throws SQLException {
			
	
			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
	
			Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
					
			ProductosHelper.checkearHeader(logeado, template);
			
	
			PreparedStatement consulta = connection.prepareStatement("INSERT INTO comentarios(id_producto_padre ,id_comentario_padre ,id_usuario_emisor ,id_usuario_receptor ,fecha ,contenido) VALUES(?, ?, ?, '0', '2018-05-01', ?)");

			consulta.setInt(1, id);
			consulta.setInt(2, idcom);
			consulta.setInt(3, logeado.getId());
			consulta.setString(4, comentarionuevo);
			
			consulta.executeUpdate();
			connection.close();
			
			
			
		return "redirect:/detalle/"+ id;
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(HttpSession session, Model template, @PathVariable int id) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
				
		ProductosHelper.checkearHeader(logeado, template);
		

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos WHERE id = ?;");

		consulta.setInt(1, id);

		ResultSet resultado = consulta.executeQuery();

		if (resultado.next()) {
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
			template.addAttribute("id", id);
			if (tipo.equals("placa de video")) {
					template.addAttribute("tipo", tipo);
					template.addAttribute("marca", marca);
					template.addAttribute("modelo", modelo);
					template.addAttribute("tiporam", tiporam);
					template.addAttribute("pci", pci);
					template.addAttribute("tamanio", tamanio);
					template.addAttribute("rendimiento", rendimiento);
					template.addAttribute("consumo", consumo);
					template.addAttribute("precio", precio);
					template.addAttribute("urlimagen", urlimagen);
					
					ComentariosHelper.cargarComentarios(connection, id, template);
					
					return "detalleGpu";
				}
			if (tipo.equals("microprocesador")) {
					template.addAttribute("tipo", tipo);
					template.addAttribute("marca", marca);
					template.addAttribute("modelo", modelo);
					template.addAttribute("socketcpu", socketcpu);
					template.addAttribute("velocidad", velocidad);
					template.addAttribute("rendimiento", rendimiento);
					template.addAttribute("consumo", consumo);
					template.addAttribute("precio", precio);
					template.addAttribute("urlimagen", urlimagen);
					
					ComentariosHelper.cargarComentarios(connection, id, template);
					
					return "detalleCpu";
				}
			if (tipo.equals("disco duro")) {
					template.addAttribute("tipo", tipo);
					template.addAttribute("marca", marca);
					template.addAttribute("modelo", modelo);
					template.addAttribute("sata", sata);
					template.addAttribute("tamanio", tamanio);
					template.addAttribute("consumo", consumo);
					template.addAttribute("precio", precio);
					template.addAttribute("urlimagen", urlimagen);

					ComentariosHelper.cargarComentarios(connection, id, template);
					
					return "detalleHDD";
				}
			if (tipo.equals("motherboard")) {
					template.addAttribute("tipo", tipo);
					template.addAttribute("marca", marca);
					template.addAttribute("modelo", modelo);
					template.addAttribute("socketcpu", socketcpu);
					template.addAttribute("tiporam", tiporam);
					template.addAttribute("pci", pci);
					template.addAttribute("sata", sata);
					template.addAttribute("consumo", consumo);
					template.addAttribute("precio", precio);
					template.addAttribute("urlimagen", urlimagen);

					ComentariosHelper.cargarComentarios(connection, id, template);
					
					return "detalleMother";
				}
			if (tipo.equals("memoria")) {
					template.addAttribute("tipo", tipo);
					template.addAttribute("marca", marca);
					template.addAttribute("modelo", modelo);
					template.addAttribute("tiporam", tiporam);
					template.addAttribute("velocidad", velocidad);
					template.addAttribute("tamanio", tamanio);
					template.addAttribute("rendimiento", rendimiento);
					template.addAttribute("consumo", consumo);
					template.addAttribute("precio", precio);
					template.addAttribute("urlimagen", urlimagen);

					ComentariosHelper.cargarComentarios(connection, id, template);
					
					return "detalleMemoria";
				}
		}
		
		
		connection.close();

		return "detalleProducto";
	}
	
	@GetMapping("/registrate")
	public String registrate(HttpSession session, Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		if (logeado == null) {
			template.addAttribute("header", "header");
		} else {
			template.addAttribute("header", "headerLogeado");
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
				if (logeado.isAdministrador() == true) {
					
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
					template.addAttribute("admin", "administrar");
				}
			return "redirect:/perfil"+ logeado.getNick() ;
		}
		return "formulario-registro-usuario";
	}
		
	@GetMapping("/registrar-usuario")
	public String registrarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String nick,
			@RequestParam String contrasenia, @RequestParam String mail) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO usuarios(nombre, apellido, nick, contrasenia, mail, idpc) VALUES( ?, ?, ?, ?, ?, null); ");
		consulta.setString(1, nombre);
		consulta.setString(2, apellido);
		consulta.setString(3, nick);
		consulta.setString(4, contrasenia);
		consulta.setString(5, mail);
		consulta.executeUpdate();
		connection.close();

		return "redirect:/registrate";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session, Model template) throws SQLException {	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		if (logeado == null) {
			template.addAttribute("header", "header");
		} else {
			template.addAttribute("header", "headerLogeado");
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
				if (logeado.isAdministrador() == true) {
					
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
					template.addAttribute("admin", "administrar");
				}
			return "redirect:/perfil"+ logeado.getNick() ;
		}
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws SQLException {	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		UsuarioHelper.cerrarSesion(session, connection);
		return "redirect:/login";
	}
	
	@PostMapping("/logear-usuario")
	public String logearUsuario(HttpSession session, Model template, @RequestParam String nick, @RequestParam String contrasenia) throws SQLException {
	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		boolean sePudo = UsuarioHelper.intentarLogearse(session, nick, contrasenia, connection);
			
			if (sePudo){
				Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
				return "redirect:/perfil/" + logeado.getNick();
			} else {
				return "redirect:/login";	
			}
		}

	@GetMapping("/perfil/{nick}")
	public String perfilUsuario(HttpSession session, Model template, @PathVariable String nick) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
		


		PreparedStatement consulta = connection
				.prepareStatement("SELECT * FROM usuarios WHERE id = ? ");
		consulta.setInt(1, logeado.getId());
		
		ResultSet resultado = consulta.executeQuery();
		
		if ( resultado.next() ) {
			String nombre = resultado.getString("nombre");
			String apellido = resultado.getString("apellido");
			//String nick = resultado.getString("nick");
			String contrasenia = resultado.getString("contrasenia");
			String mail = resultado.getString("mail");
			int idpc = resultado.getInt("idpc");
			String imagenDePerfil = resultado.getString("imagen_de_perfil");
			
			
			template.addAttribute("nombre", nombre);
			template.addAttribute("apellido", apellido);
			template.addAttribute("nick", nick);
			template.addAttribute("contrasenia", contrasenia);
			template.addAttribute("mail", mail);
			template.addAttribute("idpc", idpc);
			template.addAttribute("imagenDePerfil", imagenDePerfil);
			
			
			PreparedStatement consulta2 = connection
					.prepareStatement("SELECT * FROM pcusuarios WHERE id = ? ");
			consulta2.setInt(1, idpc);
			
			ResultSet resultado2 = consulta2.executeQuery();
	
			if ( resultado2.next() ) {
				int mother = resultado2.getInt("motherboard");
				int micro = resultado2.getInt("microprocesador");
				int placa = resultado2.getInt("placadevideo");
				int memoria1 = resultado2.getInt("ram1");
				int memoria2 = resultado2.getInt("ram2");
				int disco = resultado2.getInt("discoduro");
				//int idpropietario = resultado2.getInt("idpropietario");
			
				PreparedStatement consultaMother = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
				consultaMother.setInt(1, mother);
				ResultSet motherboard = consultaMother.executeQuery();
				motherboard.next();
				String tipomother = motherboard.getString("tipo");
				String marcamother = motherboard.getString("marca");
				String modelomother = motherboard.getString("modelo");
				int preciomother = motherboard.getInt("precio");
				String urlimagenmother = motherboard.getString("urlimagen");
				template.addAttribute("tipomother", tipomother);
				template.addAttribute("marcamother", marcamother);
				template.addAttribute("modelomother", modelomother);
				template.addAttribute("preciomother", preciomother);
				template.addAttribute("urlimagenmother", urlimagenmother);
				
				PreparedStatement consultaHD = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
				consultaHD.setInt(1, disco);
				ResultSet HD = consultaHD.executeQuery();
				HD.next();
				String tipoHD = HD.getString("tipo");
				String marcaHD = HD.getString("marca");
				String modeloHD = HD.getString("modelo");
				int precioHD = HD.getInt("precio");
				String urlimagenHD = HD.getString("urlimagen");
				template.addAttribute("tipoHD", tipoHD);
				template.addAttribute("marcaHD", marcaHD);
				template.addAttribute("modeloHD", modeloHD);
				template.addAttribute("precioHD", precioHD);
				template.addAttribute("urlimagenHD", urlimagenHD);
				
				
				PreparedStatement consultaPlaca = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
				consultaPlaca.setInt(1, placa);
				ResultSet renPlacaDeVideo = consultaPlaca.executeQuery();
				renPlacaDeVideo.next();
				String tipogpu = renPlacaDeVideo.getString("tipo");
				String marcagpu = renPlacaDeVideo.getString("marca");
				String modelogpu = renPlacaDeVideo.getString("modelo");
				int preciogpu = renPlacaDeVideo.getInt("precio");
				String urlimagengpu = renPlacaDeVideo.getString("urlimagen");
				template.addAttribute("tipogpu", tipogpu);
				template.addAttribute("marcagpu", marcagpu);
				template.addAttribute("modelogpu", modelogpu);
				template.addAttribute("preciogpu", preciogpu);
				template.addAttribute("urlimagengpu", urlimagengpu);

				PreparedStatement consultaMicro = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
				consultaMicro.setInt(1, micro);
				ResultSet renProcesador = consultaMicro.executeQuery();
				renProcesador.next();
				String tipocpu = renProcesador.getString("tipo");
				String marcacpu = renProcesador.getString("marca");
				String modelocpu = renProcesador.getString("modelo");
				int preciocpu = renProcesador.getInt("precio");
				String urlimagencpu = renProcesador.getString("urlimagen");
				template.addAttribute("tipocpu", tipocpu);
				template.addAttribute("marcacpu", marcacpu);
				template.addAttribute("modelocpu", modelocpu);
				template.addAttribute("preciocpu", preciocpu);
				template.addAttribute("urlimagencpu", urlimagencpu);	
				
				PreparedStatement consultaRam1 = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
				consultaRam1.setInt(1, memoria1);
				ResultSet ram1 = consultaRam1.executeQuery();
				ram1.next();
				String tiporam1 = ram1.getString("tipo");
				String marcaram1 = ram1.getString("marca");
				String modeloram1 = ram1.getString("modelo");
				int precioram1 = ram1.getInt("precio");
				String urlimagenram1 = ram1.getString("urlimagen");
				String tamanio1 = ram1.getString("tamanio");
				template.addAttribute("tiporam1", tiporam1);
				template.addAttribute("marcaram1", marcaram1);
				template.addAttribute("modeloram1", modeloram1);
				template.addAttribute("precioram1", precioram1);
				template.addAttribute("urlimagenram1", urlimagenram1);
				template.addAttribute("tamanio1", tamanio1);

				PreparedStatement consultaRam2 = connection.prepareStatement("SELECT * FROM productos WHERE id=? ");
				consultaRam2.setInt(1, memoria2);
				ResultSet ram2 = consultaRam2.executeQuery();
				ram2.next();
				String tiporam2 = ram2.getString("tipo");
				String marcaram2 = ram2.getString("marca");
				String modeloram2 = ram2.getString("modelo");
				int precioram2 = ram2.getInt("precio");
				String urlimagenram2 = ram2.getString("urlimagen");
				String tamanio2 = ram2.getString("tamanio");
				template.addAttribute("tiporam2", tiporam2);
				template.addAttribute("marcaram2", marcaram2);
				template.addAttribute("modeloram2", modeloram2);
				template.addAttribute("precioram2", precioram2);
				template.addAttribute("urlimagenram2", urlimagenram2);  
				template.addAttribute("tamanio2", tamanio2);
				
				String urlCrearPc = "/eliminar-pc/" +  idpc;
				String botonCrearPc = "eliminar tu pc";
				template.addAttribute("urlEliminarPc", urlCrearPc);
				template.addAttribute("botonEliminarPc", botonCrearPc); 

			}	else {
				String urlCrearPc = "/crea-tu-pc";
				String botonCrearPc = "crea tu pc";
				template.addAttribute("urlCrearPc", urlCrearPc);
				template.addAttribute("botonCrearPc", botonCrearPc);  
			}
			
		}

		return "perfilUsuario";
	}
	
	@PostMapping("/registrar-pc-usuario")
	public String registrarPcUsuario(HttpSession session, Model template, @RequestParam int micro, @RequestParam int placa, @RequestParam int memoria1, @RequestParam int memoria2,@RequestParam int mother, @RequestParam int disco) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		ProductosHelper.checkearHeader(logeado, template);
		
		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO pcusuarios(motherboard, microprocesador, placadevideo, ram1, ram2, discoduro, idpropietario) VALUES( ?, ?, ?, ?, ?, ?, ?); ");
		consulta.setInt(1, mother);
		consulta.setInt(2, micro);
		consulta.setInt(3, placa);
		consulta.setInt(4, memoria1);
		consulta.setInt(5, memoria2);
		consulta.setInt(6, disco);
		consulta.setInt(7, logeado.getId() );
		consulta.executeUpdate();
		
		PreparedStatement consulta2 = connection.prepareStatement(
				"SELECT * FROM pcusuarios WHERE idpropietario = ? ;");
		consulta2.setInt(1, logeado.getId());
		ResultSet resultado = consulta2.executeQuery();
		resultado.next();
		int idpc = resultado.getInt("id");
		
		PreparedStatement consulta3 = connection.prepareStatement(
				"UPDATE usuarios SET idpc = ? WHERE id = ? ;");

		consulta3.setInt(1, idpc);
		consulta3.setInt(2, logeado.getId());
		consulta3.executeUpdate();
		
		connection.close();
		
		
		return "redirect:/perfil/" + logeado.getNick();
	}
	
	@GetMapping("/crea-tu-pc")
	public String creaTuPC(HttpSession session,Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = UsuarioHelper.usuarioLogeado(session, connection);
		
		if (logeado == null) {
			template.addAttribute("header", "header");
			return "redirect:/login";
		} else {
			template.addAttribute("header", "headerLogeado");
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
				if (logeado.isAdministrador() == true) {
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
					template.addAttribute("admin", "administrar");
				}
		}
		

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Producto> listadoPlacaDeVideo = new ArrayList<Producto>();
		ArrayList<Producto> listadoMicroprocesador = new ArrayList<Producto>();
		ArrayList<Producto> listadoMemoria = new ArrayList<Producto>();
		ArrayList<Producto> listadoMotherboard = new ArrayList<Producto>();
		ArrayList<Producto> listadoDiscoDuro= new ArrayList<Producto>();
		
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
			
			
			if (tipo.equals("placa de video")) {
				Producto placa = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoPlacaDeVideo.add(placa);
			}
			
			if (tipo.equals("microprocesador")) {
				Producto micro = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoMicroprocesador.add(micro);
			}

			if (tipo.equals("memoria")) {
				Producto memoria = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoMemoria.add(memoria);
			}
			
			if (tipo.equals("motherboard")) {
				Producto mother = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoMotherboard.add(mother);
			}
			if (tipo.equals("disco duro")) {
				Producto disco = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
				listadoDiscoDuro.add(disco);
			}
		}
			
		template.addAttribute("listadoPlacaDeVideo", listadoPlacaDeVideo);
		template.addAttribute("listadoMicroprocesador", listadoMicroprocesador);
		template.addAttribute("listadoMemoria", listadoMemoria);
		template.addAttribute("listadoMotherboard", listadoMotherboard);
		template.addAttribute("listadoDiscoDuro", listadoDiscoDuro);
		
		return "crearMiPc";
	}

}
