package com.jajodia.covidtracker.utils

import android.content.Context
import com.jajodia.covidtracker.application.CovidTrackerApplication

/**
 * Created by Aditya V Jajodia on 11-07-2020.
 */

class ResourceProviders {

    companion object {

        fun getApplicationContext(): Context {
            return CovidTrackerApplication.instance.applicationContext
        }

        fun getStringResource(resourceId: Int): String {
            return getApplicationContext().getString(resourceId)
        }

    }

}