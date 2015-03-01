package com.example.pembukuanumk;

public class Model_Jenis_Transaksi_Lain {
	private int id;
	private String nama_jenis_transaksi;
	private String tipe;
	public Model_Jenis_Transaksi_Lain(int id, String nama_jenis_transaksi,
			String tipe) {
		super();
		this.id = id;
		this.nama_jenis_transaksi = nama_jenis_transaksi;
		this.tipe = tipe;
	}
	public Model_Jenis_Transaksi_Lain(String nama_jenis_transaksi, String tipe) {
		super();
		this.nama_jenis_transaksi = nama_jenis_transaksi;
		this.tipe = tipe;
	}
	public Model_Jenis_Transaksi_Lain() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama_jenis_transaksi() {
		return nama_jenis_transaksi;
	}
	public void setNama_jenis_transaksi(String nama_jenis_transaksi) {
		this.nama_jenis_transaksi = nama_jenis_transaksi;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	
	
}
