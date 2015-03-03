package com.example.pembukuanumk;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Katalog_produk extends Activity implements OnItemClickListener,OnClickListener  {
	private String umk_email=new String();
	ListView listView;
	DatabaseHelper db;
	SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_katalog_produk);
		setHeader(getString(R.string.Katalog_produk), false, true);
		this.db = new DatabaseHelper(this);
		listView = (ListView)findViewById(R.id.listProduct);
		prefs = getSharedPreferences("MyPref", MODE_PRIVATE); 
		umk_email= prefs.getString("email", null);
		int umk_id;
		umk_id = db.getUMK(umk_email).getId();
		List<String> product = new ArrayList<String>();
		for(int i=0;i<db.getAllproducts(umk_id).size();i++){
			product.add(db.getAllproducts(umk_id).get(i).getNama_produk());
		}
		 
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, product);
		  listView.setAdapter(adapter); 
		  listView.setOnItemClickListener(this); 
		
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
    		Button btnAddProduct = (Button) inflated.findViewById(R.id.btnAddProduct);
    		btnAddProduct.setOnClickListener(this);

    		
    }
	
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), Main_menu_v2.class);
    	startActivity(intent);
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
    	startActivity(intent);
		}
		if(v.getId()==R.id.btnAddProduct){
		Intent intent = new Intent(getApplicationContext(), Tambah_produk.class);
		/*Bundle bundleEmail = new Bundle();
		bundleEmail.putString("email", umk_email);
		intent.putExtras(bundleEmail);*/
	    startActivity(intent);	
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// ListView Clicked item index
        int itemPosition     = position;
        
        // ListView Clicked item value
        String  itemValue    = (String) listView.getItemAtPosition(position);
           
         // Show Alert 
         Toast.makeText(getApplicationContext(),
           "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
           .show();
         Intent intent = new Intent(getApplicationContext(), Product_detail.class);
         Bundle bundleProduct = new Bundle();
         bundleProduct.putString("product_id", db.getProductByName(itemValue).getNama_produk());
         /*bundleProduct.putString("email", umk_email);*/
			intent.putExtras(bundleProduct);
     	startActivity(intent);
		
	}

}
