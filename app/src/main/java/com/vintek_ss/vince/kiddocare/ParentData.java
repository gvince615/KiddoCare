package com.vintek_ss.vince.kiddocare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gvincent on 3/24/16.
 */
public class ParentData implements Parcelable {
    public static final Parcelable.Creator<ParentData> CREATOR = new Parcelable.Creator<ParentData>() {
        @Override
        public ParentData createFromParcel(Parcel source) {
            return new ParentData(source);
        }

        @Override
        public ParentData[] newArray(int size) {
            return new ParentData[size];
        }
    };
    String guardian_type;
    String first_name;
    String last_name;
    String email;
    String phoneNumber;
    boolean isAddressSameAsChild;
    String address_ln_1;
    String address_ln_2;
    String address_city;
    String address_state;
    String address_zip;

    public ParentData(String guardian_type, String first_name, String last_name, String email,
                      String phoneNumber, boolean isAddressSameAsChild, String address_ln_1, String address_ln_2, String address_city,
                      String address_state, String address_zip) {

        this.guardian_type = guardian_type;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isAddressSameAsChild = isAddressSameAsChild;
        this.address_ln_1 = address_ln_1;
        this.address_ln_2 = address_ln_2;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
    }

    protected ParentData(Parcel in) {
        this.guardian_type = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.email = in.readString();
        this.phoneNumber = in.readString();
        this.isAddressSameAsChild = in.readByte() != 0;
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
        dest.writeString(this.guardian_type);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.email);
        dest.writeString(this.phoneNumber);
        dest.writeByte(this.isAddressSameAsChild ? (byte) 1 : (byte) 0);
        dest.writeString(this.address_ln_1);
        dest.writeString(this.address_ln_2);
        dest.writeString(this.address_city);
        dest.writeString(this.address_state);
        dest.writeString(this.address_zip);
    }
}
