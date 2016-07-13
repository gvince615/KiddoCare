package com.vintekss.kiddowear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AttendanceWearActivity extends WearableActivity implements WearableListView.ClickListener {

    private List<ListViewItem> viewItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_wear);

        WearableListView wearableListView = (WearableListView) findViewById(R.id.wearable_list_view);

        viewItemList.add(new ListViewItem(R.drawable.boy2, "boy name"));
        viewItemList.add(new ListViewItem(R.drawable.girl2, "girl name"));
        viewItemList.add(new ListViewItem(R.drawable.boy2, "boy name"));
        viewItemList.add(new ListViewItem(R.drawable.girl2, "girl name"));

        wearableListView.setAdapter(new ListViewAdapter(this, viewItemList));
        wearableListView.setClickListener(this);
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Toast.makeText(this, "Click on " + viewItemList.get(viewHolder.getLayoutPosition()).text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTopEmptyRegionClick() {

    }
}
