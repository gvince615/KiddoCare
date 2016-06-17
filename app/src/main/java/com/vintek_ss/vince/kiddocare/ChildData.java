package com.vintek_ss.vince.kiddocare;

import android.graphics.Bitmap;

/**
 * Created by gvincent on 3/24/16.
 */
public class ChildData {
    String age;
    int number;
    Bitmap pic;
    String first_name;
    String last_name;
    String birth_date;
    String enroll_date;
    String address_ln_1;
    String address_ln_2;
    String address_city;
    String address_state;
    String address_zip;

    public ChildData(int number, Bitmap pic, String first_name, String last_name, String birth_date,
                     String enroll_date, String address_ln_1, String address_ln_2, String address_city,
                     String address_state, String address_zip) {

        this.number = number;
        this.pic = pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.enroll_date = enroll_date;
        this.address_ln_1 = address_ln_1;
        this.address_ln_2 = address_ln_2;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
    }

    public ChildData(int number, Bitmap pic, String first_name, String last_name, String birth_date, String age,
                     String enroll_date, String address_ln_1, String address_ln_2, String address_city,
                     String address_state, String address_zip) {

        this.number = number;
        this.pic = pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.enroll_date = enroll_date;
        this.address_ln_1 = address_ln_1;
        this.address_ln_2 = address_ln_2;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
        this.age = age;
    }

    public ChildData(int number, Bitmap pic, String first_name, String last_name, String age_bracket) {
        this.number = number;
        this.pic = pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age_bracket;
    }
}
