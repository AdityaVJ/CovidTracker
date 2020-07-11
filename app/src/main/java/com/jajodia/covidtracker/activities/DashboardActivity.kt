package com.jajodia.covidtracker.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jajodia.covidtracker.Constants
import com.jajodia.covidtracker.R
import com.jajodia.covidtracker.adapters.CasesAdapter
import com.jajodia.covidtracker.models.CountriesModel
import com.jajodia.covidtracker.models.GlobalModel
import com.jajodia.covidtracker.utils.Preferences
import com.jajodia.covidtracker.viewmodels.CovidTrackerViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.kpi_card.view.*
import java.util.*

class DashboardActivity : AppCompatActivity() {

    private val requestPermissions: Int = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val viewModel by lazy { ViewModelProvider(this).get((CovidTrackerViewModel::class.java)) }
    private var casesList: MutableList<CountriesModel>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        initKpis()

        cases_list.adapter = CasesAdapter(casesList)

        if (checkAndRequestPermissions())
            getViewModelData()

    }

    private fun getViewModelData() {
        viewModel.getResponseData().observe(this, Observer {

            casesList = it.countries?.toMutableList()
            updateKpiData(it.global)

            cases_list.adapter = CasesAdapter(casesList)
            cases_list.adapter?.notifyDataSetChanged()
            getCountry()
        })
    }

    @SuppressLint("MissingPermission")
    private fun getCountry() {

        val geocoder = Geocoder(this, Locale.getDefault())
        val c = Preferences.getPref<String>(Constants.COUNTRY)

        if (c.isNullOrBlank())
            fusedLocationClient.lastLocation?.addOnCompleteListener {
                if (it.isComplete) {
                    val lat = it.result?.latitude
                    val long = it.result?.longitude

                    if (lat != null && long != null) {
                        val addr = geocoder.getFromLocation(lat, long, 1)
                        val country = addr[0]?.countryName

                        if (!country.isNullOrEmpty()) {
                            Preferences.putPref(Constants.COUNTRY, country)
                            defaultCountryOnTop(country)
                        }
                    }
                }
            }
        else {
            defaultCountryOnTop(c)
        }
    }

    private fun defaultCountryOnTop(country: String?) {

        val defaultCountryObject = casesList?.find {
            it.country.toLowerCase() == country?.toLowerCase()
        }

        val defaultObjectPostion = casesList?.indexOf(defaultCountryObject)

        if (defaultObjectPostion != null && defaultObjectPostion >= 0) {
            Collections.swap(casesList, defaultObjectPostion, 0)
            cases_list.adapter = CasesAdapter(casesList)
            cases_list.adapter?.notifyDataSetChanged()
        }

    }

    private fun initKpis() {
        total_cases.title.text = getString(R.string.total_txt)
        deaths.title.text = getString(R.string.deaths_txt)
        recovered.title.text = getString(R.string.recovered_txt)
    }

    private fun updateKpiData(data: GlobalModel?) {
        total_cases.number.text = data?.totalConfirmed
        deaths.number.text = data?.totalDeaths
        recovered.number.text = data?.totalRecovered
    }

    private fun checkAndRequestPermissions(): Boolean {

        val permissionList = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val listPermissionsNeeded = mutableListOf<String>()

        permissionList.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                listPermissionsNeeded.add(it)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                requestPermissions
            )
            return false
        } else {
            return true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {

            requestPermissions -> {

                for (i in grantResults) {

                    if (i != PackageManager.PERMISSION_GRANTED) {

                        AlertDialog.Builder(this)
                            .setTitle(getString(R.string.permission_not_granted_header))
                            .setCancelable(false)
                            .setMessage(getString(R.string.permission_not_granted_message))
                            .setPositiveButton(getString(R.string.option_okay)) { _, _ ->
                                finish()
                            }.show()
                    }

                }

                getViewModelData()

            }

        }
    }

}
