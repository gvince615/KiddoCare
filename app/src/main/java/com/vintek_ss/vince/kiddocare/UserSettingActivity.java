package com.vintek_ss.vince.kiddocare;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by VINCE-LTPC on 12/30/2014.
 */
public class UserSettingActivity extends PreferenceActivity {
    Preference.OnPreferenceChangeListener billingOption = new Preference.OnPreferenceChangeListener() {

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean isEnabled = true;
            /*if (newValue == true){
                Toast.makeText(getApplicationContext(),
                        "TRUE", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),
                        "NOT TRUE", Toast.LENGTH_SHORT).show();
            }*/
            return isEnabled;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // add the xml resource
        addPreferencesFromResource(R.xml.user_settings);


    }
}
