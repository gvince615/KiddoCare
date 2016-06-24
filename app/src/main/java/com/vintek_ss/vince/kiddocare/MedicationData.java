package com.vintek_ss.vince.kiddocare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gvincent on 6/3/16.
 */
public class MedicationData implements Parcelable {
    public static final Parcelable.Creator<MedicationData> CREATOR = new Parcelable.Creator<MedicationData>() {
        @Override
        public MedicationData createFromParcel(Parcel source) {
            return new MedicationData(source);
        }

        @Override
        public MedicationData[] newArray(int size) {
            return new MedicationData[size];
        }
    };
    public String medication_time;
    public String medication_description;

    public MedicationData(String medication_time, String medication_description) {
        this.medication_time = medication_time;
        this.medication_description = medication_description;
    }

    protected MedicationData(Parcel in) {
        this.medication_time = in.readString();
        this.medication_description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.medication_time);
        dest.writeString(this.medication_description);
    }
}
