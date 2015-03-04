package com.example.pembukuanrumahtangga;

public class PemasukanModel {
	private int id;
	private int user_id;
	private String date;
	private String namaPemasukan;
	private int jumlahPemasukan;
	public PemasukanModel() {
		super();
	}
	public PemasukanModel( int user_id, String date,
			String namaPemasukan, int jumlahPemasukan) {
		super();
		this.user_id = user_id;
		this.date = date;
		this.namaPemasukan = namaPemasukan;
		this.jumlahPemasukan = jumlahPemasukan;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNamaPemasukan() {
		return namaPemasukan;
	}
	public void setNamaPemasukan(String namaPemasukan) {
		this.namaPemasukan = namaPemasukan;
	}
	public int getJumlahPemasukan() {
		return jumlahPemasukan;
	}
	public void setJumlahPemasukan(int jumlahPemasukan) {
		this.jumlahPemasukan = jumlahPemasukan;
	}
	
	
}
