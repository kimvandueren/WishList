package com.example.kim.wishlist.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kim.wishlist.R;

public class WishlistViewHolder extends RecyclerView.ViewHolder {
    public TextView listTitle;
    public TextView listCount;
    public View view;

    public WishlistViewHolder(@NonNull View itemView) {
        super(itemView);
        listTitle = itemView.findViewById(R.id.listTitle);
        listCount = itemView.findViewById(R.id.listCount);
        view = itemView;
    }
}
