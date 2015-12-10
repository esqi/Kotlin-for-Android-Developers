package net.esqiaktiadi.weatherapp.data.server

import net.esqiaktiadi.weatherapp.domain.model.ForecastList
import net.esqiaktiadi.weatherapp.domain.model.Forecast as ModelForecast

class ServerDataMapper {

    fun convertToDomain(id: Long, forecast: ForecastResult): ForecastList = with(forecast) {
        ForecastList(id, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast = with(forecast) {
        ModelForecast(-1, dt * 1000, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}