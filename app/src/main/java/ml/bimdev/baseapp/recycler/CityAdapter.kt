package ml.bimdev.baseapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ml.bimdev.baseapp.R
import ml.bimdev.baseapp.data.City

class CityAdapter(cities: List<City> = listOf()) :
    RecyclerView.Adapter<CityHolder>() {
    private var itemClickListener: ((City) -> Unit)? = null

    var cities = cities
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
    )

    override fun getItemCount() = cities.count()

    override fun getItemId(position: Int) = cities[position].id

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(cities[position], this::onItemClick)
    }

    private fun onItemClick(city: City) {
        itemClickListener?.let { it(city) }
    }

    fun setOnItemClickListener(listener: (city: City) -> Unit) {
        itemClickListener = listener
    }

    fun removeOnItemClickListener() {
        itemClickListener = null
    }
}
