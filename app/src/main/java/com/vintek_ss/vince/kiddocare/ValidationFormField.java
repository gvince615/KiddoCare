package com.vintek_ss.vince.kiddocare;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.sapereaude.maskedEditText.MaskedEditText;


public class ValidationFormField extends LinearLayout {

    public static final int NO_MINIMUM_LENGTH = -1;
    private int fieldHeight;
    private TextView fieldFloatingLabel;
    private TextView errorLabelOrHelperLabel;
    private FocusListeningMaskedEditText validationField;
    private RelativeLayout layoutContainer;
    private ValidationFormField dependencyField;
    private String fieldType;
    private int minLength;
    private String minLengthErrorText;
    private String regexPattern;
    private String regexErrorText;


    private boolean hasHelperText, floatingTextVisible, isLoggedIn = false;

    public ValidationFormField(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.validation_form_field, this, true);

        initializeLayout(context, attrs);
    }

    public ValidationFormField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.validation_form_field, this, true);

        initializeLayout(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ValidationFormField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.validation_form_field, this, true);

        initializeLayout(context, attrs);

    }

    public void initializeLayout(Context context, AttributeSet attrs) {
        fieldFloatingLabel = (TextView) findViewById(R.id.upperFieldNameLabel);
        errorLabelOrHelperLabel = (TextView) findViewById(R.id.fieldErrorLabel);
        validationField = (FocusListeningMaskedEditText) findViewById(R.id.fieldValue);
        validationField.setInternalFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    gainedFocus();
                } else {
                    lostFocus();
                }
            }
        });
        layoutContainer = (RelativeLayout) findViewById(R.id.validationContainer);

        fieldFloatingLabel.setTypeface(TypeFaceProvider.getTypeFace(getContext(), getResources().getString
                (R.string.myriad_pro)));
        errorLabelOrHelperLabel.setTypeface(TypeFaceProvider.getTypeFace(getContext(), getResources().getString(R.string
                .myriad_pro)));
        validationField.setTypeface(TypeFaceProvider.getTypeFace(getContext(), getResources().getString(R.string
                .myriad_pro)));

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ValidationFormField,
                0, 0);

        //set custom XML attributes
        try {
            String hintText = a.getString(R.styleable.ValidationFormField_hintText);
            String floatingLabelText = a.getString(R.styleable
                    .ValidationFormField_floatingLabelText);
            String fieldType = a.getString(R.styleable.ValidationFormField_fieldType);
            int maxLength = a.getInt(R.styleable.ValidationFormField_maxLength, Integer.MAX_VALUE);

            minLength = a.getInt(R.styleable.ValidationFormField_minLength, NO_MINIMUM_LENGTH);
            minLengthErrorText = a.getString(R.styleable.ValidationFormField_minLengthErrorText);
            regexPattern = a.getString(R.styleable.ValidationFormField_regexPattern);
            regexErrorText = a.getString(R.styleable.ValidationFormField_regexErrorText);

            if (minLengthErrorText == null || minLengthErrorText.length() == 0) {
                minLengthErrorText = regexErrorText;
            }
            hasHelperText = a.getBoolean(R.styleable.ValidationFormField_hasHelperText, false);
            isLoggedIn = a.getBoolean(R.styleable.ValidationFormField_isloggedIn, false);

            if (isLoggedIn) {
                validationField.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.validation_edit_text_logged_in));
            } else {
                validationField.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.validation_edit_text));
            }

            if (regexPattern != null || fieldType.equals(getResources().getString(R.string.email_field_type))) {
                if (regexErrorText == null)
                    throw new RuntimeException("No error text provided for regex check: " + floatingLabelText);
            }

            errorLabelOrHelperLabel.setText(regexErrorText);

            if (minLength != NO_MINIMUM_LENGTH) {
                if (minLengthErrorText == null)
                    throw new RuntimeException("No error text provided for minimum length check: " + floatingLabelText);
            }

            if (fieldType != null) {
                this.fieldType = fieldType;
            }

            if (floatingLabelText != null) {
                fieldFloatingLabel.setText(floatingLabelText);
            }

            if (hintText != null) {
                validationField.setHint(hintText);
            }

            if (maxLength != Integer.MAX_VALUE) {

                if (this.fieldType.equals(getResources().getString(R.string.enter_ssn_field_type))) {
                    validationField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new NumberInputFilter()});
                }

                if (this.fieldType.equals(getResources().getString(R.string.enter_2digit_state_field_type))) {

                }

                if (this.fieldType.equals(getResources().getString(R.string.enter_currency_field_type))) {
                    validationField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new CurrencyInputFilter()});
                    //((MaskedEditText) getValidationField()).setMask("$");
                }

                if (this.fieldType.equals(getResources().getString(R.string.enter_birth_date_field_type))) {
                    validationField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new DateInputFilter()});
                    ((MaskedEditText) getValidationField()).setMask("##/##/####");
                }

                if (this.fieldType.equals(getResources().getString(R.string.enter_full_ssn_field_type))) {
                    validationField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new FullSSNInputFilter()});
                    ((MaskedEditText) getValidationField()).setMask("###-##-####");
                } else if (this.fieldType.equals(getResources().getString(R.string.create_user_name_field_type)) ||
                        this.fieldType.equals(getResources().getString(R.string.enter_username_field_type))) {
                    validationField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new UsernameInputFilter()});
                } else {
                    validationField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                }
            }

            //word suggestion removal
            if (this.fieldType.equals(getResources().getString(R.string.enter_username_field_type)) ||
                    this.fieldType.equals(getResources().getString(R.string.enter_password_field_type)) ||
                    this.fieldType.equals(getResources().getString(R.string.enter_ssn_field_type))) {
                validationField.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                // throw new RuntimeException();
            }

            //password greeking
            if (this.fieldType.equals(getResources().getString(R.string.create_password_field_type)) ||
                    this.fieldType.equals(getResources().getString(R.string.confirm_create_password_field_type)) ||
                    this.fieldType.equals(getResources().getString(R.string.enter_password_field_type)) ||
                    this.fieldType.equals(getResources().getString(R.string.enter_ssn_field_type))) {

                validationField.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            final View containerView = layoutContainer;
            final LinearLayout.LayoutParams containerParams = (LinearLayout.LayoutParams)
                    containerView.getLayoutParams();

            fieldHeight = containerParams.height;
            errorLabelOrHelperLabel.setText(regexErrorText);


        } finally {
            a.recycle();
        }

        validationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.toString().length() > 0) {
                    if (!floatingTextVisible) {
                        displayFloatingText();
                        focusOnView(validationField);
                    }
                } else {
                    if (floatingTextVisible) {
                        hideFloatingText();
                        focusOnView(validationField);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public EditText getValidationField() {
        return validationField;
    }


    public String getFieldText() {
        return validationField.getText().toString();
    }

    public void setFieldText(String fieldText) {
        validationField.setText(fieldText);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        validationField.setEnabled(enabled);
    }

    public void setFloatingLabelText(String labelText) {
        fieldFloatingLabel.setText(labelText);
    }

    public void setHintText(String labelText) {
        validationField.setHint(labelText);
    }

    public void setErrorText(String labelText) {
        errorLabelOrHelperLabel.setText(labelText);
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public void setDependencyField(ValidationFormField dependencyField) {
        this.dependencyField = dependencyField;
    }

    public void setInputType(int inputType) {
        validationField.setInputType(inputType);
    }

    public void lostFocus() {
        if (validationField.getText().length() == 0) {
            hideFloatingText();
        }
        validate();
    }

    public void gainedFocus() {

        if (isLoggedIn) {
            validationField.setBackground(ContextCompat.getDrawable(getContext(),
                    R.drawable.validation_edit_text_logged_in));
        } else {
            validationField.setBackground(ContextCompat.getDrawable(getContext(),
                    R.drawable.validation_edit_text));
        }

        if (!hasHelperText) {
            clearFailedValidation();

        } else {
            displayErrorLabelOrHelperLabel();
            errorLabelOrHelperLabel.setTextColor(getResources().getColor(R.color.black));
        }
        if (!floatingTextVisible) {
            displayFloatingText();
        }
        focusOnView(this);
    }


    private final void focusOnView(final View v) {
        final ScrollView container = getContainingScrollView();
        if (container != null) {

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                if (v.getTop() > container.getBottom()/2){
//                    //container.smoothScrollTo(0, v.getBottom());
//                }
//                else{
//                    //container.smoothScrollTo(0, v.getTop());
//                }
//                container.smoothScrollTo(0, v.getTop());
//                }
//            }, 500);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Rect viewRect = new Rect(0, 0, 0, 0);
                    v.getFocusedRect(viewRect);
                    int scroll = v.getTop() - 250;
                    Boolean isVisible = container.requestChildRectangleOnScreen(v, viewRect, true);
                    if (!isVisible) {
                        ObjectAnimator animator = ObjectAnimator.ofInt(container, "scrollY", scroll);
                        animator.setDuration(600);
                        animator.start();
                    } else {
                        return;
                    }
                }
            }, 00);
        }
    }

    private ScrollView getContainingScrollView() {
        ViewParent v = getParent();
        int accumulatingHeight = 0;
        if (v instanceof ScrollView) {
            return (ScrollView) v;
        } else {
            ViewParent v2 = v.getParent();
            if (v2 instanceof ScrollView) {
                return (ScrollView) v2;
            } else {
                ViewParent v3 = v2.getParent();
                if (v3 instanceof ScrollView) {
                    return (ScrollView) v3;
                } else {
                    return null;
                }
            }
        }
    }

    //
    public void displayFloatingText() {
        floatingTextVisible = true;
        final View containerView = layoutContainer;
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);

        final LinearLayout.LayoutParams containerParams = (LinearLayout.LayoutParams)
                layoutContainer.getLayoutParams();

        ValueAnimator animator = ValueAnimator.ofInt(containerParams.topMargin, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                containerParams.topMargin = (Integer) valueAnimator.getAnimatedValue();
                containerView.requestLayout();
            }
        });

        animator.setDuration(300);
        animator.start();
        fieldFloatingLabel.setVisibility(View.VISIBLE);
        fieldFloatingLabel.startAnimation(anim);

    }

    public void hideFloatingText() {
        floatingTextVisible = false;
        final View containerView = layoutContainer;
        //collapsing view and giving negative top margin
        final LinearLayout.LayoutParams containerParams = (LinearLayout.LayoutParams)
                layoutContainer.getLayoutParams();
        int decreasedHeight = fieldFloatingLabel.getHeight();
        ValueAnimator animator = ValueAnimator.ofInt(containerParams.topMargin, -decreasedHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                containerParams.topMargin = (Integer) valueAnimator.getAnimatedValue();
                containerView.requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fieldFloatingLabel.setVisibility(View.INVISIBLE);
            }
        });
        animator.setDuration(300);
        animator.start();
    }

    public int displayErrorLabelOrHelperLabel() {
        final View containerView = layoutContainer;
        final LinearLayout.LayoutParams containerParams = (LinearLayout.LayoutParams)
                containerView.getLayoutParams();
        final RelativeLayout.LayoutParams errorParams = (RelativeLayout.LayoutParams)
                errorLabelOrHelperLabel.getLayoutParams();

        errorLabelOrHelperLabel.measure(View.MeasureSpec.makeMeasureSpec(errorLabelOrHelperLabel.getWidth(), MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int errorRequiredHeight = errorLabelOrHelperLabel.getMeasuredHeight();
        ValueAnimator animator = ValueAnimator.ofInt(containerParams.height, fieldHeight + errorRequiredHeight);

        //final int errorHeightScale = errorLabelOrHelperLabel.getMeasuredHeight() / errorLabelOrHelperLabel.getHeight();

        errorParams.height = errorRequiredHeight;
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                containerParams.height = (Integer) valueAnimator.getAnimatedValue();
                //errorParams.height = errorLabelOrHelperLabel.getHeight() * errorHeightScale;
                containerView.requestLayout();

            }
        });

        animator.setDuration(300);
        animator.start();

        errorLabelOrHelperLabel.setVisibility(VISIBLE);


        return errorRequiredHeight;
    }

    private void displayFailedValidation(String error) {
        if (isLoggedIn) {
            validationField.setBackground(ContextCompat.getDrawable(getContext(),
                    R.drawable.edit_text_red_gbg));
        } else {
            validationField.setBackground(ContextCompat.getDrawable(getContext(),
                    R.drawable.edit_text_red_wbg));
        }

        errorLabelOrHelperLabel.setTextColor(getResources().getColor(R.color.red));
        errorLabelOrHelperLabel.setText(error);
        displayErrorLabelOrHelperLabel();
        focusOnView(this);

    }

    public void clearFailedValidation() {

        final View containerView = layoutContainer;
        final LinearLayout.LayoutParams containerParams = (LinearLayout.LayoutParams)
                containerView.getLayoutParams();

        ValueAnimator animator = ValueAnimator.ofInt(containerParams.height, fieldHeight);

        //fade out error text
        if (errorLabelOrHelperLabel.getVisibility() != INVISIBLE) {
            final Animation out = new AlphaAnimation(errorLabelOrHelperLabel.getAlpha(), 0.0f);
            out.setDuration(300);
            errorLabelOrHelperLabel.startAnimation(out);
            focusOnView(this);

        }

        //TODO I think this does nothing
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                containerParams.height = (Integer) valueAnimator.getAnimatedValue();
                containerView.requestLayout();
            }
        });

        //set to error text invisible once resize animation is done
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                errorLabelOrHelperLabel.setVisibility(View.INVISIBLE);
            }
        });

        animator.setDuration(300);
        animator.start();

    }

    private boolean fieldMatchesRegEx(String patternString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(validationField.getText());
        boolean noAndInName = true;
        if (fieldType.equals(getResources().getString(R.string.name_field_type)) &&
                (validationField.getText().toString().contains(" and "))) {
            noAndInName = false;
        }

        return matcher.matches() && noAndInName;
    }

    public boolean validate() {
        String error = null;

        if (fieldType.equals(getResources().getString(R.string.email_field_type))) {
            regexPattern = Patterns.EMAIL_ADDRESS.toString();
        }

        if (fieldType.equals(getResources().getString(R.string.enter_2digit_state_field_type))) {
            String[] items = getResources().getStringArray(R.array.state_codes);
            boolean stateExists = stringMatchesStateFromList(validationField.getText().toString(), items);
            if (stateExists) {
                error = null;
            }
            if (!stateExists) {
                error = getResources().getString(R.string.state_error);
                displayFailedValidation(error);
                return false;
            }
        }

        if (regexPattern != null) {
            error = charValidate(regexPattern);
        }

        if (error == null) {
            if (validationField.length() == 0) {
                if (fieldType.equals(getResources().getString(R.string
                        .create_user_name_field_type))) {
                    error = getResources().getString(R.string.validation_dont_leave_username_empty);
                } else if (fieldType.equals(getResources().getString(R.string
                        .email_field_type))) {
                    error = getResources().getString(R.string.email_error);
                } else if (fieldType.equals(getResources().getString(R.string.create_password_field_type))) {
                    error = getResources().getString(R.string.password_error);
                }
            }
        }

        //if okay so far, check the next rule
        if (error == null && minLength != NO_MINIMUM_LENGTH) {
            error = minLengthValidate(minLength);
        }

        //if okay so far, check the next rule
        if (error == null && fieldType.equals(getResources().getString(R.string.confirm_create_password_field_type))) {
            error = confirmPasswordsSame();
        }

        if (error == null) {
            clearFailedValidation();
            return true;
        } else {
            displayFailedValidation(error);
            return false;
        }
    }

    private boolean stringMatchesStateFromList(String s, String[] items) {
        boolean stateIsThere = false;

        for (int i = 0; i < items.length; i++) {
            if (s.equals(items[i])) {
                stateIsThere = true;
                break;
            } else {
                stateIsThere = false;
            }
        }
        return stateIsThere;
    }

    private String minLengthValidate(int minLength) {
        if (validationField.getText().length() >= minLength) {
            return null;
        }
        return minLengthErrorText;
    }

    private String charValidate(String regexSpecifier) {
        if (fieldMatchesRegEx(regexSpecifier)) {
            return null;
        } else {
            return regexErrorText;
        }
    }

    private String confirmPasswordsSame() {
        if (getValidationField().getText().equals(dependencyField.getValidationField().getText())) {
            return null;
        }
        return getResources().getString(R.string.confirm_password_error);
    }


}
