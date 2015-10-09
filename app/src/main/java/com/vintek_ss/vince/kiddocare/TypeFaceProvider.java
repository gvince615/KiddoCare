package com.vintek_ss.vince.kiddocare;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;
import java.util.Map;

/**
 * TypeFaceProvider - holds a static HashTable of typeface objects
 */
public final class TypeFaceProvider {

    private static Map<String, Typeface> sTypeFaces = new Hashtable<>(4);

    /*Private constructor so you can not instantiate this utility class.*/
    private TypeFaceProvider() {
    }

    public static Typeface getTypeFace(Context context, String fileName) {
        Typeface tempTypeface = sTypeFaces.get(fileName);

        if (tempTypeface == null) {
            tempTypeface = Typeface.createFromAsset(context.getAssets(), fileName);
            sTypeFaces.put(fileName, tempTypeface);
        }

        return tempTypeface;
    }

}