package com.example.loginjooto.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.loginjooto.data.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "login.db";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TOKEN = "token";
    private static final String TABLE = "login";
    private static final String ID = "id";
    private static final int DB_VERSION = 1;
    private static final String LOGTAG = "DbHelper ";
    private SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE " + TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EMAIL + " TEXT," + COLUMN_TOKEN + " TEXT" + ")";
        db.execSQL(createDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void add(LoginResponse user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getUser().getEmail());
        values.put(COLUMN_TOKEN, user.getUser().getAuthenticationToken());
        db.insert(TABLE, null, values);
        db.close();
    }

    public List<LoginResponse> getAllUser(LoginResponse user) {
        String[] columns = {ID, COLUMN_EMAIL, COLUMN_TOKEN};
        String sortOrder = COLUMN_EMAIL + " ASC";
        List<LoginResponse> userList = new ArrayList<LoginResponse>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                user.getUser().setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.getUser().setAuthenticationToken(cursor.getString(cursor.getColumnIndex(COLUMN_TOKEN)));
                userList.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return userList;
    }
}
