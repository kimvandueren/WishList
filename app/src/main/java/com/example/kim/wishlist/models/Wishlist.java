package com.example.kim.wishlist.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.kim.wishlist.data.GithubTypeConverter;

import java.util.List;

@Entity(tableName = "wishlist")
public class Wishlist implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "wishlisttitle")
    private String mWishListTitle;
    @ColumnInfo(name = "wishlistitems")
    @TypeConverters(GithubTypeConverter.class)
    private List<WishlistItem> wishlistItems;

    public Wishlist(int id, String mWishListTitle, List<WishlistItem> wishlistItems) {
        this.id = id;
        this.mWishListTitle = mWishListTitle;
        this.wishlistItems = wishlistItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWishListTitle() {
        return mWishListTitle;
    }

    public void setWishListTitle(String mWishListTitle) {
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
        dest.writeValue(this.id);
        dest.writeString(this.mWishListTitle);
        dest.writeTypedList(this.wishlistItems);
    }

    protected Wishlist(Parcel in) {
        this.id = (int) in.readValue(int.class.getClassLoader());
        this.mWishListTitle = in.readString();
        this.wishlistItems = in.readArrayList(WishlistItem.class.getClassLoader());
    }

    public static final Creator<Wishlist> CREATOR = new Creator<Wishlist>() {
        @Override
        public Wishlist createFromParcel(Parcel source) {
            return new Wishlist(source);
        }

        @Override
        public Wishlist[] newArray(int size) {
            return new Wishlist[size];
        }
    };
}
