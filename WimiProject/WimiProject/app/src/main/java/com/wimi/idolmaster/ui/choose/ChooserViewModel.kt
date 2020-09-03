package com.wimi.idolmaster.ui.choose

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.wimi.idolmaster.MyApplication
import com.wimi.idolmaster.ui.base.BaseViewModel

class ChooserViewModel(application: Application) : BaseViewModel(application) {
    val viewPagerIndex = MutableLiveData(0)

    fun onClickNext() {
        var checkCondition = true

        checkCondition = if(viewPagerIndex.value == 0) {
            getApplication<MyApplication>().myType.value != null
        } else {
            getApplication<MyApplication>().myIdol.value != null
        }

        if(checkCondition)
            viewPagerIndex.value = viewPagerIndex.value!! + 1
        else
            Toast.makeText(getApplication(), "항목을 선택해주세요.", Toast.LENGTH_SHORT).show()
    }

    fun onClickBefore() {
        if(viewPagerIndex.value!! > 0)
            viewPagerIndex.value = viewPagerIndex.value!! - 1
    }
}