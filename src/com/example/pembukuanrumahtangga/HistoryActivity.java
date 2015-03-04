package com.example.pembukuanrumahtangga;

import java.util.List;

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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryActivity extends Activity {
	private String email=new String();
	private List<PengeluaranModel> listPengeluaran;
	private List<PemasukanModel> listPemasukan;
	// Database Helper
    DatabaseHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.halaman_history);
		this.db = new DatabaseHelper(this);
		email= getIntent().getExtras().getString("email");
		listPemasukan=db.getAllPemasukan(db.getUserEmailPassword(email).getId());
		listPengeluaran=db.getAllPengeluaran(db.getUserEmailPassword(email).getId());
		initTable();
	}
	
	private void initTable() {
		TableLayout tl = (TableLayout) findViewById(R.id.tableLayout);
		TableLayout tl2 = (TableLayout) findViewById(R.id.tableLayout2);
		for(int i=0; i<listPemasukan.size(); i++) {
			TextView tvCol1 = new TextView(this);
			tvCol1.setText("Pemasukan");
			TextView tvCol2 = new TextView(this);
			tvCol2.setText(listPemasukan.get(i).getNamaPemasukan());
			TextView tvCol3 = new TextView(this);
			tvCol3.setText(listPemasukan.get(i).getJumlahPemasukan()+"");
			TextView tvCol4 = new TextView(this);
			tvCol4.setText(listPemasukan.get(i).getDate());
			
			TableRow tr = new TableRow(this);
			tr.addView(tvCol1);
			tr.addView(tvCol2);
			tr.addView(tvCol3);
			tr.addView(tvCol4);
			tl.addView(tr);
		}
		for(int i=0; i<listPengeluaran.size(); i++) {
			TextView tvCol1 = new TextView(this);
			tvCol1.setText("Pengeluaran");
			TextView tvCol2 = new TextView(this);
			tvCol2.setText(listPengeluaran.get(i).getNamaPengeluaran());
			TextView tvCol3 = new TextView(this);
			tvCol3.setText(listPengeluaran.get(i).getJumlahPengeluaran()+"");
			TextView tvCol4 = new TextView(this);
			tvCol4.setText(listPengeluaran.get(i).getDate());
			
			TableRow tr = new TableRow(this);
			tr.addView(tvCol1);
			tr.addView(tvCol2);
			tr.addView(tvCol3);
			tr.addView(tvCol4);
			tl2.addView(tr);
		}
	}
}
