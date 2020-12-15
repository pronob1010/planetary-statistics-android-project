package com.finalproject.testapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelp extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME = "User";
    public static String COL_ID = "Id";
    public static String COL_NAME = "Name";
    public static String COL_DISTANCE = "Distance";
    public static String COL_SPIN = "Spin";
    public static String COL_SATELLITE = "satellite";
    private static final int VERSION = 1;


    private final String CREATE_TABLE = "create table "+TABLE_NAME+" ( "+COL_ID+" Integer primary key autoincrement, "+COL_NAME+" TEXT, "+COL_DISTANCE+" TEXT, "+COL_SPIN+" TEXT, "+COL_SATELLITE+" TEXT )";

    public DatabaseHelp(@Nullable Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public long insertData(String name,String distance, String spin,String satellite){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_DISTANCE,distance);
        contentValues.put(COL_SPIN,spin);
        contentValues.put(COL_SATELLITE,satellite);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

        return id;
    }

    public Cursor showData(){

        String ALL_DATA_QUERY = "select * From "+TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(ALL_DATA_QUERY,null);

        return cursor;
    }

    public Cursor searchData(int ID){
        String SEARCH_QUERY = "select * From "+TABLE_NAME+" where "+COL_ID+" = "+ID;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SEARCH_QUERY,null);

        return  cursor;
    }

    public boolean updateData(int id,String name,String distance,String spin,String satellite){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_DISTANCE,distance);
        contentValues.put(COL_SPIN,spin);
        contentValues.put(COL_SATELLITE,satellite);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,COL_ID+" = ? ",new String[]{String.valueOf(id)});

        return true;

    }

    public int deleteData(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int check = sqLiteDatabase.delete(TABLE_NAME,COL_ID+" = ? ",new String[]{String.valueOf(id)});

        return check;
    }
}
