package com.example.pembukuanumk;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tambah_produk extends Activity implements View.OnClickListener,View.OnFocusChangeListener {
	private String umk_email=new String();
	private EditText namaProduk,deskripsiProduk,hargaPokok,hargaJual;
	private Button tambahProduk;
	private ImageView foto_produk;
	DatabaseHelper db;
	SharedPreferences prefs;
	// PICK_PHOTO_CODE is a constant integer
		public final static int PICK_PHOTO_CODE = 1046;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tambah_produk);
		setHeader(getString(R.string.TambahProduk), false, true);
		prefs = getSharedPreferences("MyPref", MODE_PRIVATE); 
		umk_email= prefs.getString("email", null);
		this.db = new DatabaseHelper(this);
		namaProduk = (EditText)findViewById(R.id.namaProduk);
		deskripsiProduk = (EditText)findViewById(R.id.deskripsiProduk);
		hargaPokok = (EditText)findViewById(R.id.hargaPokokProduk);
		hargaJual = (EditText)findViewById(R.id.hargaJualProduk);
		namaProduk.setOnFocusChangeListener(this);
		deskripsiProduk.setOnFocusChangeListener(this);
		hargaPokok.setOnFocusChangeListener(this);
		hargaJual.setOnFocusChangeListener(this);
		
		tambahProduk = (Button)findViewById(R.id.addProductButton);
		tambahProduk.setOnClickListener(this);
		
		foto_produk = (ImageView)findViewById(R.id.fotoProduk);
		foto_produk.setOnClickListener(this);
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
	        ImageView ivPreview = (ImageView) findViewById(R.id.fotoProduk);
	        ivPreview.setImageBitmap(selectedImage);   
	    }
	}
	
	
	
	public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
	
	public void setHeader(String title, boolean btnHomeVisible, boolean btnAddVisible)
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
	public byte[] getBytesFromBitmap(Bitmap bitmap) {
	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.JPEG, 70, stream);
	    return stream.toByteArray();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnHome){
		Intent intent = new Intent(getApplicationContext(), Main_menu_v2.class);
    	startActivity(intent);
		}
		
		if(v.getId()==R.id.fotoProduk){
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
		}
		if(v.getId()==R.id.addProductButton){
			Bitmap bitmap = ((BitmapDrawable)foto_produk.getDrawable()).getBitmap();
			Model_Product mp = new Model_Product(
					namaProduk.getText().toString(),deskripsiProduk.getText().toString(),
					Long.parseLong(hargaPokok.getText().toString()),
					Long.parseLong(hargaJual.getText().toString()),
					getBytesFromBitmap(bitmap),db.getUMK(umk_email).getId()
					);
			db.createProduk(mp);
			Intent intent = new Intent(getApplicationContext(), Katalog_produk.class);
			Bundle bundle = new Bundle();
			bundle.putString("product_id", db.getProductByName(namaProduk.getText().toString()).getNama_produk());
			/*bundle.putString("email", umk_email);*/
			intent.putExtras(bundle);
			Toast.makeText(getApplicationContext(), "Produk Berhasil Ditambah", Toast.LENGTH_LONG).show();
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

}
