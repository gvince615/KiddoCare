package com.vintek_ss.vince.kiddocare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.vintek_ss.vince.kiddocare.R.drawable.ic_boy;
import static com.vintek_ss.vince.kiddocare.R.drawable.ic_girl;
import static com.vintek_ss.vince.kiddocare.R.drawable.ic_launcher;

public class ScrollingActivity extends AppCompatActivity {

    private static int ID = 0;

    Bitmap bmp;
    final static int cameraData = 0;
    final String cItems[] = {"Generic Boy Image", "Generic Girl Image", "Take Picture"};

    ImageView childImage;
    RecyclerView rv_RegistrationData;
    private int mYear, mMonth, mDay, mHour, mMinute;

    private List<Child> children;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("LastName, FirstName");
        collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.black));

        rv_RegistrationData = (RecyclerView)findViewById(R.id.rv_registration_data_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_RegistrationData.setLayoutManager(llm);
        //rv_RegistrationData.setHasFixedSize(true);

        FloatingActionButton fab_take_pic = (FloatingActionButton) findViewById(R.id.fab_take_child_picture);
        fab_take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePic();
            }
        });

//        FloatingActionButton fab_add_card = (FloatingActionButton) findViewById(R.id.fab_add_card);
//        fab_add_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Card Added", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        loadBackdrop();
        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        children = new ArrayList<>();
        children.add(new Child(1, R.drawable.ic_girl, "KateLynn", "Vincent",
                "11-20-2012", "01-01-2013", "5162 Glen Cove Ln", "Flint", "MI", "48507"));

        children.add(new Child(1, R.drawable.ic_girl, "Jeremiah", "Vincent",
                "11-10-2008", "01-01-2013", "5162 Glen Cove Ln", "Flint", "MI", "48507"));
    }

    private void initializeAdapter(){
        RegistrationRVAdapter adapter = new RegistrationRVAdapter(children);
        rv_RegistrationData.setAdapter(adapter);
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
                String newMonth = "";
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
                if (ID == (R.id.et_Cbirthdate)) {
                    TextView tv = (TextView) findViewById(R.id.et_Cbirthdate);
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" +year);
                }
                if (ID == (R.id.et_child_Edate)) {
                    TextView tv = (TextView) findViewById(R.id.et_child_Edate);
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" +year);
                }
                if (ID == (R.id.et_DTAPdate)) {
                    TextView tv = (TextView) findViewById(R.id.et_DTAPdate);
                    tv.setText(monthOfYear + "/" + dayOfMonth + "/" +year);
                }
//                if (ID == (R.id.et_MMRdate)) {
//                    TextView tv = (TextView) findViewById(R.id.et_MMRdate);
//                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth);
//                }
//                if (ID == (R.id.et_HEPBdate)) {
//                    TextView tv = (TextView) findViewById(R.id.et_HEPBdate);
//                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth);
//                }
            }
        }, mYear, mMonth, mDay);
        dateDialog.show();
        ID = v.getId();
    }

    private void loadBackdrop() {
        ImageView imageView = (ImageView) findViewById(R.id.iv_child_image);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    // Take a Pic
    public void takePic() {
        AlertDialog.Builder ab = new AlertDialog.Builder(ScrollingActivity.this);
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
