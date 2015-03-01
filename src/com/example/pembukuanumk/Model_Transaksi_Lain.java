package com.example.pembukuanumk;

public class Model_Transaksi_Lain {
	private int id;
	private String tanggal_transaksi_lain;
	private String jenis_transaksi;
	private String uraian_transaksi_lain;
	private long nilai_rupiah_transaksi_lain;
	private int umk_id;
	private int jenis_transaksi_id;
	
	public Model_Transaksi_Lain(int id, String tanggal_transaksi_lain,
			String jenis_transaksi, String uraian_transaksi_lain,
			long nilai_rupiah_transaksi_lain,int umk_id,int jenis_transaksi_id) {
		super();
		this.id = id;
		this.tanggal_transaksi_lain = tanggal_transaksi_lain;
		this.jenis_transaksi = jenis_transaksi;
		this.uraian_transaksi_lain = uraian_transaksi_lain;
		this.nilai_rupiah_transaksi_lain = nilai_rupiah_transaksi_lain;
		this.umk_id = umk_id;
		this.jenis_transaksi_id = jenis_transaksi_id;
	}

	public Model_Transaksi_Lain(String tanggal_transaksi_lain,
			String jenis_transaksi, String uraian_transaksi_lain,
			long nilai_rupiah_transaksi_lain,int umk_id,int jenis_transaksi_id) {
		super();
		this.tanggal_transaksi_lain = tanggal_transaksi_lain;
		this.jenis_transaksi = jenis_transaksi;
		this.uraian_transaksi_lain = uraian_transaksi_lain;
		this.nilai_rupiah_transaksi_lain = nilai_rupiah_transaksi_lain;
		this.umk_id = umk_id;
		this.jenis_transaksi_id = jenis_transaksi_id;
	}

	public Model_Transaksi_Lain() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTanggal_transaksi_lain() {
		return tanggal_transaksi_lain;
	}

	public void setTanggal_transaksi_lain(String tanggal_transaksi_lain) {
		this.tanggal_transaksi_lain = tanggal_transaksi_lain;
	}

	public String getJenis_transaksi() {
		return jenis_transaksi;
	}

	public void setJenis_transaksi(String jenis_transaksi) {
		this.jenis_transaksi = jenis_transaksi;
	}

	public String getUraian_transaksi_lain() {
		return uraian_transaksi_lain;
	}

	public void setUraian_transaksi_lain(String uraian_transaksi_lain) {
		this.uraian_transaksi_lain = uraian_transaksi_lain;
	}

	public long getNilai_rupiah_transaksi_lain() {
		return nilai_rupiah_transaksi_lain;
	}

	public void setNilai_rupiah_transaksi_lain(long nilai_rupiah_transaksi_lain) {
		this.nilai_rupiah_transaksi_lain = nilai_rupiah_transaksi_lain;
	}

	public int getUmk_id() {
		return umk_id;
	}

	public void setUmk_id(int umk_id) {
		this.umk_id = umk_id;
	}

	public int getJenis_transaksi_id() {
		return jenis_transaksi_id;
	}

	public void setJenis_transaksi_id(int jenis_transaksi_id) {
		this.jenis_transaksi_id = jenis_transaksi_id;
	}
	
	
	
}
