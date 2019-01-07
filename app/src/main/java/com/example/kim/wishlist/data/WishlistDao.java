package com.example.kim.wishlist.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kim.wishlist.models.Wishlist;

import java.util.List;

@Dao
public interface WishlistDao {
    @Query("SELECT * FROM wishlist")
    public List<Wishlist> getAllWishlists();

    @Insert
    public void insertWishlists(Wishlist wishlist);

    @Delete
    public void deleteWishlists(Wishlist wishlist);

    @Update
    public void updateWishlists(Wishlist wishlist);
}
