package com.example.pembukuanrumahtangga;

import com.example.pembukuanrumahtangga.MainActivity.PlaceholderFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RegisterActivity extends Activity implements View.OnClickListener {

	private EditText email,password,confirmPassword;
	private Button submitButton;
	// Database Helper
    DatabaseHelper db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.halaman_register);
		
		
		email = (EditText)findViewById(R.id.email);
		password = (EditText)findViewById(R.id.password);
		confirmPassword = (EditText)findViewById(R.id.confirmPassword);
		
		submitButton = (Button)findViewById(R.id.submitButton);
		submitButton.setOnClickListener(this);
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
		// Inserting user in db
		
		if(v.getId()==R.id.submitButton){
			this.db = new DatabaseHelper(this);
			UserEmailPassword UEP = new UserEmailPassword(email.getText().toString(),password.getText().toString(),0,0,0);
			
			long user_id= db.createUser(UEP);
			
			Toast.makeText(getApplicationContext(),"User " + db.getUserEmailPassword(email.getText().toString()).getEmail() +" berhasil dibuat", Toast.LENGTH_SHORT).show();
			
			Intent i1 = new Intent(this,MainMenuActivity.class);
			Bundle bundleEmail = new Bundle();
			bundleEmail.putString("email", email.getText().toString());
			i1.putExtras(bundleEmail);
			startActivity(i1);
			
		}
	}

}
