package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Computadora2;
import com.example.model.Juego;
import com.example.model.Pagina;
import com.example.model.Producto;
import com.example.model.Usuario;

@Controller
public class MainController {
	
	
	@Autowired
	private Environment env;
	
	@GetMapping("/")
	public String index(HttpSession session, Model template) throws SQLException {
		return "index";
	}
	
	@GetMapping("/proyectos")
	public String proyectos(HttpSession session, Model template) throws SQLException {
		return "proyectos";
	}
	
	@GetMapping("/arma-tu-pc")
	public String ArmaTuPc(Model template) throws SQLException {
		return "arma-tu-pc";
	}

	@GetMapping("/home")
	public String home(HttpSession session, Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		int offset = 0;
		
		Productos.crearListadoOfertas(session, template, connection, offset);
		
		connection.close();
		
		return "home";
	}
	
	@GetMapping("/administrar")
	public String administrar(HttpSession session,Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		if (logeado == null || logeado.isAdministrador() == false) {
			return "redirect:/login";
		}
		
		Productos.checkearHeader(logeado, template);
		
		Productos.cargarListadoComponentes(connection, template);
		
		Productos.cargarListadoJuegos(connection, template);
		
		Usuarios.cargarListadoUsuarios(connection, template);
				
		connection.close();
		
		return "administrar";
	}

	@GetMapping("/contacto")
	public String contactanos(HttpSession session, Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		connection.close();
		
		return "contactanos";
	}

	@GetMapping("/insertar-producto-prueba")
	public String insertProductoPrueba() throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement(
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'microprocesador', 'amd', 'ryzen 5 2400g', 'am4', null, null, null, '3.9', null, '9369', '65', '5300', '/imagenes/ryzen-5.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'placa de video', 'nvidia geforce', 'gtx 1050 ti', null, 'ddr5', 'pci express 3.0', null, null, '4gb', '5894', '75', '7400', '/imagenes/gtx-1050.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'disco duro', 'wd', 'caviar blue', null, null, null, 'sata3', null, '1tb', null, '30', '1200', '/imagenes/wd-blue-1tb.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'motherboard', 'asrock', 'a320m-hdv', 'am4', 'ddr3', 'pci express 3.0', 'sata3', null, null, null, '50', '1700', '/imagenes/asrock-a320m-hdv.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'memoria', 'nvidia geforce', 'gtx 750 ti', null, 'ddr5', 'pci express 3.0', null, null,'2gb', '3726', '60', '3500', '/imagenes/gtx-750.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'placa de video', 'hyperx', 'fury', null, 'ddr3', null, null, '1866','4gb', null, '5', '1370', '/imagenes/hyperx-fury-blue.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'memoria', 'hyperx', 'fury', null, 'ddr3', null, null, '1866','8gb', null, '5', '2600', '/imagenes/hyperx-fury-blue-2.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'microprocesador', 'amd', 'a8-900', 'am4', null, null, null, '3.4', null, '4850', '65', '1850', '/imagenes/a8-9600.jpg');\r\n" + 
				"INSERT INTO productos(tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen) VALUES( 'microprocesador', 'amd', 'ryzen 3 2200', 'am4', null, null, null, '3.7', null, '7351', '65', '3200', '/imagenes/ryzen-3.jpg');");


		consulta.executeUpdate();

		connection.close();

		return "redirect:/administrar";
	}
		
	
	
	
	@GetMapping("/registro-juego")
	public String registroJuego(HttpSession session,Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		return "registro-juego";
	}
	
	@GetMapping("/registro-producto")
	public String registroProducto(HttpSession session,Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		connection.close();
		
		return "registro-producto";
	}
	
	@GetMapping("/registro-usuario")
	public String registroUsuario(HttpSession session,Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		connection.close();
		
		return "registro-usuario";
	}
	
	@GetMapping("/insertar-juego")
	public String insertJuego(@RequestParam String nombre,@RequestParam String desarrollador,@RequestParam String fecha,@RequestParam String plataforma, @RequestParam String genero,@RequestParam int rencpu1, @RequestParam int rencpu2, @RequestParam int rengpu1, @RequestParam int rengpu2, @RequestParam int vram1, @RequestParam int vram2, @RequestParam int ram1, @RequestParam int ram2, @RequestParam int precio,@RequestParam String urlimagen) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Productos.insertarJuego(connection, nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen);
		
		connection.close();

		return "redirect:/registro-Juego";
	}
	
	@GetMapping("/insertar-producto")
	public String insertProducto(@RequestParam String tipo, @RequestParam String marca, @RequestParam String modelo,
			@RequestParam String socketcpu, @RequestParam String tiporam, @RequestParam String pci,
			@RequestParam String sata, @RequestParam String velocidad, @RequestParam String tamanio,
			@RequestParam String rendimiento, @RequestParam String consumo, @RequestParam int precio, @RequestParam String urlimagen) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Productos.insertarComponente(connection, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);

		connection.close();

		return "redirect:/registro-producto";
	}
	
	@GetMapping("/insertar-usuario")
	public String insertUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String nick,
			@RequestParam String contrasenia, @RequestParam String mail, @RequestParam (value = "admin", required = false) boolean admin) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuarios.insertarUsuarios(connection, nombre, apellido, nick, contrasenia, mail, admin);

		connection.close();

		return "redirect:/registro-usuario";
	}

	@GetMapping("/modificar-producto/{id}")
	public String modificarProducto(Model template, @PathVariable int id, HttpSession session) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		Productos.cargarComponente(connection, id, template);

		connection.close();
		return "formModificarProducto";
	}
	
	@GetMapping("/modificar-juego/{id}")
	public String modificarJuego(Model template, @PathVariable int id, HttpSession session) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		Productos.cargarJuego(connection, id, template);

		connection.close();
		return "formModificarJuego";
	}
	
	@GetMapping("/modificar-usuario/{id}")
	public String modificarUsuario(Model template, @PathVariable int id, HttpSession session) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		Usuarios.cargarUsuario(connection, id, template);

		connection.close();
		return "formModificarUsuario";
	}
	
	@GetMapping("/update-producto")
	public String updateProducto(Model template, HttpSession session, @RequestParam int id, @RequestParam String tipo, @RequestParam String marca, @RequestParam String modelo, @RequestParam String socketcpu, @RequestParam String tiporam, @RequestParam String pci, @RequestParam String sata, @RequestParam String velocidad, @RequestParam String tamanio, @RequestParam String rendimiento, @RequestParam String consumo, @RequestParam int precio, @RequestParam String urlimagen, @RequestParam (value = "oferta", required = false) boolean oferta) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = connection.prepareStatement("UPDATE productos SET tipo = ?, marca = ?, modelo = ?, socketcpu = ?, tiporam = ?, pci = ?, sata = ?, velocidad = ?, tamanio = ?, rendimiento = ?, consumo = ?, precio = ?, urlimagen = ?, enoferta = ? WHERE id = ? ;");

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
		consulta.setBoolean(14, oferta);
		consulta.setInt(15, id);
		consulta.executeUpdate();
		
		connection.close();
		
		return "redirect:/administrar";
	}
	
	@GetMapping("/update-juego")
	public String updateJuego(Model template, HttpSession session, @RequestParam int id, @RequestParam String nombre, @RequestParam String desarrollador, @RequestParam String fecha, @RequestParam String plataforma, @RequestParam String genero, @RequestParam int rencpu1, @RequestParam int rencpu2, @RequestParam int rengpu1, @RequestParam int rengpu2, @RequestParam int vram1, @RequestParam int vram2, @RequestParam int ram1, @RequestParam int ram2, @RequestParam int precio, @RequestParam String urlimagen) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = connection.prepareStatement("UPDATE juegos SET nombre = ?, desarrollador = ?, fecha = ?, plataforma = ?, genero = ?, rencpu1 = ?, rencpu2 = ?, rengpu1 = ?, rengpu2 = ?, vram1 = ?, vram2 = ?, ram1 = ?, ram2 = ?, precio = ?, urlimagen = ?  WHERE id = ? ;");

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
		consulta.setInt(16, id);
		consulta.executeUpdate();
		
		connection.close();
		
		return "redirect:/administrar";
	}
	
	@GetMapping("/update-usuario")
	public String updateUsuario(Model template, HttpSession session, @RequestParam int id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String nick, 
			@RequestParam String contrasenia, @RequestParam String mail, @RequestParam (value = "admin", required = false) boolean admin) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = connection.prepareStatement("UPDATE usuarios SET nombre = ?, apellido = ?, nick = ?, contrasenia = ?, mail = ?, administrador = ? WHERE id = ? ;");

		consulta.setString(1, nombre);
		consulta.setString(2, apellido);
		consulta.setString(3, nick);
		consulta.setString(4, contrasenia);
		consulta.setString(5, mail);
		consulta.setBoolean(6, admin);
		consulta.setInt(7, id);
		consulta.executeUpdate();
		
		connection.close();
		
		return "redirect:/administrar";
	}
	
	@GetMapping("/eliminar-juego/{id}")
	public String eliminarJuego(Model template, @PathVariable int id) throws SQLException {
	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM juegos WHERE id = ?;");

		consulta.setInt(1, id);

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
		return "redirect:/administrar";
	}
	
	@GetMapping("/eliminar-usuario/{id}")
	public String eliminarUsuario(Model template, @PathVariable int id) throws SQLException {
	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM usuarios WHERE id = ?;");

		consulta.setInt(1, id);

		consulta.executeUpdate();

		connection.close();
		return "redirect:/administrar";
	}
	
	@GetMapping("/subir-imagen")
	public String subirImagen(@RequestParam String inputUrlImagen, HttpSession session) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session,connection);

		PreparedStatement consulta = connection.prepareStatement(
			"UPDATE usuarios SET imagen_de_perfil = ? WHERE id = ?");
		consulta.setString(1, inputUrlImagen);
		consulta.setInt(2, logeado.getId());
		consulta.executeUpdate();
		connection.close();
		return "redirect:/perfil/"+logeado.getNick();
	}
		
	@GetMapping("/comparar-pc")
	public String compararPc(HttpSession session,Model template, @RequestParam int micro, @RequestParam int placa, 
			@RequestParam int memoria1, @RequestParam int memoria2, @RequestParam int mother, @RequestParam int disco, 
			@RequestParam int juego) throws SQLException, ParseException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
		
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
		template.addAttribute("preciomother", (" $" + preciomother));
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
		template.addAttribute("precioHD", (" $" + precioHD));
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
		template.addAttribute("preciogpu", (" $" + preciogpu));
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
		template.addAttribute("preciocpu", (" $" + preciocpu));
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
		template.addAttribute("precioram", (" $" + preciorampc1));
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
		template.addAttribute("precioram2", (" $" + preciorampc2));
		template.addAttribute("urlimagenram2", urlimagenrampc2);
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
		template.addAttribute("precio", "$" +precio);
		//Juego x = new Juego(id, nombre, desarrollador, fecha, plataforma, genero, rencpu1, rencpu2, rengpu1, rengpu2, vram1, vram2, ram1, ram2, precio, urlimagen);
		
		int renplaca;
		int renmicro;
		int vram;
		int ram;
		
		if (renPlacaDeVideoZ>rengpu2) {	
			template.addAttribute("renPlacaDeVideoJuego", "La placa de video supera los requisitos recomendados del juego");
			renplaca = 90;
			template.addAttribute("renplaca", renplaca);
		} else if (renPlacaDeVideoZ==rengpu2) {
			template.addAttribute("renPlacaDeVideoJuego", "La placa de video esta ajustada a los requisitos recomendados del juego");
			renplaca = 75;
			template.addAttribute("renplaca", renplaca);
		} else {
			if (renPlacaDeVideoZ>rengpu1) {
				template.addAttribute("renPlacaDeVideoJuego", "La placa de video supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				renplaca = 60;
				template.addAttribute("renplaca", renplaca);
			} else if (renPlacaDeVideoZ==rengpu1) {
				template.addAttribute("renPlacaDeVideoJuego", "La placa de video esta ajustada a los requisitos minimos del juego");
				renplaca = 40;
				template.addAttribute("renplaca", renplaca);
			} else {
				template.addAttribute("renPlacaDeVideoJuego", "La placa de video esta por debajo de los requisitos minimos del juego");	
				renplaca = 20;
				template.addAttribute("renplaca", renplaca);
			}
		}
		
		if (vramZ>vram2) {
			template.addAttribute("vramZ", "y la cantidad de memoria de la misma supera los requisitos recomendados del juego");
			vram = 90;
			template.addAttribute("vram", vram);
		} else if (vramZ==vram2) {
			template.addAttribute("vramZ", "y la cantidad de memoria de la misma esta ajustada a los requisitos recomendados del juego");
			vram = 75;
			template.addAttribute("vram", vram);
		} else {
			if (vramZ>vram1) {
				template.addAttribute("vramZ", "y la cantidad de memoria de la misma supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				vram = 60;
				template.addAttribute("vram", vram);
			} else if (vramZ==vram1) {
				template.addAttribute("vramZ", "y la cantidad de memoria de la misma esta ajustada a los requisitos minimos del juego");
				vram = 40;
				template.addAttribute("vram", vram);
			} else {
				template.addAttribute("vramZ", "y la cantidad de memoria de la misma esta por debajo de los requisitos minimos del juego");	
				vram = 20;
				template.addAttribute("vram", vram);
			}
		}
				
		if (renProcesadorZ>rencpu2) {
			template.addAttribute("renProcesadorZ", "El microprocesdor supera los requisitos recomendados del juego");
			renmicro = 90;
			template.addAttribute("renmicro", renmicro);
		} else if (renProcesadorZ==rencpu2) {
			template.addAttribute("renProcesadorZ", "El microprocesdor esta ajustado a los requisitos recomendados del juego");
			renmicro = 75;
			template.addAttribute("renmicro", renmicro);
		} else {
			if (renProcesadorZ>rencpu1) {
				template.addAttribute("renProcesadorZ", "El microprocesdor supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				renmicro = 60;
				template.addAttribute("renmicro", renmicro);
			} else if (renProcesadorZ==rencpu1) {
				template.addAttribute("renProcesadorZ", "El microprocesdor esta ajustado a los requisitos minimos del juego");
				renmicro = 40;
				template.addAttribute("renmicro", renmicro);
			} else {
				template.addAttribute("renProcesadorZ", "El microprocesdor esta por debajo de los requisitos minimos del juego");	
				renmicro = 20;
				template.addAttribute("renmicro", renmicro);
			}
		}

		if (rampc>ram2) {
			template.addAttribute("ramZ", "La cantidad de memoria ram supera los requisitos recomendados del juego");
			ram = 90;
			template.addAttribute("ram", ram);
		} else if (rampc==ram2) {
			template.addAttribute("ramZ", "La cantidad de memoria ram esta ajustada a los requisitos recomendados del juego");
			ram = 75;
			template.addAttribute("ram", ram);
		} else {
			if (rampc>ram1) {
				template.addAttribute("ramZ", "La cantidad de memoria ram supera los requisitos minimos del juego, pero no alcanza a los requisitos recomendado");
				ram = 60;
				template.addAttribute("ram", ram);
			} else if (rampc==ram1) {
				template.addAttribute("ramZ", "La cantidad de memoria ram esta ajustada a los requisitos minimos del juego");
				ram = 40;
				template.addAttribute("ram", ram);
			} else {
				template.addAttribute("ramZ", "La cantidad de memoria ram esta por debajo de los requisitos minimos del juego");	
				ram = 20;
				template.addAttribute("ram", ram);
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

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
			
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos ORDER BY precio ASC;");

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
			int consumo = resultado.getInt("consumo");
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

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
			
		
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
			int consumo = resultado.getInt("consumo");
			int precio = resultado.getInt("precio");
			String urlimagen = resultado.getString("urlimagen");
			
			
			Producto x = new Producto (id, tipo, marca, modelo, socketcpu, tiporam, pci, sata, velocidad, tamanio, rendimiento, consumo, precio, urlimagen);
			listadoProductos.add(x);	
		}
			
		template.addAttribute("listadoProductos", listadoProductos);
		connection.close();
		
		return "listadoProductos";
	}
	
	@GetMapping("/eliminar-pc/{idpc}")
	public String eliminarPc(HttpSession session, Model template, @PathVariable int idpc) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session,connection);
		
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
	
	@ResponseBody
	@GetMapping("/eliminar-comentario-ajax")
	public String eliminarComentarioAjax(Model template, @RequestParam int idcom, @RequestParam int id) throws SQLException {
		
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM comentarios WHERE id = ?; DELETE FROM comentarios WHERE id_comentario_padre = ?;");

		consulta.setInt(1, idcom);
		consulta.setInt(2, idcom);
		
		consulta.executeUpdate();
		
		connection.close();
		return Integer.toString(idcom);
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
	
	@ResponseBody
	@GetMapping("/modificar-comentario-ajax")
	public ArrayList<String> modificarComentarioAjax(Model template, @RequestParam int idcom, @RequestParam String comentarionuevo, @RequestParam int id, HttpSession session) throws SQLException {
		
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		if (Usuarios.usuarioLogeado(session, connection)== null) {
			ArrayList<String> respuesta = new ArrayList<String>();
			respuesta.add("false");
			return respuesta;
		}


		PreparedStatement consulta = connection.prepareStatement("UPDATE comentarios SET contenido = ? WHERE id = ? ;");

		consulta.setString(1, comentarionuevo);
		consulta.setInt(2, idcom);
		
		consulta.executeUpdate();
		
		connection.close();
		
		ArrayList<String> respuesta = new ArrayList<String>();
		String idcomen=Integer.toString(idcom);
		
		respuesta.add(comentarionuevo);
		respuesta.add(idcomen);
		
		return respuesta;
	}
	
	@ResponseBody
	@GetMapping("/procesar-comentario-ajax")
	public ArrayList<String> procesarComentarioAjax(HttpSession session, Model template,@RequestParam int id, @RequestParam String comentarionuevo, @RequestParam String fecha) throws SQLException {
			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
	
			Usuario logeado = Usuarios.usuarioLogeado(session, connection);

			Productos.checkearHeader(logeado, template);
			
			if (Usuarios.usuarioLogeado(session, connection)== null) {
				ArrayList<String> respuesta = new ArrayList<String>();
				respuesta.add("false");
				return respuesta;
			}

			PreparedStatement consulta = connection.prepareStatement("INSERT INTO comentarios(id_producto_padre ,id_comentario_padre ,id_usuario_emisor ,id_usuario_receptor ,fecha ,contenido) VALUES(?, '0', ?, '0', ? , ?)");

			consulta.setInt(1, id);
			consulta.setInt(2, logeado.getId());
			consulta.setString(3, fecha);
			consulta.setString(4, comentarionuevo);
			consulta.executeUpdate();
			PreparedStatement consulta2 = connection.prepareStatement("SELECT * FROM comentarios WHERE id_usuario_emisor = ? ORDER BY id DESC LIMIT 1");
			consulta2.setInt(1, logeado.getId());
			ResultSet resultado2 = consulta2.executeQuery();
			resultado2.next();
			
			ArrayList<String> respuesta = new ArrayList<String>();
			
			
			respuesta.add(comentarionuevo);
			respuesta.add(fecha);
			respuesta.add(logeado.getImagen_de_perfil());
			respuesta.add(logeado.getNick());
			respuesta.add(resultado2.getString("id"));
			respuesta.add("cm"+resultado2.getString("id"));
			respuesta.add(Integer.toString(id));
			connection.close();
		return respuesta;
	}
	
	@ResponseBody
	@GetMapping("/procesar-subcomentario-ajax")
	public ArrayList<String> procesarSubcomentarioAjax(HttpSession session, Model template,@RequestParam int id, @RequestParam int idcom, @RequestParam String comentarionuevo, @RequestParam String fecha) throws SQLException {
			
			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
	
			Usuario logeado = Usuarios.usuarioLogeado(session, connection);

			Productos.checkearHeader(logeado, template);
			
			if (Usuarios.usuarioLogeado(session, connection)== null) {
				ArrayList<String> respuesta = new ArrayList<String>();
				respuesta.add("false");
				return respuesta;
			}

			PreparedStatement consulta = connection.prepareStatement("INSERT INTO comentarios(id_producto_padre ,id_comentario_padre ,id_usuario_emisor ,id_usuario_receptor ,fecha ,contenido) VALUES(?, ?, ?, '0', ? , ?)");

			consulta.setInt(1, id);
			consulta.setInt(2, idcom);
			consulta.setInt(3, logeado.getId());
			consulta.setString(4, fecha);
			consulta.setString(5, comentarionuevo);
			consulta.executeUpdate();
			
			
			PreparedStatement consulta2 = connection.prepareStatement("SELECT * FROM comentarios WHERE id_usuario_emisor = ? ORDER BY id DESC LIMIT 1");
			consulta2.setInt(1, logeado.getId());
			ResultSet resultado2 = consulta2.executeQuery();
			resultado2.next();
			
			ArrayList<String> respuesta = new ArrayList<String>();
			
			respuesta.add(comentarionuevo);
			respuesta.add(fecha);
			respuesta.add(logeado.getImagen_de_perfil());
			respuesta.add(logeado.getNick());
			respuesta.add(resultado2.getString("id"));
			respuesta.add("cm"+resultado2.getString("id"));
			respuesta.add(Integer.toString(id));
			respuesta.add(Integer.toString(idcom));
			connection.close();
		return respuesta;
	}
	
	@GetMapping("/listado/{order}")
	public String listado(HttpSession session,Model template, @PathVariable int order) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
	
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		Productos.checkearHeader(logeado, template);
		
		int offset = order * 8;
		Productos.crearListadoOrdenado(session, template, connection, offset);
		
		if (order == 0){
			template.addAttribute("anterior", order);
			template.addAttribute("ant", false);
		} else {
			template.addAttribute("anterior", order - 1);
			template.addAttribute("ant", true);
		}

		//crear arraylist para mostrar "paginas" en la seccion productos. AGREGAR JOIN EN PEDIDOS SQL DE COMENTARIOS
		
		
		PreparedStatement consulta3 = connection.prepareStatement("SELECT * FROM productos ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta3.setInt(1, offset);
		ResultSet resultado3 = consulta3.executeQuery();
		
		ArrayList<Pagina> listadoPaginas = new ArrayList<Pagina>();
		int contador= 0;
		boolean hayPaginas = resultado3.next();
		
		for (int offst = 8; hayPaginas == true ;offst += 8){
			PreparedStatement consulta4 = connection.prepareStatement("SELECT * FROM productos ORDER BY tipo LIMIT 8 OFFSET ?;");
			consulta4.setInt(1, offst);
			ResultSet resultado4 = consulta4.executeQuery();
			String href= "/listado/" + contador;
			contador ++;
			hayPaginas= resultado4.next();
			Pagina x = new Pagina(contador, href);
			listadoPaginas.add(x);
		}
		template.addAttribute("listadoPaginas", listadoPaginas);
		
		
		boolean cantidad = Productos.CheckearCantidad(session, template, connection, offset);
		
		if (cantidad == false){
			template.addAttribute("siguiente", order);
			template.addAttribute("sig", false);
		} else {
			template.addAttribute("siguiente", order + 1);
			template.addAttribute("sig", true);		
		}
		connection.close();
		return "listadoProductos";
			
	}
	
	@GetMapping("/placas-de-video/{order}")
	public String placasDeVideo(HttpSession session,Model template, @PathVariable int order) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
				
		Productos.checkearHeader(logeado, template);
		int offset = order * 8;
		String tiposql = "placa de video";
		Productos.crearListadoEspecifico(session, template, connection, offset, tiposql);
		
		if (order == 0){
			template.addAttribute("anterior", order);
			template.addAttribute("ant", false);
		} else {
			template.addAttribute("anterior", order - 1);
			template.addAttribute("ant", true);
		}
		
		
		PreparedStatement consulta3 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta3.setString(1, tiposql);
		consulta3.setInt(2, offset);
		
		ResultSet resultado3 = consulta3.executeQuery();
		
		ArrayList<Pagina> listadoPaginas = new ArrayList<Pagina>();
		int contador= 0;
		boolean hayPaginas = resultado3.next();
		
		for (int offst = 8; hayPaginas == true ;offst += 8){
			PreparedStatement consulta4 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
			consulta4.setString(1, tiposql);
			consulta4.setInt(2, offst);
			
			ResultSet resultado4 = consulta4.executeQuery();
			String href= "/placas-de-video/" + contador;
			contador ++;
			hayPaginas= resultado4.next();
			Pagina x = new Pagina(contador, href);
			listadoPaginas.add(x);
		}
		template.addAttribute("listadoPaginas", listadoPaginas);
	
		
		boolean cantidad = Productos.CheckearCantidadEspecifica(session, template, connection, offset, tiposql);
		
		if (cantidad == false){
			template.addAttribute("siguiente", order);
			template.addAttribute("sig", false);
		} else {
			template.addAttribute("siguiente", order + 1);
			template.addAttribute("sig", true);		
		}
		connection.close();
		return "listadoProductos";
	}

	@GetMapping("/memorias/{order}")
	public String memorias(HttpSession session,Model template, @PathVariable int order) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
				
		Productos.checkearHeader(logeado, template);
		
		
		int offset = order * 8;
		String tiposql = "memoria";
		Productos.crearListadoEspecifico(session, template, connection, offset, tiposql);
		
		if (order == 0){
			template.addAttribute("anterior", order);
			template.addAttribute("ant", false);
		} else {
			template.addAttribute("anterior", order - 1);
			template.addAttribute("ant", true);
		}
		
		PreparedStatement consulta3 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta3.setString(1, tiposql);
		consulta3.setInt(2, offset);
		
		ResultSet resultado3 = consulta3.executeQuery();
		
		ArrayList<Pagina> listadoPaginas = new ArrayList<Pagina>();
		int contador= 0;
		boolean hayPaginas = resultado3.next();
		
		for (int offst = 8; hayPaginas == true ;offst += 8){
			PreparedStatement consulta4 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
			consulta4.setString(1, tiposql);
			consulta4.setInt(2, offst);
			
			ResultSet resultado4 = consulta4.executeQuery();
			String href= "/memorias/" + contador;
			contador ++;
			hayPaginas= resultado4.next();
			Pagina x = new Pagina(contador, href);
			listadoPaginas.add(x);
		}
		template.addAttribute("listadoPaginas", listadoPaginas);

		
		
		
		boolean cantidad = Productos.CheckearCantidadEspecifica(session, template, connection, offset, tiposql);
		
		if (cantidad == false){
			template.addAttribute("siguiente", order);
			template.addAttribute("sig", false);
		} else {
			template.addAttribute("siguiente", order + 1);
			template.addAttribute("sig", true);		
		}
		connection.close();
		return "listadoProductos";
	}
	
	@GetMapping("/microprocesadores/{order}")
	public String microprocesadores(HttpSession session,Model template, @PathVariable int order) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
				
		Productos.checkearHeader(logeado, template);
		
		
		int offset = order * 8;
		String tiposql = "microprocesador";
		Productos.crearListadoEspecifico(session, template, connection, offset, tiposql);
		
		if (order == 0){
			template.addAttribute("anterior", order);
			template.addAttribute("ant", false);
		} else {
			template.addAttribute("anterior", order - 1);
			template.addAttribute("ant", true);
		}
		
		PreparedStatement consulta3 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta3.setString(1, tiposql);
		consulta3.setInt(2, offset);
		
		ResultSet resultado3 = consulta3.executeQuery();
		
		ArrayList<Pagina> listadoPaginas = new ArrayList<Pagina>();
		int contador= 0;
		boolean hayPaginas = resultado3.next();
		
		for (int offst = 8; hayPaginas == true ;offst += 8){
			PreparedStatement consulta4 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
			consulta4.setString(1, tiposql);
			consulta4.setInt(2, offst);
			
			ResultSet resultado4 = consulta4.executeQuery();
			String href= "/microprocesadores/" + contador;
			contador ++;
			hayPaginas= resultado4.next();
			Pagina x = new Pagina(contador, href);
			listadoPaginas.add(x);
		}
		template.addAttribute("listadoPaginas", listadoPaginas);

		
		boolean cantidad = Productos.CheckearCantidadEspecifica(session, template, connection, offset, tiposql);
		
		if (cantidad == false){
			template.addAttribute("siguiente", order);
			template.addAttribute("sig", false);
		} else {
			template.addAttribute("siguiente", order + 1);
			template.addAttribute("sig", true);		
		}
		connection.close();
		return "listadoProductos";
	}
		
	@GetMapping("/discos-duros/{order}")
	public String discosDuros(HttpSession session,Model template, @PathVariable int order) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
				
		Productos.checkearHeader(logeado, template);
		

		int offset = order * 8;
		String tiposql = "disco duro";
		Productos.crearListadoEspecifico(session, template, connection, offset, tiposql);
		
		if (order == 0){
			template.addAttribute("anterior", order);
			template.addAttribute("ant", false);
		} else {
			template.addAttribute("anterior", order - 1);
			template.addAttribute("ant", true);
		}
		
		PreparedStatement consulta3 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta3.setString(1, tiposql);
		consulta3.setInt(2, offset);
		
		ResultSet resultado3 = consulta3.executeQuery();
		
		ArrayList<Pagina> listadoPaginas = new ArrayList<Pagina>();
		int contador= 0;
		boolean hayPaginas = resultado3.next();
		
		for (int offst = 8; hayPaginas == true ;offst += 8){
			PreparedStatement consulta4 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
			consulta4.setString(1, tiposql);
			consulta4.setInt(2, offst);
			
			ResultSet resultado4 = consulta4.executeQuery();
			String href= "/discos-duros/" + contador;
			contador ++;
			hayPaginas= resultado4.next();
			Pagina x = new Pagina(contador, href);
			listadoPaginas.add(x);
		}
		template.addAttribute("listadoPaginas", listadoPaginas);

		
		boolean cantidad = Productos.CheckearCantidadEspecifica(session, template, connection, offset, tiposql);
		
		if (cantidad == false){
			template.addAttribute("siguiente", order);
			template.addAttribute("sig", false);
		} else {
			template.addAttribute("siguiente", order + 1);
			template.addAttribute("sig", true);		
		}
		connection.close();
		return "listadoProductos";
	}
	
	@GetMapping("/motherboards/{order}")
	public String motherboards(HttpSession session,Model template, @PathVariable int order) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
				
		Productos.checkearHeader(logeado, template);
		

		int offset = order * 8;
		String tiposql = "motherboard";
		Productos.crearListadoEspecifico(session, template, connection, offset, tiposql);
		
		if (order == 0){
			template.addAttribute("anterior", order);
			template.addAttribute("ant", false);
		} else {
			template.addAttribute("anterior", order - 1);
			template.addAttribute("ant", true);
		}
		
		PreparedStatement consulta3 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
		consulta3.setString(1, tiposql);
		consulta3.setInt(2, offset);
		
		ResultSet resultado3 = consulta3.executeQuery();
		
		ArrayList<Pagina> listadoPaginas = new ArrayList<Pagina>();
		int contador= 0;
		boolean hayPaginas = resultado3.next();
		
		for (int offst = 8; hayPaginas == true ;offst += 8){
			PreparedStatement consulta4 = connection.prepareStatement("SELECT * FROM productos WHERE tipo = ? ORDER BY tipo LIMIT 8 OFFSET ?;");
			consulta4.setString(1, tiposql);
			consulta4.setInt(2, offst);
			
			ResultSet resultado4 = consulta4.executeQuery();
			String href= "/motherboard/" + contador;
			contador ++;
			hayPaginas= resultado4.next();
			Pagina x = new Pagina(contador, href);
			listadoPaginas.add(x);
		}
		template.addAttribute("listadoPaginas", listadoPaginas);

		
		
		boolean cantidad = Productos.CheckearCantidadEspecifica(session, template, connection, offset, tiposql);
		
		if (cantidad == false){
			template.addAttribute("siguiente", order);
			template.addAttribute("sig", false);
		} else {
			template.addAttribute("siguiente", order + 1);
			template.addAttribute("sig", true);		
		}
		connection.close();
		return "listadoProductos";
	}

	@GetMapping("/procesar-comentario")
	public String procesarComentario(HttpSession session, Model template,@RequestParam int id, @RequestParam String comentarionuevo, @RequestParam String fecha) throws SQLException {
			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
	
			Usuario logeado = Usuarios.usuarioLogeado(session, connection);
			if (logeado== null) {
				return "redirect:/login";
			}
			Productos.checkearHeader(logeado, template);
			Comentarios.insertarComentario(connection, id, logeado, fecha, comentarionuevo);
			connection.close();
		return "redirect:/detalle/"+ id;
	}
	
	
	@GetMapping("/procesar-subcomentario")
	public String procesarSubcomentario(HttpSession session, Model template,@RequestParam int id, @RequestParam int idcom, @RequestParam String comentarionuevo, @RequestParam String fecha) throws SQLException {
			
			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
	
			Usuario logeado = Usuarios.usuarioLogeado(session, connection);
					
			Productos.checkearHeader(logeado, template);
			
			if (Usuarios.usuarioLogeado(session, connection)== null) {
				return "redirect:/login";
			}

			PreparedStatement consulta = connection.prepareStatement("INSERT INTO comentarios(id_producto_padre ,id_comentario_padre ,id_usuario_emisor ,id_usuario_receptor ,fecha ,contenido) VALUES(?, ?, ?, '0', ? , ?)");

			consulta.setInt(1, id);
			consulta.setInt(2, idcom);
			consulta.setInt(3, logeado.getId());
			consulta.setString(4, fecha);
			consulta.setString(5, comentarionuevo);
			consulta.executeUpdate();
			connection.close();
		return "redirect:/detalle/"+ id;
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(HttpSession session, Model template, @PathVariable int id) throws SQLException {
		

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));

		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		if(logeado==null) {
			template.addAttribute("idlogeado", "0");
		} else {
			template.addAttribute("idlogeado", logeado.getId());
		}
				
		Productos.checkearHeader(logeado, template);
		

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
					template.addAttribute("consumo", consumo);
					template.addAttribute("precio", precio);
					template.addAttribute("urlimagen", urlimagen);
					
					template.addAttribute("bTiporam", true);
					template.addAttribute("bPci", true);
					template.addAttribute("bTamanio", true);
					template.addAttribute("bConsumo", true);
					template.addAttribute("bVelocidad", false);
					template.addAttribute("bSata", false);
					template.addAttribute("bSocket", false);
					
					Comentarios.cargarComentarios(connection, id, template);
					connection.close();
					
					return "detalleProducto";
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
					
					template.addAttribute("bSocket", true);
					template.addAttribute("bVelocidad", true);
					template.addAttribute("bConsumo", true);
					template.addAttribute("bTiporam", false);
					template.addAttribute("bPci", false);
					template.addAttribute("bTamanio", false);
					template.addAttribute("bSata", false);
					
					Comentarios.cargarComentarios(connection, id, template);
					connection.close();
					
					return "detalleProducto";
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
					
					template.addAttribute("bSata", true);
					template.addAttribute("bTamanio", true);
					template.addAttribute("bTiporam", false);
					template.addAttribute("bPci", false);
					template.addAttribute("bConsumo", false);
					template.addAttribute("bVelocidad", false);
					template.addAttribute("bSocket", false);
					
					Comentarios.cargarComentarios(connection, id, template);
					connection.close();
					
					return "detalleProducto";
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
					
					template.addAttribute("bSocket", true);
					template.addAttribute("bTiporam", true);
					template.addAttribute("bPci", true);
					template.addAttribute("bSata", true);
					template.addAttribute("bTamanio", false);
					template.addAttribute("bConsumo", false);
					template.addAttribute("bVelocidad", false);
					
					Comentarios.cargarComentarios(connection, id, template);
					connection.close();
					
					return "detalleProducto";
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

					template.addAttribute("bTiporam", true);
					template.addAttribute("bVelocidad", true);
					template.addAttribute("bTamanio", true);
					template.addAttribute("bPci", false);
					template.addAttribute("bConsumo", false);
					template.addAttribute("bSata", false);
					template.addAttribute("bSocket", false);
					
					Comentarios.cargarComentarios(connection, id, template);
					connection.close();
					
					return "detalleProducto";
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
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		Productos.checkearHeader(logeado, template);
		if (logeado == null) {
			connection.close();
			return "formulario-registro-usuario";
		} else {
			connection.close();
			return "redirect:/perfil"+ logeado.getNick() ;
		}
		
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
		
		Email email = EmailBuilder.startingBlank()
			    .from("Happel.com", "happeljavierleonardo@gmail.com")
			    .to(nick , mail)
			    .withSubject("Registro exitoso")
			    .withPlainText("Felicidades "+nick+" te has registrado en happel.com con exito")
			    .buildEmail();
		
			MailerBuilder
			  .withSMTPServer("smtp.sendgrid.net", 587, "apikey", env.getProperty("spring.datasource.sendgrid"))
			  .buildMailer()
			  .sendMail(email);
			
		connection.close();
			
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session, Model template) throws SQLException {	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		Productos.checkearHeader(logeado, template);
		
		if (logeado == null) {
			connection.close();
			return "login";
		} else {
			connection.close();
			return "redirect:/perfil/"+ logeado.getNick() ;
		}
		
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws SQLException {	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		Usuarios.cerrarSesion(session, connection);
		connection.close();
		return "redirect:/login";
	}
	
	@PostMapping("/logear-usuario")
	public String logearUsuario(HttpSession session, Model template, @RequestParam String nick, @RequestParam String contrasenia) throws SQLException {
	
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		boolean sePudo = Usuarios.intentarLogearse(session, nick, contrasenia, connection);
			
			if (sePudo){
				Usuario logeado = Usuarios.usuarioLogeado(session, connection);
				connection.close();
				return "redirect:/perfil/" + logeado.getNick();
			} else {
				connection.close();
				return "redirect:/login";	
			}
		}

	@GetMapping("/perfil/{nick}")
	public String perfilUsuario(HttpSession session, Model template, @PathVariable String nick) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		


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
				template.addAttribute("mother", mother);
				template.addAttribute("micro", micro);
				template.addAttribute("placa", placa);
				template.addAttribute("memoria1", memoria1);
				template.addAttribute("memoria2", memoria2);
				template.addAttribute("disco", disco);
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
				if(ram2.next()) {
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
				}
				
				String urlCrearPc = "/eliminar-pc/" +  idpc;
				String botonCrearPc = "eliminar tu pc";
				template.addAttribute("urlEliminarPc", urlCrearPc);
				template.addAttribute("botonEliminarPc", botonCrearPc); 
				
				PreparedStatement consultaJuego = connection.prepareStatement("SELECT * FROM juegos;");

				ResultSet resultadoJuego = consultaJuego.executeQuery();

				ArrayList<Juego> listadoJuegos = new ArrayList<Juego>();
				
				while ( resultadoJuego.next() ) {
					int jid = resultadoJuego.getInt("id");
					String jnombre = resultadoJuego.getString("nombre");
					String jdesarrollador = resultadoJuego.getString("desarrollador");
					String jfecha = resultadoJuego.getString("fecha");
					String jplataforma = resultadoJuego.getString("plataforma");
					String jgenero = resultadoJuego.getString("genero");
					int jrencpu1 = resultadoJuego.getInt("rencpu1");
					int jrencpu2 = resultadoJuego.getInt("rencpu2");
					int jrengpu1 = resultadoJuego.getInt("rengpu1");
					int jrengpu2 = resultadoJuego.getInt("rengpu2");
					int jvram1 = resultadoJuego.getInt("vram1");
					int jvram2 = resultadoJuego.getInt("vram2");
					int jram1 = resultadoJuego.getInt("ram1");
					int jram2 = resultadoJuego.getInt("ram2");
					int jprecio = resultadoJuego.getInt("precio");
					String jurlimagen = resultadoJuego.getString("urlimagen");
					
					Juego juego = new Juego(jid, jnombre, jdesarrollador, jfecha, jplataforma, jgenero, jrencpu1, jrencpu2, jrengpu1, jrengpu2, jvram1, jvram2, jram1, jram2, jprecio, jurlimagen);
					listadoJuegos.add(juego);
					
					
				}
				
				template.addAttribute("listadoJuegos", listadoJuegos);

			}	else {
				String urlCrearPc = "/crea-tu-pc";
				String botonCrearPc = "crea tu pc";
				template.addAttribute("urlCrearPc", urlCrearPc);
				template.addAttribute("botonCrearPc", botonCrearPc);  
			}
			
		}
		connection.close();
		return "perfilUsuario";
	}
	
	@PostMapping("/registrar-pc-usuario")
	public String registrarPcUsuario(HttpSession session, Model template, @RequestParam int micro, @RequestParam int placa, @RequestParam int memoria1, @RequestParam int memoria2,@RequestParam int mother, @RequestParam int disco) throws SQLException {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		Productos.checkearHeader(logeado, template);
		
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
		
		Usuario logeado = Usuarios.usuarioLogeado(session, connection);
		
		if (logeado == null) {
			template.addAttribute("header", "header");
			connection.close();
			return "redirect:/login";
		} else {
			template.addAttribute("header", "headerLogeado");
			template.addAttribute("imgperfil", logeado.getImagen_de_perfil());
				if (logeado.isAdministrador() == true) {
					template.addAttribute("logeadoisadmin", logeado.isAdministrador());
					template.addAttribute("admin", "administrar");
				}
		}
		

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM productos ORDER BY precio ASC;");

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
			int consumo = resultado.getInt("consumo");
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
		connection.close();
		return "crearMiPc";
	}

	
	@GetMapping("/arma-tu-pc/modificar-producto")
	public String ModificarProducto(Model template,@RequestParam String type, @RequestParam int idProducto) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		PreparedStatement consulta;
		
		if(type.equals("micro")) {
			consulta = connection.prepareStatement("UPDATE pc SET micro = ? WHERE id=2 ;");
		} else if (type.equals("gpu")){
			consulta = connection.prepareStatement("UPDATE pc SET gpu = ? WHERE id=2 ;");
		} else if (type.equals("mother")){
			consulta = connection.prepareStatement("UPDATE pc SET mother = ? WHERE id=2 ;");
		} else if (type.equals("disco")){
			consulta = connection.prepareStatement("UPDATE pc SET disco = ? WHERE id=2 ;");
		} else if (type.equals("ram1")){
			consulta = connection.prepareStatement("UPDATE pc SET ram1 = ? WHERE id=2 ;");
		} else if (type.equals("ram2")){
			consulta = connection.prepareStatement("UPDATE pc SET ram2 = ? WHERE id=2 ;");
		} else if (type.equals("ram3")){
			consulta = connection.prepareStatement("UPDATE pc SET ram3 = ? WHERE id=2 ;");
		} else if (type.equals("ram4")){
			consulta = connection.prepareStatement("UPDATE pc SET ram4 = ? WHERE id=2 ;");
		} else {
			consulta = connection.prepareStatement("UPDATE pc SET fuente = ? WHERE id=2 ;");
		}
		
		consulta.setInt(1, idProducto);
		consulta.executeUpdate();
		connection.close();
		
		return "redirect:/arma-tu-pc/armar";
	}
	
	@GetMapping("/arma-tu-pc/eliminar-producto")
	public String eliminarProducto(Model template, @RequestParam String type ) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		PreparedStatement consulta;
		if(type.equals("micro")) {
			consulta = connection.prepareStatement("UPDATE pc SET micro = null WHERE id=2 ;");
		} else if (type.equals("gpu")){
			consulta = connection.prepareStatement("UPDATE pc SET gpu = null WHERE id=2 ;");
		} else if (type.equals("mother")){
			consulta = connection.prepareStatement("UPDATE pc SET mother = null WHERE id=2 ;");
		} else if (type.equals("disco")){
			consulta = connection.prepareStatement("UPDATE pc SET disco = null WHERE id=2 ;");
		} else if (type.equals("ram1")){
			consulta = connection.prepareStatement("UPDATE pc SET ram1 = null WHERE id=2 ;");
		} else if (type.equals("ram2")){
			consulta = connection.prepareStatement("UPDATE pc SET ram2 = null WHERE id=2 ;");
		} else if (type.equals("ram3")){
			consulta = connection.prepareStatement("UPDATE pc SET ram3 = null WHERE id=2 ;");
		} else if (type.equals("ram4")){
			consulta = connection.prepareStatement("UPDATE pc SET ram4 = null WHERE id=2 ;");
		} else {
			consulta = connection.prepareStatement("UPDATE pc SET fuente = null WHERE id=2 ;");
		}
		consulta.executeUpdate();
		connection.close();
		return "redirect:/arma-tu-pc/armar";
	}

	@GetMapping("/arma-tu-pc/armar")
	public String armar(Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		
		SQLHelper.cargarProductos(connection, template);
		
		Computadora2 pc = SQLHelper.cargarPc(connection, template);
		
		boolean haveRam;
		
		if(pc.getRam1()==0) {
			haveRam = false;
		} else {
			haveRam = true;
		}
		
		if(pc.getMicro()==0 && haveRam == false) {
			ArmarPc.mothers(connection, template);	
		}
		if(pc.getMicro()!=0 && haveRam == false) {
			Producto micro =ArmarPc.producto(connection, template, pc.getMicro());
			ArmarPc.motherSocket(connection, template, micro.getSocketcpu());
		}
		if(haveRam == true && pc.getMicro()==0) {
			Producto ram =ArmarPc.producto(connection, template, pc.getRam1());
			ArmarPc.motherRam(connection, template, ram.getTiporam());
		}
		if(haveRam == true && pc.getMicro()!=0) {
			Producto micro =ArmarPc.producto(connection, template, pc.getMicro());
			Producto ram =ArmarPc.producto(connection, template, pc.getRam1());
			ArmarPc.motherRamSocket(connection, template, ram.getTiporam(), micro.getSocketcpu());
		}
		
		if(pc.getMother()==0) {
			ArmarPc.micros(connection, template);
			ArmarPc.rams(connection, template);
		}
		if(pc.getMother()!=0) {
			Producto mother =ArmarPc.producto(connection, template, pc.getMother());
			ArmarPc.microSocket(connection, template, mother.getSocketcpu());
			ArmarPc.ramSocket(connection, template, mother.getTiporam());
		}
		ArmarPc.discos(connection, template);
		ArmarPc.fuentes(connection, template);
		ArmarPc.gpu(connection, template);
		
		connection.close();
		return "armar-pc";
	}

	@GetMapping("/arma-tu-pc/crear-pc")
	public String crearPC(Model template) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		SQLHelper.nuevaPc(connection);
		
		connection.close();
		return "arma-tu-pc";
	}
}
