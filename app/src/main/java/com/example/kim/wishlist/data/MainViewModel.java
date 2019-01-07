package com.example.kim.wishlist.data;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.kim.wishlist.models.Wishlist;

import java.util.List;

public class MainViewModel extends ViewModel {
    private WishlistRepository mRepository;
    private LiveData<List<Wishlist>> mWishlists;

    public MainViewModel(Context context) {
        mRepository = new WishlistRepository(context);
        mWishlists = mRepository.getAllWishlists();
    }

    public LiveData<List<Wishlist>> getWishlists() {
        return mWishlists;
    }

    public void insert(Wishlist wishlist) {
        mRepository.insert(wishlist);
    }

    public void update(Wishlist wishlist) {
        mRepository.update(wishlist);
    }

    public void delete(Wishlist wishlist) {
        mRepository.delete(wishlist);
    }
}
