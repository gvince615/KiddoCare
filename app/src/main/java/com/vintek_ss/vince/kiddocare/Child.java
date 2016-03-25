package com.vintek_ss.vince.kiddocare;

/**
 * Created by gvincent on 3/24/16.
 */
public class Child {
    int number;
    int pic;
    String first_name;
    String last_name;
    String birth_date;
    String enroll_date;
    String address_ln_1;
    String address_city;
    String address_state;
    String address_zip;

    public Child(int number, int pic, String first_name, String last_name, String birth_date,
                 String enroll_date, String address_ln_1, String address_city,
                 String address_state, String address_zip) {

        this.number = number;
        this.pic = pic;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.enroll_date = enroll_date;
        this.address_ln_1 = address_ln_1;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
    }
}
