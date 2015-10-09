package com.vintek_ss.vince.kiddocare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import br.com.sapereaude.maskedEditText.MaskedEditText;

/**
 * Created by dpozega on 8/18/15.
 */
public class FocusListeningMaskedEditText extends MaskedEditText implements View.OnFocusChangeListener {
    private View.OnFocusChangeListener internalFocusListener;
    private View.OnFocusChangeListener externalFocusListener;

    public FocusListeningMaskedEditText(Context context) {
        super(context);
        super.setOnFocusChangeListener(this); //super to register with superclass instead
    }

    public FocusListeningMaskedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOnFocusChangeListener(this); //super to register with superclass instead
    }

    public FocusListeningMaskedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setOnFocusChangeListener(this); //super to register with superclass instead
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (internalFocusListener != null) {
            internalFocusListener.onFocusChange(v, hasFocus);
        }
        if (externalFocusListener != null) {
            externalFocusListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public View.OnFocusChangeListener getOnFocusChangeListener() {
        return externalFocusListener;
    }

    @Override
    public void setOnFocusChangeListener(View.OnFocusChangeListener listener) {
        externalFocusListener = listener;
    }

    void setInternalFocusChangeListener(View.OnFocusChangeListener listener) {
        internalFocusListener = listener;
    }

    View.OnFocusChangeListener getInternalFocusListener() {
        return internalFocusListener;
    }
}
