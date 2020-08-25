package com.wimi.idolmaster.ui.mypage

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wimi.idolmaster.MyApplication
import com.wimi.idolmaster.ui.base.BaseViewModel
import com.wimi.idolmaster.utils.Event
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author ReStartAllKill
 * @created on 2019-05-28
 * @modified by
 * @updated on
 */
class MyPageViewModel(application: Application) : BaseViewModel(application) {

    val myIdol = getApplication<MyApplication>().myIdol
    val myType = MutableLiveData<String>("")

    val day01 = MutableLiveData("")
    val day02 = MutableLiveData("")
    val day03 = MutableLiveData("")
    val day04 = MutableLiveData("")
    val day05 = MutableLiveData("")
    val day06 = MutableLiveData("")
    val day07 = MutableLiveData("")
    val month = MutableLiveData("")

    private val _editEvent = MutableLiveData<Event<Unit>>()
    val editEvent: LiveData<Event<Unit>> = _editEvent

    private val _logoutEvent = MutableLiveData<Event<Unit>>()
    val logoutEvent: LiveData<Event<Unit>> = _logoutEvent

    init {
        initMyTypeToList()
        initDate()
    }

    private fun initDate() {
        val c = Calendar.getInstance()

        val df = SimpleDateFormat("dd", Locale.getDefault())
        val mm = SimpleDateFormat("MM", Locale.getDefault())

        month.value = "2020  " + mm.format(c.time)
        day01.value = df.format( c.apply { add(Calendar.DATE, -3) }.time)
        day02.value = df.format( c.apply { add(Calendar.DATE, 1) }.time)
        day03.value = df.format( c.apply { add(Calendar.DATE, 1) }.time)
        day04.value = df.format( c.apply { add(Calendar.DATE, 1) }.time)
        day05.value = df.format( c.apply { add(Calendar.DATE, 1) }.time)
        day06.value = df.format( c.apply { add(Calendar.DATE, 1) }.time)
        day07.value = df.format( c.apply { add(Calendar.DATE, 1) }.time)
    }


    private fun initMyTypeToList() {
        var typeList = ""
        for(type in getApplication<MyApplication>().myType.value!!) {
            typeList += "#$type "
        }

        myType.value = typeList
    }

    fun onClickEdit() {
        _editEvent.value = Event(Unit)
    }

    fun onClickLogout() {
        _logoutEvent.value = Event(Unit)
    }
}