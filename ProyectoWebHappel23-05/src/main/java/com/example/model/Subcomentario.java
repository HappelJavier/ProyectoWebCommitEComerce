package com.example.model;

public class Subcomentario {
	
	private int id;
	private int id_usuario_emisor;
	private int id_usuario_receptor;
	private int id_productos_padre;
	private int id_comentario_padre;
	private String fecha;
	private String contenido;
	private String nombre_usuario;
	private String foto_perfil;
	
	
	public Subcomentario(int id, int id_usuario_emisor, int id_usuario_receptor, int id_productos_padre,
			int id_comentario_padre, String fecha, String contenido, String nombre_usuario, String foto_perfil) {
		super();
		this.id = id;
		this.id_usuario_emisor = id_usuario_emisor;
		this.id_usuario_receptor = id_usuario_receptor;
		this.id_productos_padre = id_productos_padre;
		this.id_comentario_padre = id_comentario_padre;
		this.fecha = fecha;
		this.contenido = contenido;
		this.nombre_usuario = nombre_usuario;
		this.foto_perfil = foto_perfil;
	}

	public Subcomentario(int id, int id_usuario_emisor, int id_usuario_receptor, int id_productos_padre,
			int id_comentario_padre, String fecha, String contenido, String nombre_usuario) {
		super();
		this.id = id;
		this.id_usuario_emisor = id_usuario_emisor;
		this.id_usuario_receptor = id_usuario_receptor;
		this.id_productos_padre = id_productos_padre;
		this.id_comentario_padre = id_comentario_padre;
		this.fecha = fecha;
		this.contenido = contenido;
		this.nombre_usuario = nombre_usuario;
	}

	public Subcomentario(int id, int id_usuario_emisor, int id_usuario_receptor, int id_productos_padre,
			int id_comentario_padre, String fecha, String contenido) {
		super();
		this.id = id;
		this.id_usuario_emisor = id_usuario_emisor;
		this.id_usuario_receptor = id_usuario_receptor;
		this.id_productos_padre = id_productos_padre;
		this.id_comentario_padre = id_comentario_padre;
		this.fecha = fecha;
		this.contenido = contenido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuario_emisor() {
		return id_usuario_emisor;
	}

	public void setId_usuario_emisor(int id_usuario_emisor) {
		this.id_usuario_emisor = id_usuario_emisor;
	}

	public int getId_usuario_receptor() {
		return id_usuario_receptor;
	}

	public void setId_usuario_receptor(int id_usuario_receptor) {
		this.id_usuario_receptor = id_usuario_receptor;
	}

	public int getId_productos_padre() {
		return id_productos_padre;
	}

	public void setId_productos_padre(int id_productos_padre) {
		this.id_productos_padre = id_productos_padre;
	}

	public int getId_comentario_padre() {
		return id_comentario_padre;
	}

	public void setId_comentario_padre(int id_comentario_padre) {
		this.id_comentario_padre = id_comentario_padre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getFoto_perfil() {
		return foto_perfil;
	}

	public void setFoto_perfil(String foto_perfil) {
		this.foto_perfil = foto_perfil;
	}

	
	
	
	

	
}
