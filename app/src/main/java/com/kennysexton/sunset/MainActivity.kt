package com.kennysexton.sunset

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennysexton.sunset.model.Movie
import com.kennysexton.sunset.model.OpenWeatherAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Add a new location", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        Log.d("test", "test")
        val apiInterface = OpenWeatherAPI.create().getMovies()
        val items : ArrayList<String> = ArrayList()
        items.add("list1")
        items.add("list2")

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerAdapter(items,this)
        recyclerView.adapter = recyclerAdapter
        Log.d("test", items.toString())


//        apiInterface.enqueue( object : Callback<List<Movie>> {
//            override fun onResponse(call: Call<List<Movie>>?, response: Response<List<Movie>>?) {
//
//                if(response?.body() != null)
//                    recyclerAdapter.setMovieListItems(response.body()!!)
//            }
//
//            override fun onFailure(call: Call<List<Movie>>?, t: Throwable?) {
//
//            }
//        })
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