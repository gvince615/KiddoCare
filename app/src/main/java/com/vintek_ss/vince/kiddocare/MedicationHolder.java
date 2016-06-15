package com.vintek_ss.vince.kiddocare;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MedicationHolder extends RecyclerView.ViewHolder {

    //private ImageView ivExample;
    private CardView cv;
    private TextView medicationTime;
    private TextInputLayout medicationDescription_label;
    private EditText medicationDescription;

    public MedicationHolder(View v) {
        super(v);
        cv = (CardView) itemView.findViewById(R.id.medicineDataCardView);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Tapped a Medication Card", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        medicationTime = (TextView) itemView.findViewById(R.id.et_medication_time);
        medicationDescription_label = (TextInputLayout) itemView.findViewById(R.id.tv_medicine_name);

        medicationDescription = (EditText) itemView.findViewById(R.id.et_medicine_name);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
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
