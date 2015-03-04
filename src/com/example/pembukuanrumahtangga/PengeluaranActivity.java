package com.example.pembukuanrumahtangga;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Build;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PengeluaranActivity extends Activity implements View.OnClickListener {

	private Button tanggal,jam,submitPengeluaran;
	private EditText namaPengeluaran,jumlahPengeluaran;
	private Spinner kategoriPengeluaran;
	private String[] array_kategoriPengeluaran;
	private String email = new String();
	// Database Helper
    DatabaseHelper db;
	Calendar c= Calendar.getInstance();
	int startYear = c.get(Calendar.YEAR);
	int startMonth = c.get(Calendar.MONTH);
	int startDay = c.get(Calendar.DAY_OF_MONTH);
	
	int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_pengeluaran);
		this.db = new DatabaseHelper(this);
		email= getIntent().getExtras().getString("email");
		tanggal = (Button)findViewById(R.id.dateButton);
		jam = (Button)findViewById(R.id.jamButton);
		submitPengeluaran = (Button)findViewById(R.id.submitPengeluaran);
		namaPengeluaran = (EditText)findViewById(R.id.namaPengeluaran);
		jumlahPengeluaran = (EditText)findViewById(R.id.jumlahPengeluaran);
		
		tanggal.setOnClickListener(this);
		jam.setOnClickListener(this);
		submitPengeluaran.setOnClickListener(this);
		
		kategoriPengeluaran = (Spinner)findViewById(R.id.spinnerKategoriPengeluaran);
		array_kategoriPengeluaran = new String[4];
		array_kategoriPengeluaran[0] = "Makan";
		array_kategoriPengeluaran[1] = "Belanja";
		array_kategoriPengeluaran[2] = "Alat Rumah Tangga";
		array_kategoriPengeluaran[3] = "Lain-lain";
		ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,array_kategoriPengeluaran);
		kategoriPengeluaran.setAdapter(adapter);
	}
	
	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	            int dayOfMonth) {
	        // TODO Auto-generated method stub
	        c.set(Calendar.YEAR, year);
	        c.set(Calendar.MONTH, monthOfYear);
	        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	        updateLabel();
	    }

	};
	
	public void updateLabel() {

	    String myFormat = "dd/MM/yy"; //In which you need put here
	    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

	    tanggal.setText(sdf.format(c.getTime()));
	    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.submitPengeluaran){
			int tempID  = db.getUserEmailPassword(email).getId();
			PengeluaranModel pm = new PengeluaranModel(tempID,tanggal.getText().toString(),namaPengeluaran.getText().toString(),Integer.parseInt(jumlahPengeluaran.getText().toString()));
			db.createPengeluaran(pm);
			Toast.makeText(getApplicationContext(), "Pengeluaran Berhasil Dibuat", Toast.LENGTH_SHORT).show();
			Intent i1 = new Intent(this,MainMenuActivity.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", email);
			i1.putExtras(bundleEmail);
			startActivity(i1);
		}
		if(v.getId()==R.id.dateButton){
			new DatePickerDialog(this, date, c
                    .get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)).show();
		}
		if(v.getId()==R.id.jamButton){
			TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					 jam.setText( hourOfDay + ":" + minute);
				}
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        }	
		
	}


}
