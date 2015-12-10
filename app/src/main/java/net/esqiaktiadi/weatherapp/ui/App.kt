package net.esqiaktiadi.weatherapp.ui

import android.app.Application
import net.esqiaktiadi.weatherapp.extensions.DelegatesExt

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}