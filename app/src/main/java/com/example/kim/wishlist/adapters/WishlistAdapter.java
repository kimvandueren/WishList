package com.example.kim.wishlist.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.activities.AddListActivity;
import com.example.kim.wishlist.activities.MainActivity;
import com.example.kim.wishlist.fragments.ListsFragment;
import com.example.kim.wishlist.models.Wishlist;
import com.example.kim.wishlist.viewholders.WishlistViewHolder;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistViewHolder> {
    private Context context;
    private Fragment fragment;
    public List<Wishlist> wishlists;

    public WishlistAdapter(Context context, Fragment fragment, List<Wishlist> wishlists) {
        this.context = context;
        this.fragment = fragment;
        this.wishlists = wishlists;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_wishlist, viewGroup, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, final int i) {
        final Wishlist wishlist = wishlists.get(i);
        holder.listTitle.setText(wishlist.getmWishListTitle());
        holder.listCount.setText(context.getResources().getString(R.string.item_count, wishlist.getWishlistItems().size()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddListActivity.class);
                ListsFragment.mModifyPosition = i;
                intent.putExtra(ListsFragment.NEW_ITEM, wishlists.get(i));
                fragment.startActivityForResult(intent, ListsFragment.EDIT_REQUESTCODE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishlists.size();
    }
}
