package com.wimi.idolmaster.ui.choose

import android.content.Intent
import androidx.lifecycle.Observer
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.ActivityChooserBinding
import com.wimi.idolmaster.ui.base.BaseActivity
import com.wimi.idolmaster.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_chooser.*

class ChooserActivity : BaseActivity<ActivityChooserBinding, ChooserViewModel>(
    R.layout.activity_chooser,
    ChooserViewModel::class
) {

    override fun onCreate() {
        initViewPager()
        subscribeChangePageEvent()
    }

    private fun initViewPager() {
        viewpager.adapter = ViewPagerAdapter(this)
        viewpager.isUserInputEnabled = false
    }

    private fun subscribeChangePageEvent() {
        viewModel.viewPagerIndex.observe(this, Observer {
            if(it == 2) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            viewpager.currentItem = it
        })
    }
}