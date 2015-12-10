package net.esqiaktiadi.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import net.esqiaktiadi.weatherapp.R
import net.esqiaktiadi.weatherapp.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        val ID = "id"
        val DEFAULT_ID = 1642911L //1650357L
    }

    var id: Long by DelegatesExt.preference(this, ID, DEFAULT_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar.setDisplayHomeAsUpEnabled(true)
        cityId.setText(id.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(!cityId.text.isEmpty()) id = cityId.text.toString().toLong()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> { onBackPressed(); true }
        else -> false
    }
}