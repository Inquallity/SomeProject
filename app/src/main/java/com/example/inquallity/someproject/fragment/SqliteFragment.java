package com.example.inquallity.someproject.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.activity.AddRowActivity;
import com.example.inquallity.someproject.adapter.UsersAdapter;
import com.example.inquallity.someproject.model.User;
import com.example.inquallity.someproject.sqlite.SqliteHelper;
import com.example.inquallity.someproject.utils.SqliteUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Maksim Radko
 */
public class SqliteFragment extends Fragment implements View.OnClickListener {

    private static final int RC_ADD_USER = 1;

    private SqliteHelper mDb;

    private FloatingActionButton mAddRow;

    private RecyclerView mUsersList;

    private UsersAdapter mAdapter = new UsersAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_sqlite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUsersList = (RecyclerView) view.findViewById(R.id.rvUsers);
        mUsersList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUsersList.setItemAnimator(new DefaultItemAnimator());
        mUsersList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                Log.d(SqliteFragment.class.getName(), "onDrawOver: called on: " + Thread.currentThread().getName() + " with " + parent.getChildCount());
            }
        });
        mUsersList.setAdapter(mAdapter);
        mAddRow = (FloatingActionButton) view.findViewById(R.id.fabAdd);
        mAddRow.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDb = new SqliteHelper(getActivity());
        refreshUsersList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_ADD_USER && resultCode == Activity.RESULT_OK) {
            refreshUsersList();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabAdd) {
            startActivity(AddRowActivity.makeIntent(getActivity()));
//            generate(100000);
        }
    }

    private void generate(int rowsCount) {
        final List<ContentValues> insertion = new ArrayList<>();
        for (int i = 0; i < rowsCount; i++) {
            final User user = new User();
            user.setName("Some name " + i);
            user.setSurname("Some surname " + i);
            user.setAge(new Random().nextInt());
            user.setEmail("Some email " + i);
            final ContentValues cv = user.toValues();
            insertion.add(cv);
        }
        final SQLiteDatabase db = mDb.getWritableDatabase();
        db.beginTransaction();
        for (ContentValues cv : insertion) {
            db.insert(User.UserTable.TABLE_NAME, null, cv);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    private void refreshUsersList() {
        //Called in main thread
        final Cursor cursor = mDb.getReadableDatabase().query(User.UserTable.TABLE_NAME, null, null,
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            final List<User> users = new ArrayList<>(cursor.getCount());
            do {
                final User user = SqliteUtils.toUser(cursor);
                users.add(user);
            } while (cursor.moveToNext());
            mAdapter.changeDataSet(users);
            cursor.close();
        }
    }

}
