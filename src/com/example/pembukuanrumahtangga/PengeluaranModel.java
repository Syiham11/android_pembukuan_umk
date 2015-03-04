package com.example.pembukuanrumahtangga;

public class PengeluaranModel {
	private int id;
	private int user_id;
	private String date;
	private String namaPengeluaran;
	private int jumlahPengeluaran;
	public PengeluaranModel(int user_id, String date,
			String namaPengeluaran, int jumlahPengeluaran) {
		super();
		this.user_id = user_id;
		this.date = date;
		this.namaPengeluaran = namaPengeluaran;
		this.jumlahPengeluaran = jumlahPengeluaran;
	}
	public PengeluaranModel() {
		super();
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNamaPengeluaran() {
		return namaPengeluaran;
	}
	public void setNamaPengeluaran(String namaPengeluaran) {
		this.namaPengeluaran = namaPengeluaran;
	}
	public int getJumlahPengeluaran() {
		return jumlahPengeluaran;
	}
	public void setJumlahPengeluaran(int jumlahPengeluaran) {
		this.jumlahPengeluaran = jumlahPengeluaran;
	}
	
	
}
