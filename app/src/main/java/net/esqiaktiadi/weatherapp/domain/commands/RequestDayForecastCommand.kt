package net.esqiaktiadi.weatherapp.domain.commands

import net.esqiaktiadi.weatherapp.domain.datasource.ForecastProvider
import net.esqiaktiadi.weatherapp.domain.model.Forecast

class RequestDayForecastCommand(
        val id: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}