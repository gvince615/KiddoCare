package com.vintek_ss.vince.kiddocare;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CalendarActivity extends Activity {
    private static int ID = 0;
    public ArrayList<Integer> al = new ArrayList<Integer>();
    public List<CalEventAdapter> eventList = new ArrayList<CalEventAdapter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        populateList();
        loadEventDataIntoListView();
        //ListView list = (ListView) findViewById(R.id.lv_taskList);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long arg3) {
                Integer value = al.get(position);
            }

        };

        ListView list = (ListView) findViewById(R.id.lv_eventList);
        list.setOnItemClickListener(listener);

        TextView textView = (TextView) findViewById(R.id.tv_MP);
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        textView.setText(SselectedDate);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_newCalItem) {
            final Dialog tDialog = new Dialog(CalendarActivity.this);
            tDialog.setContentView(R.layout.event_dialog_layout);
            tDialog.setTitle("Add Event");

            final EditText eventTitle = (EditText) tDialog.findViewById(R.id.et_eventTitle);
            final EditText eventStime = (EditText) tDialog.findViewById(R.id.et_sTime);
            final EditText eventEtime = (EditText) tDialog.findViewById(R.id.et_eTime);

            tDialog.show();
            Button BsaveEvent = (Button) tDialog.findViewById(R.id.b_saveEvent);
            BsaveEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String event = eventTitle.getText().toString();
                    String sTime = eventStime.getText().toString();
                    String eTime = eventEtime.getText().toString();

                    try {
                        saveEvent(event, sTime, eTime);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "Event NOT Saved:\n" + e, Toast.LENGTH_SHORT).show();
                    }
                    tDialog.dismiss();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveEvent(String event, String sTime, String eTime) {

        daycaremanagerDB db = new daycaremanagerDB(this);
        db.open();
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        db.saveEventtoDB(event, sTime, eTime, SselectedDate);
        // inform user
        Toast.makeText(getApplicationContext(),
                "Event Saved", Toast.LENGTH_SHORT).show();
        db.close();
        reloadEventList();
    }

    private void reloadEventList() {
        al.clear();
        eventList.clear();
        populateList();
        loadEventDataIntoListView();
    }

    private void populateList() {
        daycaremanagerDB eventData = new daycaremanagerDB(this);
        eventData.open();
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        Cursor cursor = eventData.getCursorEventData(SselectedDate);
        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED!0 Runs on the UI thread, OK for small/short queries.
        // startManagingCursor(cursor);
        int count = cursor.getCount();
        if (count >= 0) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {

                    String eventTitle = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CAL_EVENT_TITLE));
                    int eventNum = cursor.getInt(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CAL_ROWID));
                    al.add(eventNum);

                    String eventTimeS = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CAL_START_TIME));
                    String eventTimeE = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CAL_END_TIME));
                    String eventDate = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_CAL_DATE));

                    eventList.add(new CalEventAdapter(eventTitle, eventTimeS, eventTimeE, eventDate));
                    cursor.moveToNext();
                }
            }
        }
        if (count < 1) {
            Toast.makeText(getApplicationContext(), "No Events for Selected Day",
                    Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        eventData.close();
    }

    private void loadEventDataIntoListView() {
        ArrayAdapter<CalEventAdapter> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.lv_eventList);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<CalEventAdapter> {
        public MyListAdapter() {
            super(CalendarActivity.this, R.layout.event_layout,
                    eventList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.event_layout,
                        parent, false);
            }
            // Find the car to work with.
            CalEventAdapter currentEvent = eventList.get(position);
            // Fill the view
            TextView eventName = (TextView) itemView.findViewById(R.id.tv_eventTitle);
            eventName.setText(currentEvent.geteventTitle());

            TextView eventStime = (TextView) itemView.findViewById(R.id.tv_sTime);
            eventStime.setText(currentEvent.geteventTimeS());

            TextView eventEtime = (TextView) itemView.findViewById(R.id.tv_eTime);
            eventEtime.setText(currentEvent.geteventTimeE());

            return itemView;
        }
    }
}
