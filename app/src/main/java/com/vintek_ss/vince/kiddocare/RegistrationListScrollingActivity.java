package com.vintek_ss.vince.kiddocare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
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

import static com.vintek_ss.vince.kiddocare.R.drawable.ic_boy;
import static com.vintek_ss.vince.kiddocare.R.drawable.ic_girl;
import static com.vintek_ss.vince.kiddocare.R.drawable.ic_launcher;

//import android.support.design.widget.CollapsingToolbarLayout;

public class RegistrationListScrollingActivity extends AppCompatActivity {

    public static final int CHILD = 0;
    public static final int PARENT = 1;
    public static final int MEDICAL = 2;
    public static final int MEDICATION = 3;
    public static final int DISCOUNT = 4;
    final static int cameraData = 0;
    private static int ID = 0;
    final String cItems[] = {"Generic Boy Image", "Generic Girl Image", "Take Picture"};
    Bitmap bmp;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ComplexRecyclerViewAdapter adapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_edit_activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

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
        LinearLayoutManager llm = new LinearLayoutManager(this);
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

        loadBackdrop();
        initializeData();
        initializeAdapter();
    }

    private void pickACardToAdd() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(RegistrationListScrollingActivity.this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Select A Card To Add:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                RegistrationListScrollingActivity.this,
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Parent Data Card");
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
                            case "Parent Data Card":
                                items.add(new ParentData("", "", "", "", "", true, "", "", "", ""));
                                adapter.notifyDataSetChanged();
                                break;
                            case "Shot Record Data Card":
                                items.add(new ShotRecordData(0, "", ""));
                                adapter.notifyDataSetChanged();
                                break;
                            case "Medication Data Card":
                                items.add(new MedicationData("", ""));
                                adapter.notifyDataSetChanged();
                                break;
                            case "Discount Data Card":
                                items.add(new DiscountData("", ""));
                                adapter.notifyDataSetChanged();
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
        items.add(new ChildData(0, 0, "", "", "", "", "", "", "", ""));
        return items;
    }

    private void initializeAdapter(){

        adapter = new ComplexRecyclerViewAdapter(items);
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
                if (ID == (R.id.et_Medtime)) {
                    TextView tv = (TextView) findViewById(R.id.et_Medtime);
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

                if (ID == (R.id.tv_Cbirthdate)) {
                    TextView tv = (TextView) findViewById(R.id.tv_Cbirthdate);
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" +year);
                }
                if (ID == (R.id.tv_child_Edate)) {
                    TextView tv = (TextView) findViewById(R.id.tv_child_Edate);
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" +year);
                }
                if (ID == (R.id.et_DTAPdate)) {
                    TextView tv = (TextView) findViewById(R.id.et_DTAPdate);
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" +year);
                }
            }
        }, mYear, mMonth, mDay);
        dateDialog.show();
        ID = v.getId();
    }

    private void loadBackdrop() {
        childImage = (ImageView) findViewById(R.id.iv_child_image);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    // Take a Pic
    public void takePic() {
        AlertDialog.Builder ab = new AlertDialog.Builder(RegistrationListScrollingActivity.this);
        ab.setTitle("Choose");
        ab.setIcon(ic_launcher);

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
        Drawable boy = getResources().getDrawable(ic_boy);
        bmp = ((BitmapDrawable) boy).getBitmap();
        childImage.setImageBitmap(bmp);
    }

    public void girlPic() {
        Drawable girl = getResources().getDrawable(ic_girl);
        bmp = ((BitmapDrawable) girl).getBitmap();
        childImage.setImageBitmap(bmp);
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
}
