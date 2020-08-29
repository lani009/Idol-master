package com.wimi.idolmaster.ui.review.dialog

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.usecase.SaveReviewUseCase
import com.wimi.idolmaster.ui.base.BaseViewModel
import com.wimi.idolmaster.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WriteReviewViewModel(
    application: Application,
    private val saveReviewUseCase: SaveReviewUseCase
) : BaseViewModel(application) {

    val content = MutableLiveData<String>("점심시간보다는 저녁시간 대 추천")
    val summery = MutableLiveData<String>("발냄새는 조금 나지만 맛있습니다.\nBTS 사진이 많아서 좋았어요.")

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent: LiveData<Event<Unit>> = _finishEvent

    fun saveReview() {
        viewModelScope.launch {
            saveReviewUseCase("1", content.value!!, "에그슬럿", summery.value!!)
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Result.Success -> _finishEvent.postValue(Event(Unit))
                        is Result.Error -> Toast.makeText(getApplication(), "ID를 확인해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}