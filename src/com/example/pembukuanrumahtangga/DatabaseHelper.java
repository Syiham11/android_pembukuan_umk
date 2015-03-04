package com.example.pembukuanrumahtangga;

import com.example.pembukuanrumahtangga.UserEmailPassword;

 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
 

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
    private static final String DATABASE_NAME = "pembukuanRumahTangga";
    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_PEMASUKAN = "pemasukan";
    private static final String TABLE_PENGELUARAN = "pengeluaran";
 // Common column names
    private static final String KEY_ID = "id";
    //private static final String KEY_CREATED_AT = "created_at";
 // USER Table - column names
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TOTAL_PEMASUKAN = "total_pemasukan";
    private static final String TOTAL_PENGELUARAN = "total_pengeluaran";
    private static final String BALANCE = "balance";
 // PEMASUKAN Table - column names
    private static final String USER_ID_PEMASUKAN = "user_id";
    private static final String NAMA_PEMASUKAN = "nama_pemasukan";
    private static final String JUMLAH_PEMASUKAN = "jumlah_pemasukan";
    private static final String DATE_PEMASUKAN = "date";
 // PENGELUARAN Table - column names
    private static final String USER_ID_PENGELUARAN = "user_id";
    private static final String NAMA_PENGELUARAN = "nama_pengeluaran";
    private static final String JUMLAH_PENGELUARAN = "jumlah_pengeluaran";
    private static final String DATE_PENGELUARAN = "date";
 // Table Create Statements
 // USER table create statements
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EMAIL
            + " TEXT," + PASSWORD + " TEXT," + TOTAL_PEMASUKAN + " NUMBER,"+ TOTAL_PENGELUARAN + " NUMBER,"
            + BALANCE + " NUMBER)";
    // PEMASUKAN table create statements
    private static final String CREATE_TABLE_PEMASUKAN = "CREATE TABLE "
            + TABLE_PEMASUKAN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_ID_PEMASUKAN
            + " INTEGER," + NAMA_PEMASUKAN + " TEXT," + JUMLAH_PEMASUKAN + " NUMBER,"+ DATE_PEMASUKAN + " DATETIME"
            + ")";
 // PENGELUARAN table create statements
    private static final String CREATE_TABLE_PENGELUARAN = "CREATE TABLE "
            + TABLE_PENGELUARAN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_ID_PENGELUARAN
            + " INTEGER," + NAMA_PENGELUARAN + " TEXT," + JUMLAH_PENGELUARAN + " NUMBER,"+ DATE_PENGELUARAN + " DATETIME"
            + ")";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_USER);
		db.execSQL(CREATE_TABLE_PEMASUKAN);
		db.execSQL(CREATE_TABLE_PENGELUARAN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEMASUKAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENGELUARAN);
        
     // create new tables
        onCreate(db);
	}
	// ------------------------ "user" table methods ----------------//
	
	/**
     * Creating a user
     */
    public long createUser(UserEmailPassword UEP) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(EMAIL, UEP.getEmail());
        values.put(PASSWORD, UEP.getPassword());
        values.put(TOTAL_PEMASUKAN, UEP.getTotalPemasukan());
        values.put(TOTAL_PENGELUARAN, UEP.getTotalPengeluaran());
        values.put(BALANCE, UEP.getBalance());
 
        // insert row
        long USER_ID_PEMASUKAN = db.insert(TABLE_USER, null, values);
 
        
        closeDB();
        return USER_ID_PEMASUKAN;
    }
    
    /**
     * get single user
     */
    public UserEmailPassword getUserEmailPassword(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + EMAIL + " = '" + email+"'";
 
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        UserEmailPassword UEP = new UserEmailPassword();
        if (c.moveToFirst()){
            
       
        
        UEP.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        UEP.setEmail((c.getString(c.getColumnIndex(EMAIL))));
        UEP.setPassword((c.getString(c.getColumnIndex(PASSWORD))));
        UEP.setTotalPemasukan(c.getInt(c.getColumnIndex(TOTAL_PEMASUKAN)));
        UEP.setTotalPengeluaran(c.getInt(c.getColumnIndex(TOTAL_PENGELUARAN)));
        UEP.setBalance(c.getInt(c.getColumnIndex(BALANCE)));
        

        

        }
        closeDB();
        return UEP;
    }
    
    public void resetDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE " + TABLE_PEMASUKAN);
		db.execSQL(CREATE_TABLE_PEMASUKAN);
		Log.w("DB", "reset db");
    }
    
    /**
     * Creating a pemasukan
     */
    public long createPemasukan(PemasukanModel pm) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(USER_ID_PEMASUKAN, pm.getUser_id());
        values.put(NAMA_PEMASUKAN, pm.getNamaPemasukan());
        values.put(JUMLAH_PEMASUKAN, pm.getJumlahPemasukan());
        values.put(DATE_PEMASUKAN, pm.getDate());
        
 
        // insert row
        long pemasukan_id = db.insert(TABLE_PEMASUKAN, null, values);
 
        
        closeDB();
        return pemasukan_id;
    }
    
    /**
     * get single pemasukan
     */
    public PemasukanModel getPemasukanModel(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selectQuery = "SELECT  * FROM " + TABLE_PEMASUKAN + " WHERE "
                + USER_ID_PEMASUKAN + " = " + user_id;
 
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        PemasukanModel pm = new PemasukanModel();
        if (c.moveToFirst()){
            
 
        
        pm.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pm.setUser_id((c.getInt(c.getColumnIndex(USER_ID_PEMASUKAN))));
        pm.setDate((c.getString(c.getColumnIndex(DATE_PEMASUKAN))));
        pm.setNamaPemasukan(c.getString(c.getColumnIndex(NAMA_PEMASUKAN)));
        pm.setJumlahPemasukan(c.getInt(c.getColumnIndex(JUMLAH_PEMASUKAN)));
        
        

        

        }
        closeDB();
        return pm;
    }
    
    /**
     * get sum single ID pemasukan
     */
    
    public int getSumPemasukan(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int sumPemasukan = 0;
        String selectQuery = "SELECT  SUM("+JUMLAH_PEMASUKAN+") AS JLH_P FROM " + TABLE_PEMASUKAN + " WHERE "
                + USER_ID_PEMASUKAN + " = " + user_id;
 
        //Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            
        	sumPemasukan += c.getInt(c.getColumnIndex("JLH_P"));
        
        
        }
        closeDB();
        return sumPemasukan;
    }
    
    /*
     * getting all pemasukan
     * */
    public List<PemasukanModel> getAllPemasukan(int user_id) {
        List<PemasukanModel> pemasukan = new ArrayList<PemasukanModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_PEMASUKAN+" WHERE "+USER_ID_PEMASUKAN+" = "+user_id;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	PemasukanModel pm = new PemasukanModel();
                pm.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pm.setUser_id((c.getInt(c.getColumnIndex(USER_ID_PEMASUKAN))));
                pm.setNamaPemasukan(c.getString(c.getColumnIndex(NAMA_PEMASUKAN)));
                pm.setJumlahPemasukan(c.getInt(c.getColumnIndex(JUMLAH_PEMASUKAN)));
                pm.setDate(c.getString(c.getColumnIndex(DATE_PEMASUKAN)));
     
                // adding to pemasukan list
                pemasukan.add(pm);
            } while (c.moveToNext());
        }
     
        return pemasukan;
    }
    
    /**
     * Creating a pengeluaran
     */
    public long createPengeluaran(PengeluaranModel pm) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(USER_ID_PENGELUARAN, pm.getUser_id());
        values.put(NAMA_PENGELUARAN, pm.getNamaPengeluaran());
        values.put(JUMLAH_PENGELUARAN, pm.getJumlahPengeluaran());
        values.put(DATE_PENGELUARAN, pm.getDate());
        
 
        // insert row
        long pemasukan_id = db.insert(TABLE_PENGELUARAN, null, values);
 
        
        closeDB();
        return pemasukan_id;
    }
    
    /**
     * get single pengeluaran
     */
    public PengeluaranModel getPengeluaranModel(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selectQuery = "SELECT  * FROM " + TABLE_PENGELUARAN + " WHERE "
                + USER_ID_PENGELUARAN + " = " + user_id;
 
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        PengeluaranModel pm = new PengeluaranModel();
        if (c.moveToFirst()){
            
 
        
        pm.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pm.setUser_id((c.getInt(c.getColumnIndex(USER_ID_PENGELUARAN))));
        pm.setDate((c.getString(c.getColumnIndex(DATE_PENGELUARAN))));
        pm.setNamaPengeluaran(c.getString(c.getColumnIndex(NAMA_PENGELUARAN)));
        pm.setJumlahPengeluaran(c.getInt(c.getColumnIndex(JUMLAH_PENGELUARAN)));
        
        

        

        }
        closeDB();
        return pm;
    }
    
    /**
     * get sum single ID pengeluaran
     */
    
    public int getSumPengeluaran(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int sumPengeluaran = 0;
        String selectQuery = "SELECT  SUM("+JUMLAH_PENGELUARAN+") AS JLH_PENGELUARAN FROM " + TABLE_PENGELUARAN + " WHERE "
                + USER_ID_PENGELUARAN + " = " + user_id;
 
        //Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            
        	sumPengeluaran += c.getInt(c.getColumnIndex("JLH_PENGELUARAN"));
        
        
        }
        closeDB();
        return sumPengeluaran;
    }
    
    /*
     * getting all pengeluaran
     * */
    public List<PengeluaranModel> getAllPengeluaran(int user_id) {
        List<PengeluaranModel> pengeluaran = new ArrayList<PengeluaranModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_PENGELUARAN + " WHERE "+ USER_ID_PENGELUARAN + " = "+user_id;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	PengeluaranModel pm = new PengeluaranModel();
                pm.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pm.setUser_id((c.getInt(c.getColumnIndex(USER_ID_PENGELUARAN))));
                pm.setNamaPengeluaran(c.getString(c.getColumnIndex(NAMA_PENGELUARAN)));
                pm.setJumlahPengeluaran(c.getInt(c.getColumnIndex(JUMLAH_PENGELUARAN)));
                pm.setDate(c.getString(c.getColumnIndex(DATE_PENGELUARAN)));
     
                // adding to pengeluaran list
                pengeluaran.add(pm);
            } while (c.moveToNext());
        }
     
        return pengeluaran;
    }
 
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
