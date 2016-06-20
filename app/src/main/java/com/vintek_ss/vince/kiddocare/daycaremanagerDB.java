package com.vintek_ss.vince.kiddocare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class daycaremanagerDB {

    public static final String KEY_CHILD_ROWID = "_id";
    public static final String KEY_CHILD_FNAME = "child_first_name";
    public static final String KEY_CHILD_LNAME = "child_last_name";
    public static final String KEY_CHILD_BDATE = "child_birth_date";
    public static final String KEY_CHILD_EDATE = "child_enroll_date";
    public static final String KEY_CHILD_AGE = "child_age";
    public static final String KEY_CHILD_ADDRESS_LN_1 = "child_addy_ln_1";
    public static final String KEY_CHILD_ADDRESS_LN_2 = "child_addy_ln_2";
    public static final String KEY_CHILD_ADDRESS_CITY = "child_addy_city";
    public static final String KEY_CHILD_ADDRESS_STATE = "child_addy_state";
    public static final String KEY_CHILD_ADDRESS_ZIP = "child_addy_zip";
    public static final String KEY_CHILD_PIC = "child_pic";

    public static final String KEY_PARENT_ROWID = "_id";
    public static final String KEY_PARENT_CHILD_ID = "parent_child_id";
    public static final String KEY_PARENT_GUARDIAN_TYPE = "parent_guardian_type";
    public static final String KEY_PARENT_FNAME = "parent_Fname";
    public static final String KEY_PARENT_LNAME = "parent_Lname";
    public static final String KEY_PARENT_PHONE = "parent_phone";
    public static final String KEY_PARENT_EMAIL = "parent_email";
    public static final String KEY_PARENT_ADDRESS_LN_1 = "parent_addy_ln_1";
    public static final String KEY_PARENT_ADDRESS_LN_2 = "parent_addy_ln_2";
    public static final String KEY_PARENT_ADDRESS_CITY = "parent_addy_city";
    public static final String KEY_PARENT_ADDRESS_STATE = "parent_addy_state";
    public static final String KEY_PARENT_ADDRESS_ZIP = "parent_addy_zip";

    public static final String KEY_DISCOUNT_ROWID = "_id";
    public static final String KEY_DISCOUNT_CHILD_ID = "discount_child_id";
    public static final String KEY_DISCOUNT_DESC = "child_discount_desc";
    public static final String KEY_DISCOUNT_AMOUNT = "child_discount_amount";

    public static final String KEY_SHOT_RECORD_ROWID = "_id";
    public static final String KEY_SHOT_RECORD_CHILD_ID = "shot_record_child_id";
    public static final String KEY_SHOT_RECORD_FLU_SHOT_DATE = "shot_record_flu_shot_date";
    public static final String KEY_SHOT_RECORD_IMMUNIZATION_DATE = "shot_record_immunization_date";
    public static final String KEY_SHOT_RECORD_IMMUNIZATION_IMAGE = "shot_record_immunization_image";

    public static final String KEY_MEDICATION_ROWID = "_id";
    public static final String KEY_MEDICATION_CHILD_ID = "medication_child_id";
    public static final String KEY_MEDICATION_NAME = "medication_name";
    public static final String KEY_MEDICATION_TIME = "medication_time";


    public static final String KEY_ATTEN_ROWID = "_id";
    public static final String KEY_ATTEN_CHILD_ID = "atten_child_id";
    public static final String KEY_ATTEN_DATE_TIME_IN = "atten_date_time_in";
    public static final String KEY_ATTEN_DATE_TIME_OUT = "atten_date_time_out";

    public static final String KEY_TODO_ROWID = "_id";
    public static final String KEY_TODO_TITLE = "todo_title";
    public static final String KEY_TODO_DATE = "todo_date";
    public static final String KEY_TODO_TIME = "todo_time";
    public static final String KEY_TODO_STATUS = "todo_status";

    public static final String KEY_CAL_ROWID = "_id";
    public static final String KEY_CAL_EVENT_TITLE = "cal_event_title";
    public static final String KEY_CAL_START_TIME = "cal_start_time";
    public static final String KEY_CAL_END_TIME = "cal_end_time";
    public static final String KEY_CAL_DATE = "cal_date";

    public static final String KEY_MEAL_ROWID = "_id";
    public static final String KEY_MEAL_DATE = "meal_date";
    public static final String KEY_MEAL_B_AMSNACK1 = "meal_b_amsnack1";
    public static final String KEY_MEAL_B_AMSNACK2 = "meal_b_amsnack2";
    public static final String KEY_MEAL_L_PMSNACK1 = "meal_l_pmsnack1";
    public static final String KEY_MEAL_L_PMSNACK2 = "meal_l_pmsnack2";
    public static final String KEY_MEAL_D_PMSNACK1 = "meal_d_pmsnack1";
    public static final String KEY_MEAL_D_PMSNACK2 = "meal_d_pmsnack2";
    public static final String KEY_MEAL_B_NAME = "meal_b_name";
    public static final String KEY_MEAL_B_DRINK = "meal_b_drink";
    public static final String KEY_MEAL_B_FRUIT_VEG_JUICE = "meal_b_fruit_veg_juice";
    public static final String KEY_MEAL_B_BREAD = "meal_b_bread";
    public static final String KEY_MEAL_L_NAME = "meal_l_name";
    public static final String KEY_MEAL_L_DRINK = "meal_l_drink";
    public static final String KEY_MEAL_L_FRUIT_VEG1 = "meal_l_fruit_veg1";
    public static final String KEY_MEAL_L_FRUIT_VEG2 = "meal_l_fruit_veg2";
    public static final String KEY_MEAL_L_BREAD = "meal_l_bread";
    public static final String KEY_MEAL_L_PROTEIN = "meal_l_protein";
    public static final String KEY_MEAL_D_NAME = "meal_D_name";
    public static final String KEY_MEAL_D_DRINK = "meal_d_drink";
    public static final String KEY_MEAL_D_FRUIT_VEG1 = "meal_d_fruit_veg1";
    public static final String KEY_MEAL_D_FRUIT_VEG2 = "meal_d_fruit_veg2";
    public static final String KEY_MEAL_D_BREAD = "meal_d_bread";
    public static final String KEY_MEAL_D_PROTEIN = "meal_d_protein";

    public static final String KEY_FOODITEM_ROWID = "_id";
    public static final String KEY_FOODITEM_GROUP = "fooditem_group";
    public static final String KEY_FOODITEM_NAME = "fooditem_name";

    public static final String KIDDO_CARE_DB = "KiddoCareDB";
    public static final String CHILD_TABLE = "childTable";
    public static final String PARENT_TABLE = "parentTable";
    public static final String MEDICATION_TABLE = "medicationTable";
    public static final String DISCOUNT_TABLE = "discountTable";
    public static final String SHOT_RECORD_TABLE = "shotRecordTable";
    public static final String ATTENDANCE_TABLE = "attenTable";
    public static final String CALENDAR_EVENTS_TABLE = "calendarEventsTable";
    public static final String TODO_LIST_TABLE = "todoListTable";
    public static final String MEAL_PLAN_TABLE = "mealPlanTable";
    public static final String FOODITEM_TABLE = "fooditemTable";

    public static final int DATABASE_VERSION = 5;
    private final Context ourContext;
    private DbHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    public daycaremanagerDB(Context c) {
        ourContext = c;
    }

    public daycaremanagerDB open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    //////////////// CHILD ////////////////////
    public int createChildEntry(String cfn, String cln, String cbd, String ced, String cadd_ln1,
                                String cadd_ln2, String cadd_city, String cadd_state,
                                String cadd_zip, String cAge, Bitmap cPic) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        cPic.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] picArray = stream.toByteArray();

        ContentValues childCV = new ContentValues();

        childCV.put(KEY_CHILD_FNAME, cfn);
        childCV.put(KEY_CHILD_LNAME, cln);
        childCV.put(KEY_CHILD_BDATE, cbd);
        childCV.put(KEY_CHILD_EDATE, ced);
        childCV.put(KEY_CHILD_ADDRESS_LN_1, cadd_ln1);
        childCV.put(KEY_CHILD_ADDRESS_LN_2, cadd_ln2);
        childCV.put(KEY_CHILD_ADDRESS_CITY, cadd_city);
        childCV.put(KEY_CHILD_ADDRESS_STATE, cadd_state);
        childCV.put(KEY_CHILD_ADDRESS_ZIP, cadd_zip);
        childCV.put(KEY_CHILD_AGE, cAge);
        childCV.put(KEY_CHILD_PIC, picArray);

        long insertedID = ourDatabase.insert(CHILD_TABLE, null, childCV);
        return (int) insertedID;
    }

    public Cursor getChildAddress(int childNumber) {

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Integer.toString(childNumber)};

        String[] columns = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME,
                KEY_CHILD_LNAME, KEY_CHILD_BDATE, KEY_CHILD_EDATE, KEY_CHILD_AGE, KEY_CHILD_ADDRESS_LN_1,
                KEY_CHILD_ADDRESS_LN_2, KEY_CHILD_ADDRESS_CITY, KEY_CHILD_ADDRESS_STATE, KEY_CHILD_ADDRESS_ZIP,
                KEY_CHILD_AGE, KEY_CHILD_PIC};
        Cursor c = ourDatabase.query(CHILD_TABLE, columns, whereClause, whereArgs,
                null, null, null);

        return c;
    }

    public Cursor getChildEntry(int childRecordNumber) {

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(childRecordNumber)};

        String[] columns = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME,
                KEY_CHILD_LNAME, KEY_CHILD_BDATE, KEY_CHILD_EDATE, KEY_CHILD_AGE, KEY_CHILD_ADDRESS_LN_1,
                KEY_CHILD_ADDRESS_LN_2, KEY_CHILD_ADDRESS_CITY, KEY_CHILD_ADDRESS_STATE, KEY_CHILD_ADDRESS_ZIP,
                KEY_CHILD_AGE, KEY_CHILD_PIC};
        Cursor c = ourDatabase.query(CHILD_TABLE, columns, whereClause, whereArgs,
                null, null, null);

        return c;
    }

    public Cursor getAllEnrolledChildren() {

        String[] columns = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME,
                KEY_CHILD_LNAME, KEY_CHILD_BDATE, KEY_CHILD_EDATE, KEY_CHILD_AGE, KEY_CHILD_ADDRESS_LN_1,
                KEY_CHILD_ADDRESS_LN_2, KEY_CHILD_ADDRESS_CITY, KEY_CHILD_ADDRESS_STATE, KEY_CHILD_ADDRESS_ZIP,
                KEY_CHILD_AGE, KEY_CHILD_PIC};

        Cursor c = ourDatabase.query(CHILD_TABLE, columns, null,
                null, null, null, null);

        return c;
    }

    ////////////// PARENT ////////////////////
    public void createGuardianEntry(int child_id, String p_Fname, String p_Lname, String p_phone_number,
                                    String p_addy_ln_1, String p_addy_ln_2, String p_addy_city, String p_guardian_type,
                                    String p_addy_state, String p_addy_zip, String p_Email) {

        ContentValues parentCV = new ContentValues();

        parentCV.put(KEY_PARENT_CHILD_ID, child_id);
        parentCV.put(KEY_PARENT_GUARDIAN_TYPE, p_guardian_type);
        parentCV.put(KEY_PARENT_FNAME, p_Fname);
        parentCV.put(KEY_PARENT_LNAME, p_Lname);
        parentCV.put(KEY_PARENT_PHONE, p_phone_number);
        parentCV.put(KEY_PARENT_ADDRESS_LN_1, p_addy_ln_1);
        parentCV.put(KEY_PARENT_ADDRESS_LN_2, p_addy_ln_2);
        parentCV.put(KEY_PARENT_ADDRESS_CITY, p_addy_city);
        parentCV.put(KEY_PARENT_ADDRESS_STATE, p_addy_state);
        parentCV.put(KEY_PARENT_ADDRESS_ZIP, p_addy_zip);
        parentCV.put(KEY_PARENT_EMAIL, p_Email);

        ourDatabase.insert(PARENT_TABLE, null, parentCV);
    }

    public Cursor getParentEntry(int child_id) {

        String whereClause = "parent_child_id = ?";
        String[] whereArgs = new String[]{String.valueOf(child_id)};

        String[] columns = new String[]{KEY_PARENT_ROWID, KEY_PARENT_CHILD_ID, KEY_PARENT_GUARDIAN_TYPE, KEY_PARENT_FNAME, KEY_PARENT_PHONE,
                KEY_PARENT_LNAME, KEY_PARENT_ADDRESS_LN_1, KEY_PARENT_ADDRESS_LN_2, KEY_PARENT_ADDRESS_CITY,
                KEY_PARENT_ADDRESS_STATE, KEY_PARENT_ADDRESS_ZIP, KEY_PARENT_EMAIL};
        Cursor c = ourDatabase.query(PARENT_TABLE, columns, whereClause, whereArgs,
                    null, null, null);

        return c;
    }

    //////////////// DISCOUNT ////////////////////
    public long createDiscountEntry(int d_child_id, String d_desc, Double d_amount) {

        ContentValues discountCV = new ContentValues();

        discountCV.put(KEY_DISCOUNT_CHILD_ID, d_child_id);
        discountCV.put(KEY_DISCOUNT_DESC, d_desc);
        discountCV.put(KEY_DISCOUNT_AMOUNT, d_amount);

        return ourDatabase.insert(DISCOUNT_TABLE, null, discountCV);
    }

    public Cursor getDiscountEntry(int child_id) {

        String whereClause = "discount_child_id = ?";
        String[] whereArgs = new String[]{String.valueOf(child_id)};

        String[] columns = new String[]{KEY_DISCOUNT_ROWID, KEY_DISCOUNT_CHILD_ID, KEY_DISCOUNT_DESC, KEY_DISCOUNT_AMOUNT};
        Cursor c = ourDatabase.query(DISCOUNT_TABLE, columns, whereClause, whereArgs,
                null, null, null);

        return c;
    }

    //////////////// MEDICATION ////////////////////
    public long createMedicationEntry(int m_child_id, String m_name, String m_time) {

        ContentValues medicationCV = new ContentValues();

        medicationCV.put(KEY_MEDICATION_CHILD_ID, m_child_id);
        medicationCV.put(KEY_MEDICATION_NAME, m_name);
        medicationCV.put(KEY_MEDICATION_TIME, m_time);

        return ourDatabase.insert(MEDICATION_TABLE, null, medicationCV);
    }

    public Cursor getMedicationEntry(int child_id) {

        String whereClause = "medication_child_id = ?";
        String[] whereArgs = new String[]{String.valueOf(child_id)};

        String[] columns = new String[]{KEY_MEDICATION_ROWID, KEY_MEDICATION_CHILD_ID, KEY_MEDICATION_NAME, KEY_MEDICATION_TIME};
        Cursor c = ourDatabase.query(MEDICATION_TABLE, columns, whereClause, whereArgs,
                null, null, null);

        return c;
    }

    //////////////// SHOT RECORD ////////////////////
    public long createShotsEntry(int s_child_id, String s_flu_date, String s_immunization_date, Bitmap s_record_image) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        s_record_image.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] picArray = stream.toByteArray();

        ContentValues shotsCV = new ContentValues();

        shotsCV.put(KEY_SHOT_RECORD_CHILD_ID, s_child_id);
        shotsCV.put(KEY_SHOT_RECORD_FLU_SHOT_DATE, s_flu_date);
        shotsCV.put(KEY_SHOT_RECORD_IMMUNIZATION_DATE, s_immunization_date);
        shotsCV.put(KEY_SHOT_RECORD_IMMUNIZATION_IMAGE, picArray);

        return ourDatabase.insert(SHOT_RECORD_TABLE, null, shotsCV);
    }

    public Cursor getShotsEntry(int child_id) {

        String whereClause = "shot_record_child_id = ?";
        String[] whereArgs = new String[]{String.valueOf(child_id)};

        String[] columns = new String[]{KEY_SHOT_RECORD_ROWID, KEY_SHOT_RECORD_CHILD_ID, KEY_SHOT_RECORD_FLU_SHOT_DATE,
                KEY_SHOT_RECORD_IMMUNIZATION_DATE, KEY_SHOT_RECORD_IMMUNIZATION_IMAGE};
        Cursor c = ourDatabase.query(MEDICATION_TABLE, columns, whereClause, whereArgs,
                null, null, null);

        return c;
    }

    public void saveEventtoDB(String event, String sTime, String eTime, String sselectedDate) {

        ContentValues newEventCV = new ContentValues();
        newEventCV.put(KEY_CAL_EVENT_TITLE, event);
        newEventCV.put(KEY_CAL_START_TIME, sTime);
        newEventCV.put(KEY_CAL_END_TIME, eTime);
        newEventCV.put(KEY_CAL_DATE, sselectedDate);

        ourDatabase.insert(CALENDAR_EVENTS_TABLE, null, newEventCV);
    }

    public Cursor getCursorEventData(String sselectedDate) {
        String whereClause = "cal_date = ? ";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_CAL_ROWID, KEY_CAL_EVENT_TITLE,
                KEY_CAL_START_TIME, KEY_CAL_END_TIME, KEY_CAL_DATE};
        Cursor c = ourDatabase.query(CALENDAR_EVENTS_TABLE, columns, whereClause, whereArgs,
                null, null, null);
        c.moveToFirst();
        return c;
    }

    public void saveTasktoDB(String task, String sselectedDate, String time) {
        ContentValues newTaskCV = new ContentValues();
        newTaskCV.put(KEY_TODO_TITLE, task);
        newTaskCV.put(KEY_TODO_DATE, sselectedDate);
        newTaskCV.put(KEY_TODO_TIME, time);
        newTaskCV.put(KEY_TODO_STATUS, "NOT Complete");

        ourDatabase.insert(TODO_LIST_TABLE, null, newTaskCV);
    }

    public Cursor getCursorTaskData(String sselectedDate) {
        String whereClause = "todo_date = ? ";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_TODO_ROWID, KEY_TODO_TITLE,
                KEY_TODO_DATE, KEY_TODO_TIME, KEY_TODO_STATUS};
        Cursor c = ourDatabase.query(TODO_LIST_TABLE, columns, whereClause, whereArgs,
                null, null, null);
        c.moveToFirst();
        return c;
    }

    public String updateTaskDB(String task, String sselectedDate, String taskStatus) {
        String s = null;
        String whereClause = "todo_title = ? AND todo_date = ? ";
        String[] whereArgs = new String[]{task, sselectedDate};
        ContentValues updateTaskCV = new ContentValues();
        if (taskStatus.equals("NOT Complete")) {
            updateTaskCV.put(KEY_TODO_STATUS, "Complete");
        }
        if (taskStatus.equals("Complete")) {
            updateTaskCV.put(KEY_TODO_STATUS, "NOT Complete");
        }
        ourDatabase.update(TODO_LIST_TABLE, updateTaskCV, whereClause, whereArgs);
        return s;
    }

    public List<String> getfoodItems(String selected) {
        List<String> foodItems = new ArrayList<String>();
        // Select All Query
        String whereClause1 = null;
        String[] whereArgs1 = null;
        if (selected.equals("Enter Meal Name")) {
            whereClause1 = KEY_FOODITEM_GROUP + " = ?";
            whereArgs1 = new String[]{"Meal Name"};
        }

        if (selected.equals("Select Any Food Item 1")) {
            whereClause1 = KEY_FOODITEM_GROUP + " != ?";
            whereArgs1 = new String[]{"Meal Name"};
        }
        if (selected.equals("Select Any Food Item 2")) {
            whereClause1 = KEY_FOODITEM_GROUP + " != ?";
            whereArgs1 = new String[]{"Meal Name"};
        }

        if (selected.equals("Select Drink Item")) {
            whereClause1 = KEY_FOODITEM_GROUP + " = ?";
            whereArgs1 = new String[]{"Drink"};
        }
        if (selected.equals("Select Bread Item")) {
            whereClause1 = KEY_FOODITEM_GROUP + " = ?";
            whereArgs1 = new String[]{"Bread"};
        }
        if (selected.equals("Select Fruit or Veggie Item 1")) {
            whereClause1 = KEY_FOODITEM_GROUP + " = ?";
            whereArgs1 = new String[]{"Fruits & Veggies"};
        }
        if (selected.equals("Select Fruit or Veggie Item 2")) {
            whereClause1 = KEY_FOODITEM_GROUP + " = ?";
            whereArgs1 = new String[]{"Fruits & Veggies"};
        }
        if (selected.equals("Select Protein Item")) {
            whereClause1 = KEY_FOODITEM_GROUP + " = ?";
            whereArgs1 = new String[]{"Protein"};
        }
        String[] columns = new String[]{KEY_FOODITEM_NAME};
        try {
            Cursor cursor = ourDatabase.query(FOODITEM_TABLE, columns, whereClause1,
                    whereArgs1, null, null, null);

            foodItems.add("Select an Item...");
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String fooditem = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_FOODITEM_NAME));

                    foodItems.add(fooditem);

                } while (cursor.moveToNext());
            }

            // closing connection
            cursor.close();
            ourDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // returning childDatas
        return foodItems;
    }

//    public String[] getDiscount(String cfn, String cln) {
//        String[] discountData = new String[2];
//        String whereClause = "child_first_name = ? AND child_last_name = ?";
//        String[] whereArgs = new String[]{cfn, cln};
//
//        String[] columns = new String[]{KEY_CHILD_FNAME, KEY_CHILD_LNAME, KEY_CHILD_DISCOUNT_TYPE,
//                KEY_CHILD_DISCOUNT_AMOUNT};
//        Cursor c = ourDatabase.query(CHILD_TABLE, columns, whereClause, whereArgs,
//                null, null, null);
//
//        if (c.getCount() == 0) {
//
//        } else {
//            c.moveToFirst();
//            discountData[0] = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_DISCOUNT_TYPE));
//            discountData[1] = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_DISCOUNT_AMOUNT));
//        }
//        c.close();
//
//        return discountData;
//    }
//
//    public String getChildRecordNum(String cfn, String cln) {
//
//        String whereClause = "child_first_name = ? AND child_last_name = ?";
//        String[] whereArgs = new String[]{cfn, cln};
//
//        String[] columns = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME,
//                KEY_CHILD_LNAME};
//        String recNum = "";
//        try {
//            Cursor c = ourDatabase.query(CHILD_TABLE, columns, whereClause, whereArgs,
//                    null, null, null);
//
//            if (!(c.getCount() == -1)) {
//                if (c.moveToFirst()) {
//                    recNum = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID));
//                    c.close();
//                } else {
//
//                }
//
//            } else {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//
//        return recNum;
//    }


//    /////////////////////////////////////////////////////////////////
//    public void updateParentEntry(String pMfn, String pMLn, String pMcell,
//                                  String pMhome, String pMwork, String pMemail,
//                                  String pDfn, String pDLn, String pDcell,
//                                  String pDhome, String pDwork, String pDRnum,
//                                  String pMRnum, String pDemail) {
//
//        ContentValues parentMCV = new ContentValues();
//        ContentValues parentDCV = new ContentValues();
//
//        String whereClause = "_id = ? ";
//        String[] whereArgsMOM = new String[]{pMRnum};
//        String[] whereArgsDAD = new String[]{pDRnum};
//
//        parentMCV.put(KEY_PARENT_FNAME, pMfn);
//        parentMCV.put(KEY_PARENT_LNAME, pMLn);
//        parentDCV.put(KEY_PARENT_FNAME, pDfn);
//        parentDCV.put(KEY_PARENT_LNAME, pDLn);
//        parentMCV.put(KEY_PARENT_CPHONE, pMcell);
//        parentMCV.put(KEY_PARENT_HPHONE, pMhome);
//        parentMCV.put(KEY_PARENT_WPHONE, pMwork);
//        parentDCV.put(KEY_PARENT_CPHONE, pDcell);
//        parentDCV.put(KEY_PARENT_HPHONE, pDhome);
//        parentDCV.put(KEY_PARENT_WPHONE, pDwork);
//        parentMCV.put(KEY_PARENT_EMAIL, pMemail);
//        parentDCV.put(KEY_PARENT_EMAIL, pDemail);
//
//
//        if (pDfn != null) {
//            parentDCV.put(KEY_PARENT_MORD, "Father");
//        }
//        if (pMfn != null) {
//            parentMCV.put(KEY_PARENT_MORD, "Mother");
//        }
//
//        ourDatabase.update(PARENT_TABLE, parentMCV, whereClause, whereArgsMOM);
//        ourDatabase.update(PARENT_TABLE, parentDCV, whereClause, whereArgsDAD);
//
//    }
///////////////////////////////////////////////////////////////
//
//    ///////////  Get child data for CHILD RECORD REG Update
//
//    public long updateChildEntry(String Cid, String cfn, String cln,
//                                 String cbd, String ced, String cadd, String cdtap, //String cmmr, String chepb,
//                                 String cmed, String cmedT1, String cAllergy,
//                                 String cAge, Bitmap cPic, String cParent1, String cParent2,
//                                 String child_fav_activity, String cCanPickUp_DropOff_name,
//                                 String cCanPickUp_DropOff_num, String discountType, String discountAmount) {
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        cPic.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] picArray = stream.toByteArray();
//
//        String whereClause = "_id = ? ";
//        String[] whereArgs = new String[]{Cid};
//
//        ContentValues childCV = new ContentValues();
//
//        childCV.put(KEY_CHILD_FNAME, cfn);
//        childCV.put(KEY_CHILD_LNAME, cln);
//        childCV.put(KEY_CHILD_BDATE, cbd);
//        childCV.put(KEY_CHILD_EDATE, ced);
//        childCV.put(KEY_CHILD_ADDRESS, cadd);
//        childCV.put(KEY_CHILD_DTAP, cdtap);
////        childCV.put(KEY_CHILD_MMR, cmmr);
////        childCV.put(KEY_CHILD_HEPB, chepb);
//        childCV.put(KEY_CHILD_MED1, cmed);
//        childCV.put(KEY_CHILD_MED1_TIME1, cmedT1);
//        childCV.put(KEY_CHILD_CALLERGY, cAllergy);
//        childCV.put(KEY_CHILD_AGE, cAge);
//        childCV.put(KEY_CHILD_FAV_ACTIVITY, child_fav_activity);
//        childCV.put(KEY_CHILD_PIC, picArray);
//        childCV.put(KEY_CHILD_PARENT1, cParent1);
//        childCV.put(KEY_CHILD_PARENT2, cParent2);
//        childCV.put(KEY_CHILD_ALLOWED2_PU_DO_NAME, cCanPickUp_DropOff_name);
//        childCV.put(KEY_CHILD_ALLOWED2_PU_DO_NUM, cCanPickUp_DropOff_num);
//        childCV.put(KEY_CHILD_DISCOUNT_TYPE, discountType);
//        childCV.put(KEY_CHILD_DISCOUNT_AMOUNT, discountAmount);
//
//        return ourDatabase.update(CHILD_TABLE, childCV, whereClause,
//                whereArgs);
//    }
//
//    // Getting Data for ChildrenEnrolledActivity [ListView]
//    public Cursor getCursorChildList() {
//        String[] columns = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME,
//                KEY_CHILD_LNAME, KEY_CHILD_EDATE, KEY_CHILD_AGE, KEY_CHILD_INCARE,
//                KEY_CHILD_PIC};
//
//        Cursor c = ourDatabase.query(CHILD_TABLE, columns, null, null,
//                null, null, null);
//        c.moveToLast();
//        try {
//            String s = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_INCARE));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return c;
//    }

//    public Cursor get_ALL_RegData() {
//        String[] columns = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME,
//                KEY_CHILD_LNAME, KEY_CHILD_EDATE, KEY_CHILD_AGE, KEY_CHILD_INCARE,
//                KEY_CHILD_PIC};
//
//        Cursor c = ourDatabase.query(CHILD_TABLE, columns, null, null,
//                null, null, null);
//        return c;
//    }

//    public String createAttenNEWEntry(String cIn, String cfn, String cln, String clockINDate, String clockOUTDate) {
//        String result = "";
//        String childFullName = cfn + " " + cln;
//
//        //////// to create the attendance entry
//
//        ContentValues childAttenCV = new ContentValues();
//        childAttenCV.put(KEY_ATTEN_CHILD, childFullName);
//        childAttenCV.put(KEY_ATTEN_DATE_TIME_IN, clockINDate);
//        if (TextUtils.isEmpty(clockOUTDate)) {
//            //////// to update ChildData record showing in care status
//            ContentValues childCV = new ContentValues();
//            childCV.put(KEY_CHILD_INCARE, "YES");
//            String whereClause1 = KEY_CHILD_ROWID + " = ?";
//            String[] whereArgs2 = new String[]{cIn};
//
//            // UPDATE CURRENT RECORD   run it
//            try {
//                ourDatabase.update(CHILD_TABLE, childCV, whereClause1,
//                        whereArgs2);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            String[] columns = new String[]{KEY_ATTEN_ROWID, KEY_ATTEN_CHILD,
//                    KEY_ATTEN_DATE_TIME_IN, KEY_ATTEN_DATE_TIME_OUT};
//            String[] whereArgs1 = new String[]{childFullName};
//
//            Cursor c = ourDatabase.query(ATTENDANCE_TABLE, columns, whereClause1,
//                    whereArgs1, null, null, null);
//            c.moveToLast();
//            ContentValues childUpAttenCV = new ContentValues();
//            while (!c.isAfterLast()) {
//
//                String sID = c.getString(c
//                        .getColumnIndex(daycaremanagerDB.KEY_ATTEN_ROWID));
//                String whereClause3 = KEY_ATTEN_ROWID + " = ?";
//                String[] whereArgs3 = new String[]{sID};
//
//                /////// Update Atten Table ////////////////////////////////////
//                childUpAttenCV.put(KEY_ATTEN_DATE_TIME_OUT, clockOUTDate);
//
//                ourDatabase.update(ATTENDANCE_TABLE, childUpAttenCV, whereClause3,
//                        whereArgs3);
//                ////////////////////////////////////////////////////////////////
//
//                result = childFullName + " has been checked out.";
//
//            }
//        } else {
//            childAttenCV.put(KEY_ATTEN_DATE_TIME_OUT, clockOUTDate);
//            result = "Attendance Record added for " + childFullName;
//
//            // ADD NEW RECORD   run it
//            try {
//                ourDatabase.insert(ATTENDANCE_TABLE, null, childAttenCV);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }

//    public String createAttenEntry(String cIn, String childFName,
//                                   String childLName, String clockDate) {
//        String result = "";
//        String childFullName = childFName + " " + childLName;
//
//        //////// to update ChildData record showing in care status
//        ContentValues childCV = new ContentValues();
//        childCV.put(KEY_CHILD_INCARE, "YES");
//        String whereClause1 = KEY_CHILD_ROWID + " = ?";
//
//        String[] whereArgs1 = new String[]{cIn};
//
//        //c.moveToLast();
//
//        // run it
//        try {
//            ourDatabase.update(CHILD_TABLE, childCV, whereClause1,
//                    whereArgs1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //////// to create the attendance entry
//
//        ContentValues childAttenCV = new ContentValues();
//        childAttenCV.put(KEY_ATTEN_CHILD, childFullName);
//        childAttenCV.put(KEY_ATTEN_DATE_TIME_IN, clockDate);
//        //childAttenCV.put(KEY_ATTEN_TIME_IN1, clockTime);
//        // run it
//        try {
//            ourDatabase.insert(ATTENDANCE_TABLE, null, childAttenCV);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result = childFullName + " has been checked in.";
//    }

//    public String getCurrentChildName(String cID) {
//        String[] columns = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME,
//                KEY_CHILD_LNAME};
//        String whereClause1 = KEY_CHILD_ROWID + " = ?";
//        String[] whereArgs1 = new String[]{cID};
//        Cursor c = ourDatabase.query(CHILD_TABLE, columns, whereClause1, whereArgs1,
//                null, null, null);
//        c.moveToFirst();
//        String oldName = " ";
//        try {
//            String f = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME));
//            String l = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME));
//            oldName = f + " " + l;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return oldName;
//    }
//
//    public void updateChildAtten(String fullNewName, String fullOldName) {
//        ContentValues childCV = new ContentValues();
//        String whereClause2 = KEY_ATTEN_CHILD + " = ?";
//        String[] whereArgs2 = new String[]{fullOldName};
//        childCV.put(KEY_ATTEN_CHILD, fullNewName);
//        ourDatabase.update(ATTENDANCE_TABLE, childCV, whereClause2,
//                whereArgs2);
//    }


//    public String updateAttenEntry(String cIn, String cfn, String cln, String clockDate) {
//        ContentValues childAttenCV = new ContentValues();
//        ContentValues childCV = new ContentValues();
//
//        String result = "";
//        String childFullName = cfn + " " + cln;
//        String whereClause1 = KEY_ATTEN_CHILD + " = ?";
//        //String order = KEY_ATTEN_DATE_TIME_IN + " DESC";
//        String[] columns = new String[]{KEY_ATTEN_ROWID, KEY_ATTEN_CHILD,
//                KEY_ATTEN_DATE_TIME_IN, KEY_ATTEN_DATE_TIME_OUT};
//        String[] whereArgs1 = new String[]{childFullName};
//
//        Cursor c = ourDatabase.query(ATTENDANCE_TABLE, columns, whereClause1,
//                whereArgs1, null, null, null);
//        String isDateOut = "";
//        c.moveToLast();
//        while (!c.isAfterLast()) {
//            //isTimeOut = c.getString(c
//            // .getColumnIndex(daycaremanagerDB.KEY_ATTEN_TIME_OUT1));
//            isDateOut = c.getString(c
//                    .getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_TIME_OUT));
//            String sID = c.getString(c
//                    .getColumnIndex(daycaremanagerDB.KEY_ATTEN_ROWID));
//            String whereClause3 = KEY_ATTEN_ROWID + " = ?";
//            String[] whereArgs3 = new String[]{sID};
//
//            if (isDateOut == null) {
//                /////// Update Atten Table ////////////////////////////////////
//                childAttenCV.put(KEY_ATTEN_DATE_TIME_OUT, clockDate);
//                //childAttenCV.put(KEY_ATTEN_TIME_OUT1, clockTime);
//
//                ourDatabase.update(ATTENDANCE_TABLE, childAttenCV, whereClause3,
//                        whereArgs3);
//                ////////////////////////////////////////////////////////////////
//
//                ////// Update ChildData Table  ///////////////////////////////////
//                String whereClause2 = KEY_CHILD_ROWID + " = ?";
//                String[] whereArgs2 = new String[]{cIn};
//                childCV.put(KEY_CHILD_INCARE, "NO");
//                ourDatabase.update(CHILD_TABLE, childCV, whereClause2,
//                        whereArgs2);
//                /////////////////////////////////////////////////////////////
//                result = childFullName + " has been checked out.";
//
//            } else {
//                String whereClause2 = KEY_CHILD_ROWID + " = ?";
//                String[] whereArgs2 = new String[]{cIn};
//                childCV.put(KEY_CHILD_INCARE, "NO");
//                result = childFullName + " is already checked out.";
//                ourDatabase.update(CHILD_TABLE, childCV, whereClause2,
//                        whereArgs2);
//            }
//            c.moveToNext();
//        }
//        return result;
//    }

//    public Cursor get_ALL_AttenData() {
//        String[] columns = new String[]{KEY_ATTEN_ROWID, KEY_ATTEN_CHILD,
//                KEY_ATTEN_DATE_TIME_IN, KEY_ATTEN_DATE_TIME_OUT};
//
//        Cursor c = ourDatabase.query(ATTENDANCE_TABLE, columns, null, null,
//                null, null, null);
//        return c;
//    }

//    public Cursor get_Child_AttenData(String childName) {
//        String[] columns = new String[]{KEY_ATTEN_ROWID, KEY_ATTEN_CHILD,
//                KEY_ATTEN_DATE_TIME_IN, KEY_ATTEN_DATE_TIME_OUT};
//        String whereClause1 = KEY_ATTEN_CHILD + " = ?";
//        String[] whereArgs1 = new String[]{childName};
//
//        Cursor c = ourDatabase.query(ATTENDANCE_TABLE, columns, whereClause1, whereArgs1,
//                null, null, null);
//
//        return c;
//    }

    /////////////// DELETE CHILD & all records from atten for the child
//    public void deleteChild(String selectedChild) {
//
//        String whereClause1 = KEY_ATTEN_CHILD + " = ?";
//        String[] whereArgs1 = new String[]{selectedChild};
//        ourDatabase.delete(ATTENDANCE_TABLE, whereClause1, whereArgs1);
//
//        String whereClause2 = KEY_CHILD_FNAME + " = ? AND " + KEY_CHILD_LNAME + " = ?";
//        String[] whereArgs2 = selectedChild.split("\\s+");
//        ourDatabase.delete(CHILD_TABLE, whereClause2, whereArgs2);
//
//    }

//    public String get_Child_Age(String billRep_child) {
//        String[] splitP1FandL = billRep_child.split("\\s+");
//        String[] ChildColumns = new String[]{KEY_CHILD_FNAME, KEY_CHILD_LNAME,
//                KEY_CHILD_AGE};
//        Cursor c2 = ourDatabase.query(CHILD_TABLE, ChildColumns,
//                KEY_CHILD_FNAME + "=?" + " AND " + KEY_CHILD_LNAME + "=?",
//                splitP1FandL, null, null, null);
//        int numRows = c2.getCount();
//        c2.moveToFirst();
//        String cAge = c2.getString(c2
//                .getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE));
//        c2.close();
//        return cAge;
//    }

    /////////////////////////billing
//    public String[] FetchAttenData(String childName, String start, String end) {
//        String[] ChildColumns = new String[]{KEY_CHILD_FNAME, KEY_CHILD_LNAME,
//                KEY_CHILD_AGE};
//        String whereClause1 = KEY_ATTEN_CHILD + " = ?";
//
//        String[] whereArgs1 = new String[]{childName};
//
//        String[] splitP1FandL = childName.split("\\s+");
//
//
//        Cursor c = ourDatabase.rawQuery("SELECT * FROM " + ATTENDANCE_TABLE + " WHERE "
//                + KEY_ATTEN_CHILD + " = ?", whereArgs1);
//        Cursor c2 = ourDatabase.query(CHILD_TABLE, ChildColumns,
//                KEY_CHILD_FNAME + "=?" + " AND " + KEY_CHILD_LNAME + "=?",
//                splitP1FandL, null, null, null);
//
//        int numRows = c2.getCount();
//        c.moveToFirst();
//        c2.moveToFirst();
//        String cAge = c2.getString(c2
//                .getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE));
//        int recNum = 1;
//        String resultData = "";
//        float TotalBillAmount = 0;
//
//        // Get Prefs
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        ///////////////////GET AGE GROUP AMOUNTS
//        String billAmountHR = "";
//        String billAmountDAY = "";
//        switch (cAge) {
//            case "Infant":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_Infants", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_Infants", "20.00");
//                break;
//            case "Toddler":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_Toddler", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_Toddlers", "20.00");
//                break;
//            case "Preschool":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_Preschool", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_Preschool", "20.00");
//                break;
//            case "School Age":
//                billAmountHR = sharedPrefs.getString("prefBilling_Hourly_SchoolAge", "2.00");
//                billAmountDAY = sharedPrefs.getString("prefBilling_Daily_SchoolAge", "20.00");
//                break;
//        }
//        String billHrInDay = sharedPrefs.getString("prefBilling_numHrs41Day", "3");
//
//        while (!c.isAfterLast()) {
//
//            String inDate = c.getString(c
//                    .getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_TIME_IN));
//            String outDate = c.getString(c
//                    .getColumnIndex(daycaremanagerDB.KEY_ATTEN_DATE_TIME_OUT));
//
//            String BillStart = start;
//            String BillEnd = end;
//            String in = inDate;
//            String out = outDate;
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-d hh:mm a");
//            Date d1 = null;
//            Date d2 = null;
//            Date b1 = null;
//            Date b2 = null;
//            try {
//                if (TextUtils.isEmpty(outDate)) {
//                    break;
//                } else {
//                    d2 = format.parse(out);
//                    b2 = format.parse(BillEnd);
//                }
//                d1 = format.parse(in);
//                b1 = format.parse(BillStart);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//                c.moveToNext();
//            }
//            if (d1.after(b1) && d1.before(b2)) {
//                long diff = d2.getTime() - d1.getTime();
//                //long diffSec = diff / 1000 % 60;
//                long diffMin = diff / (60 * 1000) % 60;
//                long diffHr = diff / (60 * 60 * 1000) % 24;
//                long diffDay = diff / (24 * 60 * 60 * 1000);
//
//
//                //Convert Prefs to float
//                float f_BillAmountHR = 0;
//                float f_BillAmountDAY = 0;
//                float f_BillHrInDAY = 0;
//                try {
//                    f_BillAmountHR = Float.valueOf(billAmountHR.trim()).floatValue();
//                    f_BillAmountDAY = Float.valueOf(billAmountDAY.trim()).floatValue();
//                    f_BillHrInDAY = Float.valueOf(billHrInDay.trim()).floatValue();
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//
//                float billAmount = 0;
//                if (diffDay > 0) {
//                    billAmount = f_BillAmountDAY * diffDay;
//                }
//                if (diffHr >= f_BillHrInDAY) {
//                    billAmount = billAmount + f_BillAmountDAY;
//                }
//                if (diffHr < f_BillHrInDAY) {
//                    billAmount = f_BillAmountHR * diffHr;
//
//                    if (diffMin >= 45) {
//                        billAmount = billAmount + f_BillAmountHR;
//                    }
//                    if (diffMin < 45 && diffMin > 30) {
//                        billAmount = billAmount + (f_BillAmountHR / 4) * 3;
//                    }
//                    if (diffMin <= 30 && diffMin > 15) {
//                        billAmount = billAmount + (f_BillAmountHR / 2);
//                    }
//                    if (diffMin <= 15 && diffMin > 0) {
//                        billAmount = billAmount + (f_BillAmountHR / 4);
//                    }
//                }
//
//                String dayBillResult = String.format("%.2f", billAmount);
//
//                TotalBillAmount = TotalBillAmount + billAmount;
//                String timeInCare = diffHr + " hr(s): " + diffMin + " min(s)";
//                resultData = resultData + "| " + recNum
//                        + " | \nChecked In: " + inDate
//                        + "\nChecked Out: " + outDate
//                        + "\nTime in-Care: (" + timeInCare + ")\t\t$ " + dayBillResult + "\n";
//                recNum++;
//                c.moveToNext();
//            } else {
//                c.moveToNext();
//            }
//        }
//        String resultHead = "Attendance Record for "
//                + childName + " \nfrom: "
//                + start + "\nTo: "
//                + end + "\n\n";
//        String finResult = String.format("%.2f", TotalBillAmount);
//        String[] result = new String[100];
//        result[0] = resultHead + resultData;
//        result[1] = finResult;
//
//        return result;
//    }

//    public String getChildNameINCARE(String cIn, String cfn, String cln) {
//
//        String[] columns1 = new String[]{KEY_CHILD_ROWID, KEY_CHILD_FNAME, KEY_CHILD_LNAME,
//                KEY_CHILD_INCARE};
//        String whereClause1 = KEY_CHILD_ROWID + " = ?";
//        String[] whereArgs1 = new String[]{cIn};
//        String in_care = "";
//
//        try {
//            Cursor c = ourDatabase.query(CHILD_TABLE, columns1, whereClause1,
//                    whereArgs1, null, null, null);
//            if (c != null) {
//                c.moveToFirst();
//                try {
//                    in_care = c.getString(c.getColumnIndex(daycaremanagerDB.KEY_CHILD_INCARE));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return in_care;
//    }

//    public List<String> getAllChildren() {
//        List<String> children = new ArrayList<String>();
//
//        // Select All Query
//
//        String[] columns = new String[]{KEY_CHILD_FNAME, KEY_CHILD_LNAME};
//        try {
//            Cursor cursor = ourDatabase.query(CHILD_TABLE, columns, null,
//                    null, null, null, null);
//
//            children.add("Select ChildData to load");
//            // looping through all rows and adding to list
//            if (cursor.moveToFirst()) {
//                do {
//                    String childFname = cursor.getString(cursor
//                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME));
//                    String childLname = cursor.getString(cursor
//                            .getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME));
//                    children.add(childFname + " " + childLname);
//
//                } while (cursor.moveToNext());
//            }
//
//            // closing connection
//            cursor.close();
//            ourDatabase.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // returning childDatas
//        return children;
//    }

    public String putIteminDB(String usersInput, String selected) {
        String s = null;
        String result = null;
        //////// to update ChildData record showing in care status
        ContentValues itemCV = new ContentValues();
        itemCV.put(KEY_FOODITEM_GROUP, s);
        if (selected.equals("Enter Meal Name")) {
            s = "Meal Name";
            itemCV.put(KEY_FOODITEM_GROUP, s);
            itemCV.put(KEY_FOODITEM_NAME, usersInput);
        }

        if (selected.equals("Select Any Food Item 1") ||
                selected.equals("Select Any Food Item 2")) {

        }


        if (selected.equals("Select Drink Item")) {
            s = "Drink";
            itemCV.put(KEY_FOODITEM_GROUP, s);
            itemCV.put(KEY_FOODITEM_NAME, usersInput);
        }
        if (selected.equals("Select Bread Item")) {
            s = "Bread";
            itemCV.put(KEY_FOODITEM_GROUP, s);
            itemCV.put(KEY_FOODITEM_NAME, usersInput);
        }
        if (selected.equals("Select Fruit or Veggie Item 1")) {
            s = "Fruits & Veggies";
            itemCV.put(KEY_FOODITEM_GROUP, s);
            itemCV.put(KEY_FOODITEM_NAME, usersInput);
        }
        if (selected.equals("Select Fruit or Veggie Item 2")) {
            s = "Fruits & Veggies";
            itemCV.put(KEY_FOODITEM_GROUP, s);
            itemCV.put(KEY_FOODITEM_NAME, usersInput);
        }
        if (selected.equals("Select Protein Item")) {
            s = "Protein";
            itemCV.put(KEY_FOODITEM_GROUP, s);
            itemCV.put(KEY_FOODITEM_NAME, usersInput);
        }


        // run it
        try {
            ourDatabase.insert(FOODITEM_TABLE, null, itemCV);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result = usersInput + " has been added as " + s;
    }

    //// Save Breakfast/AM Snack
    public void saveNewMealPlanB(String selectedDay, String sBmealName, String sBdrink, String sBbread,
                                 String sBforv2, String sBamS1, String sBamS2) {

        // Check if meal plan exists for lunch for the day, if so - update
        try {
            final String whereClause1 = "meal_date = ?";
            String[] columns = new String[]{KEY_MEAL_DATE, KEY_MEAL_B_NAME,
                    KEY_MEAL_B_DRINK, KEY_MEAL_B_BREAD, KEY_MEAL_B_FRUIT_VEG_JUICE,
                    KEY_MEAL_B_AMSNACK1, KEY_MEAL_B_AMSNACK2, KEY_MEAL_D_PMSNACK2};
            final String[] whereArgs1 = new String[]{selectedDay};
            Cursor c = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause1,
                    whereArgs1, null, null, null);
            final ContentValues mealBCV = new ContentValues();
            mealBCV.put(KEY_MEAL_B_NAME, sBmealName);
            mealBCV.put(KEY_MEAL_B_DRINK, sBdrink);
            mealBCV.put(KEY_MEAL_B_BREAD, sBbread);
            mealBCV.put(KEY_MEAL_B_FRUIT_VEG_JUICE, sBforv2);
            mealBCV.put(KEY_MEAL_B_AMSNACK1, sBamS1);
            mealBCV.put(KEY_MEAL_B_AMSNACK2, sBamS2);
            if (c.moveToLast()) {
                ourDatabase.update(MEAL_PLAN_TABLE, mealBCV, whereClause1,
                        whereArgs1);
            } else {
                mealBCV.put(KEY_MEAL_DATE, selectedDay);
                ourDatabase.insert(MEAL_PLAN_TABLE, null, mealBCV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //// Save Lunch/PM Snack
    public void saveNewMealPlanL(String selectedDay, String sLmealName, String sLdrink, String sLbread,
                                 String sLforv1, String sLforv2, String sLpro, String sLpmS1, String sLpmS2) {
        // Check if meal plan exists for lunch for the day, if so - update
        try {
            String whereClause1 = "meal_date = ?";
            String[] columns = new String[]{KEY_MEAL_DATE, KEY_MEAL_L_NAME,
                    KEY_MEAL_L_DRINK, KEY_MEAL_L_BREAD, KEY_MEAL_L_FRUIT_VEG1,
                    KEY_MEAL_L_FRUIT_VEG2, KEY_MEAL_L_PROTEIN, KEY_MEAL_L_PMSNACK1,
                    KEY_MEAL_L_PMSNACK2};
            String[] whereArgs1 = new String[]{selectedDay};
            Cursor c = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause1,
                    whereArgs1, null, null, null);
            ContentValues mealLCV = new ContentValues();
            mealLCV.put(KEY_MEAL_L_NAME, sLmealName);
            mealLCV.put(KEY_MEAL_L_DRINK, sLdrink);
            mealLCV.put(KEY_MEAL_L_BREAD, sLbread);
            mealLCV.put(KEY_MEAL_L_FRUIT_VEG1, sLforv1);
            mealLCV.put(KEY_MEAL_L_FRUIT_VEG2, sLforv2);
            mealLCV.put(KEY_MEAL_L_PROTEIN, sLpro);
            mealLCV.put(KEY_MEAL_L_PMSNACK1, sLpmS1);
            mealLCV.put(KEY_MEAL_L_PMSNACK2, sLpmS2);
            if (c.moveToLast()) {
                ourDatabase.update(MEAL_PLAN_TABLE, mealLCV, whereClause1,
                        whereArgs1);
            } else {
                mealLCV.put(KEY_MEAL_DATE, selectedDay);
                ourDatabase.insert(MEAL_PLAN_TABLE, null, mealLCV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //// Save Dinner/Night Snack
    public void saveNewMealPlanD(String selectedDay, String sDmealName, String sDdrink, String sDbread,
                                 String sDforv1, String sDforv2, String sDpro, String sDpmS1, String sDpmS2) {
        // Check if meal plan exists for lunch for the day, if so - update
        try {
            String whereClause1 = "meal_date = ?";
            String[] columns = new String[]{KEY_MEAL_DATE, KEY_MEAL_D_NAME,
                    KEY_MEAL_D_DRINK, KEY_MEAL_D_BREAD, KEY_MEAL_D_FRUIT_VEG1,
                    KEY_MEAL_D_FRUIT_VEG2, KEY_MEAL_D_PROTEIN, KEY_MEAL_D_PMSNACK1,
                    KEY_MEAL_D_PMSNACK2};
            String[] whereArgs1 = new String[]{selectedDay};
            Cursor c = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause1,
                    whereArgs1, null, null, null);
            ContentValues mealDCV = new ContentValues();
            mealDCV.put(KEY_MEAL_D_NAME, sDmealName);
            mealDCV.put(KEY_MEAL_D_DRINK, sDdrink);
            mealDCV.put(KEY_MEAL_D_BREAD, sDbread);
            mealDCV.put(KEY_MEAL_D_FRUIT_VEG1, sDforv1);
            mealDCV.put(KEY_MEAL_D_FRUIT_VEG2, sDforv2);
            mealDCV.put(KEY_MEAL_D_PROTEIN, sDpro);
            mealDCV.put(KEY_MEAL_D_PMSNACK1, sDpmS1);
            mealDCV.put(KEY_MEAL_D_PMSNACK2, sDpmS2);
            if (c.moveToLast()) {
                ourDatabase.update(MEAL_PLAN_TABLE, mealDCV, whereClause1,
                        whereArgs1);
            } else {
                mealDCV.put(KEY_MEAL_DATE, selectedDay);
                ourDatabase.insert(MEAL_PLAN_TABLE, null, mealDCV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMPbreakfastItems(String sselectedDate) {
        String whereClause = "meal_date = ?";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_MEAL_B_NAME, KEY_MEAL_B_DRINK,
                KEY_MEAL_B_FRUIT_VEG_JUICE, KEY_MEAL_B_BREAD};
        ArrayList<String> foodItems = new ArrayList<String>();
        try {
            Cursor cursor = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause,
                    whereArgs, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String bName = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_B_NAME));
                    String bdrink = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_B_DRINK));
                    String bforveg = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_B_FRUIT_VEG_JUICE));
                    String bbread = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_B_BREAD));

                    foodItems.add(bName);
                    foodItems.add(bdrink);
                    foodItems.add(bforveg);
                    foodItems.add(bbread);


                } while (cursor.moveToNext());
            }
            // closing connection
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    public ArrayList<String> getMPAMSnackItems(String sselectedDate) {
        String whereClause = "meal_date = ?";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_MEAL_B_AMSNACK1, KEY_MEAL_B_AMSNACK2};

        ArrayList<String> foodItems2 = new ArrayList<String>();
        try {
            Cursor cursorAM = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause,
                    whereArgs, null, null, null);
            if (cursorAM.moveToFirst()) {
                do {
                    String bAMsnack1 = cursorAM.getString(cursorAM
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_B_AMSNACK1));
                    String bAMsnack2 = cursorAM.getString(cursorAM
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_B_AMSNACK2));

                    foodItems2.add(bAMsnack1);
                    foodItems2.add(bAMsnack2);

                } while (cursorAM.moveToNext());
            }
            // closing connection
            cursorAM.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return foodItems2;
    }

    public ArrayList<String> getMPLunchItems(String sselectedDate) {
        ArrayList<String> foodItems3 = new ArrayList<String>();
        String whereClause = "meal_date = ?";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_MEAL_L_NAME, KEY_MEAL_L_DRINK, KEY_MEAL_L_BREAD,
                KEY_MEAL_L_FRUIT_VEG1, KEY_MEAL_L_FRUIT_VEG2, KEY_MEAL_L_PROTEIN};

        try {
            Cursor cursorL = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause,
                    whereArgs, null, null, null);
            if (cursorL.moveToFirst()) {
                do {
                    String lName = cursorL.getString(cursorL
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_NAME));
                    String ldrink = cursorL.getString(cursorL
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_DRINK));
                    String lbread = cursorL.getString(cursorL
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_BREAD));
                    String lforveg1 = cursorL.getString(cursorL
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_FRUIT_VEG1));
                    String lforveg2 = cursorL.getString(cursorL
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_FRUIT_VEG2));
                    String lprotein = cursorL.getString(cursorL
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_PROTEIN));
                    foodItems3.add(lName);
                    foodItems3.add(ldrink);
                    foodItems3.add(lbread);
                    foodItems3.add(lforveg1);
                    foodItems3.add(lforveg2);
                    foodItems3.add(lprotein);

                } while (cursorL.moveToNext());
            }
            // closing connection
            cursorL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodItems3;
    }

    public ArrayList<String> getMPPMSnackItems(String sselectedDate) {
        ArrayList<String> foodItems4 = new ArrayList<String>();
        String whereClause = "meal_date = ?";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_MEAL_L_PMSNACK1, KEY_MEAL_L_PMSNACK2};

        try {
            Cursor cursorPM = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause,
                    whereArgs, null, null, null);
            if (cursorPM.moveToFirst()) {
                do {
                    String lPMsnack1 = cursorPM.getString(cursorPM
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_PMSNACK1));
                    String lPMsnack2 = cursorPM.getString(cursorPM
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_L_PMSNACK2));

                    foodItems4.add(lPMsnack1);
                    foodItems4.add(lPMsnack2);

                } while (cursorPM.moveToNext());
            }
            // closing connection
            cursorPM.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return foodItems4;
    }

    public ArrayList<String> getMPDinnerItems(String sselectedDate) {
        ArrayList<String> foodItems5 = new ArrayList<String>();
        String whereClause = "meal_date = ?";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_MEAL_D_NAME, KEY_MEAL_D_DRINK, KEY_MEAL_D_BREAD,
                KEY_MEAL_D_FRUIT_VEG1, KEY_MEAL_D_FRUIT_VEG2, KEY_MEAL_D_PROTEIN};

        try {
            Cursor cursorD = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause,
                    whereArgs, null, null, null);
            if (cursorD.moveToFirst()) {
                do {
                    String lName = cursorD.getString(cursorD
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_NAME));
                    String ldrink = cursorD.getString(cursorD
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_DRINK));
                    String lbread = cursorD.getString(cursorD
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_BREAD));
                    String lforveg1 = cursorD.getString(cursorD
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_FRUIT_VEG1));
                    String lforveg2 = cursorD.getString(cursorD
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_FRUIT_VEG2));
                    String lprotein = cursorD.getString(cursorD
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_PROTEIN));
                    foodItems5.add(lName);
                    foodItems5.add(ldrink);
                    foodItems5.add(lbread);
                    foodItems5.add(lforveg1);
                    foodItems5.add(lforveg2);
                    foodItems5.add(lprotein);

                } while (cursorD.moveToNext());
            }
            // closing connection
            cursorD.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodItems5;
    }

    public ArrayList<String> getMPNightPMSnackItems(String sselectedDate) {
        ArrayList<String> foodItems6 = new ArrayList<String>();

        String whereClause = "meal_date = ?";
        String[] whereArgs = new String[]{sselectedDate};
        String[] columns = new String[]{KEY_MEAL_D_PMSNACK1, KEY_MEAL_D_PMSNACK2};

        try {
            Cursor cursorN = ourDatabase.query(MEAL_PLAN_TABLE, columns, whereClause,
                    whereArgs, null, null, null);
            if (cursorN.moveToFirst()) {
                do {
                    String lPMsnack1 = cursorN.getString(cursorN
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_PMSNACK1));
                    String lPMsnack2 = cursorN.getString(cursorN
                            .getColumnIndex(daycaremanagerDB.KEY_MEAL_D_PMSNACK2));

                    foodItems6.add(lPMsnack1);
                    foodItems6.add(lPMsnack2);

                } while (cursorN.moveToNext());
            }
            // closing connection
            cursorN.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodItems6;
    }

    public Context getActivity() {
        return ourContext;
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, KIDDO_CARE_DB, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + CHILD_TABLE + " ("
                    + KEY_CHILD_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_CHILD_FNAME + " TEXT, "
                    + KEY_CHILD_LNAME + " TEXT, "
                    + KEY_CHILD_BDATE + " TEXT, "
                    + KEY_CHILD_EDATE + " TEXT, "
                    + KEY_CHILD_AGE + " TEXT, "
                    + KEY_CHILD_ADDRESS_LN_1 + " TEXT, "
                    + KEY_CHILD_ADDRESS_LN_2 + " TEXT, "
                    + KEY_CHILD_ADDRESS_CITY + " TEXT, "
                    + KEY_CHILD_ADDRESS_STATE + " TEXT, "
                    + KEY_CHILD_ADDRESS_ZIP + " TEXT, "
                    + KEY_CHILD_PIC + " BLOB);");

            db.execSQL("CREATE TABLE " + DISCOUNT_TABLE + " ("
                    + KEY_DISCOUNT_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_DISCOUNT_CHILD_ID + " INTEGER, "
                    + KEY_DISCOUNT_DESC + " TEXT, "
                    + KEY_DISCOUNT_AMOUNT + " REAL);");

            db.execSQL("CREATE TABLE " + SHOT_RECORD_TABLE + " ("
                    + KEY_SHOT_RECORD_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_SHOT_RECORD_CHILD_ID + " INTEGER, "
                    + KEY_SHOT_RECORD_FLU_SHOT_DATE + " TEXT, "
                    + KEY_SHOT_RECORD_IMMUNIZATION_DATE + " TEXT, "
                    + KEY_SHOT_RECORD_IMMUNIZATION_IMAGE + " BLOB);");

            db.execSQL("CREATE TABLE " + MEDICATION_TABLE + " ("
                    + KEY_MEDICATION_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_MEDICATION_CHILD_ID + " INTEGER, "
                    + KEY_MEDICATION_NAME + " TEXT, "
                    + KEY_MEDICATION_TIME + " TEXT);");

            db.execSQL("CREATE TABLE " + PARENT_TABLE + " ("
                    + KEY_PARENT_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_PARENT_CHILD_ID + " INTEGER, "
                    + KEY_PARENT_GUARDIAN_TYPE + " TEXT, "
                    + KEY_PARENT_FNAME + " TEXT, "
                    + KEY_PARENT_LNAME + " TEXT, "
                    + KEY_PARENT_PHONE + " TEXT, "
                    + KEY_PARENT_ADDRESS_LN_1 + " TEXT, "
                    + KEY_PARENT_ADDRESS_LN_2 + " TEXT, "
                    + KEY_PARENT_ADDRESS_CITY + " TEXT, "
                    + KEY_PARENT_ADDRESS_STATE + " TEXT, "
                    + KEY_PARENT_ADDRESS_ZIP + " TEXT, "
                    + KEY_PARENT_EMAIL + " TEXT);");

            db.execSQL("CREATE TABLE " + ATTENDANCE_TABLE + " ("
                    + KEY_ATTEN_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_ATTEN_CHILD_ID + " INTEGER, "
                    + KEY_ATTEN_DATE_TIME_IN + " TEXT, "
                    + KEY_ATTEN_DATE_TIME_OUT + " TEXT);");

            db.execSQL("CREATE TABLE " + TODO_LIST_TABLE + " ("
                    + KEY_TODO_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_TODO_TITLE + " TEXT, "
                    + KEY_TODO_DATE + " TEXT, "
                    + KEY_TODO_TIME + " TEXT, "
                    + KEY_TODO_STATUS + " TEXT);");

            db.execSQL("CREATE TABLE " + CALENDAR_EVENTS_TABLE + " ("
                    + KEY_CAL_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_CAL_EVENT_TITLE + " TEXT, "
                    + KEY_CAL_START_TIME + " TEXT, "
                    + KEY_CAL_END_TIME + " TEXT, "
                    + KEY_CAL_DATE + " TEXT);");

            db.execSQL("CREATE TABLE " + MEAL_PLAN_TABLE + " ("
                    + KEY_MEAL_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_MEAL_DATE + " TEXT, "

                    + KEY_MEAL_B_AMSNACK1 + " TEXT, "
                    + KEY_MEAL_B_AMSNACK2 + " TEXT, "

                    + KEY_MEAL_B_NAME + " TEXT, "
                    + KEY_MEAL_B_DRINK + " TEXT, "
                    + KEY_MEAL_B_FRUIT_VEG_JUICE + " TEXT, "
                    + KEY_MEAL_B_BREAD + " TEXT, "

                    + KEY_MEAL_L_PMSNACK1 + " TEXT, "
                    + KEY_MEAL_L_PMSNACK2 + " TEXT, "

                    + KEY_MEAL_L_NAME + " TEXT, "
                    + KEY_MEAL_L_DRINK + " TEXT, "
                    + KEY_MEAL_L_FRUIT_VEG1 + " TEXT, "
                    + KEY_MEAL_L_FRUIT_VEG2 + " TEXT, "
                    + KEY_MEAL_L_BREAD + " TEXT, "
                    + KEY_MEAL_L_PROTEIN + " TEXT, "

                    + KEY_MEAL_D_PMSNACK1 + " TEXT, "
                    + KEY_MEAL_D_PMSNACK2 + " TEXT, "

                    + KEY_MEAL_D_NAME + " TEXT, "
                    + KEY_MEAL_D_DRINK + " TEXT, "
                    + KEY_MEAL_D_FRUIT_VEG1 + " TEXT, "
                    + KEY_MEAL_D_FRUIT_VEG2 + " TEXT, "
                    + KEY_MEAL_D_BREAD + " TEXT, "
                    + KEY_MEAL_D_PROTEIN + " TEXT);");

            db.execSQL("CREATE TABLE " + FOODITEM_TABLE + " ("
                    + KEY_FOODITEM_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_FOODITEM_GROUP + " TEXT, "  /*PROTEIN, Fruits/Veggies, Dairy etc...*/
                    + KEY_FOODITEM_NAME + " TEXT);"); /*Ham, Fruit Cup, Milk etc...*/

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CHILD_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + PARENT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + ATTENDANCE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CALENDAR_EVENTS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + TODO_LIST_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + MEAL_PLAN_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + FOODITEM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + MEDICATION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DISCOUNT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + SHOT_RECORD_TABLE);
            onCreate(db);
        }
    }
}
