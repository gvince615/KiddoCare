/**
 * Created by gvincent on 8/14/15.
 */

package com.vintek_ss.vince.kiddocare;

/**
 * See stack overflow 3349121.
 */
public class FullSSNInputFilter extends CharwiseInputFilter {

    protected boolean isCharAllowed(char c) {
        return Character.isDigit(c) || c == '-';
    }
}