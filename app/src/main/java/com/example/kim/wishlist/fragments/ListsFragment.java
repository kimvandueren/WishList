package com.example.kim.wishlist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.activities.AddListActivity;
import com.example.kim.wishlist.activities.MainActivity;
import com.example.kim.wishlist.adapters.WishlistAdapter;
import com.example.kim.wishlist.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class ListsFragment extends Fragment {
    private static final String TAG = "Lists";

    List<Wishlist> wishlists = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private WishlistAdapter wishlistAdapter;
    private LinearLayoutManager linearLayoutManager;

    // Constants used when calling 'AddListActivity'
    public static final String NEW_ITEM = "Item";
    public static final int REQUESTCODE = 1234;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        setHasOptionsMenu(true);

        mRecyclerView = view.findViewById(R.id.listsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    // Adds options to the toolbar menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_lists, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //Makes options in toolbar menu clickable
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(getActivity(), AddListActivity.class);
                startActivityForResult(intent, REQUESTCODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI(){
        if(wishlistAdapter == null){
            wishlistAdapter = new WishlistAdapter(getActivity(), wishlists);
            mRecyclerView.setAdapter(wishlistAdapter);
        } else {
            wishlistAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.receiveData(REQUESTCODE, AddListActivity.RESULT_OK, getActivity().getIntent());
        //updateUI();
    }

    public void receiveData(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE){
            if (resultCode == AddListActivity.RESULT_OK){
                Wishlist newList = data.getParcelableExtra(NEW_ITEM);
                wishlists.add(newList);
            }
        }
    }
}
