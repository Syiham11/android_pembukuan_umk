package com.example.pembukuanumk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements View.OnClickListener,View.OnFocusChangeListener {
	
	private EditText email,password;
	private Button login,register;
	DatabaseHelper db;
	SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.db = new DatabaseHelper(this);
		editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
		email = (EditText)findViewById(R.id.loginEmail);
		password = (EditText)findViewById(R.id.loginPassword);
		email.setOnFocusChangeListener(this);
		password.setOnFocusChangeListener(this);
		
		
		login=(Button)findViewById(R.id.loginButton);
		register=(Button)findViewById(R.id.registerButton);
		
		register.setOnClickListener(this);
		login.setOnClickListener(this);
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
		if(v.getId()==R.id.registerButton){
			Intent i1 = new Intent(this,Register.class);
			startActivity(i1);
		}
		if(v.getId()==R.id.loginButton){
			String emailTemp = new String();
			String passwordTemp = new String();
			
			emailTemp = db.getUMK(email.getText().toString()).getEmail();
			passwordTemp = db.getUMK(email.getText().toString()).getPassword();
			if((email.getText().toString().equals(emailTemp)&&(password.getText().toString().equals(passwordTemp)))){
				Intent i1 = new Intent(this,Main_menu_v2.class);
				Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
				editor.putString("email", emailTemp);
				editor.commit();
				startActivity(i1);
			}
			else{
				Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
			}
			
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
