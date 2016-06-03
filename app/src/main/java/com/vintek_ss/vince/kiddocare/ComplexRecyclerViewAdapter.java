package com.vintek_ss.vince.kiddocare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The items to display in your RecyclerView
    private List<Object> items;

    public static final int CHILD = 0, PARENT = 1, MEDICAL = 2, DISCOUNT = 3;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ComplexRecyclerViewAdapter(List<Object> items) {
        this.items = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof ChildData) {
            return CHILD;
        }
        else if (items.get(position) instanceof ParentData) {
            return PARENT;
        }
//        else if (items.get(position) instanceof ) {
//            return PARENT;
//        }
//        else if (items.get(position) instanceof ParentData) {
//            return PARENT;
//        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case CHILD:
                View v1 = inflater.inflate(R.layout.registration_child_data_card, viewGroup, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case PARENT:
                View v2 = inflater.inflate(R.layout.registration_parent_data_card, viewGroup, false);
                viewHolder = new ViewHolder2(v2);
                break;
            case MEDICAL:
                break;
            case DISCOUNT:
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case CHILD:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case PARENT:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }
    private void configureViewHolder1(ViewHolder1 childViewHolder, int position) {

        ChildData childData = (ChildData) items.get(position);
        if (childData != null) {
            childViewHolder.getChildFirstName_layout().getEditText().setText(childData.first_name);
            childViewHolder.getChildLastName_layout().getEditText().setText(childData.last_name);
            childViewHolder.getChildBirthdate().setText(childData.birth_date);
            childViewHolder.getChildEnrolldate().setText(childData.enroll_date);
            childViewHolder.getChildAddressLn1_layout().getEditText().setText(childData.address_ln_1);
            childViewHolder.getChildAddressCity_layout().getEditText().setText(childData.address_city);
            childViewHolder.getChildAddressState_layout().getEditText().setText(childData.address_state);
            childViewHolder.getChildAddressZip_layout().getEditText().setText(childData.address_zip);
        }
    }

    private void configureViewHolder2(ViewHolder2 parentViewHolder, int position) {

        ParentData parentData = (ParentData) items.get(position);
        if (parentData != null) {
            parentViewHolder.getParentFirstName_layout().getEditText().setText(parentData.first_name);
            parentViewHolder.getParentLastName_layout().getEditText().setText(parentData.last_name);
            parentViewHolder.getParentEmail_layout().getEditText().setText(parentData.email);
            parentViewHolder.getParentPhone_number_layout().getEditText().setText(parentData.phoneNumber);
            parentViewHolder.getParentAddressLn1_layout().getEditText().setText(parentData.address_ln_1);
            parentViewHolder.getParentAddressCity_layout().getEditText().setText(parentData.address_city);
            parentViewHolder.getParentAddressState_layout().getEditText().setText(parentData.address_state);
            parentViewHolder.getParentAddressZip_layout().getEditText().setText(parentData.address_zip);
        }
    }
}