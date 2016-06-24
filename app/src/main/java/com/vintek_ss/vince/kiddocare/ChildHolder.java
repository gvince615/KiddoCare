package com.vintek_ss.vince.kiddocare;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressCityTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressLnOneTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressLnTwoTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressStateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressZipTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildBirthDateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildEnrollDateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildFirstNameTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildLastNameTextWatcher;

public class ChildHolder extends RecyclerView.ViewHolder {


    ChildFirstNameTextWatcher childFirstNameTextWatcher;
    ChildLastNameTextWatcher childLastNameTextWatcher;
    ChildAddressLnOneTextWatcher childAddressLnOneTextWatcher;
    ChildAddressLnTwoTextWatcher childAddressLnTwoTextWatcher;
    ChildAddressCityTextWatcher childAddressCityTextWatcher;
    ChildAddressStateTextWatcher childAddressStateTextWatcher;
    ChildAddressZipTextWatcher childAddressZipTextWatcher;
    ChildBirthDateTextWatcher childBirthDateTextWatcher;
    ChildEnrollDateTextWatcher childEnrollDateTextWatcher;
    private CardView cv;
    private TextInputEditText childFirstName, childLastName, childAddressLn1, childAddressLn2, childAddressCity, childAddressState, childAddressZip;
    private TextInputLayout childFirstName_layout, childLastName_layout, childAddressLn2_layout, childAddressLn1_layout, childAddressCity_layout, childAddressState_layout, childAddressZip_layout;
    private TextView childBirthdate, childEnrolldate;
    private Spinner childAge;


    public ChildHolder(View v,
                       ChildFirstNameTextWatcher childFirstNameTextWatcher,
                       ChildLastNameTextWatcher childLastNameTextWatcher,
                       ChildAddressLnOneTextWatcher childAddressLnOneTextWatcher,
                       ChildAddressLnTwoTextWatcher childAddressLnTwoTextWatcher,
                       ChildAddressCityTextWatcher childAddressCityTextWatcher,
                       ChildAddressStateTextWatcher childAddressStateTextWatcher,
                       ChildAddressZipTextWatcher childAddressZipTextWatcher,
                       ChildBirthDateTextWatcher childBirthDateTextWatcher,
                       ChildEnrollDateTextWatcher childEnrollDateTextWatcher
    ) {
        super(v);

        this.childFirstNameTextWatcher = childFirstNameTextWatcher;
        this.childLastNameTextWatcher = childLastNameTextWatcher;
        this.childAddressLnOneTextWatcher = childAddressLnOneTextWatcher;
        this.childAddressLnTwoTextWatcher = childAddressLnTwoTextWatcher;
        this.childAddressCityTextWatcher = childAddressCityTextWatcher;
        this.childAddressStateTextWatcher = childAddressStateTextWatcher;
        this.childAddressZipTextWatcher = childAddressZipTextWatcher;
        this.childBirthDateTextWatcher = childBirthDateTextWatcher;
        this.childEnrollDateTextWatcher = childEnrollDateTextWatcher;

        cv = (CardView)itemView.findViewById(R.id.childDataCardView);

//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "Tapped a Child Card", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        childFirstName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_first_name_layout);
        childLastName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_last_name_layout);
        childAddressLn1_layout = (TextInputLayout) itemView.findViewById(R.id.tv_et_child_address_ln_1_layout);
        childAddressLn2_layout = (TextInputLayout) itemView.findViewById(R.id.tv_et_child_address_ln_2_layout);
        childAddressCity_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_city_layout);
        childAddressState_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_state_layout);
        childAddressZip_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_zip_layout);

        childFirstName = (TextInputEditText) itemView.findViewById(R.id.et_child_first_name);
        childFirstName.addTextChangedListener(childFirstNameTextWatcher);

        childLastName = (TextInputEditText) itemView.findViewById(R.id.et_child_last_name);
        childLastName.addTextChangedListener(childLastNameTextWatcher);

        childAddressLn1 = (TextInputEditText) itemView.findViewById(R.id.et_child_address_ln_1);
        childAddressLn1.addTextChangedListener(childAddressLnOneTextWatcher);

        childAddressLn2 = (TextInputEditText) itemView.findViewById(R.id.et_child_address_ln_2);
        childAddressLn2.addTextChangedListener(childAddressLnTwoTextWatcher);

        childAddressCity = (TextInputEditText) itemView.findViewById(R.id.et_child_address_city);
        childAddressCity.addTextChangedListener(childAddressCityTextWatcher);

        childAddressState = (TextInputEditText) itemView.findViewById(R.id.et_child_address_state);
        childAddressState.addTextChangedListener(childAddressStateTextWatcher);

        childAddressZip = (TextInputEditText) itemView.findViewById(R.id.et_child_address_zip);
        childAddressZip.addTextChangedListener(childAddressZipTextWatcher);

        childAge = (Spinner) itemView.findViewById(R.id.spinner_age_bracket);

        childBirthdate = (TextView) itemView.findViewById(R.id.tv_Cbirthdate);
        childBirthdate.addTextChangedListener(childBirthDateTextWatcher);

        childEnrolldate = (TextView) itemView.findViewById(R.id.et_child_Edate);
        childEnrolldate.addTextChangedListener(childEnrollDateTextWatcher);



    }


    public EditText getChildAddressLn2() {
        return childAddressLn2;
    }

    public void setChildAddressLn2(TextInputEditText childAddressLn2) {
        this.childAddressLn2 = childAddressLn2;
    }

    public TextInputLayout getChildAddressLn2_layout() {
        return childAddressLn2_layout;
    }

    public void setChildAddressLn2_layout(TextInputLayout childAddressLn2_layout) {
        this.childAddressLn2_layout = childAddressLn2_layout;
    }

    public Spinner getChildAge() {
        return childAge;
    }

    public void setChildAge(Spinner childAge) {
        this.childAge = childAge;
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public EditText getChildFirstName() {
        return childFirstName;
    }

    public void setChildFirstName(TextInputEditText childFirstName) {
        this.childFirstName = childFirstName;
    }

    public EditText getChildLastName() {
        return childLastName;
    }

    public void setChildLastName(TextInputEditText childLastName) {
        this.childLastName = childLastName;
    }

    public EditText getChildAddressLn1() {
        return childAddressLn1;
    }

    public void setChildAddressLn1(TextInputEditText childAddressLn1) {
        this.childAddressLn1 = childAddressLn1;
    }

    public EditText getChildAddressCity() {
        return childAddressCity;
    }

    public void setChildAddressCity(TextInputEditText childAddressCity) {
        this.childAddressCity = childAddressCity;
    }

    public EditText getChildAddressState() {
        return childAddressState;
    }

    public void setChildAddressState(TextInputEditText childAddressState) {
        this.childAddressState = childAddressState;
    }

    public EditText getChildAddressZip() {
        return childAddressZip;
    }

    public void setChildAddressZip(TextInputEditText childAddressZip) {
        this.childAddressZip = childAddressZip;
    }

    public TextInputLayout getChildFirstName_layout() {
        return childFirstName_layout;
    }

    public void setChildFirstName_layout(TextInputLayout childFirstName_layout) {
        this.childFirstName_layout = childFirstName_layout;
    }

    public TextInputLayout getChildLastName_layout() {
        return childLastName_layout;
    }

    public void setChildLastName_layout(TextInputLayout childLastName_layout) {
        this.childLastName_layout = childLastName_layout;
    }

    public TextInputLayout getChildAddressLn1_layout() {
        return childAddressLn1_layout;
    }

    public void setChildAddressLn1_layout(TextInputLayout childAddressLn1_layout) {
        this.childAddressLn1_layout = childAddressLn1_layout;
    }

    public TextInputLayout getChildAddressCity_layout() {
        return childAddressCity_layout;
    }

    public void setChildAddressCity_layout(TextInputLayout childAddressCity_layout) {
        this.childAddressCity_layout = childAddressCity_layout;
    }

    public TextInputLayout getChildAddressState_layout() {
        return childAddressState_layout;
    }

    public void setChildAddressState_layout(TextInputLayout childAddressState_layout) {
        this.childAddressState_layout = childAddressState_layout;
    }

    public TextInputLayout getChildAddressZip_layout() {
        return childAddressZip_layout;
    }

    public void setChildAddressZip_layout(TextInputLayout childAddressZip_layout) {
        this.childAddressZip_layout = childAddressZip_layout;
    }

    public TextView getChildBirthdate() {
        return childBirthdate;
    }

    public void setChildBirthdate(TextView childBirthdate) {
        this.childBirthdate = childBirthdate;
    }

    public TextView getChildEnrolldate() {
        return childEnrolldate;
    }

    public void setChildEnrolldate(TextView childEnrolldate) {
        this.childEnrolldate = childEnrolldate;
    }
}
