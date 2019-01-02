package com.example.kim.wishlist.models;

public class WishlistItem {
    private String mWishListItem;

    public WishlistItem(String mWishListItem) {
        this.mWishListItem = mWishListItem;
    }

    public String getmWishListItem() {
        return mWishListItem;
    }

    public void setmWishListItem(String mWishListItem) {
        this.mWishListItem = mWishListItem;
    }

    @Override
    public String toString() {
        return mWishListItem;
    }
}
