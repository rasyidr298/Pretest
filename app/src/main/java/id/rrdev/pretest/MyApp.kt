package id.rrdev.pretest

import android.app.Application
import id.rrdev.pretest.utils.PrefManager

class MyApp: Application() {

    companion object {
        @get:Synchronized
        lateinit var instance: MyApp
        lateinit var prefManager: PrefManager

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefManager = PrefManager(this)
    }
}