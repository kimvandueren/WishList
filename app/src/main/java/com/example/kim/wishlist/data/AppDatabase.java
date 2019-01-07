package com.example.kim.wishlist.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.kim.wishlist.models.Wishlist;

@Database(entities = {Wishlist.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WishlistDao wishlistDao();
    private final static String NAME_DATABASE = "wishlist_db";

    private static AppDatabase sInstance;
    public static AppDatabase getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE).allowMainThreadQueries().build();
        }
        return sInstance;
    }
}
