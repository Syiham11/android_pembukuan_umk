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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Profil_umk extends Activity implements View.OnClickListener {

	private TextView isiNamaUmk,isiEmail,isiDeskripsiUMK,isiAlamat,isiNomorTelepon,isiSaldo;
	private ImageView logoUMK;
	private Button changeProfile;
	private String umk_email=new String();
	DatabaseHelper db;
	SharedPreferences prefs;
	
	Bundle bundleEmail = new Bundle();
	// PICK_PHOTO_CODE is a constant integer
		public final static int PICK_PHOTO_CODE = 1046;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil_umk);
		setHeader(getString(R.string.Profil_UMK), false, true);
		this.db = new DatabaseHelper(this);
		prefs = getSharedPreferences("MyPref", MODE_PRIVATE); 
		umk_email= prefs.getString("email", null);
		
		isiNamaUmk = (TextView)findViewById(R.id.isiNamaUMK);
		isiDeskripsiUMK = (TextView)findViewById(R.id.isiDeskripsiUMK);
		isiEmail = (TextView)findViewById(R.id.isiEmail);
		isiAlamat = (TextView)findViewById(R.id.isiAlamat);
		isiNomorTelepon = (TextView)findViewById(R.id.isiNomorTelepon);
		isiSaldo = (TextView)findViewById(R.id.isiSaldo);
		
		logoUMK = (ImageView)findViewById(R.id.logoUMK);
		logoUMK.setOnClickListener(this);
		
		changeProfile = (Button)findViewById(R.id.ubahProfilButton);
		changeProfile.setOnClickListener(this);
		
		
		
		setProfile(umk_email);
	}
	
	public void setProfile(String mail){
		isiNamaUmk.setText(db.getUMK(mail).getNama_umk());
		isiDeskripsiUMK.setText(db.getUMK(mail).getDeskripsi_umk());
		isiEmail.setText(db.getUMK(mail).getEmail());
		isiAlamat.setText(db.getUMK(mail).getAlamat_umk());
		isiNomorTelepon.setText(Long.toString(db.getUMK(mail).getTelepon_umk()));
		isiSaldo.setText(Long.toString(db.getUMK(mail).getSaldo_umk()));
		Bitmap bmp = BitmapFactory.decodeByteArray(db.getUMK(umk_email).getLogo_umk(), 0, db.getUMK(umk_email).getLogo_umk().length);
		ImageView image = (ImageView) findViewById(R.id.logoUMK);

		image.setImageBitmap(bmp);
	}
	
	
	private void callChangeProfile() 
    {
        final Dialog myDialog = new Dialog(this);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_change_profile, null);
        myDialog.setContentView(layout);
        myDialog.setTitle("Ubah Profil");
        final EditText email;
		final EditText password;
		final EditText namaUMK;
		final EditText deskripsiUMK;
		final EditText alamatUMK;
		final EditText teleponUMK;
		final ImageView clogoUMK;
		final EditText saldoUMK;
    	Button register,batal;

        email = (EditText)myDialog.findViewById(R.id.regEmail);
		password = (EditText)myDialog.findViewById(R.id.regPassword);
		namaUMK = (EditText)myDialog.findViewById(R.id.regNamaUMK);
		deskripsiUMK = (EditText)myDialog.findViewById(R.id.regDeskripsiUMK);
		alamatUMK = (EditText)myDialog.findViewById(R.id.regAlamatLengkap);
		teleponUMK = (EditText)myDialog.findViewById(R.id.regNomorTelepon);
		clogoUMK = (ImageView)myDialog.findViewById(R.id.regLogoUMK);
		saldoUMK = (EditText)myDialog.findViewById(R.id.regSaldo);
		/*email.setOnFocusChangeListener(this);
		password.setOnFocusChangeListener(this);
		namaUMK.setOnFocusChangeListener(this);
		deskripsiUMK.setOnFocusChangeListener(this);
		alamatUMK.setOnFocusChangeListener(this);
		teleponUMK.setOnFocusChangeListener(this);*/
		email.setText(db.getUMK(umk_email).getEmail());
		password.setText(db.getUMK(umk_email).getPassword());
		namaUMK.setText(db.getUMK(umk_email).getNama_umk());
		deskripsiUMK.setText(db.getUMK(umk_email).getDeskripsi_umk());
		alamatUMK.setText(db.getUMK(umk_email).getAlamat_umk());
		teleponUMK.setText(Long.toString(db.getUMK(umk_email).getTelepon_umk()));
		saldoUMK.setText(Long.toString(db.getUMK(umk_email).getSaldo_umk()));
		Bitmap bmp = BitmapFactory.decodeByteArray(db.getUMK(umk_email).getLogo_umk(), 0, db.getUMK(umk_email).getLogo_umk().length);
		clogoUMK.setImageBitmap(bmp);
		register=(Button)myDialog.findViewById(R.id.buttonRegister);
		batal=(Button)myDialog.findViewById(R.id.batalUbah);
		clogoUMK.setOnClickListener((new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId()==R.id.regLogoUMK){
					Intent intent = new Intent();
					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);
					startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
				}
			}
			
		}));
		register.setOnClickListener((new View.OnClickListener() {
			public void onClick(View v) {
				Bitmap bitmap = ((BitmapDrawable)clogoUMK.getDrawable()).getBitmap();
				Model_UMK umk = new Model_UMK(db.getUMK(umk_email).getId(),email.getText().toString(),
						password.getText().toString(),namaUMK.getText().toString(),
						deskripsiUMK.getText().toString(),alamatUMK.getText().toString(),
						Long.parseLong(teleponUMK.getText().toString()),getBytesFromBitmap(bitmap),Long.parseLong(saldoUMK.getText().toString()));
				db.updateUMK(umk);
				umk_email= email.getText().toString();
				setProfile(email.getText().toString());
				myDialog.dismiss();
			}
		}));
		batal.setOnClickListener((new View.OnClickListener() {
			public void onClick(View v) {
				myDialog.dismiss();
			}
		}));
         myDialog.show();
        
    }
	
	
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), Main_menu_v2.class);
    	startActivity(intent);
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
	        ImageView ivPreview = (ImageView) findViewById(R.id.regLogoUMK);
	        ivPreview.setImageBitmap(selectedImage);   
	    }
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
	public byte[] getBytesFromBitmap(Bitmap bitmap) {
	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.JPEG, 70, stream);
	    return stream.toByteArray();
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
/*		
		bundleEmail.putString("email", umk_email);
		intent.putExtras(bundleEmail);*/
    	startActivity(intent);
		}
		
		if(v.getId()==R.id.ubahProfilButton){
			callChangeProfile();
			
		}
	}

}
