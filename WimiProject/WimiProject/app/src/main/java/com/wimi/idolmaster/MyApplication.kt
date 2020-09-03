package com.wimi.idolmaster

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.wimi.idolmaster.di.appModules
import com.wimi.idolmaster.domain.model.IdolData
import com.wimi.idolmaster.utils.AppLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    val myID = MutableLiveData<String>("")
    val myIdol = MutableLiveData<IdolData>()
    val myType = MutableLiveData<ArrayList<String>>()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            AppLogger.init()
        }

        // start Koin!
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // modules
            modules(appModules)
        }
    }
}