package com.crs.bluered

import android.app.Application
import com.crs.bluered.core.utils.logging.DebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.Forest.plant(DebugTree())
        }
    }
}