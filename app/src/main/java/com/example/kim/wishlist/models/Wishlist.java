package com.example.kim.wishlist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Wishlist implements Parcelable{
    private String mWishListTitle;
    private List<WishlistItem> wishlistItems;

    public Wishlist(String mWishListTitle, List<WishlistItem> wishlistItems) {
        this.mWishListTitle = mWishListTitle;
        this.wishlistItems = wishlistItems;
    }

    public String getmWishListTitle() {
        return mWishListTitle;
    }

    public void setmWishListTitle(String mWishListTitle) {
        this.mWishListTitle = mWishListTitle;
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mWishListTitle);
        dest.writeList(wishlistItems);
    }

    public Wishlist(Parcel in) {
        this.mWishListTitle = in.readString();
        this.wishlistItems = in.readArrayList(WishlistItem.class.getClassLoader());
    }

    public static final Creator<Wishlist> CREATOR = new Creator<Wishlist>() {
        @Override
        public Wishlist createFromParcel(Parcel in) {
            return new Wishlist(in);
        }

        @Override
        public Wishlist[] newArray(int size) {
            return new Wishlist[size];
        }
    };
}
