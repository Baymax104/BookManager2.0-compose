package com.baymax104.bookmanager20compose.repo

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.hjq.toast.Toaster

/**
 * Application
 * @author John
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Toaster.init(this)
    }
}