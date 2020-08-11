package com.example.idolmastercalendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragmentArrayList.add(ConceptFragment.newInstance());
        fragmentArrayList.add(IdolFragment.newInstance());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }
}
