package com.wimi.idolmaster.ui.plan

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.wimi.idolmaster.ui.base.BaseViewModel
import java.text.SimpleDateFormat
import java.util.*

class PlanViewModel(application: Application) : BaseViewModel(application) {

    val today = MutableLiveData(getTodayString())

    private fun getTodayString(): String {
        val df = SimpleDateFormat("dd", Locale.getDefault())
        return df.format(Calendar.getInstance().time)
    }

}