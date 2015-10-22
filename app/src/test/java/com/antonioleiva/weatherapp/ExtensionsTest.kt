package com.antonioleiva.weatherapp

import com.antonioleiva.weatherapp.extensions.toDateString
import org.junit.Test
import java.text.DateFormat
import kotlin.test.assertEquals

class ExtensionsTest {

    @Test fun testLongToDateString() {
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat() {
        assertEquals("Monday, October 19, 2015", 1445275635000L.toDateString(DateFormat.FULL))
    }
}