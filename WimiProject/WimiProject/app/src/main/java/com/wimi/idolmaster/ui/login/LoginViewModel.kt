package com.wimi.idolmaster.ui.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wimi.idolmaster.domain.core.Result
import com.wimi.idolmaster.domain.usecase.LoginUseCase
import com.wimi.idolmaster.ui.base.BaseViewModel
import com.wimi.idolmaster.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application,
    private val loginUseCase: LoginUseCase
) : BaseViewModel(application) {

    val id = MutableLiveData<String>()
    val pwd = MutableLiveData<String>()

    private val _loginEvent = MutableLiveData<Event<Unit>>()
    val loginEvent: LiveData<Event<Unit>> = _loginEvent

    private fun login() {
        viewModelScope.launch {
            loginUseCase(id.value!!)
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Result.Success -> _loginEvent.postValue(Event(Unit))
                        is Result.Error -> Toast.makeText(getApplication(), "ID를 확인해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    fun onClickLogin() {
        if(!id.value.isNullOrEmpty())
            login()
    }
}