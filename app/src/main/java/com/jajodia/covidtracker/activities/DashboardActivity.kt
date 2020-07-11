package com.jajodia.covidtracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jajodia.covidtracker.R
import com.jajodia.covidtracker.repositories.CovidTrackerRepository

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

    }
}
