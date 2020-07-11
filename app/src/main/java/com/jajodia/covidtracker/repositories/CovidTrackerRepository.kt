package com.jajodia.covidtracker.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jajodia.covidtracker.api.WebService
import com.jajodia.covidtracker.models.ApiResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CovidTrackerRepository {

    private val mApiClient = WebService.apiService
    private val apiResponse: MutableLiveData<ApiResponseModel> = MutableLiveData()

    fun responseData() = apiResponse

    fun getStatsData() {

        mApiClient.getCovidData().enqueue(object : Callback<ApiResponseModel> {
            override fun onResponse(
                call: Call<ApiResponseModel>,
                response: Response<ApiResponseModel>
            ) {
                if (response.isSuccessful) {

                    if (response.body() != null) {
                        apiResponse.postValue(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponseModel>, t: Throwable) {
                apiResponse.postValue(null)
            }

        })

    }
}