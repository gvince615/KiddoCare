package com.vintek_ss.vince.kiddocare;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gvincent on 6/3/16.
 */
public class ShotRecordData implements Parcelable {
    public static final Parcelable.Creator<ShotRecordData> CREATOR = new Parcelable.Creator<ShotRecordData>() {
        @Override
        public ShotRecordData createFromParcel(Parcel source) {
            return new ShotRecordData(source);
        }

        @Override
        public ShotRecordData[] newArray(int size) {
            return new ShotRecordData[size];
        }
    };
    public String flu_shot_date;
    public String immunizations_date;
    Bitmap imageShotRecord;

    public ShotRecordData(Bitmap imageShotRecord, String flu_shot_date, String immunizations_date) {
        this.imageShotRecord = imageShotRecord;
        this.flu_shot_date = flu_shot_date;
        this.immunizations_date = immunizations_date;

    }

    protected ShotRecordData(Parcel in) {
        this.imageShotRecord = in.readParcelable(Bitmap.class.getClassLoader());
        this.flu_shot_date = in.readString();
        this.immunizations_date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.imageShotRecord, flags);
        dest.writeString(this.flu_shot_date);
        dest.writeString(this.immunizations_date);
    }
}
