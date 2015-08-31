package com.vintek_ss.vince.kiddocare;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class NewMealActivity extends Activity implements ActionBar.TabListener {
    public static android.content.Context getContext;
    public static String selectedView = null;
    public static TextView tv = null;
    private static int ID = 0;
    private static int viewID = 0;
    final Context context = this;
    String item = null;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Bundle bundle = getIntent().getExtras();
        String selectedDay = bundle.getString("dateS");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    private void saveNewMPTAB(ActionBar.Tab tab) {
        Bundle bundle = getIntent().getExtras();
        String selectedDay = bundle.getString("dateS");
        int selecTab = tab.getPosition();
        daycaremanagerDB db = new daycaremanagerDB(getApplicationContext());
        db.open();
        switch (selecTab) {
            case 0:
                TextView B_MealName = (TextView) findViewById(R.id.tv_B_MealName);
                TextView B_drink = (TextView) findViewById(R.id.tv_B_Drink);
                TextView B_bread = (TextView) findViewById(R.id.tv_B_Bread);
                TextView Bforv2 = (TextView) findViewById(R.id.tv_B_ForV2);
                TextView BamS1 = (TextView) findViewById(R.id.tv_AMS_1);
                TextView BamS2 = (TextView) findViewById(R.id.tv_AMS_2);
                String sBmealName = B_MealName.getText().toString();
                String sBdrink = B_drink.getText().toString();
                String sBbread = B_bread.getText().toString();
                String sBforv2 = Bforv2.getText().toString();
                String sBamS1 = BamS1.getText().toString();
                String sBamS2 = BamS2.getText().toString();
                db.saveNewMealPlanB(selectedDay, sBmealName, sBdrink, sBbread, sBforv2, sBamS1,
                        sBamS2);
                db.close();
                Toast.makeText(getApplicationContext(), "Breakfast Meal Plan Saved! ",
                        Toast.LENGTH_SHORT).show();
                break;
            case 1:
                TextView L_MealName = (TextView) findViewById(R.id.tv_L_MealName);
                TextView L_drink = (TextView) findViewById(R.id.tv_L_Drink);
                TextView L_bread = (TextView) findViewById(R.id.tv_L_Bread);
                TextView Lforv1 = (TextView) findViewById(R.id.tv_L_ForV1);
                TextView Lforv2 = (TextView) findViewById(R.id.tv_L_ForV2);
                TextView LpmS1 = (TextView) findViewById(R.id.tv_PMS_1);
                TextView LpmS2 = (TextView) findViewById(R.id.tv_PMS_2);
                TextView Lprotein = (TextView) findViewById(R.id.tv_L_Protein);
                String sLmealName = L_MealName.getText().toString();
                String sLdrink = L_drink.getText().toString();
                String sLbread = L_bread.getText().toString();
                String sLforv1 = Lforv1.getText().toString();
                String sLforv2 = Lforv2.getText().toString();
                String sLpmS1 = LpmS1.getText().toString();
                String sLpmS2 = LpmS2.getText().toString();
                String sLpro = Lprotein.getText().toString();
                db.saveNewMealPlanL(selectedDay, sLmealName, sLdrink, sLbread, sLforv1,
                        sLforv2, sLpro, sLpmS1, sLpmS2);
                db.close();
                Toast.makeText(getApplicationContext(), "Lunch Meal Plan Saved! ",
                        Toast.LENGTH_SHORT).show();
                break;
            case 2:
                TextView D_MealName = (TextView) findViewById(R.id.tv_D_MealName);
                TextView D_drink = (TextView) findViewById(R.id.tv_D_Drink);
                TextView D_bread = (TextView) findViewById(R.id.tv_D_Bread);
                TextView Dforv1 = (TextView) findViewById(R.id.tv_D_ForV1);
                TextView Dforv2 = (TextView) findViewById(R.id.tv_D_ForV2);
                TextView DpmS1 = (TextView) findViewById(R.id.tv_DMS_1);
                TextView DpmS2 = (TextView) findViewById(R.id.tv_DMS_2);
                TextView Dprotein = (TextView) findViewById(R.id.tv_D_Protein);
                String sDmealName = D_MealName.getText().toString();
                String sDdrink = D_drink.getText().toString();
                String sDbread = D_bread.getText().toString();
                String sDforv1 = Dforv1.getText().toString();
                String sDforv2 = Dforv2.getText().toString();
                String sDpmS1 = DpmS1.getText().toString();
                String sDpmS2 = DpmS2.getText().toString();
                String sDpro = Dprotein.getText().toString();
                db.saveNewMealPlanD(selectedDay, sDmealName, sDdrink, sDbread, sDforv1,
                        sDforv2, sDpro, sDpmS1, sDpmS2);
                db.close();
                Toast.makeText(getApplicationContext(), "Dinner Meal Plan Saved! ",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void saveNewMP() {
        ActionBar tab = getActionBar();
        ActionBar.Tab selected = tab.getSelectedTab();
        saveNewMPTAB(selected);
    }

    public void createAlert(View v) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.fooditemdialog, null);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.et_MPFoodDialogNewItem);
        final Spinner spinner = (Spinner) promptsView.findViewById(R.id.s_MPDialog_FoodItems);

        viewID = v.getId();

        switch (viewID) {
            /////////// breakfast
            case R.id.tv_B_MealName:
                tv = (TextView) findViewById(R.id.tv_B_MealName);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_B_Drink:
                tv = (TextView) findViewById(R.id.tv_B_Drink);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_B_Bread:
                tv = (TextView) findViewById(R.id.tv_B_Bread);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_B_ForV2:
                tv = (TextView) findViewById(R.id.tv_B_ForV2);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_AMS_1:
                tv = (TextView) findViewById(R.id.tv_AMS_1);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_AMS_2:
                tv = (TextView) findViewById(R.id.tv_AMS_2);
                selectedView = tv.getHint().toString();
                break;
            ////////////lunch
            case R.id.tv_L_MealName:
                tv = (TextView) findViewById(R.id.tv_L_MealName);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_L_Drink:
                tv = (TextView) findViewById(R.id.tv_L_Drink);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_L_Bread:
                tv = (TextView) findViewById(R.id.tv_L_Bread);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_L_ForV1:
                tv = (TextView) findViewById(R.id.tv_L_ForV1);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_L_ForV2:
                tv = (TextView) findViewById(R.id.tv_L_ForV2);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_L_Protein:
                tv = (TextView) findViewById(R.id.tv_L_Protein);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_PMS_1:
                tv = (TextView) findViewById(R.id.tv_PMS_1);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_PMS_2:
                tv = (TextView) findViewById(R.id.tv_PMS_2);
                selectedView = tv.getHint().toString();
                break;
            ///////////Dinner
            case R.id.tv_D_MealName:
                tv = (TextView) findViewById(R.id.tv_D_MealName);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_D_Drink:
                tv = (TextView) findViewById(R.id.tv_D_Drink);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_D_Bread:
                tv = (TextView) findViewById(R.id.tv_D_Bread);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_D_ForV1:
                tv = (TextView) findViewById(R.id.tv_D_ForV1);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_D_ForV2:
                tv = (TextView) findViewById(R.id.tv_D_ForV2);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_D_Protein:
                tv = (TextView) findViewById(R.id.tv_D_Protein);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_DMS_1:
                tv = (TextView) findViewById(R.id.tv_DMS_1);
                selectedView = tv.getHint().toString();
                break;
            case R.id.tv_DMS_2:
                tv = (TextView) findViewById(R.id.tv_DMS_2);
                selectedView = tv.getHint().toString();
                break;
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        //String selectedView = ;
        final String selected = null;
        buildMPitemspinner(selectedView, spinner);
        // set dialog message

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String usersInput = userInput.getText().toString();
                                String spinItem = spinner.getSelectedItem().toString();

                                if (usersInput.isEmpty() && spinItem.equals("Select an Item...")) {
                                    Toast.makeText(getActivity(),
                                            //Must do one or the other...
                                            "Must Select an Item OR Input an Item", Toast.LENGTH_SHORT).show();
                                }
                                if (!spinItem.equals("Select an Item...")) {
                                    item = spinItem.toString();
                                    tv.setText(item);
                                    Toast.makeText(getActivity(),
                                            item, Toast.LENGTH_SHORT).show();
                                }
                                if (!usersInput.isEmpty()) {


                                    // Select This Item
                                    item = usersInput.toString();

                                    if (selectedView.equals("Select Any Food Item 1") ||
                                            selectedView.equals("Select Any Food Item 2")) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setTitle("Add Food Item");
                                        builder.setMessage("Enter a Category for food Item :");
                                        final Spinner FoodCategory = new Spinner(getActivity());
                                        List<String> list = new ArrayList<String>();
                                        list.add("Drink");
                                        list.add("Bread");
                                        list.add("Fruits & Veggies");
                                        list.add("Protein");
                                        ArrayAdapter<String> dataAdapter =
                                                new ArrayAdapter<String>(getActivity(),
                                                        android.R.layout.simple_spinner_item, list);
                                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        FoodCategory.setAdapter(dataAdapter);
                                        builder.setView(FoodCategory);
                                        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //do stuff...
                                                String foodCat = FoodCategory.getSelectedItem().toString();

                                                if (foodCat.equals("Drink")) {
                                                    selectedView = "Select Drink Item";
                                                }
                                                if (foodCat.equals("Bread")) {
                                                    selectedView = "Select Bread Item";
                                                }
                                                if (foodCat.equals("Fruits & Veggies")) {
                                                    selectedView = "Select Fruit or Veggie Item 1";
                                                }
                                                if (foodCat.equals("Protein")) {
                                                    selectedView = "Select Protein Item";
                                                }
                                                daycaremanagerDB db = new daycaremanagerDB(getApplicationContext());
                                                db.open();
                                                String r = db.putIteminDB(item, selectedView);
                                                tv.setText(item);
                                                db.close();

                                            }
                                        });
                                        builder.setNegativeButton("Cancel", null);
                                        builder.create().show();
                                    }

                                    // Put in DB
                                    daycaremanagerDB db = new daycaremanagerDB(getApplicationContext());
                                    db.open();
                                    String r = db.putIteminDB(usersInput, selectedView);

                                    Toast.makeText(getActivity(),
                                            r, Toast.LENGTH_SHORT).show();
                                    tv.setText(item);
                                    db.close();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                Toast.makeText(getActivity(),
                                        "Canceled", Toast.LENGTH_SHORT).show();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }


    public Context getActivity() {
        return this;
    }

    public void pickDatenewMP(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        final Dialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;

//                if (ID == (R.id.tv_selectedDateMP)) {
//                    TextView tv = (TextView) findViewById(R.id.tv_selectedDateMP);
//                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
//                }
            }
        }, mYear, mMonth, mDay);
        dateDialog.show();
        ID = v.getId();
    }

    private void buildMPitemspinner(String selected, Spinner spinner) {
        // database handler

        daycaremanagerDB db = new daycaremanagerDB(getApplicationContext());
        db.open();
        // Spinner Drop down elements
        List<String> foodItems = db.getfoodItems(selected);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, foodItems);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_saveMP) {
            saveNewMP();
            //////// Save it to DB!!!!!!

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        saveNewMPTAB(tab);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A fragment for breakfast/am snack containing a simple view.
     */
    public static class breakfastAMsnackFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public breakfastAMsnackFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static breakfastAMsnackFragment newInstance(int sectionNumber) {
            breakfastAMsnackFragment fragment = new breakfastAMsnackFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_breakfast_amsnack, container, false);
            return rootView;
        }
    }

    /**
     * A fragment for lunch/pm snack containing a simple view.
     */
    public static class lunchPMsnackFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public lunchPMsnackFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static lunchPMsnackFragment newInstance(int sectionNumber) {
            lunchPMsnackFragment fragment = new lunchPMsnackFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lunch_pmsnack, container, false);
            return rootView;
        }
    }

    /**
     * A fragment for dinner containing a simple view.
     */
    public static class dinnerFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public dinnerFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static dinnerFragment newInstance(int sectionNumber) {
            dinnerFragment fragment = new dinnerFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dinner, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return breakfastAMsnackFragment.newInstance(position + 1);
                case 1:
                    return lunchPMsnackFragment.newInstance(position + 1);
                case 2:
                    return dinnerFragment.newInstance(position + 1);
            }
            return null;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_MPsection1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_MPsection2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_MPsection3).toUpperCase(l);
            }
            return null;
        }
    }

}
