package com.example.kim.wishlist.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.kim.wishlist.models.Wishlist;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WishlistRepository {
    private AppDatabase mAppDatabase;
    private WishlistDao mWishlistDao;
    private LiveData<List<Wishlist>> mWishlists;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public WishlistRepository (Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mWishlistDao = mAppDatabase.wishlistDao();
        mWishlists = mWishlistDao.getAllWishlists();
    }

    public LiveData<List<Wishlist>> getAllWishlists() {
        return mWishlists;
    }

    public void insert(final Wishlist wishlist) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mWishlistDao.insertWishlists(wishlist);
            }
        });
    }


    public void update(final Wishlist wishlist) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mWishlistDao.updateWishlists(wishlist);
            }
        });
    }


    public void delete(final Wishlist wishlist) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mWishlistDao.deleteWishlists(wishlist);
            }
        });
    }
}
