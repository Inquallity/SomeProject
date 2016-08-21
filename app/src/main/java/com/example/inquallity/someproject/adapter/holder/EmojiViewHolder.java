package com.example.inquallity.someproject.adapter.holder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * @author Maksim Radko
 */
public class EmojiViewHolder extends RecyclerView.ViewHolder {

    public EmojiViewHolder(View itemView) {
        super(itemView);
    }

    public void bindView(@NonNull Bitmap emoji) {
        ((ImageView) itemView).setImageBitmap(emoji);
    }
}
