package net.esqiaktiadi.weatherapp.ui.activities

import net.esqiaktiadi.weatherapp.domain.model.ForecastList

interface ForecastListDataFragment {

    fun setData (result: ForecastList)
}