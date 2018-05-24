package com.example.model;

public class Usuario {
	
	
	private int id;
	private String nombre;
	private String contrasenia;
	private String nick;
	private String mail;
	private String idpc;
	private String imagen_de_perfil;
	private boolean administrador;
	
	public Usuario(int id, String nombre, String contrasenia, String nick, String mail, String idpc,
			String imagen_de_perfil, boolean administrador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.nick = nick;
		this.mail = mail;
		this.idpc = idpc;
		this.imagen_de_perfil = imagen_de_perfil;
		this.administrador = administrador;
	}

	public Usuario(int id, String nombre, String contrasenia, String nick, String imagen_de_perfil, Boolean administrador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.nick = nick;
		this.imagen_de_perfil = imagen_de_perfil;
		this.administrador = administrador;
	}
	
	public Usuario(int id, String nombre, String contrasenia, String nick, String imagen_de_perfil) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.nick = nick;
		this.imagen_de_perfil = imagen_de_perfil;
	}

	public Usuario(int id, String nombre, String contrasenia, String nick) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.nick = nick;

	}
	
	public Usuario(int id, String nombre, String contrasenia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;

	}
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIdpc() {
		return idpc;
	}

	public void setIdpc(String idpc) {
		this.idpc = idpc;
	}

	public String getImagen_de_perfil() {
		return imagen_de_perfil;
	}

	public void setImagen_de_perfil(String imagen_de_perfil) {
		this.imagen_de_perfil = imagen_de_perfil;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	
	
	
}
