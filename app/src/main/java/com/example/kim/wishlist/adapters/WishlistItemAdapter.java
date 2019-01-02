package com.example.kim.wishlist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.models.WishlistItem;
import com.example.kim.wishlist.viewholders.WishlistItemViewHolder;

import java.util.List;

public class WishlistItemAdapter extends RecyclerView.Adapter<WishlistItemViewHolder> {
    private Context context;
    public List<WishlistItem> wishlistItems;

    public WishlistItemAdapter(Context context, List<WishlistItem> wishlistItems) {
        this.context = context;
        this.wishlistItems = wishlistItems;
    }

    @NonNull
    @Override
    public WishlistItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_wishlistitem, viewGroup, false);
        return new WishlistItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistItemViewHolder holder, int i) {
        final WishlistItem wishlistItem = wishlistItems.get(i);
        holder.wishlistItem.setText(wishlistItem.getmWishListItem());
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }
}