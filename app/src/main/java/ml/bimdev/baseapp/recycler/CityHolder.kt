package ml.bimdev.baseapp.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.view.*
import ml.bimdev.baseapp.data.City
import ml.bimdev.baseapp.extensions.weatherColor

class CityHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(city: City, listener: (City) -> Unit) {
        containerView.setOnClickListener { listener(city) }
        containerView.tv_city_name.text = city.name
        containerView.tv_city_temp.text = "${city.temp}°"
        containerView.tv_city_temp.setTextColor(city.weatherColor)

//        if (city.url.isNotEmpty())
//            Glide.with(containerView)
//                .load(city.url)
//                .thumbnail(0.3f)
//                .centerInside()
//                .into(iv_preview)
    }
}
