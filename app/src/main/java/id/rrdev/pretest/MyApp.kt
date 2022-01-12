package id.rrdev.pretest

import android.app.Application
import id.rrdev.pretest.di.viewModelModule
import id.rrdev.pretest.utils.PrefManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

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

        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }

}