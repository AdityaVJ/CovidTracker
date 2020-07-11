package com.jajodia.covidtracker.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jajodia.covidtracker.R
import com.jajodia.covidtracker.models.CountriesModel
import kotlinx.android.synthetic.main.entries_layout.view.*

class CasesAdapter(private val list: List<CountriesModel>?) :
    RecyclerView.Adapter<CasesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.entries_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (list?.size != null)
            list.size
        else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val singleItem = list!![position]

        holder.itemView.country.text = singleItem.country
        holder.itemView.total.text = singleItem.totalConfirmed
        holder.itemView.deaths.text = singleItem.totalDeaths
        holder.itemView.recovered.text = singleItem.totalRecovered

        if (position == 0) {
            holder.itemView.country.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            holder.itemView.total.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            holder.itemView.deaths.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            holder.itemView.recovered.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}