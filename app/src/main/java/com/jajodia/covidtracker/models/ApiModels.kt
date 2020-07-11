package com.jajodia.covidtracker.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponseModel(
    @Json(name = "Global") val global: GlobalModel?,
    @Json(name = "Countries") val countries: List<CountriesModel>?,
    @Json(name = "Date") val date: String?
)

@JsonClass(generateAdapter = true)
data class CountriesModel(
    @Json(name = "Country") val country: String,
    @Json(name = "CountryCode") val countryCode: String,
    @Json(name = "Slug") val slug: String,
    @Json(name = "NewConfirmed") val newConfirmed: String,
    @Json(name = "TotalConfirmed") val totalConfirmed: String,
    @Json(name = "NewDeaths") val newDeaths: String,
    @Json(name = "TotalDeaths") val totalDeaths: String,
    @Json(name = "NewRecovered") val newRecovered: String,
    @Json(name = "TotalRecovered") val totalRecovered: String,
    @Json(name = "Date") val date: String
)

@JsonClass(generateAdapter = true)
data class GlobalModel(
    @Json(name = "NewConfirmed") val newConfirmed: String,
    @Json(name = "TotalConfirmed") val totalConfirmed: String,
    @Json(name = "NewDeaths") val newDeaths: String,
    @Json(name = "TotalDeaths") val totalDeaths: String,
    @Json(name = "NewRecovered") val newRecovered: String,
    @Json(name = "TotalRecovered") val totalRecovered: String
)