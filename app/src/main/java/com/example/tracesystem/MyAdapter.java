package com.example.tracesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<record> list;

    public MyAdapter(Context context, ArrayList<record> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        record record = list.get(position);
        holder.datetime.setText(record.getDatetime());
        holder.Mass.setText(record.getMass());
        holder.pH.setText(record.getpH());
        holder.comment.setText(record.getComment());
        holder.sapCollector.setText(record.getSapCollector());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView datetime, Mass, pH, comment, sapCollector;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            datetime = itemView.findViewById(R.id.viewdatetimecontent);
            Mass = itemView.findViewById(R.id.viewmasscontent);
            pH = itemView.findViewById(R.id.viewpHcontent);
            comment = itemView.findViewById(R.id.viewremarkscontent);
            sapCollector = itemView.findViewById(R.id.viewsapCollectorcontent);
        }
    }
}

