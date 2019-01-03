package com.example.kim.wishlist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.models.Wishlist;
import com.example.kim.wishlist.viewholders.WishlistViewHolder;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistViewHolder> {
    private Context context;
    public List<Wishlist> wishlists;

    public WishlistAdapter(Context context, List<Wishlist> wishlists) {
        this.context = context;
        this.wishlists = wishlists;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_wishlist, viewGroup, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int i) {
        final Wishlist wishlist = wishlists.get(i);
        holder.listTitle.setText(wishlist.getmWishListTitle());
        holder.listCount.setText(context.getResources().getString(R.string.item_count, wishlist.getWishlistItems().size()));
    }

    @Override
    public int getItemCount() {
        return wishlists.size();
    }
}
