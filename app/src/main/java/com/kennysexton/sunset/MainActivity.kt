package com.kennysexton.sunset

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennysexton.sunset.model.OpenWeatherAPI
import com.kennysexton.sunset.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setContentView(R.layout.activity_main)

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Add a new location", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val apiInterface = OpenWeatherAPI.create().getMovies(BuildConfig.OPEN_WEATHER_KEY)
        val items: ArrayList<WeatherResponse> = ArrayList()

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerAdapter(items, this)
        recyclerView.adapter = recyclerAdapter
        Timber.d(items.toString())

        apiInterface.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {

                if (response?.body() != null) {
                    Timber.d(response.body()!!.toString())
                    response.body()?.let { items.add(it) }
                    recyclerAdapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Timber.e("response failure:  $t")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}