package com.vintek_ss.vince.kiddocare;

import android.app.Activity;
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
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Calendar;
import java.util.List;

import static com.vintek_ss.vince.kiddocare.R.drawable.ic_boy;
import static com.vintek_ss.vince.kiddocare.R.drawable.ic_girl;
import static com.vintek_ss.vince.kiddocare.R.drawable.ic_launcher;


public class RegistrationActivity extends Activity implements
        OnItemSelectedListener {
    final static int cameraData = 0;
    private static int ID = 0;
    public final String[] discount = new String[2];
    final String cItems[] = {"Generic Boy Image", "Generic Girl Image", "Take Picture"};
    String discountType = "";
    String discountAmount = "";
    String childLabel = "";
    Spinner spinner;
    Spinner child_Age;
    boolean newDiscount = false;
    // child
    EditText childFname, ChildFavActivity;
    EditText childLname;
    TextView childBirth, childID;
    TextView childEnroll;
    EditText childAddress;
    TextView childDTAPDate, parentM_Rnum, parentD_Rnum;
    TextView childMMRdate;
    TextView childHEPBdate;
    EditText childMed1;
    TextView childMedtime1;
    EditText childAllergy;
    ImageView childPic;
    // Can Pick Up
    EditText CcanPU_fname, CcanPU_lname, CcanPU_num;
    // parent
    EditText parentM_Fname, parentM_Lname, parentM_Cphone, parentM_Hphone,
            parentM_Wphone, parentM_email, parentD_Fname,
            parentD_Lname, parentD_Cphone, parentD_Hphone, parentD_Wphone,
            parentD_email;
    Bitmap bmp;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private ViewFlipper viewFlipper;
    private float lastX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // Spinner element
        spinner = (Spinner) findViewById(R.id.s_reg_children);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();
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


            childLabel = parent.getItemAtPosition(position).toString();
            daycaremanagerDB db = new daycaremanagerDB(this);
            db.open();
            // this will load the registration form the child/parent data (FOR SIBLING)
            Cursor c = db.getChildEntry(childLabel);
            int count = c.getCount();

            String cln = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME));
            String cadd = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS));
            String cParent1 = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_PARENT1));
            String cParent2 = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_PARENT2));
            String cCanPickUp_DropOff_name = c.getString(c.getColumnIndex
                    (daycaremanagerDB.KEY_CHILD_ALLOWED2_PU_DO_NAME));
            CcanPU_fname = (EditText) findViewById(R.id.et_ppuFname);
            CcanPU_lname = (EditText) findViewById(R.id.et_ppuLname);
            String cCanPickUp_DropOff_num = c.getString(c.getColumnIndex
                    (daycaremanagerDB.KEY_CHILD_ALLOWED2_PU_DO_NUM));
            CcanPU_num = (EditText) findViewById(R.id.et_ppuNum);

            try {
                String[] splitFandL = cCanPickUp_DropOff_name.split("\\s+");
                String cPUfn = splitFandL[0];
                String cPUln = splitFandL[1];
                CcanPU_fname.setText(cPUfn);
                CcanPU_lname.setText(cPUln);
                CcanPU_num.setText(cCanPickUp_DropOff_num);
            } catch (Exception e) {
                e.printStackTrace();
            }


            childLname = (EditText) findViewById(R.id.et_childLname);
            childLname.setText(cln);
            childAddress = (EditText) findViewById(R.id.et_childAddy);
            childAddress.setText(cadd);

            ///// Get and Set Parent Data for Record Update

            Cursor c1 = null;
            try {

                c1 = db.getParentEntry(cParent1);
                if (c1 == null) {

                } else {
                    parentM_Rnum = (TextView) findViewById(R.id.tv_momRecNum);
                    String p1recNum = c1.getString(c1.getColumnIndex(daycaremanagerDB.KEY_PARENT_ROWID));
                    parentM_Rnum.setText(p1recNum);

                    parentM_Fname = (EditText) findViewById(R.id.et_M_fname);
                    String p1fn = c1.getString(c1.getColumnIndex(daycaremanagerDB.KEY_PARENT_FNAME));
                    parentM_Fname.setText(p1fn);
                    parentM_Lname = (EditText) findViewById(R.id.et_M_lname);
                    String p1ln = c1.getString(c1.getColumnIndex(daycaremanagerDB.KEY_PARENT_LNAME));
                    parentM_Lname.setText(p1ln);
                    parentM_Cphone = (EditText) findViewById(R.id.et_M_Cnum);
                    String p1Cn = c1.getString(c1.getColumnIndex(daycaremanagerDB.KEY_PARENT_CPHONE));
                    parentM_Cphone.setText(p1Cn);
                    parentM_Hphone = (EditText) findViewById(R.id.et_M_Hnum);
                    String p1Hn = c1.getString(c1.getColumnIndex(daycaremanagerDB.KEY_PARENT_HPHONE));
                    parentM_Hphone.setText(p1Hn);
                    parentM_Wphone = (EditText) findViewById(R.id.et_M_Wnum);
                    String p1Wn = c1.getString(c1.getColumnIndex(daycaremanagerDB.KEY_PARENT_WPHONE));
                    parentM_Wphone.setText(p1Wn);
                    parentM_email = (EditText) findViewById(R.id.et_M_email);
                    String p1email = c1.getString(c1.getColumnIndex(daycaremanagerDB.KEY_PARENT_EMAIL));
                    parentM_email.setText(p1email);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Cursor c2 = null;
            try {
                c2 = db.getParentEntry(cParent2);
                parentD_Rnum = (TextView) findViewById(R.id.tv_dadRecNum);
                String p2recNum = c2.getString(c2.getColumnIndex(daycaremanagerDB.KEY_PARENT_ROWID));
                parentD_Rnum.setText(p2recNum);

                parentD_Fname = (EditText) findViewById(R.id.et_D_fname);
                String p2fn = c2.getString(c2.getColumnIndex(daycaremanagerDB.KEY_PARENT_FNAME));
                parentD_Fname.setText(p2fn);
                parentD_Lname = (EditText) findViewById(R.id.et_D_lname);
                String p2ln = c2.getString(c2.getColumnIndex(daycaremanagerDB.KEY_PARENT_LNAME));
                parentD_Lname.setText(p2ln);
                parentD_Cphone = (EditText) findViewById(R.id.et_D_Cnum);
                String p2Cn = c2.getString(c2.getColumnIndex(daycaremanagerDB.KEY_PARENT_CPHONE));
                parentD_Cphone.setText(p2Cn);
                parentD_Hphone = (EditText) findViewById(R.id.et_D_Hnum);
                String p2Hn = c2.getString(c2.getColumnIndex(daycaremanagerDB.KEY_PARENT_HPHONE));
                parentD_Hphone.setText(p2Hn);
                parentD_Wphone = (EditText) findViewById(R.id.et_D_Wnum);
                String p2Wn = c2.getString(c2.getColumnIndex(daycaremanagerDB.KEY_PARENT_WPHONE));
                parentD_Wphone.setText(p2Wn);
                parentD_email = (EditText) findViewById(R.id.et_D_email);
                String p2email = c2.getString(c2.getColumnIndex(daycaremanagerDB.KEY_PARENT_EMAIL));
                parentD_email.setText(p2email);
            } catch (Exception e) {
                e.printStackTrace();
            }

            db.close();
            if (c != null) {
                c.close();
            }
            if (c1 != null) {
                c1.close();
            }
            if (c2 != null) {
                c2.close();
            }

        }

    }

    // Load all data on reg for to edit Record
    public void loadDatatoEDITRecord(View v) {

        Spinner cSpin = (Spinner) findViewById(R.id.s_reg_children);
        int selectedPos = cSpin.getSelectedItemPosition();

        if (selectedPos == 0) {
            cSpin.setBackgroundResource(R.drawable.red_box);
            Toast.makeText(getApplicationContext(), "Must First Select a Child",
                    Toast.LENGTH_SHORT).show();
        } else {
            final String selectedChild = cSpin.getSelectedItem().toString();
            final Dialog tDialog = new Dialog(RegistrationActivity.this);
            tDialog.setContentView(R.layout.edit_record);
            tDialog.setTitle("Choose an action for...");
            final TextView sChild = (TextView) tDialog.findViewById(R.id.tv_selectedCName);
            final TextView sMessage = (TextView) tDialog.findViewById(R.id.tv_message);
            sChild.setText(selectedChild);
            final RadioButton rbEdit = (RadioButton) tDialog.findViewById(R.id.rb_edit);
            final RadioButton rbDelete = (RadioButton) tDialog.findViewById(R.id.rb_delete);
            RadioGroup rGroup = (RadioGroup) tDialog.findViewById(R.id.radioGroup);
            Button bAction = (Button) tDialog.findViewById(R.id.b_action);
            Button bCancel = (Button) tDialog.findViewById(R.id.b_cancel);
            //rGroup.clearCheck();
            tDialog.show();

        /* Attach CheckedChangeListener to radio group */
            rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (rbEdit.isChecked()) {
                        sMessage.setText("Edit will dismiss this window and populate all " +
                                "fields in the registration window to be edited as desired.  " +
                                "Then tap Save Registration");
                    }
                    if (rbDelete.isChecked()) {
                        sMessage.setText("WARNING!! If you choose to delete a child from the " +
                                "database, it will delete all associated records as well. " +
                                "It is recomended to completed a final billing report for " +
                                "this child prior to deletion if you have not done so already.");
                    }
                }
            });

            bAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean edit = rbEdit.isChecked();
                    Boolean delete = rbDelete.isChecked();

                    if (edit == true) {
                        daycaremanagerDB db = new daycaremanagerDB(RegistrationActivity.this);
                        db.open();
                        // this will load the registration form the child/parent data (FOR for EDIT record)
                        Cursor c = db.getChildEntry(childLabel);
                        String cfn = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME));
                        String Cid = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID));
                        String cbd = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_BDATE));
                        String ced = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_EDATE));
                        String cadd = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS));
                        String cdtap = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_DTAP));
                        String cmmr = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_MMR));
                        String chepb = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_HEPB));
                        String cmed = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_MED1));
                        String cmedT1 = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_MED1_TIME1));
                        String cAllergy = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_CALLERGY));
                        String cAge = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE));
                        String cFavActivity = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_FAV_ACTIVITY));
                        String discountType = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_DISCOUNT_TYPE));
                        String discountAmount = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_DISCOUNT_AMOUNT));

                        byte[] childPIC = c.getBlob(c
                                .getColumnIndex(daycaremanagerDB.KEY_CHILD_PIC));
                        Bitmap cPic = BitmapFactory.decodeByteArray(childPIC, 0,
                                childPIC.length);

                        ImageView iv = (ImageView) findViewById(R.id.iv_ChildPic);
                        iv.setImageBitmap(cPic);
                        childFname = (EditText) findViewById(R.id.et_childFname);
                        childFname.setText(cfn);
                        //childLname = (EditText) findViewById(R.id.et_childLname);
                        // childLname.setText(cln);
                        childID = (TextView) findViewById(R.id.textView12);
                        childID.setText(Cid);
                        childEnroll = (TextView) findViewById(R.id.et_child_Edate);
                        childEnroll.setText(ced);
                        childBirth = (TextView) findViewById(R.id.et_Cbirthdate);
                        childBirth.setText(cbd);
                        childAddress = (EditText) findViewById(R.id.et_childAddy);
                        childAddress.setText(cadd);
                        childDTAPDate = (TextView) findViewById(R.id.et_DTAPdate);
                        childDTAPDate.setText(cdtap);
                        //            childMMRdate = (TextView) findViewById(R.id.et_MMRdate);
                        //            childMMRdate.setText(cmmr);
                        //            childHEPBdate = (TextView) findViewById(R.id.et_HEPBdate);
                        //            childHEPBdate.setText(chepb);
                        childMed1 = (EditText) findViewById(R.id.et_Medname);
                        childMed1.setText(cmed);
                        childMedtime1 = (TextView) findViewById(R.id.et_Medtime);
                        childMedtime1.setText(cmedT1);
                        childAllergy = (EditText) findViewById(R.id.et_Callergies);
                        childAllergy.setText(cAllergy);
                        ChildFavActivity = (EditText) findViewById(R.id.et_childFavActivities);
                        ChildFavActivity.setText(cFavActivity);
                        /////// Get the Age Group
                        child_Age = (Spinner) findViewById(R.id.s_childAgeGroup);
                        int position = 0;
                        if (cAge.equals("Infant")) {
                            position = 0;
                        }
                        if (cAge.equals("Toddler")) {
                            position = 1;
                        }
                        if (cAge.equals("Preschool")) {
                            position = 2;
                        }
                        if (cAge.equals("School Age")) {
                            position = 3;
                        }
                        child_Age.setSelection(position);

                        TextView discountTV = (TextView) findViewById(R.id.tv_discount);
                        discountTV.setText(discountType + " discount of 0" + discountAmount);
                        ///// Get and Set Parent Data for Record Update

                        db.close();
                        if (c != null) {
                            c.close();
                        }
                        Toast.makeText(getApplicationContext(),
                                "Editing...", Toast.LENGTH_SHORT).show();
                    }
                    if (delete == true) {
                        try {
                            daycaremanagerDB del = new daycaremanagerDB(RegistrationActivity.this);
                            del.open();
                            del.deleteChild(selectedChild);
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Deleting...", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                    tDialog.dismiss();
                }
            });
            bCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tDialog.dismiss();
                }
            });

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void selectDiscount(View v) {

        final Dialog disDialog = new Dialog(RegistrationActivity.this);
        disDialog.setContentView(R.layout.discount_layout);
        disDialog.setTitle("Add Discount");
        final Spinner discount_type = (Spinner) disDialog.findViewById(R.id.s_discountType);
        final Spinner discount_amount = (Spinner) disDialog.findViewById(R.id.s_discountAmount);
        disDialog.show();
        Button BsaveDiscount = (Button) disDialog.findViewById(R.id.b_saveDiscount);
        BsaveDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountType = discount_type.getSelectedItem().toString();
                discountAmount = discount_amount.getSelectedItem().toString();

                discount[0] = discountType;
                discount[1] = discountAmount;


                disDialog.dismiss();
                TextView discountTV = (TextView) findViewById(R.id.tv_discount);
                newDiscount = true;
                discountTV.setText(discountType + " discount of 0" + discountAmount);

            }
        });
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
                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth);
                }
                if (ID == (R.id.et_child_Edate)) {
                    TextView tv = (TextView) findViewById(R.id.et_child_Edate);
                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth);
                }
                if (ID == (R.id.et_DTAPdate)) {
                    TextView tv = (TextView) findViewById(R.id.et_DTAPdate);
                    tv.setText(year + "-" + newMonth + "-" + dayOfMonth);
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

    // Take a Pic
    public void takePic(final View v) {
        AlertDialog.Builder ab = new AlertDialog.Builder(RegistrationActivity.this);
        ab.setTitle("Choose");
        ab.setIcon(ic_launcher);

        ab.setItems(cItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case (0):
                        boyPic(v);
                        break;
                    case (1):
                        girlPic(v);
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

    public void boyPic(View v) {
        ImageView iv = (ImageView) findViewById(R.id.iv_ChildPic);
        Drawable boy = getResources().getDrawable(ic_boy);
        bmp = ((BitmapDrawable) boy).getBitmap();
        iv.setImageBitmap(bmp);
    }

    public void girlPic(View v) {
        ImageView iv = (ImageView) findViewById(R.id.iv_ChildPic);
        Drawable girl = getResources().getDrawable(ic_girl);
        bmp = ((BitmapDrawable) girl).getBitmap();
        iv.setImageBitmap(bmp);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView iv = (ImageView) findViewById(R.id.iv_ChildPic);
        // camera capture
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }

    //save child reg
    public void save_info(View v) {

        boolean didItWork = true;
        try {
            // getting child data
            childID = (TextView) findViewById(R.id.textView12);
            // OPTIONAL
            childMed1 = (EditText) findViewById(R.id.et_Medname);
            childMedtime1 = (TextView) findViewById(R.id.et_Medtime);
            childAllergy = (EditText) findViewById(R.id.et_Callergies);
            ChildFavActivity = (EditText) findViewById(R.id.et_childFavActivities);
            childPic = (ImageView) findViewById(R.id.iv_ChildPic);
            // REQUIRED

            childFname = (EditText) findViewById(R.id.et_childFname);
            String cfn = childFname.getText().toString();
            childLname = (EditText) findViewById(R.id.et_childLname);
            String cln = childLname.getText().toString();
            childBirth = (TextView) findViewById(R.id.et_Cbirthdate);
            String cbd = childBirth.getText().toString();
            childEnroll = (TextView) findViewById(R.id.et_child_Edate);
            String ced = childEnroll.getText().toString();
            childAddress = (EditText) findViewById(R.id.et_childAddy);
            String cadd = childAddress.getText().toString();

            childDTAPDate = (TextView) findViewById(R.id.et_DTAPdate);
            // FLU not dTap
            String cdtap = childDTAPDate.getText().toString();

            child_Age = (Spinner) findViewById(R.id.s_childAgeGroup);
            String cID = childID.getText().toString();
            String child_fav_activity = ChildFavActivity.getText().toString();
            String cmed = childMed1.getText().toString();
            String cmedT1 = childMedtime1.getText().toString();
            String cAllergy = childAllergy.getText().toString();

            String cAge = child_Age.getSelectedItem().toString();
            Bitmap cPic = ((BitmapDrawable) childPic.getDrawable()).getBitmap();

            // getting parent data
            ////// MUST HAVE AT LEAST 1 PARENT (MOM OR DAD)
            parentM_Rnum = (TextView) findViewById(R.id.tv_momRecNum);
            parentM_Fname = (EditText) findViewById(R.id.et_M_fname);
            parentM_Lname = (EditText) findViewById(R.id.et_M_lname);
            // MUST HAVE at least 1 CONTACT NUMBER
            parentM_Cphone = (EditText) findViewById(R.id.et_M_Cnum);
            parentM_Hphone = (EditText) findViewById(R.id.et_M_Hnum);
            parentM_Wphone = (EditText) findViewById(R.id.et_M_Wnum);
            // NOT REQUIRED
            parentM_email = (EditText) findViewById(R.id.et_M_email);

            parentD_Rnum = (TextView) findViewById(R.id.tv_dadRecNum);
            ////// MUST HAVE AT LEAST 1 PARENT (MOM OR DAD)
            parentD_Fname = (EditText) findViewById(R.id.et_D_fname);
            parentD_Lname = (EditText) findViewById(R.id.et_D_lname);
            // MUST HAVE at least 1 CONTACT NUMBER
            parentD_Cphone = (EditText) findViewById(R.id.et_D_Cnum);
            parentD_Hphone = (EditText) findViewById(R.id.et_D_Hnum);
            parentD_Wphone = (EditText) findViewById(R.id.et_D_Wnum);
            // NOT REQUIRED
            parentD_email = (EditText) findViewById(R.id.et_D_email);

            // getting can pick up info
            ////// NOT REQUIRED
            CcanPU_fname = (EditText) findViewById(R.id.et_ppuFname);
            CcanPU_lname = (EditText) findViewById(R.id.et_ppuLname);
            CcanPU_num = (EditText) findViewById(R.id.et_ppuNum);

            TextView discountTV = (TextView) findViewById(R.id.tv_discount);

            if (newDiscount == false) {
                daycaremanagerDB getDis = new daycaremanagerDB(this);
                getDis.open();
                String[] discount = getDis.getDiscount(cfn, cln);
                if (discount[0] == null) {

                } else {
                    discountType = discount[0];
                    discountAmount = discount[1];
                    discountTV.setText(discountType + " discount of 0" + discountAmount);
                    String dis = discountTV.getText().toString();
                }

                getDis.close();


            } else {

            }


            String CPUfn = CcanPU_fname.getText().toString();
            String CPUln = CcanPU_lname.getText().toString();
            String CPUnum = CcanPU_num.getText().toString();

            String pMRnum = parentM_Rnum.getText().toString();
            String pMfn = parentM_Fname.getText().toString();
            String pMLn = parentM_Lname.getText().toString();
            String pMcell = parentM_Cphone.getText().toString();
            String pMhome = parentM_Hphone.getText().toString();
            String pMwork = parentM_Wphone.getText().toString();
            String pMemail = parentM_email.getText().toString();

            String pDRnum = parentD_Rnum.getText().toString();
            String pDfn = parentD_Fname.getText().toString();
            String pDLn = parentD_Lname.getText().toString();
            String pDcell = parentD_Cphone.getText().toString();
            String pDhome = parentD_Hphone.getText().toString();
            String pDwork = parentD_Wphone.getText().toString();
            String pDemail = parentD_email.getText().toString();


            String CPUname = CPUfn + " " + CPUln;

            String cParent1 = pMfn + " " + pMLn;
            String cParent2 = pDfn + " " + pDLn;

            daycaremanagerDB entry = new daycaremanagerDB(this);

            entry.open();
            String newRec = getResources().getString(R.string._new);

            if (cID == newRec) {
                if (TextUtils.isEmpty(cfn) || TextUtils.isEmpty(cln)
                        || TextUtils.isEmpty(cbd) || TextUtils.isEmpty(ced)
                        || TextUtils.isEmpty(cadd) || TextUtils.isEmpty(discount[0])
                        || TextUtils.isEmpty(cAge)) {

                    childFname.setBackgroundResource(R.drawable.red_box);
                    childLname.setBackgroundResource(R.drawable.red_box);
                    childBirth.setBackgroundResource(R.drawable.red_box);
                    childEnroll.setBackgroundResource(R.drawable.red_box);
                    childAddress.setBackgroundResource(R.drawable.red_box);
                    //childDTAPDate.setBackgroundResource(R.drawable.red_box);
                    //childMMRdate.setBackgroundResource(R.drawable.red_box);
                    //childHEPBdate.setBackgroundResource(R.drawable.red_box);
                    discountTV.setBackgroundResource(R.drawable.red_box);

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(this,
                            "Missing Child Info\nRed fields are Required!", duration);
                    toast.show();
                    didItWork = false;
                } else {
                    entry.createChildEntry(cfn, cln, cbd, ced, cadd, cdtap, //cmmr,  chepb,
                            cmed, cmedT1, cAllergy, cAge, cPic, cParent1,
                            cParent2, child_fav_activity, CPUname, CPUnum, discountType, discountAmount);
                    //entry.close();
                }

            } else {
                String fullNewName = cfn + " " + cln;
                String fullOldName = entry.getCurrentChildName(cID);
                if (fullNewName != fullOldName && discountType == "") {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(this, "Must Reselct a Child Discount", duration);
                    toast.show();
                    didItWork = false;
                    discountTV.setBackgroundResource(R.drawable.red_box);
                } else {
                    entry.updateChildEntry(cID, cfn, cln,
                            cbd, ced, cadd, cdtap, //cmmr, chepb,
                            cmed, cmedT1, cAllergy,
                            cAge, cPic, cParent1, cParent2, child_fav_activity,
                            CPUname, CPUnum, discountType, discountAmount);

                    entry.updateChildAtten(fullNewName, fullOldName);
                }
            }


            if (pMRnum == newRec || pDRnum == newRec) {
                entry.createParentEntry(pMfn, pMLn, pMcell, pMhome, pMwork,
                        pMemail, pDfn, pDLn, pDcell, pDhome, pDwork,
                        pDemail);
            } else {
                entry.updateParentEntry(pMfn, pMLn, pMcell, pMhome, pMwork,
                        pMemail, pDfn, pDLn, pDcell, pDhome, pDwork, pDRnum, pMRnum,
                        pDemail);
            }

            entry.close();


        } catch (Exception e) {
            didItWork = false;
            String error = e.toString();

            CharSequence text = "Unsuccessful" + "\n" + error;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } finally {
            if (didItWork) {
                CharSequence text = "Success";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reg, menu);
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
        if (id == R.id.action_saveReg) {

            View v = this.findViewById(R.id.b_saveNew);
            save_info(v);

            //////// Save it to DB!!!!!!

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}