package com.vintek_ss.vince.kiddocare;

import android.content.Intent;
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

    RecyclerView rv_RegisteredData;
    private List<Child> children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_list_activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        rv_RegisteredData = (RecyclerView)findViewById(R.id.rv_registered_child_data_list);
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
        children = new ArrayList<>();
        children.add(new Child(1, R.drawable.ic_girl, "KateLynn", "Vincent",
                "Toddler"));

        children.add(new Child(1, R.drawable.ic_girl, "Jeremiah", "Vincent",
                "School Age"));
    }

    private void initializeAdapter(){
        RegisteredRVAdapter adapter = new RegisteredRVAdapter(children);
        rv_RegisteredData.setAdapter(adapter);
    }
}
