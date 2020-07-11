package com.jajodia.covidtracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jajodia.covidtracker.R
import com.jajodia.covidtracker.adapters.CasesAdapter
import com.jajodia.covidtracker.models.CountriesModel
import com.jajodia.covidtracker.repositories.CovidTrackerRepository
import com.jajodia.covidtracker.viewmodels.CovidTrackerViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get((CovidTrackerViewModel::class.java)) }
    private var casesList: List<CountriesModel>? = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        cases_list.adapter = CasesAdapter(casesList)

        viewModel.getResponseData().observe(this, Observer {
            casesList = it.countries
            cases_list.adapter = CasesAdapter(casesList)
            cases_list.adapter?.notifyDataSetChanged()
        })

    }
}
