package com.example.pembukuanumk;

public class Model_Transaksi_Penjualan {
	private int id;
	private String tanggal_transaksi_penjualan;
	private String uraian_transaksi;
	private long nilai_rupiah;
	private int umk_id;
	
	public Model_Transaksi_Penjualan() {
		super();
	}
	public Model_Transaksi_Penjualan(String tanggal_transaksi_penjualan,
			 String uraian_transaksi,
			long nilai_rupiah,int umk_id) {
		super();
		this.tanggal_transaksi_penjualan = tanggal_transaksi_penjualan;
		this.uraian_transaksi = uraian_transaksi;
		this.nilai_rupiah = nilai_rupiah;
		this.umk_id = umk_id;
	}
	public Model_Transaksi_Penjualan(int id,
			String tanggal_transaksi_penjualan, String uraian_transaksi, long nilai_rupiah,int umk_id) {
		super();
		this.id = id;
		this.tanggal_transaksi_penjualan = tanggal_transaksi_penjualan;
		this.uraian_transaksi = uraian_transaksi;
		this.nilai_rupiah = nilai_rupiah;
		this.umk_id = umk_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTanggal_transaksi_penjualan() {
		return tanggal_transaksi_penjualan;
	}
	public void setTanggal_transaksi_penjualan(String tanggal_transaksi_penjualan) {
		this.tanggal_transaksi_penjualan = tanggal_transaksi_penjualan;
	}
	
	public String getUraian_transaksi() {
		return uraian_transaksi;
	}
	public void setUraian_transaksi(String uraian_transaksi) {
		this.uraian_transaksi = uraian_transaksi;
	}
	public long getNilai_rupiah() {
		return nilai_rupiah;
	}
	public void setNilai_rupiah(long nilai_rupiah) {
		this.nilai_rupiah = nilai_rupiah;
	}
	public int getUmk_id() {
		return umk_id;
	}
	public void setUmk_id(int umk_id) {
		this.umk_id = umk_id;
	}
	
	
}
