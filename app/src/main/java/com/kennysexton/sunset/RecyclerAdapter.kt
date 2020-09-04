package com.kennysexton.sunset

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kennysexton.sunset.model.WeatherResponse
import kotlinx.android.synthetic.main.item_row.view.*
import timber.log.Timber

class RecyclerAdapter(private val items : ArrayList<WeatherResponse>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d("In OnCreateViewHolder")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.location.text = items[position].name
        holder.weatherDescription.text = items[position].weather[0].description
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val location: TextView = view.location
    val weatherDescription: TextView = view.weatherDescription
}