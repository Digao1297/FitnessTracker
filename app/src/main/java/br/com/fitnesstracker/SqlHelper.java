package br.com.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SqlHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fitness_tracker.db";
    private static final int DB_VERSION = 1;

    private static SqlHelper INSTANCE;

    static SqlHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SqlHelper(context);
        }
        return INSTANCE;
    }

    private SqlHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE calc (id INTEGER primary key, type_calc TEXT, result DECIMAL,created_date DATETIME)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB_Upgrade", "on Upgrade disparado");
    }

    long addItem(String type, double value) {
        SQLiteDatabase db = getWritableDatabase();

        long calcId = 0;
        try {
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("type_calc", type);
            values.put("result", value);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt","BR"));
            values.put("created_date", sdf.format(new Date()));

            calcId = db.insertOrThrow("calc",null, values);

            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.e("SQLite", e.getMessage(), e);
        } finally {
            if(db.isOpen())
                db.endTransaction();
        }

        return calcId;
    }
}
