package com.example.pembukuanumk;



import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;

public class Register extends Activity implements View.OnClickListener,View.OnFocusChangeListener {
	
	private EditText email,password,namaUMK,deskripsiUMK,alamatUMK,teleponUMK,saldoUMK;
	private ImageView logoUMK;
	private Button register;
	DatabaseHelper db;
	// PICK_PHOTO_CODE is a constant integer
			public final static int PICK_PHOTO_CODE = 1046;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		this.db = new DatabaseHelper(this);
		email = (EditText)findViewById(R.id.regEmail);
		password = (EditText)findViewById(R.id.regPassword);
		namaUMK = (EditText)findViewById(R.id.regNamaUMK);
		deskripsiUMK = (EditText)findViewById(R.id.regDeskripsiUMK);
		alamatUMK = (EditText)findViewById(R.id.regAlamatLengkap);
		teleponUMK = (EditText)findViewById(R.id.regNomorTelepon);
		saldoUMK = (EditText)findViewById(R.id.saldoAwalUMK);
		logoUMK = (ImageView)findViewById(R.id.logoUMK);
		logoUMK.setOnClickListener(this);
		email.setOnFocusChangeListener(this);
		password.setOnFocusChangeListener(this);
		namaUMK.setOnFocusChangeListener(this);
		deskripsiUMK.setOnFocusChangeListener(this);
		alamatUMK.setOnFocusChangeListener(this);
		teleponUMK.setOnFocusChangeListener(this);
		saldoUMK.setOnFocusChangeListener(this);
		
		register=(Button)findViewById(R.id.buttonRegister);
		register.setOnClickListener(this);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data != null) {
	        Uri photoUri = data.getData();
	        // Do something with the photo based on Uri
	        Bitmap selectedImage = null;
			try {
				selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Load the selected image into a preview
	        ImageView ivPreview = (ImageView) findViewById(R.id.logoUMK);
	        ivPreview.setImageBitmap(selectedImage);   
	    }
	}
	
	public byte[] getBytesFromBitmap(Bitmap bitmap) {
	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.JPEG, 70, stream);
	    return stream.toByteArray();
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
		
		if(v.getId()==R.id.buttonRegister){
			Bitmap bitmap = ((BitmapDrawable)logoUMK.getDrawable()).getBitmap();
			Model_UMK UMK = new Model_UMK(email.getText().toString(),
					password.getText().toString(),namaUMK.getText().toString(),
					deskripsiUMK.getText().toString(),alamatUMK.getText().toString(),
					Long.parseLong(teleponUMK.getText().toString()),getBytesFromBitmap(bitmap),Long.parseLong(saldoUMK.getText().toString()));
			
			db.createUMK(UMK);
			Intent i1 = new Intent(this,Main_menu_v2.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", email.getText().toString());
			i1.putExtras(bundleEmail);
			startActivity(i1);
		}
		if(v.getId()==R.id.logoUMK){
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if (!hasFocus) {
            hideKeyboard(v);
        }
	}
}

