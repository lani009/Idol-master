package com.wimi.idolmaster.ui.choose.concept

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wimi.idolmaster.MyApplication
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.usecase.GetTasteListUseCase
import com.wimi.idolmaster.ui.base.BaseViewModel
import com.wimi.idolmaster.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ConceptViewModel(
    application: Application,
    private val getTasteListUseCase: GetTasteListUseCase
) : BaseViewModel(application) {

    val text01 = MutableLiveData("")
    val text02 = MutableLiveData("")
    val text03 = MutableLiveData("")
    val text04 = MutableLiveData("")
    val text05 = MutableLiveData("")
    val text06 = MutableLiveData("")
    val text07 = MutableLiveData("")
    val text08 = MutableLiveData("")
    val text09 = MutableLiveData("")
    val text10 = MutableLiveData("")

    val textList = ObservableArrayList<String>()

    private val _textChangeEvent = MutableLiveData<Event<Unit>>()
    val textChangeEvent: LiveData<Event<Unit>> = _textChangeEvent

    init {
        getTasteList()
    }

    private fun getTasteList() {
        viewModelScope.launch {
            getTasteListUseCase()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Result.Success -> {
                            text01.postValue(it.data[0])
                            text02.postValue(it.data[1])
                            text03.postValue(it.data[2])
                            text04.postValue(it.data[3])
                            text05.postValue(it.data[4])
                            text06.postValue(it.data[5])
                            text07.postValue(it.data[6])
                            text08.postValue(it.data[7])
                            text09.postValue(it.data[8])
                            text10.postValue(it.data[9])
                        }
                        is Result.Error -> Toast.makeText(getApplication(), "ID를 확인해주세요", Toast.LENGTH_SHORT).show()
                        else -> {}
                    }
                }
        }
    }

    fun onClickTextTitle(text: String) {
        if(!textList.contains(text)) {
            textList.add(text)
            getApplication<MyApplication>().myType.value = textList
        }

        _textChangeEvent.value = Event(Unit)
    }
}