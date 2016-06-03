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
        else if (items.get(position) instanceof MedicalData) {
            return MEDICAL;
        }
        else if (items.get(position) instanceof DiscountData) {
            return DISCOUNT;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case CHILD:
                View v1 = inflater.inflate(R.layout.registration_child_data_card, viewGroup, false);
                viewHolder = new ChildHolder(v1);
                break;
            case PARENT:
                View v2 = inflater.inflate(R.layout.registration_parent_data_card, viewGroup, false);
                viewHolder = new ParentHolder(v2);
                break;
            case MEDICAL:
                View v3 = inflater.inflate(R.layout.registration_medical_data_card, viewGroup, false);
                viewHolder = new MedicalHolder(v3);
                break;
            case DISCOUNT:
                View v4 = inflater.inflate(R.layout.registration_discount_data_card, viewGroup, false);
                viewHolder = new DiscountHolder(v4);
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
                ChildHolder vh1 = (ChildHolder) viewHolder;
                configureChildViewHolder(vh1, position);
                break;
            case PARENT:
                ParentHolder vh2 = (ParentHolder) viewHolder;
                configureParentViewHolder(vh2, position);
                break;
            case MEDICAL:
                MedicalHolder vh3 = (MedicalHolder) viewHolder;
                configureMedicalViewHolder(vh3, position);
                break;
            case DISCOUNT:
                DiscountHolder vh4 = (DiscountHolder) viewHolder;
                configureDiscountViewHolder(vh4, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }
    private void configureChildViewHolder(ChildHolder childViewHolder, int position) {

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

    private void configureParentViewHolder(ParentHolder parentViewHolder, int position) {

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
    private void configureMedicalViewHolder(MedicalHolder medicalViewHolder, int position) {

        MedicalData medicalData = (MedicalData) items.get(position);
        if (medicalData != null) {
            medicalViewHolder.getFluShotDate().setText(medicalData.flu_shot_date);
            medicalViewHolder.getImmunizationDate().setText(medicalData.immunizations_date);
            medicalViewHolder.getMedicationTime().setText(medicalData.medication_time);
            medicalViewHolder.getMedicationDescription_label().getEditText().setText(medicalData.medication_description);
        }
    }
    private void configureDiscountViewHolder(DiscountHolder discountViewHolder, int position) {

        DiscountData discountData = (DiscountData) items.get(position);
        if (discountData != null) {
            discountViewHolder.getDiscountDescription_label().getEditText().setText(discountData.discount_description);
            //discountViewHolder.getDiscountAmount().getChildAt(position).getTe.(discountData.discount_description);
        }
    }
}