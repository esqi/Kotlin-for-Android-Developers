package net.esqiaktiadi.weatherapp.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import net.esqiaktiadi.weatherapp.R
import net.esqiaktiadi.weatherapp.domain.commands.RequestForecastCommand
import net.esqiaktiadi.weatherapp.extensions.DelegatesExt
import net.esqiaktiadi.weatherapp.ui.fragments.CardContentFragment
import net.esqiaktiadi.weatherapp.ui.fragments.ListContentFragment
import net.esqiaktiadi.weatherapp.ui.fragments.TileContentFragment
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {

    val id: Long by DelegatesExt.preference(this, SettingsActivity.ID,
            SettingsActivity.DEFAULT_ID)

//    val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initToolbar()
        setSupportActionBar(toolbar)
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }

        // Set behavior of Navigation drawer
        navView.setNavigationItemSelectedListener {
            // This method will trigger on item Click of navigation menu
            // Set item in checked state
            it.setChecked(true)

            // TODO: handle navigation

            // Closing drawer on item click
            drawer!!.closeDrawers()
            true
        }
        // Adding Floating Action Button to bottom right of main view
        fab.setOnClickListener { v ->
            Snackbar.make(v, "Hello SnackBar!",
                    Snackbar.LENGTH_LONG).show()
        }

//        attachToScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = async {
        val result = RequestForecastCommand(id).execute()
        uiThread {
            viewpager.adapter.let {
                if(it is Adapter){
                    val adapter = it
                    (0 until it.count).forEach{
                        adapter.getItem(it).let {
                            if(it is ForecastListDataFragment){
                                it.setData(result)
                            }
                        }
                    }
                }
            }
//            supportActionBar.title = "${result.city} (${result.country})"
        }
    }

    // Add Fragments to Tabs
    private fun setupViewPager(viewPager: ViewPager) {
        with(Adapter(supportFragmentManager)){
            addFragment(ListContentFragment(), "List")
            addFragment(TileContentFragment(), "Tile")
            addFragment(CardContentFragment(), "Card")
            viewPager.adapter = this
        }
    }

    internal class Adapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment = mFragmentList[position]

        override fun getCount(): Int = mFragmentList.size

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence = mFragmentTitleList[position]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId){
        R.id.action_settings -> {startActivity<SettingsActivity>();true}
        android.R.id.home -> {
            drawer!!.openDrawer(GravityCompat.START)
            super.onOptionsItemSelected(item)
        }
        else -> super.onOptionsItemSelected(item)
    }
}
