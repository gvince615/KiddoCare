package com.vintek_ss.vince.kiddocare;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChildHolder extends RecyclerView.ViewHolder {



    private CardView cv;
    private EditText childFirstName, childLastName, childAddressLn1, childAddressCity, childAddressState, childAddressZip;
    private TextInputLayout childFirstName_layout, childLastName_layout, childAddressLn1_layout, childAddressCity_layout, childAddressState_layout, childAddressZip_layout;
    private TextView childBirthdate, childEnrolldate;

    public ChildHolder(View v) {
        super(v);
        cv = (CardView)itemView.findViewById(R.id.childDataCardView);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Tapped a Child Card", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        childFirstName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_first_name_layout);
        childLastName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_last_name_layout);
        childBirthdate = (TextView) itemView.findViewById(R.id.tv_Cbirthdate);
        childEnrolldate = (TextView) itemView.findViewById(R.id.et_child_Edate);
        childAddressLn1_layout = (TextInputLayout) itemView.findViewById(R.id.tv_et_child_address_ln_1_layout);
        childAddressCity_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_city_layout);
        childAddressState_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_state_layout);
        childAddressZip_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_zip_layout);

        childFirstName = (EditText)itemView.findViewById(R.id.et_childFname);
        childLastName = (EditText)itemView.findViewById(R.id.et_child_last_name);
        childAddressLn1 = (EditText)itemView.findViewById(R.id.et_child_address_ln_1);
        childAddressCity = (EditText)itemView.findViewById(R.id.et_child_address_city);
        childAddressState = (EditText)itemView.findViewById(R.id.et_child_address_state);
        childAddressZip = (EditText)itemView.findViewById(R.id.et_child_address_zip);


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

    public void setChildFirstName(EditText childFirstName) {
        this.childFirstName = childFirstName;
    }

    public EditText getChildLastName() {
        return childLastName;
    }

    public void setChildLastName(EditText childLastName) {
        this.childLastName = childLastName;
    }

    public EditText getChildAddressLn1() {
        return childAddressLn1;
    }

    public void setChildAddressLn1(EditText childAddressLn1) {
        this.childAddressLn1 = childAddressLn1;
    }

    public EditText getChildAddressCity() {
        return childAddressCity;
    }

    public void setChildAddressCity(EditText childAddressCity) {
        this.childAddressCity = childAddressCity;
    }

    public EditText getChildAddressState() {
        return childAddressState;
    }

    public void setChildAddressState(EditText childAddressState) {
        this.childAddressState = childAddressState;
    }

    public EditText getChildAddressZip() {
        return childAddressZip;
    }

    public void setChildAddressZip(EditText childAddressZip) {
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
