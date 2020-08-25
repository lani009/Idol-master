package com.wimi.idolmaster.ui.choose

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wimi.idolmaster.ui.choose.concept.ConceptFragment
import com.wimi.idolmaster.ui.choose.idol.IdolFragment
import java.util.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentArrayList = ArrayList<Fragment>()

    init {
        fragmentArrayList.add(ConceptFragment())
        fragmentArrayList.add(IdolFragment())
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentArrayList[position]
    }

    override fun getItemCount(): Int {
        return fragmentArrayList.size
    }
}