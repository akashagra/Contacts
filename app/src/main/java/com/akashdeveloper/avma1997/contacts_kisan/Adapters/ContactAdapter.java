package com.akashdeveloper.avma1997.contacts_kisan.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.Contact;
import com.akashdeveloper.avma1997.contacts_kisan.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {


    private ArrayList<Contact> dataList;
    private ContactClickListener mListener;
    private Context mContext;

    public interface ContactClickListener {
        void onItemClick(View view, int position);
    }


    public ContactAdapter(Context context, ArrayList<Contact> dataList, ContactClickListener listener) {
        this.dataList = dataList;
        mContext = context;
        mListener = listener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.single_row_view,parent,false);

        return new ContactViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        holder.txtContactFirstName.setText(dataList.get(position).getFirstname());
        holder.txtContactLastName.setText(dataList.get(position).getLastname());
        holder.txtContactPhoneNo.setText(dataList.get(position).getPhoneno());
    }

    @Override
    public int getItemCount() {
        Log.i("item_size",dataList.size()+"");

        return dataList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtContactFirstName, txtContactLastName, txtContactPhoneNo;
        ContactClickListener mContactClickListener;

        ContactViewHolder(View itemView, ContactClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContactClickListener=listener;
            txtContactFirstName = itemView.findViewById(R.id.txt_first_name);
            txtContactLastName = itemView.findViewById(R.id.txt_last_name);
            txtContactPhoneNo = itemView.findViewById(R.id.txt_phone_no);
        }

        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (id == R.id.contact_view) {
                    mContactClickListener.onItemClick(view, position);

                }

            }
        }
    }
}



