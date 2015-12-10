package net.esqiaktiadi.weatherapp.data.server

import com.google.gson.Gson
import java.net.URL

class ForecastByIdRequest(val id: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&id="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + id).readText()
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}