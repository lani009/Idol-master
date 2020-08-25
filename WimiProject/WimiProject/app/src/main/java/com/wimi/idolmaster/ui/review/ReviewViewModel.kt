package com.wimi.idolmaster.ui.review

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.Review
import com.wimi.idolmaster.domain.usecase.GetReviewUseCase
import com.wimi.idolmaster.ui.base.BaseViewModel
import com.wimi.idolmaster.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ReviewViewModel(
    application: Application,
    private val getReviewUseCase: GetReviewUseCase
) : BaseViewModel(application) {

    private val _reviewList = MutableLiveData<List<Review>>()
    val reviewList: LiveData<List<Review>> = _reviewList

    private val _addReviewEvent = MutableLiveData<Event<Unit>>()
    val addReviewEvent: LiveData<Event<Unit>> = _addReviewEvent

    init {
        getReview()
    }

    private fun getReview() {
        viewModelScope.launch {
            getReviewUseCase("에그슬럿")
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Result.Success -> _reviewList.postValue(it.data)
                        is Result.Error -> Toast.makeText(getApplication(), "ID를 확인해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    fun onClickAddReview() {
        _addReviewEvent.value = Event(Unit)
    }
}