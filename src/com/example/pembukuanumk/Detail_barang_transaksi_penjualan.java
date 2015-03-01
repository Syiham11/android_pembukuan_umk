package com.example.pembukuanumk;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Detail_barang_transaksi_penjualan extends Activity implements OnItemClickListener,OnClickListener,OnCheckedChangeListener {
	private String umk_email=new String();
	private Button addDetailTransaksiPenjualan;
	DatabaseHelper db;
	TableLayout table_barang;
	private LinearLayout detail_barang;
	List<Model_M2M_Transaksi_Produk> tempBarang;
	List<Model_Product> listProduct;
	private int[] idJumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_barang_transaksi_penjualan);
		this.db = new DatabaseHelper(this);
		umk_email= getIntent().getExtras().getString("email");
		
		detail_barang = (LinearLayout)findViewById(R.id.detail_barang);
		table_barang = (TableLayout)findViewById(R.id.table_barang);
		addDetailTransaksiPenjualan = (Button)findViewById(R.id.addDetailTransaksiPenjualan);
		addDetailTransaksiPenjualan.setOnClickListener(this);
		getView();
	}
	
	public void getView(){
		listProduct = db.getAllproducts(db.getUMK(umk_email).getId());
		int Array_Count=listProduct.size();
		idJumber = new int[Array_Count];
		TableRow rowhead =new TableRow(this);
		rowhead.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		TextView tv1 = new TextView(this);
		TextView tv2 = new TextView(this);
		tv1.setText("Nama Barang");
		tv1.setTextAppearance(getApplicationContext(), 6);
		tv2.setText("Jumlah Barang");
		tv2.setTextAppearance(getApplicationContext(), 6);
		rowhead.addView(tv1);
		rowhead.addView(tv2);
		table_barang.addView(rowhead);
		for (int i = 0; i < Array_Count; i++) 
		{
		    TableRow row =new TableRow(this);
		    row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		    CheckBox checkBox = new CheckBox(this);
		    checkBox.setOnCheckedChangeListener(this);
		    checkBox.setId(i);
		    checkBox.setText(listProduct.get(i).getNama_produk());
		    row.addView(checkBox);
		    EditText et = new EditText(this);
		    et.setId(View.generateViewId());
		    idJumber[i] = et.getId();
		    row.addView(et);
		    table_barang.addView(row);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.addDetailTransaksiPenjualan){
			Intent intent = new Intent(getApplicationContext(), Transaksi_penjualan.class);
			Bundle bundleEmail = new Bundle();
			/*bundleEmail.putString("email", umk_email);
			intent.putExtras(bundleEmail);*/
			
			tempBarang = new ArrayList<Model_M2M_Transaksi_Produk>();
			Model_M2M_Transaksi_Produk m2m = new Model_M2M_Transaksi_Produk();
			List<Model_M2M_Transaksi_Produk> tempBarang2 = new ArrayList<Model_M2M_Transaksi_Produk>();
			for(int i=0;i<listProduct.size();i++){
				CheckBox c = (CheckBox)findViewById(i);
				EditText e = (EditText)findViewById(idJumber[i]);
				int trmp = Integer.parseInt(e.getText().toString());
				if(c.isChecked()){
					m2m.setProduk_ids(db.getProductByName(c.getText().toString()).getId());
					m2m.setJumlah_barang(trmp);
				}
				tempBarang.add(m2m);
			}
			for(int i=0;i<tempBarang.size();i++){
				tempBarang2.add(tempBarang.get(i));
			}
			bundleEmail.putParcelableArrayList("m2m_mtp", (ArrayList<? extends Parcelable>) tempBarang2);
	    	startActivity(intent);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}

}
