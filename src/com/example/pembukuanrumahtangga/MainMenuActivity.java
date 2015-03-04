package com.example.pembukuanrumahtangga;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class MainMenuActivity extends Activity implements View.OnClickListener {
	private ImageButton addPemasukan,addPengeluaran,history,exitApp;
	private TextView totalPemasukan,totalPengeluaran,balance;
	private String email=new String();
	//private PemasukanModel pm;
	// Database Helper
    DatabaseHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_utama);
		this.db = new DatabaseHelper(this);
		email= getIntent().getExtras().getString("email");
		
		addPemasukan = (ImageButton)findViewById(R.id.addPemasukan);
		addPengeluaran = (ImageButton)findViewById(R.id.addPengeluaran);
		history = (ImageButton)findViewById(R.id.history);
		exitApp = (ImageButton)findViewById(R.id.exitApp);
		totalPemasukan =(TextView)findViewById(R.id.totalPemasukan);
		totalPengeluaran =(TextView)findViewById(R.id.totalPengeluaran);
		balance =(TextView)findViewById(R.id.balance);
		
		addPemasukan.setOnClickListener(this);
		addPengeluaran.setOnClickListener(this);
		history.setOnClickListener(this);
		exitApp.setOnClickListener(this);
		//pm = db.getPemasukanModel(db.getUserEmailPassword(email).getId());
		int newPemasukan = db.getSumPemasukan(db.getUserEmailPassword(email).getId());
		int newPengeluaran = db.getSumPengeluaran(db.getUserEmailPassword(email).getId());
		int currPemasukan = db.getUserEmailPassword(email).getTotalPemasukan();
		int currPengeluaran = db.getUserEmailPassword(email).getTotalPengeluaran();
		currPemasukan+=newPemasukan;
		currPengeluaran+=newPengeluaran;
		totalPemasukan.setText(""+currPemasukan);
		totalPengeluaran.setText(""+currPengeluaran);
		balance.setText(""+(currPemasukan-currPengeluaran));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.addPemasukan){
			Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
			Intent i1 = new Intent(this,PemasukanActivity.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", email);
			i1.putExtras(bundleEmail);
			startActivity(i1);
		}
		if(v.getId()==R.id.addPengeluaran){
			Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
			Intent i2 = new Intent(this,PengeluaranActivity.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", email);
			i2.putExtras(bundleEmail);
			startActivity(i2);
		}
		if(v.getId()==R.id.history){
			Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
			Intent i3 = new Intent(this,HistoryActivity.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", email);
			i3.putExtras(bundleEmail);
			startActivity(i3);
		}
		if(v.getId()==R.id.exitApp){
			Toast.makeText(getApplicationContext(), "berhasil keluar", Toast.LENGTH_SHORT).show();
			finish();
            System.exit(0);
		}
	}
}
