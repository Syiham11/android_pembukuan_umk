package com.example.pembukuanumk;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class Ubah_product_detail extends Activity implements View.OnClickListener,View.OnFocusChangeListener {
	private String bundleProduct=new String();
	private String umk_email=new String();
	private EditText ubah_namaProduk,ubah_deskripsiProduk,ubah_hargaPokok,ubah_hargaJual;
	private Button ubah_Produk;
	private ImageView ubah_foto_produk;
	DatabaseHelper db;
	SharedPreferences prefs;
	// PICK_PHOTO_CODE is a constant integer
		public final static int PICK_PHOTO_CODE = 1046;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_detail_product);
		setHeader(getString(R.string.UbahDetailProduk), false, true);
		bundleProduct= getIntent().getExtras().getString("product_id");
		prefs = getSharedPreferences("MyPref", MODE_PRIVATE); 
		umk_email= prefs.getString("email", null);
		this.db=new DatabaseHelper(this);
		
		ubah_namaProduk = (EditText)findViewById(R.id.ubah_namaProduk);
		ubah_deskripsiProduk = (EditText)findViewById(R.id.ubah_deskripsiProduk);
		ubah_hargaPokok = (EditText)findViewById(R.id.ubah_hargaPokokProduk);
		ubah_hargaJual = (EditText)findViewById(R.id.ubah_hargaJualProduk);
		ubah_namaProduk.setOnFocusChangeListener(this);
		ubah_deskripsiProduk.setOnFocusChangeListener(this);
		ubah_hargaPokok.setOnFocusChangeListener(this);
		ubah_hargaJual.setOnFocusChangeListener(this);
		
		ubah_namaProduk.setText(db.getProductByName(bundleProduct).getNama_produk());
		ubah_deskripsiProduk.setText(db.getProductByName(bundleProduct).getDeskripsi_produk());
		ubah_hargaPokok.setText(Long.toString(db.getProductByName(bundleProduct).getHarga_pokok_produk()));
		ubah_hargaJual.setText(Long.toString(db.getProductByName(bundleProduct).getHarga_jual_produk()));
		
		ubah_Produk = (Button)findViewById(R.id.ubah_ProductButton);
		ubah_Produk.setOnClickListener(this);
		
		ubah_foto_produk = (ImageView)findViewById(R.id.ubah_fotoProduk);
		ubah_foto_produk.setOnClickListener(this);
		
		Bitmap bmp = BitmapFactory.decodeByteArray(db.getProductByName(bundleProduct).getImage(), 0, db.getProductByName(bundleProduct).getImage().length);
		ImageView image = (ImageView) findViewById(R.id.ubah_fotoProduk);

		image.setImageBitmap(bmp);
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
	        ImageView ivPreview = (ImageView) findViewById(R.id.ubah_fotoProduk);
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
		
		if(v.getId()==R.id.ubah_fotoProduk){
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
		}
		if(v.getId()==R.id.ubah_ProductButton){
			Bitmap bitmap = ((BitmapDrawable)ubah_foto_produk.getDrawable()).getBitmap();
			Model_Product mp = new Model_Product(
					ubah_namaProduk.getText().toString(),ubah_deskripsiProduk.getText().toString(),
					Long.parseLong(ubah_hargaPokok.getText().toString()),
					Long.parseLong(ubah_hargaJual.getText().toString()),
					getBytesFromBitmap(bitmap),db.getUMK(umk_email).getId()
					);
			db.updateProduct(mp);
			Intent intent = new Intent(getApplicationContext(), Katalog_produk.class);
			Bundle bundle = new Bundle();
			bundle.putString("product_id", ubah_namaProduk.getText().toString());
			bundle.putString("email", umk_email);
			intent.putExtras(bundle);
			Toast.makeText(getApplicationContext(), "Produk Berhasil Diubah", Toast.LENGTH_LONG).show();
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
