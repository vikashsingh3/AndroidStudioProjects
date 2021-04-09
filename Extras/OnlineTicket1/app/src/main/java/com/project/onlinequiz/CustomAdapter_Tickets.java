package com.project.onlinequiz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CustomAdapter_Tickets extends RecyclerView.Adapter<CustomAdapter_Tickets.MyViewHolder> {

    ArrayList<String> ticketIDList, issueList, statusList, created_dateList;

    Context context;

    public CustomAdapter_Tickets(Context context, ArrayList<String> ticketIDList, ArrayList<String> issueList,
                                 ArrayList<String> statusList, ArrayList<String> created_dateList) {
        this.context = context;
        this.ticketIDList = ticketIDList;
        this.issueList = issueList;
        this.statusList = statusList;
        this.created_dateList = created_dateList;
    }

    @NonNull
    @Override
    public CustomAdapter_Tickets.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout, parent, false);
        return new CustomAdapter_Tickets.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomAdapter_Tickets.MyViewHolder holder, final int position) {
        // set the data in items
        holder.txt_ticket_number.setText(String.format("ID: %s", ticketIDList.get(position)));
        holder.txt_issue.setText(String.format("%s", issueList.get(position)));
        holder.txt_status.setText(String.format("%s", statusList.get(position)));
        holder.txt_created_date.setText(String.format("%s", created_dateList.get(position)));
    }


    @Override
    public int getItemCount() {
        return ticketIDList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_ticket_number, txt_issue, txt_status, txt_created_date;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_ticket_number = itemView.findViewById(R.id.txt_ticket_number);
            txt_issue = itemView.findViewById(R.id.txt_issue);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_created_date = itemView.findViewById(R.id.txt_created_date);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
