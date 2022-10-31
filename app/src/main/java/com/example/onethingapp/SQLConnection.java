package com.example.onethingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLConnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserData";

    public SQLConnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE userDetails(" +
                    "email text NOT NULL, " +
                    "password text NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS userDetails");

        onCreate(DB);
    }

    public Boolean insertUserDate(String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = DB.insert("userDetails", null, contentValues);
        if(result == -1){
            DB.close();
            return false;

        }else{
            DB.close();
            return true;
        }
    }

    public Boolean updateUserDate(String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("SELECT * FROM userDetails WHERE email=?", new String[] {email});
        if(cursor.getCount()>0) {
            long result = DB.update("userDetails", contentValues, "email=?", new String[]{email});
            if (result == -1) {
                cursor.close();
                return false;
            } else {
                cursor.close();
                return true;
            }
        }else{
            cursor.close();
            return false;
        }
    }

    public Boolean deleteUserDate(String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("SELECT * FROM userDetails WHERE email=?", new String[] {email});
        if(cursor.getCount()>0) {
            long result = DB.delete("userDetails", "email=?", new String[]{email});
            if (result == -1) {
                cursor.close();
                return false;
            } else {
                cursor.close();
                return true;
            }
        }else{
            cursor.close();
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM userDetails", null);
    }

    public Boolean validateLogin(String email, String password){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM userDetails WHERE email=? AND password=?", new String[] {email, password});
        if(cursor.getCount()>0) {
            cursor.close();
            return true;

        }else{
            cursor.close();
            return false;
        }

    }
    public ArrayList<String[]> getDbTableDetails() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor c = DB.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'", null);
        ArrayList<String[]> result = new ArrayList<>();
        int i;
        result.add(c.getColumnNames());
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String[] temp = new String[c.getColumnCount()];
            for (i = 0; i < temp.length; i++) {
                temp[i] = c.getString(i);
            }
            result.add(temp);
        }
        c.close();
        return result;

    }
}
