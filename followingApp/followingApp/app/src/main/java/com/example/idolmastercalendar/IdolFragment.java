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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IdolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdolFragment extends Fragment {

    private IdolAdapter idolAdapter = new IdolAdapter();

    public static IdolFragment newInstance() {
        return new IdolFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 아이돌 정보 데이터를 추가해줍니다. 해당 데이터 개수만큼 화면에 나옵니다.
        idolAdapter.idolDataArrayList.add(new IdolData("BTS", R.drawable.bts_image));
        idolAdapter.idolDataArrayList.add(new IdolData("RED\nVELVET", R.drawable.redvelvet_image));
        idolAdapter.idolDataArrayList.add(new IdolData("CHUNG\nHA", R.drawable.chungha_image));
        idolAdapter.idolDataArrayList.add(new IdolData("BLACK\nPINK", R.drawable.blackpink_image));
        idolAdapter.idolDataArrayList.add(new IdolData("OH\nMY\nGIRL", R.drawable.ohmygirl_image));
        idolAdapter.idolDataArrayList.add(new IdolData("DAY6", R.drawable.day6_image));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idol, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(idolAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //recyclerView.addItemDecoration(new ItemDecoration(24));
    }

}