package com.vintek_ss.vince.kiddocare;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ChildRegistrationRVAdapter extends RecyclerView.Adapter<ChildRegistrationRVAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private final List<ParentData> parentDatas;
    private final List<ChildData> childDatas;

    private int[] dataSetTypes;
    private List mDataSet;

    public static final int CHILD = 0;
    public static final int PARENT = 1;
    public static final int MEDICAL = 2;
    public static final int DISCOUNT = 3;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public static class ChildViewHolder extends ViewHolder {
        CardView cv;
        EditText childFirstName, childLastName, childAddressLn1, childAddressCity, childAddressState, childAddressZip;
        TextInputLayout childFirstName_layout, childLastName_layout, childAddressLn1_layout, childAddressCity_layout, childAddressState_layout, childAddressZip_layout;
        TextView childBirthdate, childEnrolldate;

        ChildViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.childDataCardView);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Tapped a Child Card", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            childFirstName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_first_name_layout);
            childLastName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_last_name_layout);
            childAddressLn1_layout = (TextInputLayout) itemView.findViewById(R.id.tv_et_child_address_ln_1_layout);
            childAddressCity_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_city_layout);
            childAddressState_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_state_layout);
            childAddressZip_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_zip_layout);

            childFirstName = (EditText)itemView.findViewById(R.id.et_childFname);
            childLastName = (EditText)itemView.findViewById(R.id.et_child_last_name);
            childAddressLn1 = (EditText)itemView.findViewById(R.id.et_child_address_ln_1);
            childAddressCity = (EditText)itemView.findViewById(R.id.et_child_address_city);
            childAddressState = (EditText)itemView.findViewById(R.id.et_child_address_state);
            childAddressZip = (EditText)itemView.findViewById(R.id.et_child_address_zip);

            childBirthdate = (TextView) itemView.findViewById(R.id.tv_Cbirthdate);
            childEnrolldate = (TextView) itemView.findViewById(R.id.et_child_Edate);



        }
    }

    public static class ParentViewHolder extends ViewHolder {
        CardView cv;
        EditText parentFirstName, parentLastName, parentAddressLn1, parentAddressCity,
                parentAddressState, parentAddressZip, parentEmail, parentPhoneNumber;
        TextInputLayout parentFirstName_layout, parentLastName_layout, parentAddressLn1_layout,
                parentAddressCity_layout, parentAddressState_layout, parentAddressZip_layout,
                parentPhone_number_layout, parentEmail_layout;

        ParentViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.parentDataCardView);

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Tapped a Parent Card", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            parentFirstName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_parentFname);
            parentLastName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_parentLname);
            parentEmail_layout = (TextInputLayout) itemView.findViewById(R.id.tv_guardians_email_address);
            parentPhone_number_layout = (TextInputLayout) itemView.findViewById(R.id.tv_guardian_phone_number);
            parentAddressLn1_layout = (TextInputLayout) itemView.findViewById(R.id.tv_et_guardian_address_ln_1_layout);
            parentAddressCity_layout = (TextInputLayout) itemView.findViewById(R.id.tv_guardian_address_city_layout);
            parentAddressState_layout = (TextInputLayout) itemView.findViewById(R.id.tv_guardian_address_state_layout);
            parentAddressZip_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_address_zip_layout);

            parentFirstName = (EditText)itemView.findViewById(R.id.et_parentFname);
            parentLastName = (EditText)itemView.findViewById(R.id.et_parentLname);
            parentAddressLn1 = (EditText)itemView.findViewById(R.id.et_guardian_address_ln_1);
            parentEmail = (EditText)itemView.findViewById(R.id.et_guardians_email_address);
            parentPhoneNumber = (EditText)itemView.findViewById(R.id.et_guardian_phone_number);
            parentAddressCity = (EditText)itemView.findViewById(R.id.et_guardian_address_city);
            parentAddressState = (EditText)itemView.findViewById(R.id.et_guardian_address_state);
            parentAddressZip = (EditText)itemView.findViewById(R.id.et_guardian_address_zip);
        }
    }

    public class MedicalViewHolder extends ViewHolder {
//        TextView headline;
//        Button read_more;

        public MedicalViewHolder(View v) {
            super(v);
//            this.headline = (TextView) v.findViewById(R.id.headline);
//            this.read_more = (Button) v.findViewById(R.id.read_more);
        }
    }

    public class DiscountViewHolder extends ViewHolder {
//        TextView headline;
//        Button read_more;

        public DiscountViewHolder(View v) {
            super(v);
//            this.headline = (TextView) v.findViewById(R.id.headline);
//            this.read_more = (Button) v.findViewById(R.id.read_more);
        }
    }

    public ChildRegistrationRVAdapter(List<ChildData> childDatas, List<ParentData> parentDatas, int[] datasetTypes) {
        this.childDatas = childDatas;
        this.parentDatas = parentDatas;


        dataSetTypes = datasetTypes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        if (viewType == CHILD) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.registration_child_data_card, viewGroup, false);
            return new ChildViewHolder(v);
        } else if (viewType == PARENT) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.registration_parent_data_card, viewGroup, false);
            return new MedicalViewHolder(v);
        } else if (viewType == MEDICAL){
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.registration_medical_data_card, viewGroup, false);
            return new ParentViewHolder(v);
        } else {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.registration_discount_data_card, viewGroup, false);
            return new ParentViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (viewHolder.getItemViewType() == CHILD) {

            ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;

            childViewHolder.childFirstName_layout.getEditText().setText(childDatas.get(i).first_name);
            childViewHolder.childLastName_layout.getEditText().setText(childDatas.get(i).last_name);
            childViewHolder.childBirthdate.setText(childDatas.get(i).birth_date);
            childViewHolder.childEnrolldate.setText(childDatas.get(i).enroll_date);
            childViewHolder.childAddressLn1_layout.getEditText().setText(childDatas.get(i).address_ln_1);
            childViewHolder.childAddressCity_layout.getEditText().setText(childDatas.get(i).address_city);
            childViewHolder.childAddressState_layout.getEditText().setText(childDatas.get(i).address_state);
            childViewHolder.childAddressZip_layout.getEditText().setText(childDatas.get(i).address_zip);

        }

        else if (viewHolder.getItemViewType() == PARENT){
            ParentViewHolder parentViewHolder = (ParentViewHolder) viewHolder;

            parentViewHolder.parentFirstName_layout.getEditText().setText(parentDatas.get(i).first_name);
            parentViewHolder.parentLastName_layout.getEditText().setText(parentDatas.get(i).last_name);
            parentViewHolder.parentEmail_layout.getEditText().setText(parentDatas.get(i).email);
            parentViewHolder.parentPhone_number_layout.getEditText().setText(parentDatas.get(i).phoneNumber);
            parentViewHolder.parentAddressLn1_layout.getEditText().setText(parentDatas.get(i).address_ln_1);
            parentViewHolder.parentAddressCity_layout.getEditText().setText(parentDatas.get(i).address_city);
            parentViewHolder.parentAddressState_layout.getEditText().setText(parentDatas.get(i).address_state);
            parentViewHolder.parentAddressZip_layout.getEditText().setText(parentDatas.get(i).address_zip);

        }
        else if (viewHolder.getItemViewType() == MEDICAL) {
//            MedicalViewHolder holder = (MedicalViewHolder) viewHolder;
//            holder.headline.setText(childDatas[position]);
        }
        else {
//            DiscountViewHolder holder = (DiscountViewHolder) viewHolder;
//            holder.headline.setText(childDatas[position]);
        }

    }

    @Override
    public int getItemCount() {
        int i = childDatas.size();
        return childDatas.size();

    }
}