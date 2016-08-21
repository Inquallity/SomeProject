package com.example.inquallity.someproject.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.model.User;
import com.example.inquallity.someproject.sqlite.SqliteHelper;

/**
 * @author Maksim Radko
 */
public class AddRowActivity extends AppCompatActivity {

    private EditText mName;

    private EditText mSurname;

    private EditText mAge;

    private EditText mEmail;

    private View mConfirm;

    @NonNull
    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, AddRowActivity.class);
    }

    public void onCreateClick(View view) {
        final User user = new User();
        user.setName(getText(mName));
        user.setSurname(getText(mSurname));
        user.setAge(getInteger(mAge));
        user.setEmail(getText(mEmail));
        final ContentValues cv = user.toValues();
        final SqliteHelper dbh = new SqliteHelper(this);
        final SQLiteDatabase db = dbh.getWritableDatabase();
        final long result = db.insert(User.UserTable.TABLE_NAME, null, cv);
        if (result > 0) {
            Snackbar.make(mName, getString(R.string.insert_result, result), Snackbar.LENGTH_LONG)
                    .setCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            setResult(RESULT_OK);
                            finish();
                        }
                    }).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_add_row);
        initViews();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private int getInteger(@NonNull EditText et) {
        final String result = et.getText().toString();
        int intResult = 0;
        try {
            intResult = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            Log.e(this.getClass().getName(), e.getMessage(), e);
        }
        return intResult;
    }

    @NonNull
    private String getText(@NonNull EditText et) {
        return et.getText().toString();
    }

    private void initViews() {
        mName = (EditText) findViewById(R.id.etName);
        mSurname = (EditText) findViewById(R.id.etSurname);
        mAge = (EditText) findViewById(R.id.etAge);
        mEmail = (EditText) findViewById(R.id.etEmail);
        mConfirm = findViewById(R.id.btnCreate);
    }
}
