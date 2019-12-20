package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

class Badge_count {
    public int bronze;
    public int silver;
    public int gold;
}

class Item implements Parcelable {
    public Badge_count badge_counts;
    public int account_id;
    public long reputation;
    public String user_type;
    public int user_id;
    public int accept_rate;
    public String location;
    public String website_url;
    public String link;
    public String profile_image;
    public String display_name;


    protected Item(Parcel in) {
        account_id = in.readInt();
        reputation = in.readLong();
        user_type = in.readString();
        user_id = in.readInt();
        accept_rate = in.readInt();
        location = in.readString();
        website_url = in.readString();
        link = in.readString();
        profile_image = in.readString();
        display_name = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(account_id);
        dest.writeLong(reputation);
        dest.writeString(user_type);
        dest.writeInt(user_id);
        dest.writeInt(accept_rate);
        dest.writeString(location);
        dest.writeString(website_url);
        dest.writeString(link);
        dest.writeString(profile_image);
        dest.writeString(display_name);
    }
}

public class StackApiResponse {
    public List<Item> items;
    public boolean has_more;
    public int quota_max;
    public int quota_remaining;
}
