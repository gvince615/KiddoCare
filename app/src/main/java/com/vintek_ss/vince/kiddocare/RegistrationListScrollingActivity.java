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
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
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

import static com.vintek_ss.vince.kiddocare.R.mipmap.ic_launcher_new;

public class RegistrationListScrollingActivity extends AppCompatActivity {

    public static final int CHILD = 0;
    public static final int PARENT = 1;
    public static final int MEDICAL = 2;
    public static final int MEDICATION = 3;
    public static final int DISCOUNT = 4;
    final static int cameraData = 0;

    private static int ID = 0;
    private final String imageOptions[] = {"Generic Boy Image", "Generic Girl Image", "Take Picture"};
    private Bitmap bmp;
    private ChildRegistrationRVAdapter adapter;
    private ImageView childImage;
    private RecyclerView rv_RegistrationData;
    private ArrayList<Object> cards = new ArrayList<>();
    private String mCollapsedTitle = "KateLynn Vincent";
    private String mExpandedTitle = "KateLynn Vincent";
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int cardDatasetTypes[] = {CHILD, PARENT, MEDICAL, MEDICATION, DISCOUNT}; //view types

    private int newChildNumber;
    private int childNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_edit_activity_scrolling);

        childImage = (ImageView) findViewById(R.id.iv_child_image);

        setUpToolbarAndAppBar();
        setUpRecyclerView();
        setUpFloatingActionButtons();

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState != null) {
            //cards.clear();
            childNumber = savedInstanceState.getInt(RegisteredListScrollingActivity.REGISTERED_CHILD_DATA);
            if (savedInstanceState.containsKey("child_card")) {
                cards.add(savedInstanceState.getParcelable("child_card"));
            }
            if (savedInstanceState.containsKey("parent_card_1")) {
                cards.add(savedInstanceState.getParcelable("parent_card_1"));
            }
            if (savedInstanceState.containsKey("parent_card_2")) {
                cards.add(savedInstanceState.getParcelable("parent_card_2"));
            }

        } else {
            if (bundle != null) {
                childNumber = bundle.getInt(RegisteredListScrollingActivity.REGISTERED_CHILD_DATA);

            } else {
                loadBlankChildCard();
            }
        }

    }

    private void setUpFloatingActionButtons() {
        FloatingActionButton fab_take_pic = (FloatingActionButton) findViewById(R.id.fab_take_child_picture);
        fab_take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePic();
            }
        });

        FloatingActionButton fab_add_card = (FloatingActionButton) findViewById(R.id.fab_add_card);
        fab_add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickACardToAdd();
            }
        });
    }

    private void setUpRecyclerView() {
        rv_RegistrationData = (RecyclerView) findViewById(R.id.rv_registration_data_list);

        RecyclerView.LayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv_RegistrationData.setLayoutManager(llm);

        rv_RegistrationData.setItemAnimator(new DefaultItemAnimator());

        adapter = new ChildRegistrationRVAdapter(cards);
        rv_RegistrationData.setAdapter(adapter);

    }

    private void setUpToolbarAndAppBar() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        toolbar.setTitle(mExpandedTitle);

        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.START);
        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.END);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.black));

        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
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
                                cards.add(new ParentData("", "", "", "", "", true, "", "", "", "", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                            case "Shot Record Data Card":
                                Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                                        R.mipmap.ic_shot_record);
                                cards.add(new ShotRecordData(image, "", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                            case "Medication Data Card":
                                cards.add(new MedicationData("", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                            case "Discount Data Card":
                                cards.add(new DiscountData("", ""));
                                adapter.notifyItemInserted(rv_RegistrationData.getChildCount() + 1);
                                break;
                        }

                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content_coordinatorLayout);
                        Snackbar.make(coordinatorLayout, strName + " Added", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
        builderSingle.show();
    }

    private ArrayList<Object> loadBlankChildCard() {
        Bitmap image = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.ic_launcher_new);

        cards.add(new ChildData(0, "", "", "", "", "", "", "", "", "", ""));
        return cards;
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

    public void save_info() {
        for (int card = 1; card <= rv_RegistrationData.getChildCount(); card++) {

            View currentCard = rv_RegistrationData.getChildAt(card - 1);

            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ChildHolder) {

                ChildData childData = createChildObject(currentCard);

                daycaremanagerDB entry = new daycaremanagerDB(this);
                entry.open();
                newChildNumber = entry.createChildEntry(childData.first_name, childData.last_name, childData.birth_date, childData.enroll_date, childData.address_ln_1, childData.address_ln_2,
                        childData.address_city, childData.address_state, childData.address_zip, childData.age, ((BitmapDrawable) childImage.getDrawable()).getBitmap());
                entry.close();

            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ParentHolder) {

                ParentData parentData = createParentObject(currentCard);

                Boolean addressSameAsChild = ((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getIsAddressSameAsChild().isChecked();

                if (addressSameAsChild) {
                    daycaremanagerDB db = new daycaremanagerDB(this);
                    db.open();
                    Cursor addressCursor = db.getChildAddress(newChildNumber);
                    if (addressCursor.moveToFirst()) {
                        parentData.address_ln_1 = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_LN_1));
                        parentData.address_ln_2 = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_LN_2));
                        parentData.address_city = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_CITY));
                        parentData.address_state = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_STATE));
                        parentData.address_zip = addressCursor.getString(addressCursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_ZIP));
                        db.close();
                        addressCursor.close();
                    }
                }

                daycaremanagerDB guardianEntry = new daycaremanagerDB(this);
                guardianEntry.open();
                guardianEntry.createGuardianEntry(newChildNumber, parentData.first_name, parentData.last_name, parentData.phoneNumber, parentData.address_ln_1,
                        parentData.address_ln_2, parentData.address_city, parentData.guardian_type, parentData.address_state, parentData.address_zip, parentData.email);
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

    private ParentData createParentObject(View currentCard) {

        ParentData parentData = new ParentData(
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getGuardianType().getSelectedItem()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentFirstName().getText()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentLastName().getText()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentEmail().getText()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentPhoneNumber().getText()),
                ((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getIsAddressSameAsChild().isChecked(),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressLn1().getText()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressLn2().getText()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressCity().getText()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressState().getText()),
                String.valueOf(((ParentHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getParentAddressZip().getText())
        );

        return parentData;
    }

    private ChildData createChildObject(View currentCard) {
        ChildData childData = new ChildData(
                childNumber,
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildFirstName().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildLastName().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildBirthdate().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAge().getSelectedItem()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildEnrolldate().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressLn1().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressLn2().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressCity().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressState().getText()),
                String.valueOf(((ChildHolder) rv_RegistrationData.getChildViewHolder(currentCard)).getChildAddressZip().getText())
        );

        return childData;
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

        ab.setItems(imageOptions, new DialogInterface.OnClickListener() {
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

        childImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.boy2, null));
    }

    public void girlPic() {
        childImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.girl2, null));
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
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        int parentIterator = 1;
        for (int card = 1; card <= rv_RegistrationData.getChildCount(); card++) {

            View currentCard = rv_RegistrationData.getChildAt(card - 1);

            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ChildHolder) {
                ChildData childCard = createChildObject(currentCard);
                outState.putParcelable("child_card", childCard);
            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ParentHolder) {
                ParentData parentCard = createParentObject(currentCard);
                outState.putParcelable("parent_card_" + parentIterator, parentCard);
                parentIterator++;
            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof MedicationHolder) {

            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof ShotsHolder) {

            }
            if (rv_RegistrationData.getChildViewHolder(currentCard) instanceof DiscountHolder) {

            }

        }
    }
}
