package com.example.inquallity.someproject.model;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.example.inquallity.someproject.BuildConfig;

/**
 * @author Maksim Radko
 */
public class User {

    private String mName;

    private String mSurname;

    private int mAge;

    private String mEmail;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public ContentValues toValues() {
        final ContentValues cv = new ContentValues();
        cv.put(UserTable.NAME, mName);
        cv.put(UserTable.SURNAME, mSurname);
        cv.put(UserTable.AGE, mAge);
        cv.put(UserTable.EMAIL, mEmail);
        return cv;
    }

    public interface UserTable extends BaseColumns {

        String CREATE = "CREATE TABLE IF NOT EXISTS tb_user (name TEXT, surname TEXT, age INTEGER, email TEXT);";

        String TABLE_NAME = "tb_user";

        String NAME = "name";

        String SURNAME = "surname";

        String AGE = "age";

        String EMAIL = "email";

        String URI = "content://" + BuildConfig.APPLICATION_ID + "/" + TABLE_NAME;
    }

}
