package com.antonioleiva.weatherapp.domain.datasource

import com.antonioleiva.weatherapp.domain.model.Forecast
import com.antonioleiva.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    
    fun requestForecastById(id: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}