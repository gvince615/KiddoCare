package com.vintek_ss.vince.kiddocare;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gvincent on 3/24/16.
 */
public class RegisteredRVAdapter extends RecyclerView.Adapter<RegisteredRVAdapter.ChildViewHolder> {

    List<ChildData> childDatas;
    private Context context;

    RegisteredRVAdapter(List<ChildData> childDatas, Context context) {
        this.childDatas = childDatas;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.registered_list_child_data_card, viewGroup, false);
        ChildViewHolder cvh = new ChildViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ChildViewHolder childViewHolder, int i) {

        childViewHolder.childPhoto.setImageBitmap(childDatas.get(i).pic);
        childViewHolder.childRecordNumber.setText(String.valueOf(childDatas.get(i).number));
        childViewHolder.childFirstName_layout.getEditText().setText(childDatas.get(i).first_name);
        childViewHolder.childLastName_layout.getEditText().setText(childDatas.get(i).last_name);
        childViewHolder.childAgeBracket_layout.getEditText().setText(childDatas.get(i).age);

    }

    @Override
    public int getItemCount() {
        return childDatas.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView childPhoto;
        EditText childFirstName, childLastName, childAgeBracket;
        TextInputLayout childFirstName_layout, childLastName_layout, childAgeBracket_layout;
        TextView childRecordNumber;

        ChildViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.childDataCardView);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//
                }
            });

            childPhoto = (ImageView) itemView.findViewById(R.id.child_image);
            childRecordNumber = (TextView) itemView.findViewById(R.id.registered_childRecordNumber);
            childFirstName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_first_name_layout);
            childLastName_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_last_name_layout);
            childAgeBracket_layout = (TextInputLayout) itemView.findViewById(R.id.tv_child_age_bracket_layout);
        }
    }
}
