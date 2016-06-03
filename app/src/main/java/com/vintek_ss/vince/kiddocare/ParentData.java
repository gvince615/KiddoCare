package com.vintek_ss.vince.kiddocare;

/**
 * Created by gvincent on 3/24/16.
 */
public class ParentData {
    String first_name;
    String last_name;
    String email;
    String phoneNumber;
    String address_ln_1;
    String address_city;
    String address_state;
    String address_zip;

    public ParentData(String first_name, String last_name, String email,
                      String phoneNumber, String address_ln_1, String address_city,
                      String address_state, String address_zip) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address_ln_1 = address_ln_1;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
    }

}
