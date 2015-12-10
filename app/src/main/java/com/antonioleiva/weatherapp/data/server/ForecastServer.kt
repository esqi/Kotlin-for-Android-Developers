package com.antonioleiva.weatherapp.data.server

import com.antonioleiva.weatherapp.data.db.ForecastDb
import com.antonioleiva.weatherapp.domain.datasource.ForecastDataSource
import com.antonioleiva.weatherapp.domain.model.Forecast
import com.antonioleiva.weatherapp.domain.model.ForecastList

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastById(id: Long, date: Long): ForecastList? {
        val result = ForecastByIdRequest(id).execute()
        val converted = dataMapper.convertToDomain(id, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastById(id, date)
    }

    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()
}