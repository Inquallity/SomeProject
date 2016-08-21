package com.example.inquallity.someproject.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.adapter.holder.EmojiViewHolder;
import com.example.inquallity.someproject.model.Emoji;

import java.util.Collections;
import java.util.List;

/**
 * @author Maksim Radko
 */
public class EmojisAdapter extends RecyclerView.Adapter<EmojiViewHolder> {

    private List<Bitmap> mEmojis = Collections.emptyList();

    public void changeDataSet(@NonNull List<Bitmap> entries) {
        mEmojis = Collections.unmodifiableList(entries);
        notifyDataSetChanged();
    }

    @Override
    public EmojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.li_emoji, parent, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmojiViewHolder holder, int position) {
        holder.bindView(mEmojis.get(position));
    }

    @Override
    public int getItemCount() {
        return mEmojis.size();
    }
}
