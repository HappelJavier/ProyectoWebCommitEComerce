package com.example.model;

public class Juego {

	private int id;
	private String nombre;
	private String desarrollador;
	private String fecha;
	private String plataforma;
	private String genero;
	private int rencpu1;
	private int rencpu2;
	private int rengpu1;
	private int rengpu2;
	private int vram1;
	private int vram2;
	private int ram1;
	private int ram2;
	private int precio;
	private String urlimagen;
	
	public Juego() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Juego(int id, String nombre, String desarrollador, String fecha, String plataforma, String genero,
			int rencpu1, int rencpu2, int rengpu1, int rengpu2, int vram1, int vram2, int ram1, int ram2, int precio,
			String urlimagen) {
		this.id = id;
		this.nombre = nombre;
		this.desarrollador = desarrollador;
		this.fecha = fecha;
		this.plataforma = plataforma;
		this.genero = genero;
		this.rencpu1 = rencpu1;
		this.rencpu2 = rencpu2;
		this.rengpu1 = rengpu1;
		this.rengpu2 = rengpu2;
		this.vram1 = vram1;
		this.vram2 = vram2;
		this.ram1 = ram1;
		this.ram2 = ram2;
		this.precio = precio;
		this.urlimagen = urlimagen;
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



	public String getDesarrollador() {
		return desarrollador;
	}


	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPlataforma() {
		return plataforma;
	}


	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}


	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public int getRencpu1() {
		return rencpu1;
	}

	public void setRencpu1(int rencpu1) {
		this.rencpu1 = rencpu1;
	}


	public int getRencpu2() {
		return rencpu2;
	}

	public void setRencpu2(int rencpu2) {
		this.rencpu2 = rencpu2;
	}


	public int getRengpu1() {
		return rengpu1;
	}


	public void setRengpu1(int rengpu1) {
		this.rengpu1 = rengpu1;
	}


	public int getRengpu2() {
		return rengpu2;
	}



	public void setRengpu2(int rengpu2) {
		this.rengpu2 = rengpu2;
	}


	public int getVram1() {
		return vram1;
	}


	public void setVram1(int vram1) {
		this.vram1 = vram1;
	}


	public int getVram2() {
		return vram2;
	}


	public void setVram2(int vram2) {
		this.vram2 = vram2;
	}


	public int getRam1() {
		return ram1;
	}

	public void setRam1(int ram1) {
		this.ram1 = ram1;
	}

	public int getRam2() {
		return ram2;
	}

	public void setRam2(int ram2) {
		this.ram2 = ram2;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getUrlimagen() {
		return urlimagen;
	}

	public void setUrlimagen(String urlimagen) {
		this.urlimagen = urlimagen;
	}
	
	
}
