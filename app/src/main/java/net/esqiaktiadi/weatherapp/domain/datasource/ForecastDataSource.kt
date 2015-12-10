package net.esqiaktiadi.weatherapp.domain.datasource

import net.esqiaktiadi.weatherapp.domain.model.Forecast
import net.esqiaktiadi.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    
    fun requestForecastById(id: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}