package com.kennysexton.sunset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kennysexton.sunset.navigation.LocationSearch
import com.kennysexton.sunset.navigation.WeatherLanding
import com.kennysexton.sunset.ui.components.LocationSearch
import com.kennysexton.sunset.ui.theme.SunriseSunsetTheme
import com.kennysexton.sunset.ui.components.WeatherLandingScreen

class MainActivity : ComponentActivity() {
    private val weatherLandingVM by viewModels<WeatherLandingVM>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val weatherList = weatherLandingVM.getWeatherData()

        setContent {
            SunriseSunsetTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = WeatherLanding,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<WeatherLanding> {
                            WeatherLandingScreen(innerPadding, weatherList, weatherLandingVM)
                        }
                        composable<LocationSearch> {
                            LocationSearch(innerPadding)
                        }
                    }


                }
            }
        }
    }
}

//const val weatherIconPath = "https://openweathermap.org/img/wn/"
//const val weatherIconEnding = "@2x.png"
//        setContentView(R.layout.activity_main)
//
//        // FOB
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            val snackbar = Snackbar.make(view, "Add a new location", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
//            snackbar.show()
//
//            supportFragmentManager.replaceFragment(
//                SearchFragment.TAG,
//                R.id.main_fragment,
//                true
//            ) {
//                SearchFragment.newInstance()
//            }
//        }
//
//        // Recycler init
//        recyclerView = findViewById(R.id.locationsList)
//
//        // Color behind the rows of our recycle view
//        swipeBackground = ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary))
//
//        // Shared preferences
//        sharedpreferences = getSharedPreferences(
//            getString(R.string.LOCATIONSKEY),
//            Context.MODE_PRIVATE
//        )
//
//        getStoredLocationList()
//
//
//        val apiInterface = OpenWeatherAPI.create().getCurrentWeather(
//            BuildConfig.OPEN_WEATHER_KEY,
//            "imperial"
//        ) //TODO move imperial to shared preferences
//
//
//        val weatherResponseList: ArrayList<WeatherResponse> = ArrayList()
//
//        apiInterface.enqueue(object : Callback<WeatherResponse> {
//            override fun onResponse(
//                call: Call<WeatherResponse>?,
//                response: Response<WeatherResponse>?
//            ) {
//                if (response?.body() != null) {
//                    Timber.d("response success: ${response.body()}")
//                    response.body()?.let { weatherResponseList.add(it) }
//                    showData(weatherResponseList)
//                }
//            }
//
//
//            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
//                Timber.e("response failure:  $t")
//            }
//        })
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//}

//    private fun getStoredLocationList(): MutableSet<String?>? {
//
//        val locations: MutableSet<String?>? =
//            sharedpreferences.getStringSet(
//                getString(R.string.LOCATIONSKEY),
//                null
//            ) as MutableSet<String?>?
//
//        Timber.d("Returned a set of size: ${locations?.size} from sharedpreferences")
//        return locations
//    }

/**
 * Populates the recyclerView from Earthquake List
 */
//    private fun showData(items: ArrayList<WeatherResponse>) {
//        recyclerView.apply {
//            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//            recyclerAdapter = RecyclerAdapter(items, this@MainActivity)
//            recyclerView.adapter = recyclerAdapter
//            Timber.d(items.toString())
//
//            // Initialize Delete Icon
//            val deleteIcon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_trash)!!
//
//            // Swipe to delete
//            val itemTouchHelperCallback = object :
//                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//
//                /**
//                 * One of itemTouchHelper's functions.  Not used in this example
//                 * @return Boolean
//                 */
//                override fun onMove(
//                    recyclerView: RecyclerView,
//                    viewHolder: RecyclerView.ViewHolder,
//                    target: RecyclerView.ViewHolder
//                ): Boolean {
//                    return false
//                }
//
//                /**
//                 * Detects a swipe on a RecyclerView row
//                 * @param viewHolder Our recyclerView
//                 * @param positon in the adapter
//                 */
//                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, positon: Int) {
//                    (adapter as RecyclerAdapter).removeItem(viewHolder as RecyclerAdapter.ViewHolder)
//                }
//
//                /**
//                 * Programmatically generated view behind each row
//                 */
//                override fun onChildDraw(
//                    c: Canvas,
//                    recyclerView: RecyclerView,
//                    viewHolder: RecyclerView.ViewHolder,
//                    dX: Float,
//                    dY: Float,
//                    actionState: Int,
//                    isCurrentlyActive: Boolean
//                ) {
//                    val rowRectangle = viewHolder.itemView
//
//                    // Calculate vertical margins for delete Icon
//                    val iconMargin = (rowRectangle.height - deleteIcon.intrinsicHeight) / 2
//
//                    // Swiped Right
//                    if (dX > 0) {
//                        swipeBackground.setBounds(
//                            rowRectangle.left,
//                            rowRectangle.top,
//                            dX.toInt(),
//                            rowRectangle.bottom
//                        )
//                        deleteIcon.setBounds(
//                            rowRectangle.left + iconMargin,
//                            rowRectangle.top + iconMargin,
//                            rowRectangle.left + iconMargin + deleteIcon.intrinsicWidth,
//                            rowRectangle.bottom - iconMargin
//                        )
//                    }
//                    // Swiped Left
//                    else {
//                        swipeBackground.setBounds(
//                            rowRectangle.right + dX.toInt(),
//                            rowRectangle.top,
//                            rowRectangle.right,
//                            rowRectangle.bottom
//                        )
//                        deleteIcon.setBounds(
//                            rowRectangle.right - iconMargin - deleteIcon.intrinsicWidth,
//                            rowRectangle.top + iconMargin,
//                            rowRectangle.right - iconMargin,
//                            rowRectangle.bottom - iconMargin
//                        )
//                    }
//
//                    // Draw the red background
//                    swipeBackground.draw(c)
//
//                    c.save()
//
//                    // Clipping so icon appears behind row
//                    if (dX > 0) {
//                        c.clipRect(
//                            rowRectangle.left,
//                            rowRectangle.top,
//                            dX.toInt(),
//                            rowRectangle.bottom
//                        )
//                    } else {
//                        c.clipRect(
//                            rowRectangle.right + dX.toInt(),
//                            rowRectangle.top,
//                            rowRectangle.right,
//                            rowRectangle.bottom
//                        )
//                    }
//
//                    deleteIcon.draw(c)
//                    c.restore()
//
//                    super.onChildDraw(
//                        c,
//                        recyclerView,
//                        viewHolder,
//                        dX,
//                        dY,
//                        actionState,
//                        isCurrentlyActive
//                    )
//                }
//            }
//            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
//            itemTouchHelper.attachToRecyclerView(recyclerView)
//
//        }
//
//    }
//}