package com.example.pembukuanumk;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Main_menu_v2 extends Activity implements View.OnClickListener {
	
	private Button profilUMK,katalogProduk,transaksiPenjualan,transaksiLain,laporan;
	private String umk_email=new String();
	DatabaseHelper db;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu_v2);
		setHeader(getString(R.string.HomeActivityTitle), false, true);
		this.db = new DatabaseHelper(this);
		prefs = getSharedPreferences("MyPref", MODE_PRIVATE); 
		umk_email= prefs.getString("email", null);
		
		
		profilUMK = (Button)findViewById(R.id.profil_umk_button);
		katalogProduk = (Button)findViewById(R.id.katalog_button);
		transaksiPenjualan = (Button)findViewById(R.id.transaksi_penjualan_button);
		transaksiLain = (Button)findViewById(R.id.transaksi_lain_button);
		laporan = (Button)findViewById(R.id.laporan_button);
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
	
	public void setHeader(String title, boolean btnHomeVisible, boolean btnFeedbackVisible)
    {
    		ViewStub stub = (ViewStub) findViewById(R.id.vsHeader);
    		View inflated = stub.inflate();
          
    		TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
    		txtTitle.setText(title);
    		
    		Button btnHome = (Button) inflated.findViewById(R.id.btnHome);
    		btnHome.setOnClickListener(this);

    		
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
		if(v.getId()==R.id.btnHome){
		Intent intent = new Intent(getApplicationContext(), Main_menu_v2.class);
		Bundle bundleEmail = new Bundle();
		bundleEmail.putString("email", umk_email);
		intent.putExtras(bundleEmail);
    	startActivity(intent);
		}
		if(v.getId()==R.id.profil_umk_button){
			Intent intent = new Intent(getApplicationContext(), Profil_umk.class);
			/*Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);*/
	    	startActivity(intent);
		}
		if(v.getId()==R.id.katalog_button){
			Intent intent = new Intent(getApplicationContext(), Katalog_produk.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);
	    	startActivity(intent);
		}
		if(v.getId()==R.id.transaksi_penjualan_button){
			Intent intent = new Intent(getApplicationContext(), Transaksi_penjualan.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);
	    	startActivity(intent);
		}
		if(v.getId()==R.id.transaksi_lain_button){
			Intent intent = new Intent(getApplicationContext(), Transaksi_lain.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);
	    	startActivity(intent);
		}
		if(v.getId()==R.id.laporan_button){
			Intent intent = new Intent(getApplicationContext(), Laporan.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);
	    	startActivity(intent);
		}
	}

	
}

