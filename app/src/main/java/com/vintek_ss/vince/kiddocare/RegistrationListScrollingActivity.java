package com.vintek_ss.vince.kiddocare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.vintek_ss.vince.kiddocare.R.mipmap.ic_launcher_new;

//import android.support.design.widget.CollapsingToolbarLayout;

public class RegistrationListScrollingActivity extends AppCompatActivity {

    public static final int CHILD = 0;
    public static final int PARENT = 1;
    public static final int MEDICAL = 2;
    public static final int MEDICATION = 3;
    public static final int DISCOUNT = 4;
    final static int cameraData = 0;
    private static final String SAVED_LAYOUT_MANAGER = "SAVED_RV_LAYOUT_MANAGER";
    private static int ID = 0;
    final String cItems[] = {"Generic Boy Image", "Generic Girl Image", "Take Picture"};
    Bitmap bmp;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ChildRegistrationRVAdapter adapter;
    LinearLayoutManager llm;
    AppBarLayout appbar;
    ImageView childImage;
    RecyclerView rv_RegistrationData;
    FloatingActionButton fab_take_pic, fab_add_card;
    ArrayList<Object> items = new ArrayList<>();
    String mCollapsedTitle = "KateLynn Vincent";
    String mExpandedTitle = "KateLynn Vincent";
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int datasetTypes[] = {CHILD, PARENT, MEDICAL, MEDICATION, DISCOUNT}; //view types

    private List<ChildData> childDataCard;
    private List<ParentData> parentCard;
    private int newChildNumber;
    private Parcelable mListState;
    private Parcelable layoutManagerSavedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_edit_activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        childImage = (ImageView) findViewById(R.id.iv_child_image);

        collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        toolbar.setTitle(mExpandedTitle);

        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.START);
        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.END);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.black));

        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if(verticalOffset >= -480){
                    collapsingToolbarLayout.setTitle(mExpandedTitle);
                }else if(!toolbar.getTitle().equals(mCollapsedTitle)&& verticalOffset <= 400){
                    collapsingToolbarLayout.setTitle(mCollapsedTitle);
                }
            }
        });

        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_RegistrationData = (RecyclerView)findViewById(R.id.rv_registration_data_list);
        llm = new LinearLayoutManager(this);
        rv_RegistrationData.setLayoutManager(llm);


        fab_take_pic = (FloatingActionButton) findViewById(R.id.fab_take_child_picture);
        fab_take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePic();
            }
        });

        fab_add_card = (FloatingActionButton) findViewById(R.id.fab_add_card);
        fab_add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickACardToAdd();
            }
        });
        //int childNumber = 0;
        initializeAdapter();
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                //childNumber = bundle.getInt(RegisteredListScrollingActivity.REGISTERED_CHILD_DATA);
            } else {
                loadBackdrop();
                initializeData();
            }
        } else {
            //childNumber = savedInstanceState.getInt(RegisteredListScrollingActivity.REGISTERED_CHILD_DATA);
            loadBackdrop();
            initializeData();
            super.onRestoreInstanceState(savedInstanceState);
        }



    }

    private void pickACardToAdd() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(RegistrationListScrollingActivity.this);
        builderSingle.setIcon(R.mipmap.ic_launcher_new);
        builderSingle.setTitle("Select A Card Type To Add:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                RegistrationListScrollingActivity.this,
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Guardian Data Card");
        arrayAdapter.add("Shot Record Data Card");
        arrayAdapter.add("Medication Data Card");
        arrayAdapter.add("Discount Data Card");

        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);

                        switch (strName){
                            case "Guardian Data Card":
                                items.add(new ParentData("", "", "", "", "", true, "", "", "", "", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                            case "Shot Record Data Card":
                                Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                                        R.mipmap.ic_shot_record);
                                items.add(new ShotRecordData(image, "", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                            case "Medication Data Card":
                                items.add(new MedicationData("", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                            case "Discount Data Card":
                                items.add(new DiscountData("", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                        }
                        Snackbar.make(fab_add_card, strName + " Added", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
        builderSingle.show();
    }

    private void initializeData(){
        getSampleArrayList();
    }

    private ArrayList<Object> getSampleArrayList() {
        Bitmap image = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.ic_launcher_new);

        items.add(new ChildData(0, image, "", "", "", "", "", "", "", "", ""));
        return items;
    }

    private void initializeAdapter(){

        adapter = new ChildRegistrationRVAdapter(items);
        rv_RegistrationData.setAdapter(adapter);
    }

    public void pickTime(View v) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);

        Dialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String strI = "";
                if (ID == (R.id.et_medication_time)) {
                    TextView tv = (TextView) findViewById(R.id.et_medication_time);
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

    public void pickDate(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        Dialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                TextView tv = null;

                if (ID == (R.id.tv_Cbirthdate)) {
                    tv = (TextView) findViewById(R.id.tv_Cbirthdate);
                }
                if (ID == (R.id.et_child_Edate)) {
                    tv = (TextView) findViewById(R.id.et_child_Edate);
                }
                if (ID == (R.id.et_immunization_date)) {
                    tv = (TextView) findViewById(R.id.et_immunization_date);
                }
                if (ID == (R.id.et_flu_shot_date)) {
                    tv = (TextView) findViewById(R.id.et_flu_shot_date);
                }

                if (tv != null) {
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" +year);
                }
            }
        }, mYear, mMonth, mDay);
        dateDialog.show();
        ID = v.getId();
    }

    private void loadBackdrop() {

    }

    public void save_info() {
        for (int card = 1; card <= rv_RegistrationData.getChildCount(); card++) {

            View currentCard = rv_RegistrationData.getChildAt(card - 1);

            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ChildHolder) {
                Bitmap childsImage = ((BitmapDrawable) this.childImage.getDrawable()).getBitmap();
                String childFirstName = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildFirstName().getText());
                String childLastName = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildLastName().getText());
                String childBirthDate = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildBirthdate().getText());
                String childEnrollDate = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildEnrolldate().getText());
                String childAddyLn1 = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressLn1().getText());
                String childAddyLn2 = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressLn2().getText());
                String childAddyCity = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressCity().getText());
                String childAddyState = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressState().getText());
                String childAddyZip = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressZip().getText());
                String childAge = String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAge().getSelectedItem());

                daycaremanagerDB entry = new daycaremanagerDB(this);
                entry.open();
                newChildNumber = entry.createChildEntry(childFirstName, childLastName, childBirthDate, childEnrollDate, childAddyLn1, childAddyLn2,
                        childAddyCity, childAddyState, childAddyZip, childAge, childsImage);
                entry.close();

            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ParentHolder) {

                String guardianType = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getGuardianType().getSelectedItem());
                String guardianFirstName = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentFirstName().getText());
                String guardianLastName = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentLastName().getText());
                String guardianPhone = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentPhoneNumber().getText());
                String guardianEmail = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentEmail().getText());

                Boolean addressSameAsChild = ((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getIsAddressSameAsChild().isChecked();

                String guardianAddyLn1 = null;
                String guardianAddyLn2 = null;
                String guardianAddyCity = null;
                String guardianAddyState = null;
                String guardianAddyZip = null;
                if (addressSameAsChild) {
                    daycaremanagerDB db = new daycaremanagerDB(this);
                    db.open();
                    Cursor addressCursor = db.getChildAddress(newChildNumber);
                    if (addressCursor.moveToFirst()) {
                        guardianAddyLn1 = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_LN_1));
                        guardianAddyLn2 = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_LN_2));
                        guardianAddyCity = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_CITY));
                        guardianAddyState = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_STATE));
                        guardianAddyZip = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_ZIP));
                        db.close();
                        addressCursor.close();
                    }
                } else {
                    guardianAddyLn1 = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressLn1().getText());
                    guardianAddyLn2 = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressLn2().getText());
                    guardianAddyCity = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressCity().getText());
                    guardianAddyState = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressState().getText());
                    guardianAddyZip = String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressZip().getText());
                }

                daycaremanagerDB guardianEntry = new daycaremanagerDB(this);
                guardianEntry.open();
                guardianEntry.createGuardianEntry(newChildNumber, guardianFirstName, guardianLastName, guardianPhone, guardianAddyLn1,
                        guardianAddyLn2, guardianAddyCity, guardianType, guardianAddyState, guardianAddyZip, guardianEmail);
                guardianEntry.close();
            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ShotsHolder) {
                String medicalFluShotDate = String.valueOf(((ShotsHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getFluShotDate().getText());
                String medicalImmunizationShotDate = String.valueOf(((ShotsHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getImmunizationDate().getText());
                Drawable medicalImmunizationImage = ((ShotsHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getIvShotRecord().getDrawable();
                Bitmap medicalImmunizationBmp = ((BitmapDrawable) medicalImmunizationImage).getBitmap();

                daycaremanagerDB medicalEntry = new daycaremanagerDB(this);
                medicalEntry.open();
                medicalEntry.createShotsEntry(newChildNumber, medicalFluShotDate, medicalImmunizationShotDate, medicalImmunizationBmp);
                medicalEntry.close();
            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof MedicationHolder) {

            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof DiscountHolder) {

            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_saveReg) {
            save_info();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Take a Pic
    public void takePic() {
        AlertDialog.Builder ab = new AlertDialog.Builder(RegistrationListScrollingActivity.this);
        ab.setTitle("Choose");
        ab.setIcon(ic_launcher_new);

        ab.setItems(cItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case (0):
                        boyPic();
                        break;
                    case (1):
                        girlPic();
                        break;
                    case (2):
                        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i, cameraData);
                        break;
                }
            }
        });
        ab.show();
    }

    public void boyPic() {
        childImage.setImageResource(R.mipmap.ic_boy);
    }

    public void girlPic() {
        childImage.setImageResource(R.mipmap.ic_girl);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // camera capture
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            childImage.setImageBitmap(bmp);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        bundle.putParcelable(SAVED_LAYOUT_MANAGER, rv_RegistrationData.getLayoutManager().onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            layoutManagerSavedState = ((Bundle) state).getParcelable(SAVED_LAYOUT_MANAGER);
        }
        super.onRestoreInstanceState(state);
    }


    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     * <p/>
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);

        if (state instanceof Bundle) {
            layoutManagerSavedState = ((Bundle) state).getParcelable(SAVED_LAYOUT_MANAGER);
        }
        super.onRestoreInstanceState(state);


    }
}
