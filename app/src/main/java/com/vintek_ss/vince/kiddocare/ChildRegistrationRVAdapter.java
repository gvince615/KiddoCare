package com.vintek_ss.vince.kiddocare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressCityTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressLnOneTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressLnTwoTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressStateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildAddressZipTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildBirthDateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildEnrollDateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildFirstNameTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ChildLastNameTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.DiscountDescTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.MedDescTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.MedTimeTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentAddressCityTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentAddressLnOneTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentAddressLnTwoTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentAddressStateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentAddressZipTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentEmailTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentFirstNameTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentLastNameTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ParentPhoneTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ShotsFluDateTextWatcher;
import com.vintek_ss.vince.kiddocare.TextWatchers.ShotsImmunDateTextWatcher;

import java.util.List;

public class ChildRegistrationRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int CHILD = 0, PARENT = 1, MEDICAL = 2, MEDICATION = 3, DISCOUNT = 4;
    // The cards to display in your RecyclerView
    private List<Object> cards;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChildRegistrationRVAdapter(List<Object> cards) {
        this.cards = cards;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.cards.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (cards.get(position) instanceof ChildData) {
            return CHILD;
        }
        if (cards.get(position) instanceof ParentData) {
            return PARENT;
        }
        if (cards.get(position) instanceof ShotRecordData) {
            return MEDICAL;
        }
        if (cards.get(position) instanceof MedicationData) {
            return MEDICATION;
        }
        if (cards.get(position) instanceof DiscountData) {
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
                viewHolder = new ChildHolder(v1,
                        new ChildFirstNameTextWatcher(),
                        new ChildLastNameTextWatcher(),
                        new ChildAddressLnOneTextWatcher(),
                        new ChildAddressLnTwoTextWatcher(),
                        new ChildAddressCityTextWatcher(),
                        new ChildAddressStateTextWatcher(),
                        new ChildAddressZipTextWatcher(),
                        new ChildBirthDateTextWatcher(),
                        new ChildEnrollDateTextWatcher()
                );
                break;
            case PARENT:
                View v2 = inflater.inflate(R.layout.registration_parent_data_card, viewGroup, false);
                viewHolder = new ParentHolder(v2,
                        new ParentFirstNameTextWatcher(),
                        new ParentLastNameTextWatcher(),
                        new ParentAddressLnOneTextWatcher(),
                        new ParentAddressLnTwoTextWatcher(),
                        new ParentAddressCityTextWatcher(),
                        new ParentAddressStateTextWatcher(),
                        new ParentAddressZipTextWatcher(),
                        new ParentPhoneTextWatcher(),
                        new ParentEmailTextWatcher()
                );
                break;
            case MEDICAL:
                View v3 = inflater.inflate(R.layout.registration_medical_data_card, viewGroup, false);
                viewHolder = new ShotsHolder(v3,
                        new ShotsFluDateTextWatcher(),
                        new ShotsImmunDateTextWatcher()
                );
                break;
            case MEDICATION:
                View v5 = inflater.inflate(R.layout.registration_medicine_data_card, viewGroup, false);
                viewHolder = new MedicationHolder(v5,
                        new MedDescTextWatcher(),
                        new MedTimeTextWatcher()
                );
                break;
            case DISCOUNT:
                View v4 = inflater.inflate(R.layout.registration_discount_data_card, viewGroup, false);
                viewHolder = new DiscountHolder(v4, new DiscountDescTextWatcher());
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
                //vh2.childFirstNameTextWatcher.updatePosition(position);
                configureParentViewHolder(vh2, position);
                break;
            case MEDICAL:
                ShotsHolder vh3 = (ShotsHolder) viewHolder;
                //vh3.childFirstNameTextWatcher.updatePosition(position);
                configureMedicalViewHolder(vh3, position);
                break;
            case MEDICATION:
                MedicationHolder vh4 = (MedicationHolder) viewHolder;
                //vh4.childFirstNameTextWatcher.updatePosition(position);
                configureMedicationViewHolder(vh4, position);
                break;
            case DISCOUNT:
                DiscountHolder vh5 = (DiscountHolder) viewHolder;
                //vh5.childFirstNameTextWatcher.updatePosition(position);
                configureDiscountViewHolder(vh5, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }
    private void configureChildViewHolder(ChildHolder childViewHolder, int position) {

        ChildData childData = (ChildData) cards.get(position);
        childViewHolder.childFirstNameTextWatcher.setParams(cards, position);
        childViewHolder.childLastNameTextWatcher.setParams(cards, position);
        childViewHolder.childAddressLnOneTextWatcher.setParams(cards, position);
        childViewHolder.childAddressLnTwoTextWatcher.setParams(cards, position);
        childViewHolder.childAddressCityTextWatcher.setParams(cards, position);
        childViewHolder.childAddressStateTextWatcher.setParams(cards, position);
        childViewHolder.childAddressZipTextWatcher.setParams(cards, position);
        childViewHolder.childBirthDateTextWatcher.setParams(cards, position);
        childViewHolder.childEnrollDateTextWatcher.setParams(cards, position);

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

        ParentData parentData = (ParentData) cards.get(position);

        parentViewHolder.parentFirstNameTextWatcher.setParams(cards, position);
        parentViewHolder.parentLastNameTextWatcher.setParams(cards, position);
        parentViewHolder.parentAddressLnOneTextWatcher.setParams(cards, position);
        parentViewHolder.parentAddressLnTwoTextWatcher.setParams(cards, position);
        parentViewHolder.parentAddressCityTextWatcher.setParams(cards, position);
        parentViewHolder.parentAddressStateTextWatcher.setParams(cards, position);
        parentViewHolder.parentAddressZipTextWatcher.setParams(cards, position);
        parentViewHolder.parentEmailTextWatcher.setParams(cards, position);
        parentViewHolder.parentPhoneTextWatcher.setParams(cards, position);

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

    private void configureMedicalViewHolder(ShotsHolder medicalViewHolder, int position) {

        ShotRecordData shotRecordData = (ShotRecordData) cards.get(position);

        medicalViewHolder.shotsFluDateTextWatcher.setParams(cards, position);
        medicalViewHolder.shotsImmunDateTextWatcher.setParams(cards, position);

        if (shotRecordData != null) {
            medicalViewHolder.getFluShotDate().setText(shotRecordData.flu_shot_date);
            medicalViewHolder.getImmunizationDate().setText(shotRecordData.immunizations_date);
            medicalViewHolder.getIvShotRecord().setImageBitmap(shotRecordData.imageShotRecord);
        }
    }

    private void configureMedicationViewHolder(MedicationHolder medicationHolder, int position) {

        MedicationData medicationData = (MedicationData) cards.get(position);

        medicationHolder.medDescTextWatcher.setParams(cards, position);
        medicationHolder.medTimeTextWatcher.setParams(cards, position);

        if (medicationData != null) {
            medicationHolder.getMedicationTime().setText(medicationData.medication_time);
            medicationHolder.getMedicationDescription_label().getEditText().setText(medicationData.medication_description);
        }
    }
    private void configureDiscountViewHolder(DiscountHolder discountViewHolder, int position) {

        DiscountData discountData = (DiscountData) cards.get(position);

        discountViewHolder.discountDescTextWatcher.setParams(cards, position);

        if (discountData != null) {
            discountViewHolder.getDiscountDescription_label().getEditText().setText(discountData.discount_description);
            //discountViewHolder.getDiscountAmount().getChildAt(position).getTe.(discountData.discount_description);
        }
    }
}