package com.example.kim.wishlist.data;

import android.arch.persistence.room.TypeConverter;

import com.example.kim.wishlist.models.WishlistItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class GithubTypeConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<WishlistItem> stringToWishlistItemList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<WishlistItem>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String wishlistItemListToString(List<WishlistItem> wishlistItems) {
        return gson.toJson(wishlistItems);
    }
}
