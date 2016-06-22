package com.vintek_ss.vince.kiddocare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShotsHolder extends RecyclerView.ViewHolder {

    private ImageView ivShotRecord;
    private CardView cv;
    private TextView fluShotDate, immunizationDate;

    public ShotsHolder(View v) {
        super(v);
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
        immunizationDate = (TextView) itemView.findViewById(R.id.et_immunization_date);
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
