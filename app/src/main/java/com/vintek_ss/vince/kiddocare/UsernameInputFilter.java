package com.vintek_ss.vince.kiddocare;

/**
 * Created by dpozega on 8/4/15.
 */
public class UsernameInputFilter extends CharwiseInputFilter {
    public final String prohibited = " |~'\"*`&<>:\\%?"; //NOTE: there is  space in here intentionally!

    protected boolean isCharAllowed(char c) {
        return prohibited.indexOf(c) == -1;
    }
}
