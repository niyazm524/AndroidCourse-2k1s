package ml.bimdev.baseapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ml.bimdev.baseapp.R
import ml.bimdev.baseapp.data.City

class CityAdapter :
    ListAdapter<City, CityHolder>(CitiesDiffCallback()) {
    private var itemClickListener: ((City) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
    )

    override fun getItemId(position: Int) = getItem(position).id

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(getItem(position), this::onItemClick)
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

class CitiesDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: City, newItem: City) = oldItem == newItem
}
