package com.wimi.idolmaster.ui.login

import android.content.Intent
import com.wimi.idolmaster.MyApplication
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.ActivityLoginBinding
import com.wimi.idolmaster.ui.base.BaseActivity
import com.wimi.idolmaster.ui.choose.ChooserActivity
import com.wimi.idolmaster.utils.EventObserver


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    R.layout.activity_login,
    LoginViewModel::class
) {

    override fun onCreate() {
        init()
        subscribeLoginEvent()
    }

    private fun init() {
        viewModel.getApplication<MyApplication>().myID.value = ""
        viewModel.getApplication<MyApplication>().myType.value = null
        viewModel.getApplication<MyApplication>().myIdol.value = null
    }

    private fun subscribeLoginEvent() {
        viewModel.loginEvent.observe(this, EventObserver {
            viewModel.getApplication<MyApplication>().myID.value = viewModel.id.value
            viewModel.id.value = ""
            viewModel.pwd.value = ""
            startActivity(Intent(this@LoginActivity, ChooserActivity::class.java))
        })
    }

}