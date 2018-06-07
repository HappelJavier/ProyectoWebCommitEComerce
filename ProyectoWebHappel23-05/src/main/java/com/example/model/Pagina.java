package com.example.model;

public class Pagina {
	
	
	private int id;
	private String href;
	
	
	
	public Pagina(int id, String href) {
		super();
		this.id = id;
		this.href = href;
	}
	
	
	public Pagina(String href) {
		super();
		this.href = href;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	
	
}
