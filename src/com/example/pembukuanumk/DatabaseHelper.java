package com.example.pembukuanumk;





import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	// Logcat tag
    private static final String LOG = "DatabaseHelper";
	// Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "pembukuanUmk";
    
    //Table-table yang ada
    private static final String TABLE_UMK = "umk";
    private static final String TABLE_PRODUK = "produk";
    private static final String TABLE_TRANSAKSI_PENJUALAN = "transaksi_penjualan";
    private static final String TABLE_TRANSAKSI_LAIN = "transaksi_lain";
    private static final String TABLE_M2M_TRANSAKSI_PRODUK = "m2m_transaksi_produk";
    private static final String TABLE_JENIS_TRANSAKSI_LAIN = "jenis_transaksi_lain";
    // Common column names
    private static final String KEY_ID = "id";
    //Column table UMK
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String NAMA_UMK = "nama_umk";
    private static final String DESKRIPSI_UMK = "deskripsi_umk";
    private static final String ALAMAT_UMK = "alamat_umk";
    private static final String TELEPON_UMK = "telepon_umk";
    private static final String LOGO_UMK = "logo_umk";
    private static final String SALDO_UMK = "saldo_umk";
    //Column table produk
    private static final String NAMA_PRODUK="nama_produk";
    private static final String DESKRIPSI_PRODUK="deskripsi_produk";
    private static final String HARGA_POKOK_PRODUK="harga_pokok_produk";
    private static final String HARGA_JUAL_PRODUK="harga_jual_produk";
    private static final String FOTO_PRODUK="foto_produk";
    private static final String UMK_IDS="umk_ids";
  //Column table transaksi_penjualan
    private static final String TANGGAL_TRANSAKSI_PENJUALAN="tanggal_transaksi_penjualan";
    private static final String URAIAN_TRANSAKSI="uraian_transaksi";
    private static final String NILAI_RUPIAH="nilai_rupiah";
    private static final String UMK_IDS_TP="umk_ids";
  //Column table transaksi_lain
    private static final String TANGGAL_TRANSAKSI_LAIN="tanggal_transaksi_lain";
    private static final String JENIS_TRANSAKSI="jenis_transaksi";
    private static final String URAIAN_TRANSAKSI_LAIN="uraian_transaksi_lain";
    private static final String NILAI_RUPIAH_TRANSAKSI_LAIN="nilai_rupiah_transaksi_lain";
    private static final String JENIS_TRANSAKSI_ID="jenis_transaksi_id";
    private static final String UMK_IDS_TL="umk_ids";
  //Column table m2m transaksi produk
    private static final String PRODUK_IDS="produk_id";
    private static final String JUMLAH_BARANG="jumlah_barang";
  //Column table m2m jenis transaksi lain
    private static final String NAMA_JENIS_TRANSAKSI="nama_jenis_transaksi";
    private static final String TIPE="tipe";

    
    
 // Table Create Statements
    
    //TABLE UMK CREATE STATEMENT
    private static final String CREATE_TABLE_UMK = "CREATE TABLE "
            + TABLE_UMK + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EMAIL
            + " TEXT," + PASSWORD + " TEXT," + NAMA_UMK + " TEXT,"+ DESKRIPSI_UMK + " TEXT,"
            + ALAMAT_UMK + " TEXT, "+ TELEPON_UMK + " NUMBER, " + LOGO_UMK + " NUMBER, " + SALDO_UMK + " NUMBER)";
  //TABLE PRODUK CREATE STATEMENT
    private static final String CREATE_TABLE_PRODUK = "CREATE TABLE "
            + TABLE_PRODUK + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAMA_PRODUK
            + " TEXT," + DESKRIPSI_PRODUK + " TEXT," + HARGA_POKOK_PRODUK + " NUMBER,"+ HARGA_JUAL_PRODUK + " NUMBER,"
            + FOTO_PRODUK + " NUMBER, "+ UMK_IDS + " NUMBER)";
  //TABLE TRANSAKSI_PENJUALAN CREATE STATEMENT
    private static final String CREATE_TABLE_TRANSAKSI_PENJUALAN = "CREATE TABLE "
            + TABLE_TRANSAKSI_PENJUALAN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TANGGAL_TRANSAKSI_PENJUALAN
            + " DATETIME," + URAIAN_TRANSAKSI + " TEXT,"
            + NILAI_RUPIAH + " NUMBER, "+ UMK_IDS_TP + " NUMBER)";
  //TABLE TRANSAKSI_LAIN CREATE STATEMENT
    private static final String CREATE_TABLE_TRANSAKSI_LAIN = "CREATE TABLE "
            + TABLE_TRANSAKSI_LAIN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TANGGAL_TRANSAKSI_LAIN
            + " DATETIME," + JENIS_TRANSAKSI + " TEXT," + URAIAN_TRANSAKSI_LAIN + " TEXT," + JENIS_TRANSAKSI_ID + " NUMBER,"
            + NILAI_RUPIAH_TRANSAKSI_LAIN + " NUMBER, "+ UMK_IDS_TP + " NUMBER)";
  //TABLE M2M_TRANSAKSI_PRODUK CREATE STATEMENT
    private static final String CREATE_TABLE_M2M_TRANSAKSI_PRODUK = "CREATE TABLE "
            + TABLE_M2M_TRANSAKSI_PRODUK + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUK_IDS
            + " NUMBER," + JUMLAH_BARANG + " NUMBER)";
  //TABLE JENIS_TRANSAKSI_LAIN
    private static final String CREATE_TABLE_JENIS_TRANSAKSI_LAIN = "CREATE TABLE "
            + TABLE_JENIS_TRANSAKSI_LAIN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAMA_JENIS_TRANSAKSI
            + " TEXT," + TIPE + " TEXT)";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

  

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_UMK);
		db.execSQL(CREATE_TABLE_PRODUK);
		db.execSQL(CREATE_TABLE_TRANSAKSI_PENJUALAN);
		db.execSQL(CREATE_TABLE_TRANSAKSI_LAIN);
		db.execSQL(CREATE_TABLE_M2M_TRANSAKSI_PRODUK);
		db.execSQL(CREATE_TABLE_JENIS_TRANSAKSI_LAIN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_UMK);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUK);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI_PENJUALAN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI_LAIN);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_M2M_TRANSAKSI_PRODUK);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_JENIS_TRANSAKSI_LAIN);
		onCreate(db);
	}
	
	// ------------------------ "UMK" table methods ----------------//
	
	/**
     * Creating a UMK
     */

	public long createUMK(Model_UMK UMK) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(EMAIL, UMK.getEmail());
        values.put(PASSWORD, UMK.getPassword());
        values.put(NAMA_UMK, UMK.getNama_umk());
        values.put(ALAMAT_UMK, UMK.getAlamat_umk());
        values.put(DESKRIPSI_UMK, UMK.getDeskripsi_umk());
        values.put(TELEPON_UMK, UMK.getTelepon_umk());
        values.put(LOGO_UMK, UMK.getLogo_umk());
        values.put(SALDO_UMK, UMK.getSaldo_umk());
 
        // insert row
        long ROW_UMK = db.insert(TABLE_UMK, null, values);
 
        
        close();
        return ROW_UMK;
    }
	
	/**
     * get single UMK
     */
    public Model_UMK getUMK(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selectQuery = "SELECT  * FROM " + TABLE_UMK + " WHERE "
                + EMAIL + " = '" + email+"'";
 
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        Model_UMK UMK = new Model_UMK();
        if (c.moveToFirst()){

        UMK.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        UMK.setEmail((c.getString(c.getColumnIndex(EMAIL))));
        UMK.setPassword((c.getString(c.getColumnIndex(PASSWORD))));
        UMK.setNama_umk(c.getString(c.getColumnIndex(NAMA_UMK)));
        UMK.setDeskripsi_umk(c.getString(c.getColumnIndex(DESKRIPSI_UMK)));
        UMK.setAlamat_umk(c.getString(c.getColumnIndex(ALAMAT_UMK)));
        UMK.setTelepon_umk(c.getLong(c.getColumnIndex(TELEPON_UMK)));
        UMK.setLogo_umk(c.getBlob(c.getColumnIndex(LOGO_UMK)));
        UMK.setSaldo_umk(c.getLong(c.getColumnIndex(SALDO_UMK)));

        }
        close();
        return UMK;
    }
    
    /**
     * Update an UMK
     */
    
    public int updateUMK(Model_UMK umk){
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(EMAIL, umk.getEmail());
        values.put(PASSWORD, umk.getPassword());
        values.put(DESKRIPSI_UMK, umk.getDeskripsi_umk());
        values.put(ALAMAT_UMK, umk.getAlamat_umk());
        values.put(TELEPON_UMK, umk.getTelepon_umk());
        values.put(LOGO_UMK, umk.getLogo_umk());
        values.put(SALDO_UMK, umk.getSaldo_umk());
     
        // updating row
        int i =  db.update(TABLE_UMK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(umk.getId()) });
        db.close();
        
        return i;
    }
    
	// ------------------------ "PRODUK" table methods ----------------//
	
	/**
     * Creating a PRODUK
     */

	public long createProduk(Model_Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(NAMA_PRODUK, product.getNama_produk());
        values.put(DESKRIPSI_PRODUK, product.getDeskripsi_produk());
        values.put(HARGA_POKOK_PRODUK, product.getHarga_pokok_produk());
        values.put(HARGA_JUAL_PRODUK, product.getHarga_jual_produk());
        values.put(FOTO_PRODUK, product.getImage());
        values.put(UMK_IDS, product.getUmk_id());
 
        // insert row
        long ROW_PRODUK = db.insert(TABLE_PRODUK, null, values);
 
        
        close();
        return ROW_PRODUK;
    }
	
	/**
     * get single product
     */
    public Model_Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUK + " WHERE "
                + KEY_ID + " = " + id;
 
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        Model_Product product = new Model_Product();
        if (c.moveToFirst()){

        	product.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        	product.setNama_produk((c.getString(c.getColumnIndex(NAMA_PRODUK))));
        	product.setDeskripsi_produk((c.getString(c.getColumnIndex(DESKRIPSI_PRODUK))));
        	product.setHarga_pokok_produk(c.getLong(c.getColumnIndex(HARGA_POKOK_PRODUK)));
        	product.setHarga_jual_produk(c.getLong(c.getColumnIndex(HARGA_JUAL_PRODUK)));
        	product.setImage(c.getBlob(c.getColumnIndex(FOTO_PRODUK)));
        	product.setUmk_id(c.getInt(c.getColumnIndex(UMK_IDS)));

        }
        close();
        return product;
    }
    
    public Model_Product getProductByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUK + " WHERE "
                + NAMA_PRODUK + " = '" + name+"'";
 
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        Model_Product product = new Model_Product();
        if (c.moveToFirst()){

        	product.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        	product.setNama_produk((c.getString(c.getColumnIndex(NAMA_PRODUK))));
        	product.setDeskripsi_produk((c.getString(c.getColumnIndex(DESKRIPSI_PRODUK))));
        	product.setHarga_pokok_produk(c.getLong(c.getColumnIndex(HARGA_POKOK_PRODUK)));
        	product.setHarga_jual_produk(c.getLong(c.getColumnIndex(HARGA_JUAL_PRODUK)));
        	product.setImage(c.getBlob(c.getColumnIndex(FOTO_PRODUK)));
        	product.setUmk_id(c.getInt(c.getColumnIndex(UMK_IDS)));

        }
        close();
        return product;
    }
    
    /**
     * Update an Product
     */
    
    public int updateProduct(Model_Product product){
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(NAMA_PRODUK, product.getNama_produk());
        values.put(DESKRIPSI_PRODUK, product.getDeskripsi_produk());
        values.put(HARGA_POKOK_PRODUK, product.getHarga_pokok_produk());
        values.put(HARGA_JUAL_PRODUK, product.getHarga_jual_produk());
        values.put(FOTO_PRODUK, product.getImage());
     
        // updating row
        int i =  db.update(TABLE_PRODUK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
        db.close();
        
        return i;
    }
    
    /*
     * getting all Product
     * */
    public List<Model_Product> getAllproducts(int umk_id) {
        List<Model_Product> products = new ArrayList<Model_Product>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUK + " WHERE umk_ids = " + umk_id;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Model_Product pd = new Model_Product();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setNama_produk((c.getString(c.getColumnIndex(NAMA_PRODUK))));
            	pd.setDeskripsi_produk((c.getString(c.getColumnIndex(DESKRIPSI_PRODUK))));
            	pd.setHarga_pokok_produk(c.getLong(c.getColumnIndex(HARGA_POKOK_PRODUK)));
            	pd.setHarga_jual_produk(c.getLong(c.getColumnIndex(HARGA_JUAL_PRODUK)));
            	pd.setImage(c.getBlob(c.getColumnIndex(FOTO_PRODUK)));
            	pd.setUmk_id(c.getInt(c.getColumnIndex(UMK_IDS)));
     
                // adding to products list
            	products.add(pd);
            } while (c.moveToNext());
        }
     
        return products;
    }
    
 // ------------------------ "TRANSAKSI_PENJUALAN" table methods ----------------//
	
 	/**
      * Creating a TRANSAKSI_PENJUALAN
      */

 	public long createTransaksi_penjualan(Model_Transaksi_Penjualan tp) {
         SQLiteDatabase db = this.getWritableDatabase();
  
         ContentValues values = new ContentValues();
         
         values.put(TANGGAL_TRANSAKSI_PENJUALAN, tp.getTanggal_transaksi_penjualan());
         values.put(URAIAN_TRANSAKSI, tp.getUraian_transaksi());
         values.put(NILAI_RUPIAH, tp.getNilai_rupiah());
         values.put(UMK_IDS_TP, tp.getUmk_id());
  
         // insert row
         long ROW_TRANSAKSI_PENJUALAN = db.insert(TABLE_TRANSAKSI_PENJUALAN, null, values);
  
         
         close();
         return ROW_TRANSAKSI_PENJUALAN;
     }
 	
     
     /*
      * getting all transaksi_penjualan
      * */
     public List<Model_Transaksi_Penjualan> getAllTransaksi_penjualan(int umk_id) {
         List<Model_Transaksi_Penjualan> transaksi_penjualan = new ArrayList<Model_Transaksi_Penjualan>();
         String selectQuery = "SELECT  * FROM " + TABLE_TRANSAKSI_PENJUALAN + " WHERE umk_ids = " + umk_id;
      
         Log.e(LOG, selectQuery);
      
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor c = db.rawQuery(selectQuery, null);
      
         // looping through all rows and adding to list
         if (c.moveToFirst()) {
             do {
                 Model_Transaksi_Penjualan mtp = new Model_Transaksi_Penjualan();
                 mtp.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                 mtp.setTanggal_transaksi_penjualan(c.getString(c.getColumnIndex(TANGGAL_TRANSAKSI_PENJUALAN)));
             	mtp.setUraian_transaksi(c.getString(c.getColumnIndex(URAIAN_TRANSAKSI)));
             	mtp.setNilai_rupiah(c.getLong(c.getColumnIndex(NILAI_RUPIAH)));
             	mtp.setUmk_id(c.getInt(c.getColumnIndex(UMK_IDS_TP)));
      
                 // adding to products list
             	transaksi_penjualan.add(mtp);
             } while (c.moveToNext());
         }
      
         return transaksi_penjualan;
     }
     
     /*
      * getting all transaksi_penjualan
      * */
     public List<Model_Transaksi_Penjualan> getAllTransaksi_penjualan_ByDate(int umk_id,String tg1,String tg2) {
         List<Model_Transaksi_Penjualan> transaksi_penjualan = new ArrayList<Model_Transaksi_Penjualan>();
         String selectQuery = "SELECT  * FROM " + TABLE_TRANSAKSI_PENJUALAN + " WHERE umk_ids = " + umk_id + " AND (" + TANGGAL_TRANSAKSI_PENJUALAN + " BETWEEN " + tg1 + " AND "+ tg2 + ")";
      
         Log.e(LOG, selectQuery);
      
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor c = db.rawQuery(selectQuery, null);
      
         // looping through all rows and adding to list
         if (c.moveToFirst()) {
             do {
                 Model_Transaksi_Penjualan mtp = new Model_Transaksi_Penjualan();
                 mtp.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                 mtp.setTanggal_transaksi_penjualan(c.getString(c.getColumnIndex(TABLE_TRANSAKSI_PENJUALAN)));
             	mtp.setUraian_transaksi(c.getString(c.getColumnIndex(URAIAN_TRANSAKSI)));
             	mtp.setNilai_rupiah(c.getLong(c.getColumnIndex(NILAI_RUPIAH)));
             	mtp.setUmk_id(c.getInt(c.getColumnIndex(UMK_IDS_TP)));
      
                 // adding to products list
             	transaksi_penjualan.add(mtp);
             } while (c.moveToNext());
         }
      
         return transaksi_penjualan;
     }
     
  // ------------------------ "TRANSAKSI_LAIN" table methods ----------------//
 	
  	/**
       * Creating a TRANSAKSI_LAIN
       */

  	public long createTransaksi_lain(Model_Transaksi_Lain tl) {
          SQLiteDatabase db = this.getWritableDatabase();
   
          ContentValues values = new ContentValues();
          
          values.put(TANGGAL_TRANSAKSI_LAIN, tl.getTanggal_transaksi_lain());
          values.put(JENIS_TRANSAKSI, tl.getJenis_transaksi());
          values.put(URAIAN_TRANSAKSI_LAIN, tl.getUraian_transaksi_lain());
          values.put(NILAI_RUPIAH_TRANSAKSI_LAIN, tl.getNilai_rupiah_transaksi_lain());
          values.put(UMK_IDS_TL, tl.getUmk_id());
          values.put(JENIS_TRANSAKSI_ID, tl.getJenis_transaksi_id());
   
          // insert row
          long ROW_TRANSAKSI_LAIN = db.insert(TABLE_TRANSAKSI_LAIN, null, values);
   
          
          close();
          return ROW_TRANSAKSI_LAIN;
      }
  	
      
      /*
       * getting all transaksi_lain
       * */
      public List<Model_Transaksi_Lain> getAllTransaksi_lain(int umk_id) {
          List<Model_Transaksi_Lain> transaksi_lain = new ArrayList<Model_Transaksi_Lain>();
          String selectQuery = "SELECT  * FROM " + TABLE_TRANSAKSI_LAIN + " WHERE umk_ids = " + umk_id;
       
          Log.e(LOG, selectQuery);
       
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor c = db.rawQuery(selectQuery, null);
       
          // looping through all rows and adding to list
          if (c.moveToFirst()) {
              do {
                  Model_Transaksi_Lain mtl = new Model_Transaksi_Lain();
                  mtl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                  mtl.setTanggal_transaksi_lain(c.getString(c.getColumnIndex(TABLE_TRANSAKSI_PENJUALAN)));
              	mtl.setJenis_transaksi(c.getString(c.getColumnIndex(JENIS_TRANSAKSI)));
              	mtl.setUraian_transaksi_lain(c.getString(c.getColumnIndex(URAIAN_TRANSAKSI_LAIN)));
              	mtl.setNilai_rupiah_transaksi_lain(c.getLong(c.getColumnIndex(NILAI_RUPIAH_TRANSAKSI_LAIN)));
              	mtl.setUmk_id(c.getInt(c.getColumnIndex(UMK_IDS_TL)));
              	mtl.setJenis_transaksi_id(c.getInt(c.getColumnIndex(JENIS_TRANSAKSI_ID)));
       
                  // adding to products list
              	transaksi_lain.add(mtl);
              } while (c.moveToNext());
          }
       
          return transaksi_lain;
      }
      
      /*
       * getting all transaksi_lain
       * */
      public List<Model_Transaksi_Lain> getAllTransaksi_lain_ByDate(int umk_id,String tg1,String tg2) {
          List<Model_Transaksi_Lain> transaksi_lain = new ArrayList<Model_Transaksi_Lain>();
          String selectQuery = "SELECT  * FROM " + TABLE_TRANSAKSI_LAIN + " WHERE umk_ids = " + umk_id + " AND (" + TANGGAL_TRANSAKSI_PENJUALAN + " BETWEEN " + tg1 + " AND "+ tg2 + ")";
       
          Log.e(LOG, selectQuery);
       
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor c = db.rawQuery(selectQuery, null);
       
          // looping through all rows and adding to list
          if (c.moveToFirst()) {
              do {
            	  Model_Transaksi_Lain mtl = new Model_Transaksi_Lain();
                  mtl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                  mtl.setTanggal_transaksi_lain(c.getString(c.getColumnIndex(TABLE_TRANSAKSI_PENJUALAN)));
              	mtl.setJenis_transaksi(c.getString(c.getColumnIndex(JENIS_TRANSAKSI)));
              	mtl.setUraian_transaksi_lain(c.getString(c.getColumnIndex(URAIAN_TRANSAKSI_LAIN)));
              	mtl.setNilai_rupiah_transaksi_lain(c.getLong(c.getColumnIndex(NILAI_RUPIAH_TRANSAKSI_LAIN)));
              	mtl.setUmk_id(c.getInt(c.getColumnIndex(UMK_IDS_TL)));
              	mtl.setJenis_transaksi_id(c.getInt(c.getColumnIndex(JENIS_TRANSAKSI_ID)));
       
                  // adding to products list
              	transaksi_lain.add(mtl);
              } while (c.moveToNext());
          }
       
          return transaksi_lain;
      }
      
   // ------------------------ "M2M_TRANSAKSI_PENJUALAN" table methods ----------------//
      
      /**
       * Creating a M2M_TRANSAKSI_PRODUK
       */

  	public long createM2M_transaksi_produk(Model_M2M_Transaksi_Produk mtp) {
          SQLiteDatabase db = this.getWritableDatabase();
   
          ContentValues values = new ContentValues();
          
          values.put(PRODUK_IDS, mtp.getProduk_ids());
          values.put(JUMLAH_BARANG, mtp.getJumlah_barang());
   
          // insert row
          long ROW_M2M_TRANSAKSI_PRODUK = db.insert(TABLE_M2M_TRANSAKSI_PRODUK, null, values);
   
          
          close();
          return ROW_M2M_TRANSAKSI_PRODUK;
      }
  	
  	/*
     * getting all transaksi_lain
     * */
    public List<Model_M2M_Transaksi_Produk> getAllM2M_transaksi_produk() {
        List<Model_M2M_Transaksi_Produk> mtp = new ArrayList<Model_M2M_Transaksi_Produk>();
        String selectQuery = "SELECT  * FROM " + TABLE_M2M_TRANSAKSI_PRODUK;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Model_M2M_Transaksi_Produk m2m = new Model_M2M_Transaksi_Produk();
                m2m.setKey_id(c.getInt((c.getColumnIndex(KEY_ID))));
                m2m.setProduk_ids(c.getInt(c.getColumnIndex(PRODUK_IDS)));
                m2m.setJumlah_barang(c.getInt(c.getColumnIndex(JUMLAH_BARANG)));
     
                // adding to products list
                mtp.add(m2m);
            } while (c.moveToNext());
        }
     
        return mtp;
    }
    
 
      
   // ------------------------ "JENIS_TRANSAKSI_LAIN" table methods ----------------//
      
      /**
       * Creating a JENIS_TRANSAKSI_LAIN
       */

  	public long createJenis_transaksi_lain(Model_Jenis_Transaksi_Lain mtp) {
          SQLiteDatabase db = this.getWritableDatabase();
   
          ContentValues values = new ContentValues();
          
          values.put(NAMA_JENIS_TRANSAKSI, mtp.getNama_jenis_transaksi());
          values.put(TIPE, mtp.getTipe());
   
          // insert row
          long ROW_JENIS_TRANSAKSI_LAIN = db.insert(TABLE_JENIS_TRANSAKSI_LAIN, null, values);
   
          
          close();
          return ROW_JENIS_TRANSAKSI_LAIN;
      }
  	
  	/*
     * getting all jenis_transaksi_lain
     * */
    public List<Model_Jenis_Transaksi_Lain> getAllJenis_transaksi_lain() {
        List<Model_Jenis_Transaksi_Lain> mtp = new ArrayList<Model_Jenis_Transaksi_Lain>();
        String selectQuery = "SELECT  * FROM " + TABLE_JENIS_TRANSAKSI_LAIN;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Model_Jenis_Transaksi_Lain m2m = new Model_Jenis_Transaksi_Lain();
                m2m.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                m2m.setNama_jenis_transaksi(c.getString(c.getColumnIndex(NAMA_JENIS_TRANSAKSI)));
                m2m.setTipe(c.getString(c.getColumnIndex(TIPE)));
     
                // adding to products list
                mtp.add(m2m);
            } while (c.moveToNext());
        }
     
        return mtp;
    }
    
    /*
     * getting all jenis_transaksi_lain
     * */
    public Model_Jenis_Transaksi_Lain getAllJenis_transaksi_lain_By_Name(String name) {
        Model_Jenis_Transaksi_Lain mtp = new Model_Jenis_Transaksi_Lain();
        String selectQuery = "SELECT  * FROM " + TABLE_JENIS_TRANSAKSI_LAIN + "WHERE " + NAMA_JENIS_TRANSAKSI + " = " + name;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	
            	mtp.setId(c.getInt((c.getColumnIndex(KEY_ID))));
            	mtp.setNama_jenis_transaksi(c.getString(c.getColumnIndex(NAMA_JENIS_TRANSAKSI)));
            	mtp.setTipe(c.getString(c.getColumnIndex(TIPE)));
     
                // adding to products list
            } while (c.moveToNext());
        }
     
        return mtp;
    }
      
}
