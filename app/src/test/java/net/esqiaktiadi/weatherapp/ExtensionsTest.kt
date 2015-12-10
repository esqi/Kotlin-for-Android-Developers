package net.esqiaktiadi.weatherapp

import net.esqiaktiadi.weatherapp.extensions.toDateString
import org.junit.Test
import java.text.DateFormat
import kotlin.test.assertEquals

class ExtensionsTest {

    @Test fun testLongToDateString() {
        assertEquals("Oct 20, 2015", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat() {
        assertEquals("Tuesday, October 20, 2015", 1445275635000L.toDateString(DateFormat.FULL))
    }
}