package com.vintek_ss.vince.kiddocare.TextWatchers;

import android.text.Editable;
import android.text.TextWatcher;

import com.vintek_ss.vince.kiddocare.ParentData;

import java.util.List;

/**
 * Created by gvincent on 6/23/16.
 */
public class ParentAddressCityTextWatcher implements TextWatcher {
    List<Object> cards;
    int position;
    Object card;

    public void setParams(List<Object> cards, int position) {
        this.card = cards.get(position);
        this.cards = cards;
        this.position = position;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ((ParentData) card).address_city = s.toString();
    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}