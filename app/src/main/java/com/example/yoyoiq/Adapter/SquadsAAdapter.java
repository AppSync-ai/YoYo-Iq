package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.SquadsA;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class SquadsAAdapter extends RecyclerView.Adapter<SquadsAAdapter.MyViewHolder> {
    Context context;
    ArrayList<SquadsA> list;

    public SquadsAAdapter(Context context, ArrayList<SquadsA> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SquadsAAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_players, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SquadsAAdapter.MyViewHolder holder, int position) {
        SquadsA listData = list.get(position);
        holder.playerName.setText(listData.getName());
        holder.country.setText(listData.getMatchAB());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, country;
        ImageView playerImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            country = itemView.findViewById(R.id.country);
            playerImg = itemView.findViewById(R.id.playerImg);
        }
    }
}