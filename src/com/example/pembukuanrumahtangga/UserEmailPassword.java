package com.example.pembukuanrumahtangga;

public class UserEmailPassword {
	private int id;
	private String email;
	private String password;
	private int totalPemasukan;
	private int totalPengeluaran;
	private int balance;
	
	public UserEmailPassword(){
		
	}
	
	

	public UserEmailPassword(String email, String password,
			int totalPemasukan, int totalPengeluaran, int balance) {
		super();
		
		this.email = email;
		this.password = password;
		this.totalPemasukan = totalPemasukan;
		this.totalPengeluaran = totalPengeluaran;
		this.balance = balance;
	}

	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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
	
	public int getTotalPemasukan() {
		return totalPemasukan;
	}

	public void setTotalPemasukan(int totalPemasukan) {
		this.totalPemasukan = totalPemasukan;
	}

	public int getTotalPengeluaran() {
		return totalPengeluaran;
	}

	public void setTotalPengeluaran(int totalPengeluaran) {
		this.totalPengeluaran = totalPengeluaran;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
