package com.vintek_ss.vince.kiddocare;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MealActivity extends Activity {

    private static int ID = 0;
    public int iNULL, iEMPTY;
    List<String> groupList;
    List<String> breakfastItems;
    List<String> childList;
    ArrayList<String> LunchItems;
    ArrayList<String> PMSnackItems;
    ArrayList<String> AMSnackItems;
    Map<String, List<String>> foodItemsList;
    ExpandableListView expListView;
    ArrayList<String> DinnerItems;
    ArrayList<String> NightPMSnackItems;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        createGroupList();
        crateFoodCollection();

        TextView textView1 = (TextView) findViewById(R.id.tv_newOrFromDB);
        TextView textView2 = (TextView) findViewById(R.id.tv_MPdate);
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));

        //change based on if MP exists in db
        //textView1.setText(SselectedDate);
        textView2.setText(SselectedDate);

        expListView = (ExpandableListView) findViewById(R.id.elv_MealPlan_Selections);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, foodItemsList);
        expListView.setAdapter(expListAdapter);
        setGroupIndicatorToRight();
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Breakfast");
        groupList.add("AM Snack");
        groupList.add("Lunch");
        groupList.add("PM Snack");
        groupList.add("Dinner");
        groupList.add("Night Snack");
    }

    private void crateFoodCollection() {
        ////////////////////////////////////////////////////////////////
        //////// HERE GET CURRENT MEAL PLAN, IF NONE CREATE NEW/////////
        ////////////////////////////////////////////////////////////////

        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        daycaremanagerDB db = new daycaremanagerDB(getApplicationContext());
        db.open();
        breakfastItems = new ArrayList<String>();
        AMSnackItems = new ArrayList<String>();
        LunchItems = new ArrayList<String>();
        PMSnackItems = new ArrayList<String>();
        DinnerItems = new ArrayList<String>();
        NightPMSnackItems = new ArrayList<String>();

        breakfastItems = db.getMPbreakfastItems(SselectedDate);
        AMSnackItems = db.getMPAMSnackItems(SselectedDate);
        LunchItems = db.getMPLunchItems(SselectedDate);
        PMSnackItems = db.getMPPMSnackItems(SselectedDate);
        DinnerItems = db.getMPDinnerItems(SselectedDate);
        NightPMSnackItems = db.getMPNightPMSnackItems(SselectedDate);
        db.close();
        foodItemsList = new LinkedHashMap<String, List<String>>();
        for (String MPFood : groupList) {
            if (MPFood.equals("Breakfast")) {
                iNULL = 0;
                iEMPTY = 0;
                loadChild(breakfastItems, MPFood);
            } else if (MPFood.equals("AM Snack")) {
                iNULL = 0;
                iEMPTY = 0;
                loadChild(AMSnackItems, MPFood);
            } else if (MPFood.equals("Lunch")) {
                iNULL = 0;
                iEMPTY = 0;
                loadChild(LunchItems, MPFood);
            } else if (MPFood.equals("PM Snack")) {
                iNULL = 0;
                iEMPTY = 0;
                loadChild(PMSnackItems, MPFood);
            } else if (MPFood.equals("Dinner")) {
                iNULL = 0;
                iEMPTY = 0;
                loadChild(DinnerItems, MPFood);
            } else if (MPFood.equals("Night Snack")) {
                iNULL = 0;
                iEMPTY = 0;
                loadChild(NightPMSnackItems, MPFood);
            }
            foodItemsList.put(MPFood, childList);
        }
    }

    private void loadChild(List<String> foodItems, String MPFood) {
        childList = new ArrayList<String>();
        try {
            for (String childItems : foodItems) {
                if (childItems == null) {
                    if (iNULL <= 0) {
                        iNULL = iNULL + 1;
                    } else {
                    }
                }
                if (childItems.equals("")) {
                    if (iEMPTY <= 0) {
                        iEMPTY = iEMPTY + 1;
                    } else {
                    }
                } else {
                    childList.add(childItems);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_newMP) {
            Intent i = new Intent(this, NewMealActivity.class);
            Bundle bundle = new Bundle();
            // Get date and format it
            TextView textView2 = (TextView) findViewById(R.id.tv_MPdate);
            String SselectedDate = textView2.getText().toString();
            bundle.putString("dateS", SselectedDate);
            i.putExtras(bundle);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pickDate(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        final Dialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;

                if (ID == (R.id.tv_MPdate)) {
                    TextView tv = (TextView) findViewById(R.id.tv_MPdate);
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
                }
            }
        }, mYear, mMonth, mDay);
        dateDialog.show();
        ID = v.getId();
    }
}
