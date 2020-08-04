package com.example.idolmastercalendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IdolAdapter extends RecyclerView.Adapter<IdolAdapter.IdolViewHolder> {

    ArrayList<IdolData> idolDataArrayList = new ArrayList<>();

    @NonNull
    @Override
    public IdolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IdolViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entertainer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IdolViewHolder holder, int position) {
        ImageView imageView = holder.itemView.findViewById(R.id.idolImage);
        imageView.setImageResource(idolDataArrayList.get(position).getIdolImage());

        final TextView textView = holder.itemView.findViewById(R.id.idolName);
        textView.setText(idolDataArrayList.get(position).idolName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.parseColor("#6CFF72"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return idolDataArrayList.size();
    }

    class IdolViewHolder extends RecyclerView.ViewHolder {

        public IdolViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
