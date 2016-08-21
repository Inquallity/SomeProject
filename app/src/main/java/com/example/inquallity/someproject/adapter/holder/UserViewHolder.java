package com.example.inquallity.someproject.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.model.User;

/**
 * @author Maksim Radko
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;

    private TextView mSurname;

    private TextView mAge;

    private TextView mEmail;

    public UserViewHolder(View view) {
        super(view);
        mName = (TextView) view.findViewById(R.id.tvName);
        mSurname = (TextView) view.findViewById(R.id.tvSurname);
        mAge = (TextView) view.findViewById(R.id.tvAge);
        mEmail = (TextView) view.findViewById(R.id.tvEmail);
    }

    public void bindView(@NonNull User user) {
        mName.setText(user.getName());
        mSurname.setText(user.getSurname());
        mAge.setText(String.valueOf(user.getAge()));
        mEmail.setText(user.getEmail());
    }
}
