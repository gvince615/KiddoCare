package com.vintek_ss.vince.kiddocare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quickenloans.floatui.QLFloatingEditLayout;

import java.util.List;

/**
 * Created by gvincent on 3/24/16.
 */
public class RegistrationRVAdapter extends RecyclerView.Adapter<RegistrationRVAdapter.ChildViewHolder> {

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        //ImageView childPhoto;
        QLFloatingEditLayout childFirstName;
        QLFloatingEditLayout childLastName;
        TextView childBirthdate;
        TextView childEnrolldate;
        QLFloatingEditLayout childAddressLn1;
        QLFloatingEditLayout childAddressCity;
        //QLFloatingEditLayout childAddressState;
        QLFloatingEditLayout childAddressZip;

        ChildViewHolder(View itemView) {
            super(itemView);

            //childPhoto = (ImageView)itemView.findViewById(R.id.iv_child_image);
            cv = (CardView)itemView.findViewById(R.id.childDataCardView);
            childFirstName = (QLFloatingEditLayout)itemView.findViewById(R.id.et_childFname);
            childLastName = (QLFloatingEditLayout)itemView.findViewById(R.id.et_childLname);
            childBirthdate = (TextView) itemView.findViewById(R.id.et_Cbirthdate);
            childEnrolldate = (TextView) itemView.findViewById(R.id.et_child_Edate);
            childAddressLn1 = (QLFloatingEditLayout)itemView.findViewById(R.id.et_child_address_ln_1);
            childAddressCity = (QLFloatingEditLayout)itemView.findViewById(R.id.et_child_address_city);
            //childAddressState = (QLFloatingEditLayout)itemView.findViewById(R.id.person_name);
            childAddressZip = (QLFloatingEditLayout)itemView.findViewById(R.id.et_child_address_zip);

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

        childViewHolder.childFirstName.getEditText().setText(children.get(i).first_name);
        childViewHolder.childLastName.getEditText().setText(children.get(i).last_name);
        childViewHolder.childBirthdate.setText(children.get(i).birth_date);
        childViewHolder.childEnrolldate.setText(children.get(i).enroll_date);
        childViewHolder.childAddressLn1.getEditText().setText(children.get(i).address_ln_1);
        childViewHolder.childAddressCity.getEditText().setText(children.get(i).address_city);
        //childViewHolder.childAddressState.getEditText().setText(children.get(i).address_state);
        childViewHolder.childAddressZip.getEditText().setText(children.get(i).address_zip);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }
}
