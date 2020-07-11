package com.jajodia.covidtracker.application

import android.app.Application

class CovidTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        @get:Synchronized
        lateinit var instance: CovidTrackerApplication
            private set
    }

}