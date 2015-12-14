/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.esqiaktiadi.weatherapp.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.esqiaktiadi.weatherapp.R
import net.esqiaktiadi.weatherapp.domain.model.ForecastList
import net.esqiaktiadi.weatherapp.ui.activities.DetailActivity
import net.esqiaktiadi.weatherapp.ui.activities.ForecastListDataFragment
import net.esqiaktiadi.weatherapp.ui.adapters.ForecastTileAdapter
import org.jetbrains.anko.startActivity

/**
 * Provides UI for the view with Tile.
 */
class TileContentFragment : Fragment(), ForecastListDataFragment {

    override fun setData(result: ForecastList) {
        forecastList = result
        setAdapter()
    }

    private var forecastList : ForecastList? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val recyclerView = inflater!!.inflate(R.layout.recycler_view, container, false) as RecyclerView
        // Set padding for Tiles
        val tilePadding = resources.getDimensionPixelSize(R.dimen.tile_padding)
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        setAdapter(recyclerView)
        return recyclerView
    }

    private fun setAdapter(v : View? = view){
        if(v != null){
            forecastList?.let {
                val result = forecastList
                val adapter = ForecastTileAdapter(result) {
                    activity.startActivity<DetailActivity>(DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }
                if(v is RecyclerView){
                    v.adapter = adapter
                }
            }
        }

    }
}