package net.esqiaktiadi.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tile.view.*
import net.esqiaktiadi.weatherapp.R
import net.esqiaktiadi.weatherapp.domain.model.Forecast
import net.esqiaktiadi.weatherapp.domain.model.ForecastList
import net.esqiaktiadi.weatherapp.extensions.ctx
import net.esqiaktiadi.weatherapp.extensions.toDateString
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.onClick

class ForecastTileAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastTileAdapter.TileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val view = parent.ctx.layoutInflater.inflate(R.layout.item_tile, parent, false)
        return TileViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size()

    class TileViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
//                itemView.maxTemperature.text = "${high.toString()}º"
//                itemView.minTemperature.text = "${low.toString()}º"
                itemView.onClick { itemClick(forecast) }
            }
        }
    }
}