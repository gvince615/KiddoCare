package com.vintek_ss.vince.kiddocare;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RegisteredListScrollingActivity extends AppCompatActivity {

    public static final String KEY_REGISTERED_CHILD_TO_EDIT = "KEY_REGISTERED_CHILD_TO_EDIT";
    public static final String KEY_REGISTERED_CHILD_IS_IN_EDIT_MODE = "KEY_REGISTERED_CHILD_IS_IN_EDIT_MODE";

    private RecyclerView rv_RegisteredData;
    private List<ChildData> childDatas;
    private RegisteredRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_list_activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_RegisteredData = (RecyclerView)findViewById(R.id.rv_registered_child_data_list);
        rv_RegisteredData.addOnItemTouchListener(
                new RecyclerItemClickListener(RegisteredListScrollingActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(RegisteredListScrollingActivity.this, RegistrationListScrollingActivity.class);
                        intent.putExtra(KEY_REGISTERED_CHILD_TO_EDIT, childDatas.get(position).number);
                        intent.putExtra(KEY_REGISTERED_CHILD_IS_IN_EDIT_MODE, true);
                        startActivity(intent);
                    }
                })
        );
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_RegisteredData.setLayoutManager(llm);


        FloatingActionButton fab_add_card = (FloatingActionButton) findViewById(R.id.fab_add_card);
        fab_add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegistrationListScrollingActivity.class);
                startActivity(intent);
            }
        });

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        childDatas = new ArrayList<>();

        daycaremanagerDB db = new daycaremanagerDB(this);
        db.open();
        Cursor cursor = db.getAllEnrolledChildren();

        if (cursor.moveToFirst()) {
            try {
                while (!cursor.isAfterLast()) {

                    byte[] childPIC = cursor.getBlob(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_PIC));
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    Bitmap bmChildPIC = BitmapFactory.decodeByteArray(childPIC, 0, childPIC.length, options);

                    childDatas.add(new ChildData(
                            cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID)),
                            bmChildPIC, cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME)),
                            cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME)),
                            cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE))));

                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
                db.close();
            }
        }
        CheckDatabase();
    }

    private void CheckDatabase() {
        daycaremanagerDB db;
        db = new daycaremanagerDB(this);
        db.open();

        iterateThroughChildCursor(db.getAllElements(daycaremanagerDB.CHILD_TABLE));
        iterateThroughParentCursor(db.getAllElements(daycaremanagerDB.PARENT_TABLE));
        iterateThroughMedsCursor(db.getAllElements(daycaremanagerDB.MEDICATION_TABLE));
        iterateThroughShotsCursor(db.getAllElements(daycaremanagerDB.SHOT_RECORD_TABLE));
        iterateThroughDiscountCursor(db.getAllElements(daycaremanagerDB.DISCOUNT_TABLE));

        db.close();
    }

    private void iterateThroughDiscountCursor(Cursor cursor) {
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Log.d("Discount", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_DISCOUNT_ROWID))));
                    Log.d("Discount", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_DISCOUNT_CHILD_ID))));
                    Log.d("Discount", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_DISCOUNT_DESC)));
                    Log.d("Discount", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_DISCOUNT_AMOUNT)));

                } while (cursor.moveToNext());
            }

        } finally {
            try {
                cursor.close();
            } catch (Exception ignore) {
            }
        }
    }

    private void iterateThroughShotsCursor(Cursor cursor) {
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Log.d("Shots", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_SHOT_RECORD_ROWID))));
                    Log.d("Shots", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_SHOT_RECORD_CHILD_ID))));
                    Log.d("Shots", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_SHOT_RECORD_FLU_SHOT_DATE)));
                    Log.d("Shots", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_SHOT_RECORD_IMMUNIZATION_DATE)));

                } while (cursor.moveToNext());
            }

        } finally {
            try {
                cursor.close();
            } catch (Exception ignore) {
            }
        }
    }

    private void iterateThroughMedsCursor(Cursor cursor) {
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Log.d("Meds", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_MEDICATION_ROWID))));
                    Log.d("Meds", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_MEDICATION_CHILD_ID))));
                    Log.d("Meds", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_MEDICATION_NAME)));
                    Log.d("Meds", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_MEDICATION_TIME)));

                } while (cursor.moveToNext());
            }

        } finally {
            try {
                cursor.close();
            } catch (Exception ignore) {
            }
        }
    }

    private void iterateThroughParentCursor(Cursor cursor) {
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Log.d("Parent", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_ROWID))));
                    Log.d("Parent", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_CHILD_ID))));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_FNAME)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_LNAME)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_GUARDIAN_TYPE)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_EMAIL)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_PHONE)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_ADDRESS_LN_1)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_ADDRESS_LN_2)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_ADDRESS_CITY)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_ADDRESS_STATE)));
                    Log.d("Parent", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_PARENT_ADDRESS_ZIP)));

                } while (cursor.moveToNext());
            }

        } finally {
            try {
                cursor.close();
            } catch (Exception ignore) {
            }
        }
    }

    private void iterateThroughChildCursor(Cursor cursor) {
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Log.d("Child", String.valueOf(cursor.getInt(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID))));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_BDATE)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_EDATE)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_LN_1)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_LN_2)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_CITY)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_STATE)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ADDRESS_ZIP)));
                    Log.d("Child", cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE)));

                } while (cursor.moveToNext());
            }

        } finally {
            try {
                cursor.close();
            } catch (Exception ignore) {
            }
        }
    }

    private void initializeAdapter(){
        adapter = new RegisteredRVAdapter(childDatas, this);
        rv_RegisteredData.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
