package com.vintek_ss.vince.kiddocare;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.vintek_ss.vince.kiddocare.R.color.green;
import static com.vintek_ss.vince.kiddocare.R.color.transparent;

public class AttendanceActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private static final int SETTINGS_RESULT = 1;
    private static int ID = 0;
    public ArrayList<Integer> al = new ArrayList<Integer>();
    public List<customAdapterEnrolled> childList = new ArrayList<customAdapterEnrolled>();
    Spinner spinner;
    int attenRow = 0;
    boolean ChildINcare = false;
    private ViewFlipper viewFlipper;
    private float lastX;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        spinner = (Spinner) findViewById(R.id.s_reg_children);

        populateList();
        loadDataIntoListView();
        loadSpinnerData();

        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long arg3) {
                Integer value = al.get(position);
                childClickedCIn(position);
            }

        };
        ListView list = (ListView) findViewById(R.id.lv_enrolledchild_checkin);
        list.setOnItemClickListener(listener);
        spinner.setOnItemSelectedListener(this);
    }

    private void loadSpinnerData() {
        // database handler
        daycaremanagerDB db = new daycaremanagerDB(getApplicationContext());
        db.open();
        // Spinner Drop down elements
        List<String> children = db.getAllChildren();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, children);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        db.close();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        if (position == 0) {

        } else {

            String selectedChild = spinner.getSelectedItem().toString();

            daycaremanagerDB dbAtten = new daycaremanagerDB(getApplicationContext());
            dbAtten.open();
            Cursor c = dbAtten.get_Child_AttenData(selectedChild);
            c.moveToLast();


            String clockIN = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_IN));
            String clockOUT = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_OUT));
            //attenRow = c.getInt(c.getColumnIndex(daycaremanagerDB.KEY_ATTEN_ROWID));


            TextView timeIN = (TextView) findViewById(R.id.et_attenTimeIn);
            TextView dateIN = (TextView) findViewById(R.id.et_attenDateIn);
            TextView timeOUT = (TextView) findViewById(R.id.et_attenTimeOut);
            TextView dateOUT = (TextView) findViewById(R.id.et_attenDateOut);
            if (!TextUtils.isEmpty(clockIN) && !TextUtils.isEmpty(clockOUT)) {
                ChildINcare = false;
            }
            if (TextUtils.isEmpty(clockOUT) && !TextUtils.isEmpty(clockIN)) {
                String date = "";
                String time = "";
                String[] splitDandT = clockIN.split("\\s+");
                date = splitDandT[0];
                time = splitDandT[1] + " " + splitDandT[2];


                dateIN.setText(date);
                timeIN.setText(time);
                ChildINcare = true;

            } else {
                dateIN.setText("Select Date");
                timeIN.setText("Select Time");
                dateOUT.setText("Select Date");
                timeOUT.setText("Select Time");
            }

            dbAtten.close();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void SaveUpdateAtten(View v) {
        String selectedChild = spinner.getSelectedItem().toString();
        String[] splitFandL = selectedChild.split("\\s+");
        String cfn = splitFandL[0];
        String cln = splitFandL[1];

        TextView TimeIN = (TextView) findViewById(R.id.et_attenTimeIn);
        String timeIN = TimeIN.getText().toString();
        TextView DateIN = (TextView) findViewById(R.id.et_attenDateIn);
        String dateIN = DateIN.getText().toString();
        String clockINDate = dateIN + " " + timeIN;

        TextView TimeOUT = (TextView) findViewById(R.id.et_attenTimeOut);
        String timeOUT = TimeOUT.getText().toString();
        TextView DateOUT = (TextView) findViewById(R.id.et_attenDateOut);
        String dateOUT = DateOUT.getText().toString();
        String clockOUTDate = dateOUT + " " + timeOUT;

        if (TextUtils.isEmpty(dateIN) && TextUtils.isEmpty(dateOUT) || TextUtils.isEmpty(timeOUT)
                && TextUtils.isEmpty(timeIN)) {

            TimeOUT.setBackgroundResource(R.drawable.red_box);
            TimeIN.setBackgroundResource(R.drawable.red_box);
            DateOUT.setBackgroundResource(R.drawable.red_box);
            DateIN.setBackgroundResource(R.drawable.red_box);


            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this,
                    "Missing date/time Info\nDate and Time are required for IN or OUT", duration);
            toast.show();
        } else {
            ///////////////////////////////////////////////// String cIn = String.valueOf(attenRow);

            daycaremanagerDB dbAtten = new daycaremanagerDB(getApplicationContext());
            dbAtten.open();
            String result = "";
            String cIn = dbAtten.getChildRecordNum(cfn, cln);
            if (ChildINcare == true) {
                result = dbAtten.updateAttenEntry(cIn, cfn, cln, clockOUTDate);
            } else {
                result = dbAtten.createAttenNEWEntry(cIn, cfn, cln, clockINDate, clockOUTDate);
            }
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_SHORT).show();
            dbAtten.close();
        }
    }

    public void childClickedCIn(int position) {

        ListView list = (ListView) findViewById(R.id.lv_enrolledchild_checkin);
        daycaremanagerDB AttenEntry = new daycaremanagerDB(this);
        AttenEntry.open();
        String r = "";
        //// Get Data
        TextView childFirst = (TextView) list.getChildAt(position).findViewById(R.id.tv_cfirst);
        String cfn = childFirst.getText().toString();
        TextView childLast = (TextView) list.getChildAt(position).findViewById(R.id.tv_clast);
        String cln = childLast.getText().toString();
        TextView childnum = (TextView) list.getChildAt(position).findViewById(R.id.tv_recordnum);

        //String childFullName = cfn + "" + cln;
        String cIn = AttenEntry.getChildRecordNum(cfn, cln);

        String isLoggedIn = AttenEntry.getChildNameINCARE(cIn, cfn, cln);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MMM-d h:mm a");

        //SimpleDateFormat time = new SimpleDateFormat("HH:mm a");
        String clockDate = date.format(c.getTime());
        //String clockTime = time.format(c.getTime());
        String n = "NO";
        String y = "YES";
        // child is logged in NEED TO LOG CHILD OUT
        if (isLoggedIn.equals(y)) {
            r = AttenEntry.updateAttenEntry(cIn, cfn, cln, clockDate);
            list.getChildAt(position).setBackgroundColor(getResources().getColor(transparent));
        }
        // child logged out NEED TO LOG CHILD IN
        if (isLoggedIn.equals(n)) {
            r = AttenEntry.createAttenEntry(cIn, cfn, cln, clockDate);

            list.getChildAt(position).setBackgroundColor(getResources().getColor(green));
        }
        Toast.makeText(getApplicationContext(), r,
                Toast.LENGTH_SHORT).show();
        AttenEntry.close();
    }

    private void loadDataIntoListView() {

        ArrayAdapter<customAdapterEnrolled> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.lv_enrolledchild_checkin);
        list.setAdapter(adapter);

    }

    private void populateList() {
        daycaremanagerDB childInfo = new daycaremanagerDB(this);
        childInfo.open();

        Cursor cursor = childInfo.getCursorChildList();

        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED!0 Runs on the UI thread, OK for small/short queries.
        // startManagingCursor(cursor);
        int count = cursor.getCount();

        if (count >= 0) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {

                    String childRecord = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID));
                    int childRecInt = cursor.getInt(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID));
                    al.add(childRecInt);

                    String childFname = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME));
                    String childLname = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME));
                    String childEdate = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_EDATE));
                    String childActive = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE));

                    byte[] childPIC = cursor.getBlob(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_PIC));
                    Bitmap bmChildPIC = BitmapFactory.decodeByteArray(childPIC, 0,
                            childPIC.length);

                    String isLoggedIn = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_INCARE));

                    childList.add(new customAdapterEnrolled(childRecord,
                            childFname, childLname, childEdate, childActive,
                            bmChildPIC, isLoggedIn));

                    cursor.moveToNext();

                }
            }
        }

        if (count < 1) {
            Toast.makeText(getApplicationContext(), "No Children Enrolled",
                    Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        childInfo.close();
    }

    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void pickDate(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        Dialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String newMonth = "";
                monthOfYear++;
                switch (monthOfYear) {
                    case 1:
                        newMonth = "Jan";
                        break;
                    case 2:
                        newMonth = "Feb";
                        break;
                    case 3:
                        newMonth = "Mar";
                        break;
                    case 4:
                        newMonth = "Apr";
                        break;
                    case 5:
                        newMonth = "May";
                        break;
                    case 6:
                        newMonth = "Jun";
                        break;
                    case 7:
                        newMonth = "Jul";
                        break;
                    case 8:
                        newMonth = "Aug";
                        break;
                    case 9:
                        newMonth = "Sep";
                        break;
                    case 10:
                        newMonth = "Oct";
                        break;
                    case 11:
                        newMonth = "Nov";
                        break;
                    case 12:
                        newMonth = "Dec";
                        break;
                }
                if (ID == (R.id.et_attenDateIn)) {
                    TextView tv = (TextView) findViewById(R.id.et_attenDateIn);
                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth);
                }
                if (ID == (R.id.et_attenDateOut)) {
                    TextView tv = (TextView) findViewById(R.id.et_attenDateOut);
                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth);
                }
            }
        }, mYear, mMonth, mDay);
        dateDialog.show();
        ID = v.getId();
    }

    public void pickTime(View v) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);

        Dialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String strI = "";

                if (ID == (R.id.et_attenTimeIn)) {
                    TextView tv = (TextView) findViewById(R.id.et_attenTimeIn);
                    String am_or_pm = "";
                    if (minute <= 9) {
                        strI = "0" + minute;
                    } else {
                        strI = "" + minute;
                    }
                    if (hourOfDay >= 13 && hourOfDay < 24) {
                        int newHr = hourOfDay - 12;
                        am_or_pm = "pm";
                        tv.setText(newHr + ":" + strI + " " + am_or_pm);
                    }
                    if (hourOfDay == 12) {
                        am_or_pm = "pm";
                        tv.setText(hourOfDay + ":" + strI + " " + am_or_pm);
                    }
                    if (hourOfDay == 0) {
                        am_or_pm = "am";
                        String strIHr = "12";
                        tv.setText(strIHr + ":" + strI + " " + am_or_pm);
                    }
                    if (hourOfDay <= 11 && hourOfDay != 0) {
                        am_or_pm = "am";

                        tv.setText(hourOfDay + ":" + strI + " " + am_or_pm);
                    }
                }
                if (ID == (R.id.et_attenTimeOut)) {
                    TextView tv = (TextView) findViewById(R.id.et_attenTimeOut);
                    String am_or_pm = "";
                    if (minute <= 9) {
                        strI = "0" + minute;
                    } else {
                        strI = "" + minute;
                    }
                    if (hourOfDay >= 13 && hourOfDay < 24) {
                        int newHr = hourOfDay - 12;
                        am_or_pm = "pm";
                        tv.setText(newHr + ":" + strI + " " + am_or_pm);
                    }
                    if (hourOfDay == 12) {
                        am_or_pm = "pm";
                        tv.setText(hourOfDay + ":" + strI + " " + am_or_pm);
                    }
                    if (hourOfDay == 0) {
                        am_or_pm = "am";
                        String strIHr = "12";
                        tv.setText(strIHr + ":" + strI + " " + am_or_pm);
                    }
                    if (hourOfDay <= 11 && hourOfDay != 0) {
                        am_or_pm = "am";

                        tv.setText(hourOfDay + ":" + strI + " " + am_or_pm);
                    }

                }
            }
        }, mHour, mMinute, false);
        timeDialog.show();
        ID = v.getId();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper2);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, UserSettingActivity.class);
            startActivityForResult(intent, SETTINGS_RESULT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SETTINGS_RESULT) {
            //displayUserSettings();
        }

    }

    // Using the following method, we will handle all screen swaps.
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();

                // Handling left to right screen swap.
                if (lastX < currentX) {

                    // If there aren't any other children, just break.
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // Next screen comes in from left.
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
                    // Current screen goes out from right.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);

                    // Display next screen.
                    viewFlipper.showNext();
                }

                // Handling right to left screen swap.
                if (lastX > currentX) {

                    // If there is a child (to the left), kust break.
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;

                    // Next screen comes in from right.
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
                    // Current screen goes out from left.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);

                    // Display previous screen.
                    viewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }

    private class MyListAdapter extends ArrayAdapter<customAdapterEnrolled> {
        public MyListAdapter() {
            super(AttendanceActivity.this, R.layout.listview_row,
                    childList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)

            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.listview_row,
                        parent, false);
            }
            // Find the car to work with.
            customAdapterEnrolled currentChild = childList.get(position);

            // Fill the view
            ImageView imageView = (ImageView) itemView
                    .findViewById(R.id.iv_childpic);
            imageView.setImageBitmap(currentChild.getChildPIC());

            TextView fname = (TextView) itemView.findViewById(R.id.tv_cfirst);
            fname.setText(currentChild.getChildFNAME());

            TextView lname = (TextView) itemView.findViewById(R.id.tv_clast);
            lname.setText(currentChild.getChildLNAME());

            TextView enrolled = (TextView) itemView.findViewById(R.id.tv_dateEnrolled);
            enrolled.setText(currentChild.getchildEdate());

            TextView active = (TextView) itemView.findViewById(R.id.tv_isActive);
            active.setText(currentChild.getchildActive());

            TextView recNum = (TextView) itemView.findViewById(R.id.tv_recordnum);
            recNum.setText(currentChild.getchildRecord());

            TextView logIn = (TextView) itemView.findViewById(R.id.tv_loggedIn);
            logIn.setText(currentChild.childLoggedIn());
            String n = "NO";
            String y = "YES";
            if (currentChild.childLoggedIn().equals(y)) {

                itemView.setBackgroundColor(getResources().getColor(green));
            }
            if (currentChild.childLoggedIn().equals(n)) {

                itemView.setBackgroundColor(getResources().getColor(transparent));
            }
            return itemView;

        }
    }
}
