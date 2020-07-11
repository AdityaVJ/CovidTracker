package com.jajodia.covidtracker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.jajodia.covidtracker.models.ApiResponseModel
import com.jajodia.covidtracker.repositories.CovidTrackerRepository

class CovidTrackerViewModel : ViewModel() {

    private val covidTrackerRepository = CovidTrackerRepository()
    private val apiResponseData: LiveData<ApiResponseModel> = covidTrackerRepository.responseData()

    fun getResponseData() = apiResponseData

    init {
        covidTrackerRepository.getStatsData()
    }
}