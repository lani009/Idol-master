package com.wimi.idolmaster.ui.choose

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.wimi.idolmaster.ui.base.BaseViewModel

class ChooserViewModel(application: Application) : BaseViewModel(application) {
    val viewPagerIndex = MutableLiveData(0)

    fun onClickNext() {
        viewPagerIndex.value = viewPagerIndex.value!! + 1
    }

    fun onClickBefore() {
        if(viewPagerIndex.value!! > 0)
            viewPagerIndex.value = viewPagerIndex.value!! - 1
    }
}