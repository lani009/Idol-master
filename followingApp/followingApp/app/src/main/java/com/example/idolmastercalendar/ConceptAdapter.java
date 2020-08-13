package com.example.idolmastercalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConceptAdapter extends RecyclerView.Adapter<ConceptAdapter.ConceptViewHolder> {

    ArrayList<String> conceptList = new ArrayList<>();

    @NonNull
    @Override
    public ConceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConceptViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_concept, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConceptViewHolder holder, int position) {
        /*ImageView imageView = holder.itemView.findViewById(R.id.idolImage);
        imageView.setImageResource(idolDataArrayList.get(position).getIdolImage());

        final TextView textView = holder.itemView.findViewById(R.id.idolName);
        textView.setText(idolDataArrayList.get(position).idolName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.parseColor("#6CFF72"));
            }
        });*/

        TextView textView = holder.itemView.findViewById(R.id.conceptText);
        textView.setText(conceptList.get(position));
    }

    @Override
    public int getItemCount() {
        return conceptList.size();
    }

    class ConceptViewHolder extends RecyclerView.ViewHolder {

        public ConceptViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
