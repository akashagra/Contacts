package com.akashdeveloper.avma1997.contacts_kisan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.Message;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{


       private ArrayList<Message> dataList;
        private Context mContext;




        public MessageAdapter(Context context, ArrayList<Message> dataList) {
            this.dataList = dataList;
            mContext = context;

        }

        @Override
        public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.single_row_message,parent,false);
            return new MessageViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MessageViewHolder holder, int position) {

            holder.ContactFirstName.setText(dataList.get(position).getFirstname());
            holder.ContactLastName.setText(dataList.get(position).getLastname());
            holder.ContactPhoneNo.setText(dataList.get(position).getPhoneNo());
            holder.date.setText(dataList.get(position).getDate());
            holder.message.setText(dataList.get(position).getOtp());
        }

        @Override
        public int getItemCount() {
         //   Log.i("item_size",dataList.size()+"");

            return dataList.size();
        }

        public static class MessageViewHolder extends RecyclerView.ViewHolder  {

            TextView ContactFirstName, ContactLastName, ContactPhoneNo,date,message;


            MessageViewHolder(View itemView) {
                super(itemView);

                ContactFirstName = itemView.findViewById(R.id.first_name_message_fragment);
                ContactLastName = itemView.findViewById(R.id.last_name_message_fragment);
                ContactPhoneNo = itemView.findViewById(R.id.phone_no_message_fragment);
                date=itemView.findViewById(R.id.textview_date);
                message=itemView.findViewById(R.id.textview_message);

            }



                }
            }

