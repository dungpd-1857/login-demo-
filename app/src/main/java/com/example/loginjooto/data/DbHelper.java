package com.example.loginjooto.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.loginjooto.data.model.LoginResponse;
import com.example.loginjooto.data.model.User;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "login.db";
    private static volatile DbHelper instance;
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TOKEN = "token";
    private static final String TABLE = "login";
    private static final String ID = "id";
    private static final int DB_VERSION = 1;
    private static final String LOGTAG = "DbHelper";
    private SQLiteDatabase db;
    Context mContext;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE "
                + TABLE
                + "("
                + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EMAIL
                + " TEXT,"
                + COLUMN_TOKEN
                + " TEXT"
                + ")";
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

//        values.put(ID, user.getUser().getId());
        values.put(COLUMN_EMAIL, user.getUser().getEmail());
        values.put(COLUMN_TOKEN, user.getUser().getAuthenticationToken());
        db.insert(TABLE, null, values);
        db.close();
    }

    public List<LoginResponse> getAllUser() {
        String[] columns = {
                ID, COLUMN_EMAIL, COLUMN_TOKEN
        };
        String sortOrder = COLUMN_EMAIL + " ASC";
        List<LoginResponse> userList = new ArrayList<LoginResponse>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                LoginResponse user = new LoginResponse();
//                user.getUser().setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
                user.getUser().setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.getUser().setAuthenticationToken(cursor.getString(cursor.getColumnIndex(COLUMN_TOKEN)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        for (LoginResponse list : userList) {
            Log.d("values : ", +list.getUser().getId() + list.getUser().getEmail() + list.getUser()
                    .getAuthenticationToken());
        }
        return userList;
    }
}
