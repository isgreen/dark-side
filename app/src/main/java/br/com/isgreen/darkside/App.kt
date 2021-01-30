package br.com.isgreen.darkside

import android.app.Application
import br.com.isgreen.darkside.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Éverdes Soares on 01/29/2021.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appComponent)
        }
    }
}