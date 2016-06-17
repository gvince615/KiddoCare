package com.vintek_ss.vince.kiddocare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Build;
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


public class TasksActivity extends Activity {
    private static int ID = 0;
    public ArrayList<Integer> al = new ArrayList<Integer>();
    public List<TaskAdapter> taskList = new ArrayList<TaskAdapter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        populateList();
        loadTaskDataIntoListView();
        //ListView list = (ListView) findViewById(R.id.lv_taskList);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long arg3) {
                Integer value = al.get(position);
                saveTaskStatus(position);
            }

        };

        ListView list = (ListView) findViewById(R.id.lv_taskList);
        list.setOnItemClickListener(listener);

        TextView textView = (TextView) findViewById(R.id.tv_MP);
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        textView.setText(SselectedDate);
    }

    private void populateList() {
        daycaremanagerDB taskData = new daycaremanagerDB(this);
        taskData.open();
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        Cursor cursor = taskData.getCursorTaskData(SselectedDate);
        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED!0 Runs on the UI thread, OK for small/short queries.
        // startManagingCursor(cursor);
        int count = cursor.getCount();
        if (count >= 0) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {

                    String taskTitle = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_TODO_TITLE));
                    int taskNum = cursor.getInt(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_TODO_ROWID));
                    al.add(taskNum);

                    String taskTime = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_TODO_TIME));
                    String taskStatus = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_TODO_STATUS));
                    String taskDate = cursor.getString(cursor
                            .getColumnIndex(daycaremanagerDB.KEY_TODO_DATE));

                    taskList.add(new TaskAdapter(taskTitle, taskTime, taskStatus, taskDate));
                    cursor.moveToNext();
                }
            }
        }
        if (count < 1) {
            Toast.makeText(getApplicationContext(), "No Tasks for Selected Day",
                    Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        taskData.close();
    }

    private void loadTaskDataIntoListView() {
        ArrayAdapter<TaskAdapter> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.lv_taskList);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasks, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_addTask) {


            final Dialog tDialog = new Dialog(TasksActivity.this);
            tDialog.setContentView(R.layout.task_dialog_layout);
            tDialog.setTitle("Add Task");
            final EditText taskTitle = (EditText) tDialog.findViewById(R.id.et_task);
            final EditText taskTime = (EditText) tDialog.findViewById(R.id.et_taskTime);
            tDialog.show();
            Button BsaveTask = (Button) tDialog.findViewById(R.id.b_saveTask);
            BsaveTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String task = taskTitle.getText().toString();
                    String time = taskTime.getText().toString();

                    try {
                        saveTask(task, time);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "Task NOT Saved:\n" + e, Toast.LENGTH_SHORT).show();
                    }
                    tDialog.dismiss();
                }
            });
            //tDialog.create();
            //tDialog.show();
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveTask(String task, String time) {

        daycaremanagerDB db = new daycaremanagerDB(this);
        db.open();
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        db.saveTasktoDB(task, SselectedDate, time);
        // inform user
        Toast.makeText(getApplicationContext(),
                "Task Saved", Toast.LENGTH_SHORT).show();
        db.close();
        reloadTaskList();
    }

    private void saveTaskStatus(Integer position) {
        ListView list = (ListView) findViewById(R.id.lv_taskList);
        daycaremanagerDB db = new daycaremanagerDB(this);
        db.open();
        Bundle bundle = getIntent().getExtras();
        Long selectedDay = bundle.getLong("dateLong");
        String SselectedDate = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(selectedDay));
        //// Get Data
        TextView taskTitle = (TextView) list.getChildAt(position).findViewById(R.id.tv_taskTitle);
        String task = taskTitle.getText().toString();

        TextView taskStatus = (TextView) list.getChildAt(position).findViewById(R.id.tv_Satus);
        String status = taskStatus.getText().toString();

        String s = db.updateTaskDB(task, SselectedDate, status);

        // inform user
        Toast.makeText(getApplicationContext(),
                "Task Updated", Toast.LENGTH_SHORT).show();
        db.close();
        reloadTaskList();
    }

    private void reloadTaskList() {
        al.clear();
        taskList.clear();
        populateList();
        loadTaskDataIntoListView();
    }

    private class MyListAdapter extends ArrayAdapter<TaskAdapter> {
        public MyListAdapter() {
            super(TasksActivity.this, R.layout.task_list_layout,
                    taskList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.task_list_layout,
                        parent, false);
            }
            // Find the car to work with.
            TaskAdapter currentTask = taskList.get(position);
            // Fill the view
            TextView taskName = (TextView) itemView.findViewById(R.id.tv_taskTitle);
            taskName.setText(currentTask.gettaskTitle());

            TextView taskStatus = (TextView) itemView.findViewById(R.id.tv_Satus);
            String status = currentTask.gettaskStatus();
            taskStatus.setText(currentTask.gettaskStatus());
            if (status.equals("Complete")) {
                taskStatus.setTextColor(getResources().getColor(R.color.drkgreen));
            }
            if (status.equals("NOT Complete")) {
                taskStatus.setText(currentTask.gettaskStatus());
                taskStatus.setTextColor(getResources().getColor(R.color.drkred));
            }
            TextView taskDate = (TextView) itemView.findViewById(R.id.tv_timeDue);
            String taskDateTime = ("Due: " + currentTask.gettaskTime());
            taskDate.setText(taskDateTime);

            /*CheckBox cbTaskStatus = (CheckBox) itemView.findViewById(R.id.cb_taskStatus);
            String taskStatus = (currentTask.gettaskStatus());
            if(taskStatus.equals("NOT Complete")){
                cbTaskStatus.setChecked(false);
            }
            if(taskStatus.equals("Complete")){
                cbTaskStatus.setChecked(true);
            }*/
            return itemView;
        }
    }
}
