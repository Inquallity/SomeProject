package com.example.inquallity.someproject.utils;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.inquallity.someproject.model.User;

/**
 * @author Maksim Radko
 */
public class SqliteUtils {

    @NonNull
    public static User toUser(@NonNull Cursor cursor) {
        final int nameIdx = cursor.getColumnIndex(User.UserTable.NAME);
        final int surnameIdx = cursor.getColumnIndex(User.UserTable.SURNAME);
        final int ageIdx = cursor.getColumnIndex(User.UserTable.AGE);
        final int emailIdx = cursor.getColumnIndex(User.UserTable.EMAIL);
        final User user = new User();
        user.setName(cursor.getString(nameIdx));
        user.setSurname(cursor.getString(surnameIdx));
        user.setAge(cursor.getInt(ageIdx));
        user.setEmail(cursor.getString(emailIdx));
        return user;
    }
}
