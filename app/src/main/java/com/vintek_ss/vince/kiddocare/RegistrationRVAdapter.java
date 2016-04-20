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

/**
 * Created by gvincent on 3/24/16.
 */
public class RegistrationRVAdapter extends RecyclerView.Adapter<RegistrationRVAdapter.ChildViewHolder> {

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        //ImageView childPhoto;
        EditText childFirstName, childLastName, childAddressLn1, childAddressCity, childAddressState, childAddressZip;
        TextInputLayout childFirstName_layout, childLastName_layout, childAddressLn1_layout, childAddressCity_layout, childAddressState_layout, childAddressZip_layout;
        TextView childBirthdate, childEnrolldate;

        ChildViewHolder(View itemView) {
            super(itemView);

            //childPhoto = (ImageView)itemView.findViewById(R.id.iv_child_image);
            cv = (CardView)itemView.findViewById(R.id.childDataCardView);

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Tapped a Card", Snackbar.LENGTH_LONG)
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

            childBirthdate = (TextView) itemView.findViewById(R.id.et_Cbirthdate);
            childEnrolldate = (TextView) itemView.findViewById(R.id.et_child_Edate);

        }
    }

    List<Child> children;

    RegistrationRVAdapter(List<Child> children){
        this.children = children;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.registration_child_data_card, viewGroup, false);
        ChildViewHolder cvh = new ChildViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ChildViewHolder childViewHolder, int i) {

        //childViewHolder.childPhoto.setImageResource(children.get(i).pic);

        childViewHolder.childFirstName_layout.getEditText().setText(children.get(i).first_name);
        childViewHolder.childLastName_layout.getEditText().setText(children.get(i).last_name);
        childViewHolder.childBirthdate.setText(children.get(i).birth_date);
        childViewHolder.childEnrolldate.setText(children.get(i).enroll_date);
        childViewHolder.childAddressLn1_layout.getEditText().setText(children.get(i).address_ln_1);
        childViewHolder.childAddressCity_layout.getEditText().setText(children.get(i).address_city);
        childViewHolder.childAddressState_layout.getEditText().setText(children.get(i).address_state);
        childViewHolder.childAddressZip_layout.getEditText().setText(children.get(i).address_zip);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }
}
