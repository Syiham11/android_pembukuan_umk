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


public class PemasukanActivity extends Activity implements View.OnClickListener {
	
	private Button tanggal,jam,submitPemasukan;
	private EditText jumlahPemasukan,namaPemasukan;
	private Spinner kategoriPemasukan;
	private String[] array_kategoriPemasukan;
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
		setContentView(R.layout.menu_pemasukan);
		email= getIntent().getExtras().getString("email");
		this.db = new DatabaseHelper(this);
		tanggal = (Button)findViewById(R.id.dateButton);
		jam = (Button)findViewById(R.id.jamButton);
		submitPemasukan = (Button)findViewById(R.id.submitPemasukan);
		jumlahPemasukan = (EditText)findViewById(R.id.jumlahPemasukan);
		namaPemasukan = (EditText)findViewById(R.id.namaPemasukan);
		tanggal.setOnClickListener(this);
		jam.setOnClickListener(this);
		submitPemasukan.setOnClickListener(this);
		
		kategoriPemasukan = (Spinner)findViewById(R.id.spinnerKategoriPemasukan);
		array_kategoriPemasukan = new String[4];
		array_kategoriPemasukan[0] = "Gaji";
		array_kategoriPemasukan[1] = "Bonus";
		array_kategoriPemasukan[2] = "Tak terduga";
		array_kategoriPemasukan[3] = "Lain-lain";
		ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,array_kategoriPemasukan);
		kategoriPemasukan.setAdapter(adapter);
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
		if(v.getId()==R.id.submitPemasukan){
			int tempID  = db.getUserEmailPassword(email).getId();
			PemasukanModel pm = new PemasukanModel(tempID,tanggal.getText().toString(),namaPemasukan.getText().toString(),Integer.parseInt(jumlahPemasukan.getText().toString()));
			db.createPemasukan(pm);
			Toast.makeText(getApplicationContext(), "Pemasukan Berhasil Dibuat", Toast.LENGTH_SHORT).show();
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