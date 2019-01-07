package com.example.kim.wishlist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.viewholders.WishlistItemViewHolder;

import java.util.List;

public class WishlistItemAdapter extends RecyclerView.Adapter<WishlistItemViewHolder> {
    private Context context;
    public List<String> wishlistItems;

    public WishlistItemAdapter(Context context, List<String> wishlistItems) {
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
    public void onBindViewHolder(@NonNull WishlistItemViewHolder holder, final int position) {
        final String wishlistItem = wishlistItems.get(position);
        holder.wishlistItem.setText(wishlistItem);

        // Button to remove items from the list
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, wishlistItems.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }
}
