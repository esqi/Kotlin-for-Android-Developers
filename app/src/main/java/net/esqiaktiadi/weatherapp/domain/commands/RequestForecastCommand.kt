package net.esqiaktiadi.weatherapp.domain.commands

import net.esqiaktiadi.weatherapp.domain.datasource.ForecastProvider
import net.esqiaktiadi.weatherapp.domain.model.ForecastList

class RequestForecastCommand(
        val id: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestById(id, DAYS)
}