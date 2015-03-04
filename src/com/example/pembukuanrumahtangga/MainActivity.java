package com.example.pembukuanrumahtangga;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements View.OnClickListener {

	private EditText email,password;
	private Button register,login;
	// Database Helper
    DatabaseHelper db;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		this.db = new DatabaseHelper(this);
		//db.resetDB();
		
		email = (EditText)findViewById(R.id.emailaddress);
		password = (EditText)findViewById(R.id.password);
		
		register = (Button)findViewById(R.id.registerButton);
		login = (Button)findViewById(R.id.loginButton);
		register.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.registerButton:
			Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
			Intent i1 = new Intent(this,RegisterActivity.class);
			startActivity(i1);
			break;
		
		case R.id.loginButton:
			String emailTemp = new String();
			String passwordTemp = new String();
			emailTemp = db.getUserEmailPassword(email.getText().toString()).getEmail();
			passwordTemp = db.getUserEmailPassword(email.getText().toString()).getPassword();
			if(email.getText().toString().equals(emailTemp) && password.getText().toString().equals(passwordTemp)){
				Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
				Intent i2 = new Intent(this,MainMenuActivity.class);
				Bundle bundleEmail = new Bundle();
				bundleEmail.putString("email", emailTemp);
				i2.putExtras(bundleEmail);
				startActivity(i2);
			}else{
				Toast.makeText(getApplicationContext(), "Email atau Password salah", Toast.LENGTH_SHORT).show();
			}
			
			break;
		}
	}

}
