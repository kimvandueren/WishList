package com.example.kim.wishlist.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import com.example.kim.wishlist.R;

public class AddListActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        toolbar = findViewById(R.id.addListToolbar);
        setSupportActionBar(toolbar);
    }
}
