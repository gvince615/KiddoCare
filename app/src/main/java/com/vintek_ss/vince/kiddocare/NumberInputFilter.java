package com.vintek_ss.vince.kiddocare;

/**
 * Created by dpozega on 8/4/15.
 * <p/>
 * See stack overflow 3349121.
 */
public class NumberInputFilter extends CharwiseInputFilter {

    protected boolean isCharAllowed(char c) {
        return Character.isDigit(c);
    }
}
