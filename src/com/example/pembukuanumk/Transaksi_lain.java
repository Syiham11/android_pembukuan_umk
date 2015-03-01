package com.example.pembukuanumk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Transaksi_lain extends Activity implements View.OnClickListener,View.OnFocusChangeListener,OnItemSelectedListener {
	DatabaseHelper db;
	private String umk_email=new String();
	private TextView jam;
	private EditText uraian,nilaiRupiah;
	private Spinner jenisTransaksiPenjualan;
	private Button addTransaksi;
	private ImageButton tambahJenisTransaksi;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaksi_lain);
		setHeader(getString(R.string.Transaksi_lain), false, true);
		this.db = new DatabaseHelper(this);
		prefs = getSharedPreferences("MyPref", MODE_PRIVATE); 
		umk_email= prefs.getString("email", null);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.US);
		   //get current date time with Date()
		   Date date = new Date();
	 
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();

	 
		
		jam = (TextView)findViewById(R.id.isiJam_transaksiLain);
		jam.setText(dateFormat.format(cal.getTime()));
		
		uraian = (EditText)findViewById(R.id.isiUraian_transaksiLain);
		nilaiRupiah = (EditText)findViewById(R.id.isiNilaiRupiah_transaksiLain);
		uraian.setOnFocusChangeListener(this);
		nilaiRupiah.setOnFocusChangeListener(this);
		
		jenisTransaksiPenjualan = (Spinner)findViewById(R.id.spinnerJenisTransaksiLain);
		jenisTransaksiPenjualan.setOnItemSelectedListener(this);

		addTransaksi = (Button)findViewById(R.id.buttonTransaksiPenjualanLain);
		addTransaksi.setOnClickListener(this);
		
		tambahJenisTransaksi = (ImageButton)findViewById(R.id.tambahJenisTransaksi);
		tambahJenisTransaksi.setOnClickListener(this);
		
		// Loading spinner data from database
        loadSpinnerData();
	}
	
	public void loadSpinnerData() {
        // Spinner Drop down elements
        List<String> lables = new ArrayList<String>(); 
        List<Model_Jenis_Transaksi_Lain> mtl = new ArrayList<Model_Jenis_Transaksi_Lain>();
        for(int i=0;i<db.getAllJenis_transaksi_lain().size();i++){
        	lables.add(mtl.get(i).getNama_jenis_transaksi());
        }
 
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
 
        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        jenisTransaksiPenjualan.setAdapter(dataAdapter);
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
		/*Bundle bundleEmail = new Bundle();
		bundleEmail.putString("email", umk_email);
		intent.putExtras(bundleEmail);*/
    	startActivity(intent);
		}
		if(v.getId()==R.id.buttonTransaksiPenjualanLain){
			Model_Transaksi_Lain mtl = new Model_Transaksi_Lain(jam.getText().toString(),jenisTransaksiPenjualan.getItemAtPosition(jenisTransaksiPenjualan.getSelectedItemPosition()).toString(),uraian.getText().toString(),Long.parseLong(nilaiRupiah.getText().toString()),db.getUMK(umk_email).getId(),db.getAllJenis_transaksi_lain_By_Name(jenisTransaksiPenjualan.getItemAtPosition(jenisTransaksiPenjualan.getSelectedItemPosition()).toString()).getId());
			db.createTransaksi_lain(mtl);
			Toast.makeText(getApplicationContext(), "Transaksi Berhasil Ditambah", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this,Main_menu_v2.class);
			/*Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);*/
			startActivity(intent);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if (!hasFocus) {
            hideKeyboard(v);
        }
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(parent.getContext(), 
				"OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
				Toast.LENGTH_SHORT).show();
		  }
		
	

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
