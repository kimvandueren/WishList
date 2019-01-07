package com.example.kim.wishlist.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WishlistItem implements Parcelable {
    private String mWishListItem;

    public WishlistItem(String mWishListItem) {
        this.mWishListItem = mWishListItem;
    }

    public String getWishListItem() {
        return mWishListItem;
    }

    public void setWishListItem(String mWishListItem) {
        this.mWishListItem = mWishListItem;
    }

    @Override
    public String toString() {
        return mWishListItem;
    }

    protected WishlistItem(Parcel in) {
        mWishListItem = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mWishListItem);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WishlistItem> CREATOR = new Creator<WishlistItem>() {
        @Override
        public WishlistItem createFromParcel(Parcel in) {
            return new WishlistItem(in);
        }

        @Override
        public WishlistItem[] newArray(int size) {
            return new WishlistItem[size];
        }
    };
}
