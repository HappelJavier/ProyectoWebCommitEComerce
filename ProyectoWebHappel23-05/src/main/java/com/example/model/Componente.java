package com.example.model;

public class Componente {

	private String rendimientoGpu;
	private String rendimientoCpu;
	private String memoriaRam;
	private String memoriaVram;
	private int precio;
	public Componente(String rendimientoGpu, String rendimientoCpu, String memoriaRam, String memoriaVram, int precio) {
		super();
		this.rendimientoGpu = rendimientoGpu;
		this.rendimientoCpu = rendimientoCpu;
		this.memoriaRam = memoriaRam;
		this.memoriaVram = memoriaVram;
		this.precio = precio;
	}
	public String getRendimientoGpu() {
		return rendimientoGpu;
	}
	public void setRendimientoGpu(String rendimientoGpu) {
		this.rendimientoGpu = rendimientoGpu;
	}
	public String getRendimientoCpu() {
		return rendimientoCpu;
	}
	public void setRendimientoCpu(String rendimientoCpu) {
		this.rendimientoCpu = rendimientoCpu;
	}
	public String getMemoriaRam() {
		return memoriaRam;
	}
	public void setMemoriaRam(String memoriaRam) {
		this.memoriaRam = memoriaRam;
	}
	public String getMemoriaVram() {
		return memoriaVram;
	}
	public void setMemoriaVram(String memoriaVram) {
		this.memoriaVram = memoriaVram;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	
	
	
}
