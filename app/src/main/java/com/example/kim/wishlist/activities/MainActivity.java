package com.example.kim.wishlist.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.adapters.SectionsPageAdapter;
import com.example.kim.wishlist.fragments.FriendsFragment;
import com.example.kim.wishlist.fragments.ListsFragment;
import com.example.kim.wishlist.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializes adapter and ViewPager for TabLayout
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    // Method to set up ViewPager and add fragments
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListsFragment(), getString(R.string.tab_lists));
        adapter.addFragment(new FriendsFragment(), getString(R.string.tab_friends));
        adapter.addFragment(new ProfileFragment(), getString(R.string.tab_profile));

        viewPager.setAdapter(adapter);
    }

}
