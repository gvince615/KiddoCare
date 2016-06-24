package com.vintek_ss.vince.kiddocare;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vintek_ss.vince.kiddocare.TextWatchers.MedDescTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.MedTimeTextWatcher;

public class MedicationHolder extends RecyclerView.ViewHolder {

    MedDescTextWatcher medDescTextWatcher;
    MedTimeTextWatcher medTimeTextWatcher;
    //private ImageView ivExample;
    private CardView cv;
    private TextView medicationTime;
    private TextInputLayout medicationDescription_label;
    private TextInputEditText medicationDescription;

    public MedicationHolder(View v,
                            MedDescTextWatcher medDescTextWatcher,
                            MedTimeTextWatcher medTimeTextWatcher) {
        super(v);

        this.medDescTextWatcher = medDescTextWatcher;
        this.medTimeTextWatcher = medTimeTextWatcher;

        cv = (CardView) itemView.findViewById(R.id.medicineDataCardView);

//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "Tapped a Medication Card", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        medicationTime = (TextView) itemView.findViewById(R.id.et_medication_time);
        medicationTime.addTextChangedListener(medTimeTextWatcher);

        medicationDescription_label = (TextInputLayout) itemView.findViewById(R.id.tv_medicine_name);

        medicationDescription = (TextInputEditText) itemView.findViewById(R.id.et_medicine_name);
        medicationDescription.addTextChangedListener(medDescTextWatcher);
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

    public void setMedicationDescription(TextInputEditText medicationDescription) {
        this.medicationDescription = medicationDescription;
    }

}
