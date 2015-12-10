package net.esqiaktiadi.weatherapp.domain.datasource

import net.esqiaktiadi.weatherapp.domain.model.Forecast
import net.esqiaktiadi.weatherapp.domain.model.ForecastList
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertNotNull

class ForecastProviderTest {

    @Test fun testDataSourceReturnsValue() {
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast(0)).then { Forecast(0, 0, "desc", 20, 0, "url") }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))
    }

    @Test fun emptyDatabaseReturnsServerValue() {
        val db = mock(ForecastDataSource::class.java)
        val server = mock(ForecastDataSource::class.java)
        `when`(server.requestForecastById(any(Long::class.java), any(Long::class.java)))
                .then { ForecastList(0, "city", "country", listOf()) }

        val provider = ForecastProvider(listOf(db, server))
        assertNotNull(provider.requestById(0, 0))
    }
}