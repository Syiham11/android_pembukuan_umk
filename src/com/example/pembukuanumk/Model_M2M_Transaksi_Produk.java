package com.example.pembukuanumk;

public class Model_M2M_Transaksi_Produk {
	private int key_id;
	private int produk_ids;
	private int jumlah_barang;
	private int umk_id;
	public Model_M2M_Transaksi_Produk(int produk_ids, int jumlah_barang,int umk_id) {
		super();
		this.produk_ids = produk_ids;
		this.jumlah_barang = jumlah_barang;
		this.umk_id = umk_id;
	}
	public Model_M2M_Transaksi_Produk(int key_id, int produk_ids,
			int jumlah_barang,int umk_id) {
		super();
		this.key_id = key_id;
		this.produk_ids = produk_ids;
		this.jumlah_barang = jumlah_barang;
		this.umk_id = umk_id;
	}
	public Model_M2M_Transaksi_Produk() {
		super();
	}
	public int getKey_id() {
		return key_id;
	}
	public void setKey_id(int key_id) {
		this.key_id = key_id;
	}
	public int getProduk_ids() {
		return produk_ids;
	}
	public void setProduk_ids(int produk_ids) {
		this.produk_ids = produk_ids;
	}
	public int getJumlah_barang() {
		return jumlah_barang;
	}
	public void setJumlah_barang(int jumlah_barang) {
		this.jumlah_barang = jumlah_barang;
	}
	public int getUmk_id() {
		return umk_id;
	}
	public void setUmk_id(int umk_id) {
		this.umk_id = umk_id;
	}
	
	
	
	
}
