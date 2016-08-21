package com.example.inquallity.someproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.adapter.holder.UserViewHolder;
import com.example.inquallity.someproject.model.User;

import java.util.Collections;
import java.util.List;

/**
 * @author Maksim Radko
 */
public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> mUserList = Collections.emptyList();

    public void changeDataSet(@NonNull List<User> entries) {
        mUserList = Collections.unmodifiableList(entries);
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.li_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bindView(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(this.getClass().getName(), "getItemCount called");
        return mUserList.size();
    }
}
