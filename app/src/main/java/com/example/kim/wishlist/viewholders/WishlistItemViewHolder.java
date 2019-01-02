package com.example.kim.wishlist.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kim.wishlist.R;

public class WishlistItemViewHolder extends RecyclerView.ViewHolder {
    public TextView wishlistItem;
    public View view;

    public WishlistItemViewHolder(@NonNull View itemView) {
        super(itemView);
        wishlistItem = itemView.findViewById(R.id.wishlistItem);
        view = itemView;
    }
}
