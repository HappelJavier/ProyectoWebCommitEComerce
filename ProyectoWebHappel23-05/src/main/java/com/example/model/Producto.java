package com.example.model;


public class Producto {
	
	
	private int id;
	private String tipo;
	private String marca;
	private String modelo;
	private String socketcpu;
	private String tiporam;
	private String pci;
	private String sata;
	private String velocidad;
	private String tamanio;
	private String rendimiento;
	private int consumo;
	private int precio;
	private String urlimagen;
	
	public Producto(int id, String tipo, String marca, String modelo, String socketcpu, String tiporam, String pci,
			String sata, String velocidad, String tamanio, String rendimiento, int consumo, int precio,
			String urlimagen) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
		this.socketcpu = socketcpu;
		this.tiporam = tiporam;
		this.pci = pci;
		this.sata = sata;
		this.velocidad = velocidad;
		this.tamanio = tamanio;
		this.rendimiento = rendimiento;
		this.consumo = consumo;
		this.precio = precio;
		this.urlimagen = urlimagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSocketcpu() {
		return socketcpu;
	}

	public void setSocketcpu(String socketcpu) {
		this.socketcpu = socketcpu;
	}

	public String getTiporam() {
		return tiporam;
	}

	public void setTiporam(String tiporam) {
		this.tiporam = tiporam;
	}

	public String getPci() {
		return pci;
	}

	public void setPci(String pci) {
		this.pci = pci;
	}

	public String getSata() {
		return sata;
	}

	public void setSata(String sata) {
		this.sata = sata;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(String rendimiento) {
		this.rendimiento = rendimiento;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
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
