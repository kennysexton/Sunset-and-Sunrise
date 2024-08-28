package com.kennysexton.sunset

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kennysexton.sunset.model.WeatherResponse
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import timber.log.Timber
import kotlin.math.roundToInt

const val weatherIconPath = "https://openweathermap.org/img/wn/"
const val weatherIconEnding = "@2x.png"

class RecyclerAdapter(private val items : ArrayList<WeatherResponse>, private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

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

        holder.temperature.text = context.resources.getString(R.string.weatherDegrees, items[position].main.temp.roundToInt())
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

    /**
     * Function for deleting rows via swipe action
     * @param viewHolder
     */
    fun removeItem(viewHolder: ViewHolder){
        val removedPosition = viewHolder.adapterPosition
        val removedItem = items[viewHolder.adapterPosition]
        val removedTitle = removedItem.name

        items.removeAt(removedPosition)
        notifyItemRemoved(removedPosition)


        // Log the row deletion
        Timber.d("$removedTitle deleted")


        // Snack bar to let the user know that they deleted a row
        Snackbar.make(viewHolder.itemView, "$removedTitle  deleted", Snackbar.LENGTH_LONG).setAction("UNDO"){
            items.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)

            // Write to the log that an undo
            Timber.d("$removedTitle restored")
        }.show()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val location: TextView = view.findViewById<TextView>(R.id.location)
        //    val weatherDescription: TextView = view.weatherDescription
        val temperature: TextView = view.findViewById<TextView>(R.id.temperature)
        val weatherIcon: ImageView = view.findViewById<ImageView>(R.id.weatherIcon)
    }


}


