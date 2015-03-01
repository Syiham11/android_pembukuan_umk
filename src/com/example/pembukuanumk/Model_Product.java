package com.example.pembukuanumk;

public class Model_Product {
	private int id;
	private String nama_produk;
	private String deskripsi_produk;
	private long harga_pokok_produk;
	private long harga_jual_produk;
	private byte[] image;
	private int umk_id;
	
	
	public Model_Product(int id, String nama_produk, String deskripsi_produk,
			long harga_pokok_produk, long harga_jual_produk, byte[] image,int umk_id) {
		super();
		this.id = id;
		this.nama_produk = nama_produk;
		this.deskripsi_produk = deskripsi_produk;
		this.harga_pokok_produk = harga_pokok_produk;
		this.harga_jual_produk = harga_jual_produk;
		this.image = image;
		this.umk_id=umk_id;
	}
	public Model_Product() {
		super();
	}
	public Model_Product(String nama_produk, String deskripsi_produk,
			long harga_pokok_produk, long harga_jual_produk, byte[] image,int umk_id) {
		super();
		this.nama_produk = nama_produk;
		this.deskripsi_produk = deskripsi_produk;
		this.harga_pokok_produk = harga_pokok_produk;
		this.harga_jual_produk = harga_jual_produk;
		this.image = image;
		this.umk_id=umk_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama_produk() {
		return nama_produk;
	}
	public void setNama_produk(String nama_produk) {
		this.nama_produk = nama_produk;
	}
	public String getDeskripsi_produk() {
		return deskripsi_produk;
	}
	public void setDeskripsi_produk(String deskripsi_produk) {
		this.deskripsi_produk = deskripsi_produk;
	}
	public long getHarga_pokok_produk() {
		return harga_pokok_produk;
	}
	public void setHarga_pokok_produk(long harga_pokok_produk) {
		this.harga_pokok_produk = harga_pokok_produk;
	}
	public long getHarga_jual_produk() {
		return harga_jual_produk;
	}
	public void setHarga_jual_produk(long harga_jual_produk) {
		this.harga_jual_produk = harga_jual_produk;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getUmk_id() {
		return umk_id;
	}
	public void setUmk_id(int umk_id) {
		this.umk_id = umk_id;
	}
}
