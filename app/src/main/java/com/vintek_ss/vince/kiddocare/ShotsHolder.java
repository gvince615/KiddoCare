package com.vintek_ss.vince.kiddocare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vintek_ss.vince.kiddocare.TextWatchers.ShotsFluDateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ShotsImmunDateTextWatcher;

public class ShotsHolder extends RecyclerView.ViewHolder {

    ShotsFluDateTextWatcher shotsFluDateTextWatcher;
    ShotsImmunDateTextWatcher shotsImmunDateTextWatcher;
    private ImageView ivShotRecord;
    private CardView cv;
    private TextView fluShotDate, immunizationDate;

    public ShotsHolder(View v,
                       ShotsFluDateTextWatcher shotsFluDateTextWatcher,
                       ShotsImmunDateTextWatcher shotsImmunDateTextWatcher) {

        super(v);

        this.shotsFluDateTextWatcher = shotsFluDateTextWatcher;
        this.shotsImmunDateTextWatcher = shotsImmunDateTextWatcher;

        cv = (CardView)itemView.findViewById(R.id.medicalDataCardView);

//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "Tapped a Medical Card", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        ivShotRecord = (ImageView) itemView.findViewById(R.id.iv_shot_record);

        fluShotDate = (TextView) itemView.findViewById(R.id.et_flu_shot_date);
        fluShotDate.addTextChangedListener(shotsFluDateTextWatcher);

        immunizationDate = (TextView) itemView.findViewById(R.id.et_immunization_date);
        immunizationDate.addTextChangedListener(shotsImmunDateTextWatcher);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public TextView getFluShotDate() {
        return fluShotDate;
    }

    public void setFluShotDate(TextView fluShotDate) {
        this.fluShotDate = fluShotDate;
    }

    public TextView getImmunizationDate() {
        return immunizationDate;
    }

    public void setImmunizationDate(TextView immunizationDate) {
        this.immunizationDate = immunizationDate;
    }

    public ImageView getIvShotRecord() {
        return ivShotRecord;
    }

    public void setIvShotRecord(ImageView ivShotRecord) {
        this.ivShotRecord = ivShotRecord;
    }
}
