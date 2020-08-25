package com.wimi.idolmaster.ui.splash

import android.content.Intent
import android.os.Handler
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.ActivitySplashBinding
import com.wimi.idolmaster.ui.base.BaseActivity
import com.wimi.idolmaster.ui.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(
    R.layout.activity_splash,
    SplashViewModel::class
) {

    override fun onCreate() {
        startPostDelayed(goToMainActivity())
    }

    private fun startPostDelayed(intent: Intent) {
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun goToMainActivity() = Intent(this, LoginActivity::class.java)
}
