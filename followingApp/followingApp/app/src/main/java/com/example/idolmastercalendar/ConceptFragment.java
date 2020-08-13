package com.example.idolmastercalendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConceptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConceptFragment extends Fragment {

    private ArrayList<String> template = new ArrayList<String>();
    private int templateIndex = 0;

    public static ConceptFragment newInstance() {
        return new ConceptFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        template.add("더러움");
        template.add("아늑함");
        template.add("맛있음");
        template.add("시끄러움");
        template.add("쾌적");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_concept, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.textRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        ConceptAdapter adapter = new ConceptAdapter();
        recyclerView.setAdapter(adapter);

        ImageView cloudImage = view.findViewById(R.id.cloudImage);
        cloudImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(templateIndex < template.size()) {
                    adapter.conceptList.add(template.get(templateIndex++));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        //adapter.conceptList.add("")
    }
}