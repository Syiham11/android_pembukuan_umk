package com.example.pembukuanumk;

public class Model_UMK {
	private int id;
	private String email;
	private String password;
	private String nama_umk;
	private String deskripsi_umk;
	private String alamat_umk;
	private long telepon_umk;
	private byte[] logo_umk;
	private long saldo_umk;
	
	public Model_UMK(){
		
	}
	
	public Model_UMK(String email, String password, String nama_umk,
			String deskripsi_umk, String alamat_umk, long telepon_umk,byte[] logo_umk,long saldo_umk) {
		super();
		this.email = email;
		this.password = password;
		this.nama_umk = nama_umk;
		this.deskripsi_umk = deskripsi_umk;
		this.alamat_umk = alamat_umk;
		this.telepon_umk = telepon_umk;
		this.logo_umk = logo_umk;
		this.saldo_umk = saldo_umk;
	}
	public Model_UMK(int id,String email, String password, String nama_umk,
			String deskripsi_umk, String alamat_umk, long telepon_umk,byte[] logo_umk,long saldo_umk) {
		super();
		this.id=id;
		this.email = email;
		this.password = password;
		this.nama_umk = nama_umk;
		this.deskripsi_umk = deskripsi_umk;
		this.alamat_umk = alamat_umk;
		this.telepon_umk = telepon_umk;
		this.logo_umk = logo_umk;
		this.saldo_umk = saldo_umk;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNama_umk() {
		return nama_umk;
	}
	public void setNama_umk(String nama_umk) {
		this.nama_umk = nama_umk;
	}
	public String getDeskripsi_umk() {
		return deskripsi_umk;
	}
	public void setDeskripsi_umk(String deskripsi_umk) {
		this.deskripsi_umk = deskripsi_umk;
	}
	public String getAlamat_umk() {
		return alamat_umk;
	}
	public void setAlamat_umk(String alamat_umk) {
		this.alamat_umk = alamat_umk;
	}
	public long getTelepon_umk() {
		return telepon_umk;
	}
	public void setTelepon_umk(long telepon_umk) {
		this.telepon_umk = telepon_umk;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getLogo_umk() {
		return logo_umk;
	}

	public void setLogo_umk(byte[] logo_umk) {
		this.logo_umk = logo_umk;
	}

	public long getSaldo_umk() {
		return saldo_umk;
	}

	public void setSaldo_umk(long saldo_umk) {
		this.saldo_umk = saldo_umk;
	}
	
}
