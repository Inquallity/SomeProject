package com.example.inquallity.someproject.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.inquallity.someproject.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Radko
 */
public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "app.db";

    private static final int DB_VERSION = 1;

    private static final List<String> CREATE_SCRIPTS = new ArrayList<>();

    static {
        CREATE_SCRIPTS.add(User.UserTable.CREATE);
    }

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String create : CREATE_SCRIPTS) {
            db.execSQL(create);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String delete : CREATE_SCRIPTS) {
            db.execSQL(delete);
        }
        onCreate(db);
    }
}
