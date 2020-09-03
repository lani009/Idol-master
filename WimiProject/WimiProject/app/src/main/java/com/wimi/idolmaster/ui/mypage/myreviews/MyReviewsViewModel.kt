package com.wimi.idolmaster.ui.mypage.myreviews

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wimi.idolmaster.MyApplication
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.model.MyReview
import com.wimi.idolmaster.domain.model.Review
import com.wimi.idolmaster.domain.usecase.GetMyReviewUseCase
import com.wimi.idolmaster.ui.base.BaseViewModel
import com.wimi.idolmaster.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MyReviewsViewModel(
    application: Application,
    private val getMyReviewUseCase: GetMyReviewUseCase
) : BaseViewModel(application) {

    private val _myReviewList = MutableLiveData<List<MyReview>>()
    val myReviewList: LiveData<List<MyReview>> = _myReviewList

    val myReviewObservableList = ObservableArrayList<MyReview>()

    init {
        getMyReview()
    }

    private fun getMyReview() {
        viewModelScope.launch {
            getMyReviewUseCase(getApplication<MyApplication>().myID.value!!)
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Result.Success -> _myReviewList.value = it.data
                        is Result.Error -> Toast.makeText(getApplication(), "ID를 확인해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}