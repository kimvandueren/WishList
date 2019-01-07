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
    private List<String> wishlistItems;

    public Wishlist(String mWishListTitle, List<String> wishlistItems) {
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

    public List<String> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<String> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mWishListTitle);
        dest.writeList(this.wishlistItems);
    }

    protected Wishlist(Parcel in) {
        this.mWishListTitle = in.readString();
        this.wishlistItems = in.readArrayList(String.class.getClassLoader());
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
