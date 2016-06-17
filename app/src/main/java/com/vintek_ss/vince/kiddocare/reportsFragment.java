package com.vintek_ss.vince.kiddocare;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class reportsFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Context context;


    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static reportsFragment newInstance(int sectionNumber) {
        reportsFragment fragment = new reportsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Context getContext() {
        context = getActivity().getApplicationContext();
        return context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reports, container,
                false);
        TextView attenTV = (TextView) rootView.findViewById(R.id.tv_Atten_Rec);
        TextView regTV = (TextView) rootView.findViewById(R.id.tv_reg_records);
        TextView oth = (TextView) rootView.findViewById(R.id.tv_parent_rec);
//        populateAttenData(attenTV);
//        populateRegData(regTV);
        populateParentData(oth);
        return rootView;
    }

//    public void populateAttenData(TextView tv) {
//        daycaremanagerDB db = new daycaremanagerDB(getContext());
//        db.open();
//        Cursor c = db.get_ALL_AttenData();
//        String title = ("Attendance Records\n");
//        String output = (title);
//        while (c.moveToNext()) {
//            String record = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_ROWID));
//            String child = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_CHILD));
//            String dateIN = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_TIME_IN));
//            //String timeIN = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_TIME_IN1));
//            //String timeOUT = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_TIME_OUT1));
//            String dateOUT = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_TIME_OUT));
//            if (dateOUT == null) {
//
//                dateOUT = ("Currently Signed in");
//            }
//            if (c.isLast()) {
//                output = output + record + " :\t" + child + "\n"
//                        + dateIN + " |\t" + dateOUT;
//            } else {
//                output = output + record + " :\t" + child + "\n"
//                        + dateIN + " |\t" + dateOUT + "\n";
//            }
//        }
//        tv.setText(output);
//        db.close();
//    }

//    public void populateRegData(TextView tv) {
//        daycaremanagerDB db = new daycaremanagerDB(getContext());
//        db.open();
//        Cursor c = db.get_ALL_RegData();
//        String title = ("Registration Records\n");
//        String output = (title);
//        while (c.moveToNext()) {
//            String record = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID));
//            String fname = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME));
//            String lname = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME));
//            String edate = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_EDATE));
//            String age = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE));
//            String incare = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_INCARE));
//
//            if (c.isLast()) {
//                output = output + record + " :\t" + fname + "\t" + lname + "\n"
//                        + edate + " |\t" + age + " |\t " + incare;
//            } else {
//                output = output + record + " :\t" + fname + " " + lname + "\n"
//                        + edate + " |\t" + age + " |\t " + incare + "\n";
//            }
//        }
//        tv.setText(output);
//        db.close();
//    }

    public void populateParentData(TextView tv) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String upass = sharedPrefs.getString("prefUserPassword", "NOPASSWORD");
        String billAmountHR = sharedPrefs.getString("prefBilling_Hourly", "2.00");

        String title = ("Preferences\n");
        String output = (title);
        output = output + "password : " + upass + " | amount per hr : " + billAmountHR + "";

        tv.setText(output);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
}
