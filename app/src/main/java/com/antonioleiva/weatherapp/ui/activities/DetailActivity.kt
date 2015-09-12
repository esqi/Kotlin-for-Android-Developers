package com.antonioleiva.weatherapp.ui.activities;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.antonioleiva.weatherapp.R
import com.antonioleiva.weatherapp.domain.commands.RequestDayForecastCommand
import com.antonioleiva.weatherapp.domain.model.Forecast
import com.antonioleiva.weatherapp.extensions.color
import com.antonioleiva.weatherapp.extensions.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.activity_detail.icon
import kotlinx.android.synthetic.activity_detail.maxTemperature
import kotlinx.android.synthetic.activity_detail.minTemperature
import kotlinx.android.synthetic.activity_detail.weatherDescription
import org.jetbrains.anko.async
import org.jetbrains.anko.ctx
import org.jetbrains.anko.textColor
import org.jetbrains.anko.uiThread
import java.text.DateFormat

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra(CITY_NAME)

        async {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread { bindForecast(result) }
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        supportActionBar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first.toString()}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}