package com.vintek_ss.vince.kiddocare;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by VINCE-LTPC on 12/6/2014.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Activity context;
    private Map<String, List<String>> foodItemsList;
    private List<String> MPFood;

    public ExpandableListAdapter(Activity context, List<String> MPFood, Map<String,
            List<String>> foodItemsList) {
        this.context = context;
        this.foodItemsList = foodItemsList;
        this.MPFood = MPFood;
    }

    @Override
    public int getGroupCount() {
        return MPFood.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return foodItemsList.get(MPFood.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return MPFood.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return foodItemsList.get(MPFood.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String food = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.elv_parent_layout,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.tv_Meal);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(food);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String item = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elv_child_layout, null);
        }
        TextView MPitem = (TextView) convertView.findViewById(R.id.tv_MealName);
        MPitem.setText(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ChildHolder {
        TextView userInput;

    }
}
