package com.kennysexton.sunset

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.kennysexton.sunset.model.OpenWeatherAPI
import com.kennysexton.sunset.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    // Color behind the rows of our recycle view
    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#F24D4D"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setContentView(R.layout.activity_main)

        // FOB
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Add a new location", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Recycler init
        recyclerView = findViewById(R.id.locationsList)

        val apiInterface = OpenWeatherAPI.create().getCurrentWeather(
            BuildConfig.OPEN_WEATHER_KEY,
            "imperial"
        ) //TODO move imperial to shared preferences
        val items: ArrayList<WeatherResponse> = ArrayList()


        apiInterface.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>?,
                response: Response<WeatherResponse>?
            ) {

                if (response?.body() != null) {
                    Timber.d(response.body()!!.toString())
                    response.body()?.let { items.add(it) }
                    showData(items)
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

    /**
     * Populates the recyclerView from Earthquake List
     * @param List of Earthquakes
     */
    private fun showData(items: ArrayList<WeatherResponse>) {
            recyclerView.apply {
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerAdapter = RecyclerAdapter(items, this@MainActivity)
                recyclerView.adapter = recyclerAdapter
                Timber.d(items.toString())

            // Initialize Delete Icon
            val deleteIcon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_trash)!!


            // Swipe to delete
            val itemTouchHelperCallback = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                /**
                 * One of itemTouchHelper's functions.  Not used in this example
                 * @return Boolean
                 */
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                /**
                 * Detects a swipe on a RecyclerView row
                 * @param viewHolder Our recyclerView
                 * @param positon in the adapter
                 */
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, positon: Int) {
                    (adapter as RecyclerAdapter).removeItem(viewHolder as RecyclerAdapter.ViewHolder)
                }

                /**
                 * Programmatically generated view behind each row
                 */
                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    val rowRectangle = viewHolder.itemView

                    // Calculate vertical margins for delete Icon
                    val iconMargin = (rowRectangle.height - deleteIcon.intrinsicHeight) / 2

                    // Swiped Right
                    if (dX > 0) {
                        swipeBackground.setBounds(
                            rowRectangle.left,
                            rowRectangle.top,
                            dX.toInt(),
                            rowRectangle.bottom
                        )
                        deleteIcon.setBounds(
                            rowRectangle.left + iconMargin,
                            rowRectangle.top + iconMargin,
                            rowRectangle.left + iconMargin + deleteIcon.intrinsicWidth,
                            rowRectangle.bottom - iconMargin
                        )
                    }
                    // Swiped Left
                    else {
                        swipeBackground.setBounds(
                            rowRectangle.right + dX.toInt(),
                            rowRectangle.top,
                            rowRectangle.right,
                            rowRectangle.bottom
                        )
                        deleteIcon.setBounds(
                            rowRectangle.right - iconMargin - deleteIcon.intrinsicWidth,
                            rowRectangle.top + iconMargin,
                            rowRectangle.right - iconMargin,
                            rowRectangle.bottom - iconMargin
                        )
                    }


                    // Draw the red background
                    swipeBackground.draw(c)

                    c.save()

                    // Clipping so icon appears behind row
                    if (dX > 0) {
                        c.clipRect(
                            rowRectangle.left,
                            rowRectangle.top,
                            dX.toInt(),
                            rowRectangle.bottom
                        )
                    } else {
                        c.clipRect(
                            rowRectangle.right + dX.toInt(),
                            rowRectangle.top,
                            rowRectangle.right,
                            rowRectangle.bottom
                        )
                    }

                    deleteIcon.draw(c)
                    c.restore()

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }
            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(recyclerView)

        }

    }
}