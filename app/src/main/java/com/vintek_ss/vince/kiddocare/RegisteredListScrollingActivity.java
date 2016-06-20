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
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RegisteredListScrollingActivity extends AppCompatActivity {

    public static final String REGISTERED_CHILD_DATA = "REGISTERED_CHILD_DATA";
    RecyclerView rv_RegisteredData;
    private List<ChildData> childDatas;

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
                        intent.putExtra(REGISTERED_CHILD_DATA, childDatas.get(position).number);
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

                    childDatas.add(new ChildData(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_ROWID), bmChildPIC, cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_FNAME)),
                            cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_LNAME)), cursor.getString(cursor.getColumnIndex(daycaremanagerDB.KEY_CHILD_AGE))));

                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
                db.close();
            }
        }
    }

    private void initializeAdapter(){
        RegisteredRVAdapter adapter = new RegisteredRVAdapter(childDatas, this);
        rv_RegisteredData.setAdapter(adapter);
    }

}
