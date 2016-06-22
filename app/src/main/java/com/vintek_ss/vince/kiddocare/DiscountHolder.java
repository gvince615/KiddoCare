package com.vintek_ss.vince.kiddocare;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class DiscountHolder extends RecyclerView.ViewHolder {

    private CardView cv;
    private TextInputEditText discountDescription;
    private TextInputLayout discountDescription_label;
    private Spinner discountAmount;

    public DiscountHolder(View v) {
        super(v);
        cv = (CardView)itemView.findViewById(R.id.discountDataCardView);

//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "Tapped a Discount Card", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        discountDescription_label = (TextInputLayout) itemView.findViewById(R.id.tv_discount_description);
        discountDescription = (TextInputEditText) itemView.findViewById(R.id.et_discount_description);

        discountAmount = (Spinner)itemView.findViewById(R.id.spinner_discount);

    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public TextInputLayout getDiscountDescription_label() {
        return discountDescription_label;
    }

    public void setDiscountDescription_label(TextInputLayout discountDescription_label) {
        this.discountDescription_label = discountDescription_label;
    }

    public Spinner getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Spinner discountAmount) {
        this.discountAmount = discountAmount;
    }

    public EditText getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(TextInputEditText discountDescription) {
        this.discountDescription = discountDescription;
    }
}
