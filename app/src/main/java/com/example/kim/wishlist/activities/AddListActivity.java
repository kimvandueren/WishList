package com.example.kim.wishlist.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.adapters.WishlistItemAdapter;
import com.example.kim.wishlist.fragments.ListsFragment;
import com.example.kim.wishlist.models.WishlistItem;

import java.util.ArrayList;
import java.util.List;

public class AddListActivity extends AppCompatActivity {
    public static WishlistItem wishlistItem;

    private Toolbar toolbar;

    private List<WishlistItem> mWishlistItems;
    private WishlistItemAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mNewListItem;

    private ImageView addButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        toolbar = findViewById(R.id.addListToolbar);
        setSupportActionBar(toolbar);

        // Initializes variables for RecyclerView of Wishlist items
        mRecyclerView = findViewById(R.id.listItemsRecyclerView);
        mNewListItem = findViewById(R.id.editListItems);
        mWishlistItems = new ArrayList<>();

        // Sets LayoutManager for RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Button to add item to the list
        addButton = findViewById(R.id.addButton);
        addButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String text = mNewListItem.getText().toString();
                WishlistItem newWishlistItem = new WishlistItem(text);

                if (!(TextUtils.isEmpty(text))) {
                    mWishlistItems.add(newWishlistItem);
                    updateUI();
                    mNewListItem.setText("");
                }

                return false;
            }
        });
    }

    public void updateUI(){
        if (mAdapter == null) {
            mAdapter = new WishlistItemAdapter(this, mWishlistItems);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
