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
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Transaksi_penjualan extends Activity implements View.OnClickListener,View.OnFocusChangeListener,OnItemSelectedListener {
	DatabaseHelper db;
	private String umk_email=new String();
	private TextView jam;
	private EditText uraian,nilaiRupiah;
	private Button addTransaksi;
	private ImageButton addBarangTransaksi;
	LinearLayout linearLayout;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaksi_penjualan);
		setHeader(getString(R.string.Transaksi_penjualan), false, true);
		this.db = new DatabaseHelper(this);
		prefs = getSharedPreferences("MyPref", MODE_PRIVATE); 
		umk_email= prefs.getString("email", null);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.US);
		   //get current date time with Date()
		   Date date = new Date();
	 
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();

		linearLayout = (LinearLayout)findViewById(R.id.linearLayoutTransaksiPenjualanBarang);
		
		jam = (TextView)findViewById(R.id.isiJam);
		jam.setText(dateFormat.format(cal.getTime()));
		
		uraian = (EditText)findViewById(R.id.isiUraian);
		nilaiRupiah = (EditText)findViewById(R.id.isiNilaiRupiah);
		uraian.setOnFocusChangeListener(this);
		nilaiRupiah.setOnFocusChangeListener(this);
	
		
		addTransaksi = (Button)findViewById(R.id.buttonTransaksiPenjualan);
		addTransaksi.setOnClickListener(this);
		addBarangTransaksi = (ImageButton)findViewById(R.id.addTransaksiBarang);
		addBarangTransaksi.setOnClickListener(this);
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
		if(v.getId()==R.id.addTransaksiBarang){
			Intent intent = new Intent(getApplicationContext(), Detail_barang_transaksi_penjualan.class);
			/*Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);*/
	    	startActivity(intent);
		}
		if(v.getId()==R.id.buttonTransaksiPenjualan){
			List<?> temp = getIntent().getParcelableArrayListExtra("m2m_mtp");
			for(int j=0;j<temp.size();j++){
				db.createM2M_transaksi_produk((Model_M2M_Transaksi_Produk) temp.get(j));
			}
			Model_Transaksi_Penjualan mtp = new Model_Transaksi_Penjualan(jam.getText().toString(),
					uraian.getText().toString(),
					Long.parseLong(nilaiRupiah.getText().toString()),db.getUMK(umk_email).getId());
			db.createTransaksi_penjualan(mtp);
			Intent intent = new Intent(getApplicationContext(), Detail_barang_transaksi_penjualan.class);
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
