package com.jajodia.covidtracker.api

import com.jajodia.covidtracker.models.ApiResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("summary")
    fun getCovidData(): Call<ApiResponseModel>
}