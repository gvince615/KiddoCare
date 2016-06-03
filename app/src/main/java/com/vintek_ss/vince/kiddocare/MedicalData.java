package com.vintek_ss.vince.kiddocare;

/**
 * Created by gvincent on 6/3/16.
 */
public class MedicalData {
    String flu_shot_date;
    String immunizations_date;
    String medication_time;
    String medication_description;

    public MedicalData(String flu_shot_date, String immunizations_date, String medication_time, String medication_description) {
        this.flu_shot_date = flu_shot_date;
        this.immunizations_date = immunizations_date;
        this.medication_time = medication_time;
        this.medication_description = medication_description;
    }
}
