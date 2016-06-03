package com.vintek_ss.vince.kiddocare;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MedicalHolder extends RecyclerView.ViewHolder {

    //private ImageView ivExample;
    private CardView cv;
    private TextView fluShotDate, immunizationDate, medicationTime;
    private TextInputLayout medicationDescription_label;
    private EditText  medicationDescription;

    public MedicalHolder(View v) {
        super(v);
        cv = (CardView)itemView.findViewById(R.id.medicalDataCardView);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Tapped a Medical Card", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fluShotDate = (TextView) itemView.findViewById(R.id.et_flu_shot_date);
        immunizationDate = (TextView) itemView.findViewById(R.id.et_immunization_date);
        medicationTime = (TextView) itemView.findViewById(R.id.et_medication_time);
        medicationDescription_label = (TextInputLayout) itemView.findViewById(R.id.tv_medicine_name);

        medicationDescription = (EditText)itemView.findViewById(R.id.et_medicine_name);
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

    public TextView getMedicationTime() {
        return medicationTime;
    }

    public void setMedicationTime(TextView medicationTime) {
        this.medicationTime = medicationTime;
    }

    public TextInputLayout getMedicationDescription_label() {
        return medicationDescription_label;
    }

    public void setMedicationDescription_label(TextInputLayout medicationDescription_label) {
        this.medicationDescription_label = medicationDescription_label;
    }

    public EditText getMedicationDescription() {
        return medicationDescription;
    }

    public void setMedicationDescription(EditText medicationDescription) {
        this.medicationDescription = medicationDescription;
    }

}
