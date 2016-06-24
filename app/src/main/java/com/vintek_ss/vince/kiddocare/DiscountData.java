package com.vintek_ss.vince.kiddocare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gvincent on 6/3/16.
 */
public class DiscountData implements Parcelable {
    public static final Parcelable.Creator<DiscountData> CREATOR = new Parcelable.Creator<DiscountData>() {
        @Override
        public DiscountData createFromParcel(Parcel source) {
            return new DiscountData(source);
        }

        @Override
        public DiscountData[] newArray(int size) {
            return new DiscountData[size];
        }
    };
    public String discount_description;
    public String discount_amount;

    public DiscountData(String discount_description, String discount_amount) {
        this.discount_description = discount_description;
        this.discount_amount = discount_amount;
    }

    protected DiscountData(Parcel in) {
        this.discount_description = in.readString();
        this.discount_amount = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.discount_description);
        dest.writeString(this.discount_amount);
    }
}
