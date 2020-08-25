package com.wimi.idolmaster.ui.login

import android.content.Intent
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
        subscribeLoginEvent()
    }

    private fun subscribeLoginEvent() {
        viewModel.loginEvent.observe(this, EventObserver {
            viewModel.id.value = ""
            viewModel.pwd.value = ""
            startActivity(Intent(this@LoginActivity, ChooserActivity::class.java))
        })
    }

}