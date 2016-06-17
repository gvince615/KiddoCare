package com.vintek_ss.vince.kiddocare;

import android.graphics.Bitmap;

/**
 * Created by gvincent on 6/3/16.
 */
public class ShotRecordData {
    Bitmap imageShortRecord;
    String flu_shot_date;
    String immunizations_date;


    public ShotRecordData(Bitmap imageShortRecord, String flu_shot_date, String immunizations_date) {
        this.imageShortRecord = imageShortRecord;
        this.flu_shot_date = flu_shot_date;
        this.immunizations_date = immunizations_date;

    }
}
