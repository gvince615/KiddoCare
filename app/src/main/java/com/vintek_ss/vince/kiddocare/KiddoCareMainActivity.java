package com.vintek_ss.vince.kiddocare;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wefika.calendar.CollapseCalendarView;

import net.simonvt.menudrawer.MenuDrawer;

import org.joda.time.LocalDate;

public class KiddoCareMainActivity extends AppCompatActivity {

    private MenuDrawer mMenuDrawer;
    FloatingActionButton floatingActionButton;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiddo_care_main);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_launcher);

        CollapseCalendarView calendarView = (CollapseCalendarView) findViewById(R.id.calendar);
        calendarView.init(LocalDate.now(), LocalDate.now().minusYears(10), LocalDate.now().plusYears(10));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        mMenuDrawer = MenuDrawer.attach(this, Position.BOTTOM);
//        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
//        mMenuDrawer.setContentView(R.layout.activity_kiddo_care_main);
//        mMenuDrawer.setMenuView(R.layout.menu_sample);
//        mMenuDrawer.setBackgroundColor(getResources().getColor(R.color.trans));
//        mMenuDrawer.setBackground(null);

        ///CalendarManager manager = new CalendarManager(LocalDate.now(), CalendarManager.State.MONTH, LocalDate.now(), LocalDate.now().plusYears(1));



    }



}
