package com.vintek_ss.vince.kiddocare;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by gvincent on 3/24/16.
 */
public class ChildData implements Serializable, Parcelable {
    public static final Parcelable.Creator<ChildData> CREATOR = new Parcelable.Creator<ChildData>() {
        @Override
        public ChildData createFromParcel(Parcel source) {
            return new ChildData(source);
        }

        @Override
        public ChildData[] newArray(int size) {
            return new ChildData[size];
        }
    };
    String age;
    int number;
    Bitmap pic;
    String first_name;
    String last_name;
    String birth_date;
    String enroll_date;
    String address_ln_1;
    String address_ln_2;
    String address_city;
    String address_state;
    String address_zip;

    public ChildData(int number, Bitmap pic, String first_name, String last_name, String birth_date,
                     String enroll_date, String address_ln_1, String address_ln_2, String address_city,
                     String address_state, String address_zip) {

        this.number = number;
        this.pic = pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.enroll_date = enroll_date;
        this.address_ln_1 = address_ln_1;
        this.address_ln_2 = address_ln_2;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
    }

    public ChildData(int number, Bitmap pic, String first_name, String last_name, String birth_date, String age,
                     String enroll_date, String address_ln_1, String address_ln_2, String address_city,
                     String address_state, String address_zip) {

        this.number = number;
        this.pic = pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.enroll_date = enroll_date;
        this.address_ln_1 = address_ln_1;
        this.address_ln_2 = address_ln_2;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
        this.age = age;
    }

    public ChildData(int number, Bitmap pic, String first_name, String last_name, String age_bracket) {
        this.number = number;
        this.pic = pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age_bracket;
    }

    public ChildData(int number, String first_name, String last_name, String birth_date, String age, String enroll_date, String address_ln_1, String address_ln_2, String address_city, String address_state, String address_zip) {
        this.number = number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.enroll_date = enroll_date;
        this.address_ln_1 = address_ln_1;
        this.address_ln_2 = address_ln_2;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
        this.age = age;
    }

    protected ChildData(Parcel in) {
        this.age = in.readString();
        this.number = in.readInt();
        this.pic = in.readParcelable(Bitmap.class.getClassLoader());
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.birth_date = in.readString();
        this.enroll_date = in.readString();
        this.address_ln_1 = in.readString();
        this.address_ln_2 = in.readString();
        this.address_city = in.readString();
        this.address_state = in.readString();
        this.address_zip = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.age);
        dest.writeInt(this.number);
        dest.writeParcelable(this.pic, flags);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.birth_date);
        dest.writeString(this.enroll_date);
        dest.writeString(this.address_ln_1);
        dest.writeString(this.address_ln_2);
        dest.writeString(this.address_city);
        dest.writeString(this.address_state);
        dest.writeString(this.address_zip);
    }
}
