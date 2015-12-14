package net.esqiaktiadi.weatherapp.ui.adapters

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card.view.*
import net.esqiaktiadi.weatherapp.R
import net.esqiaktiadi.weatherapp.domain.model.Forecast
import net.esqiaktiadi.weatherapp.domain.model.ForecastList
import net.esqiaktiadi.weatherapp.extensions.ctx
import net.esqiaktiadi.weatherapp.extensions.toDateString
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.onClick

class ForecastCardAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastCardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = parent.ctx.layoutInflater.inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size()

    class CardViewHolder(view: View, val itemClick: (Forecast) -> Unit)
    : RecyclerView.ViewHolder(view) {

        init {
            // Adding SnackBar to Action Button inside card
            itemView.actionButton.onClick {
                Snackbar.make(it, "Action is pressed",
                        Snackbar.LENGTH_LONG).show()
            }

            itemView.favoriteButton.onClick {
                Snackbar.make(it, "Added to Favorite",
                        Snackbar.LENGTH_LONG).show()
            }

            itemView.shareButton.onClick {
                Snackbar.make(it, "Share article",
                        Snackbar.LENGTH_LONG).show()
            }
        }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
//                            itemView.maxTemperature.text = "${high.toString()}º"
//                            itemView.minTemperature.text = "${low.toString()}º"
                itemView.onClick { itemClick(forecast) }
            }
        }
    }
}