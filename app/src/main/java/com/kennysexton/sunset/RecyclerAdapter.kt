package com.kennysexton.sunset

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kennysexton.sunset.model.WeatherResponse
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*
import timber.log.Timber
import kotlin.math.roundToInt

const val weatherIconPath = "https://openweathermap.org/img/wn/"
const val weatherIconEnding = "@2x.png"

class RecyclerAdapter(private val items : ArrayList<WeatherResponse>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d("In OnCreateViewHolder")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val iconPath = "$weatherIconPath${items[position].weather[0].icon}$weatherIconEnding"
        Timber.d("Loading icon from path: $iconPath")

        holder.location.text = items[position].name
//        holder.weatherDescription.text = items[position].weather[0].description

        holder.temperature.text = items[position].main.temp.roundToInt().toString()
        Picasso.get().load(iconPath).into(holder.weatherIcon , object : Callback {
            override fun onSuccess() {
                Timber.d("Picasso image load successful")
                // put transition here
            }

            override fun onError(e: Exception?) {
                Timber.e("Picasso load error $e for icon with path: $iconPath")
            }
        })
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val location: TextView = view.location
//    val weatherDescription: TextView = view.weatherDescription
    val temperature: TextView = view.temperature
    val weatherIcon: ImageView = view.weatherIcon

}