package com.example.pembukuanumk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main_menu extends Activity implements View.OnClickListener {
	
	private ImageButton profilUMK,katalogProduk,transaksiPenjualan,transaksiLain,laporan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		profilUMK = (ImageButton)findViewById(R.id.profilUMK);
		katalogProduk = (ImageButton)findViewById(R.id.katalogProduk);
		transaksiPenjualan = (ImageButton)findViewById(R.id.transaksiPenjualan);
		transaksiLain = (ImageButton)findViewById(R.id.transaksiLain);
		laporan = (ImageButton)findViewById(R.id.laporan);
		profilUMK.setOnClickListener(this);
		katalogProduk.setOnClickListener(this);
		transaksiPenjualan.setOnClickListener(this);
		transaksiLain.setOnClickListener(this);
		laporan.setOnClickListener(this);
	}
	
	public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
}
