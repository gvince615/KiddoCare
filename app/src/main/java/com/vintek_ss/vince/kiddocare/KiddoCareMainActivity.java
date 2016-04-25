package com.vintek_ss.vince.kiddocare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.wefika.calendar.CollapseCalendarView;

import org.joda.time.LocalDate;

public class KiddoCareMainActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    FloatingActionButton addCalEvent_fab;

    ImageButton goToRegistration_ib, goToMenu_ib, goToAbout_ib, goToAttendance_ib, goToBilling_ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiddo_care_main);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setLogo(R.drawable.ic_launcher);
        }

        initMain();
    }

    private void initMain() { // Initializes image buttons on main activity

        final CollapseCalendarView calendarView = (CollapseCalendarView) findViewById(R.id.calendar);
        if (calendarView != null) {
            calendarView.init(LocalDate.now(), LocalDate.now().minusYears(10), LocalDate.now().plusYears(10));
        }

        addCalEvent_fab = (FloatingActionButton) findViewById(R.id.fab_addCalEvent);
        if (addCalEvent_fab != null) {
            addCalEvent_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (calendarView != null) {
                        Snackbar.make(view, "Adding new Calendar Event for, " + calendarView.getSelectedDate(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });
        }

        goToRegistration_ib = (ImageButton) findViewById(R.id.btn_registration);
        if (goToRegistration_ib != null) {
            goToRegistration_ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent registrationIntent = new Intent(getApplicationContext(), RegisteredListScrollingActivity.class);
                    startActivity(registrationIntent);
                }
            });
        }

        goToMenu_ib = (ImageButton) findViewById(R.id.btn_menu);
// TODO
//       if (goToMenu_ib != null) {
//            goToMenu_ib.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent menuIntent = new Intent(getApplicationContext(), MealActivity.class);
//                    startActivity(menuIntent);
//                }
//            });
//        }

        goToAbout_ib = (ImageButton) findViewById(R.id.btn_about);
// TODO
//        if (goToAbout_ib != null) {
//            goToAbout_ib.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent aboutIntent = new Intent(getApplicationContext(), AboutActivity.class);
//                    startActivity(aboutIntent);
//                }
//            });
//        }

        goToAttendance_ib = (ImageButton) findViewById(R.id.btn_attendance);
        if (goToAttendance_ib != null) {
            goToAttendance_ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent attendanceIntent = new Intent(getApplicationContext(), AttendanceActivity.class);
                    startActivity(attendanceIntent);
                }
            });
        }

        goToBilling_ib = (ImageButton) findViewById(R.id.btn_billing);
        if (goToBilling_ib != null) {
            goToBilling_ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent billingIntent = new Intent(getApplicationContext(), BillingActivity.class);
                    startActivity(billingIntent);
                }
            });
        }
    }
}
