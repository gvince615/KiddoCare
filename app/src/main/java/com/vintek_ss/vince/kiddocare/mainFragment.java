package com.vintek_ss.vince.kiddocare;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class mainFragment extends Fragment {


    private static final String ARG_SECTION_NUMBER = "section_number";

    public static mainFragment newInstance(int sectionNumber) {
        mainFragment fragment = new mainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container,
                false);
        // When calendar date is changed
        CalendarView calendar = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                month = month + 1;
                loadCalListView(rootView);
                CalendarView calendar = (CalendarView) rootView.findViewById(R.id.calendarView);
                long LselectedDate = calendar.getDate();
                String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(LselectedDate));
                Toast.makeText(getActivity(),
                        "Selected Date: " + SselectedDate, Toast.LENGTH_SHORT).show();
            }

        });
        loadCalListView(rootView);
        // When an item is clicked in the list
        ListView callistview = (ListView) rootView.findViewById(R.id.calListView);
        callistview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CalendarView calendar = (CalendarView) rootView.findViewById(R.id.calendarView);
                // Get date and format it
                long LselectedDate = calendar.getDate();
                String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(LselectedDate));
                Bundle bundle = new Bundle();
                bundle.putLong("dateLong", LselectedDate);
                switch (position) {
                    // see scheduled childDatas for selected day
                    case 0:
                        Intent call_intent = new Intent(getActivity(), CalendarActivity.class);
                        call_intent.putExtras(bundle);
                        startActivity(call_intent);
                        break;
                    // see meal plan for selected day
                    case 1:
                        Intent meal_intent = new Intent(getActivity(), MealActivity.class);
                        meal_intent.putExtras(bundle);
                        startActivity(meal_intent);
                        break;
                    //see TODOList for selected day
                    case 2:
                        Intent task_intent = new Intent(getActivity(), TasksActivity.class);
                        task_intent.putExtras(bundle);
                        startActivity(task_intent);
                        break;
                }
            }
        });
        return rootView;
    }

    private void loadCalListView(View rootView) {
        String itemNote[] = {"Calendar Events for the Selected Day",
                "Meal Plan for the Selected Day",
                "To Do List for the Selected Day"};
        String itemName[] = {"Calendar Events",
                "Meal Plan", "To Do List"};
        int[] images = new int[]{R.drawable.ic_events,
                R.drawable.ic_myplate, R.drawable.ic_todo};
        // Each row in the list stores item name, note and image
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("item", itemName[i]);
            hm.put("note", itemNote[i]);
            hm.put("image", Integer.toString(images[i]));

            aList.add(hm);
        }
        // Keys used in Hashmap
        String[] from = {"image", "item", "note"};
        // Ids of views in listview_layout
        int[] to = {R.id.iv_CalItemImage, R.id.tv_CalItem, R.id.tv_calItemNote};
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                aList, R.layout.callistview_layout, from, to);
        // Getting a reference to listview of main.xml layout file
        ListView callistview = (ListView) rootView.findViewById(R.id.calListView);

        // Setting the adapter to the listView
        callistview.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
}
