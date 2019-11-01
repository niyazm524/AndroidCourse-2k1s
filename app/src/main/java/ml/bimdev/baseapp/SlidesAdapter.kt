package ml.bimdev.baseapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SlidesAdapter(slides: List<Slide>) :
    RecyclerView.Adapter<SlideHolder>() {
    private var itemClickListener: ((Slide) -> Unit)? = null

    var slides = slides
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SlideHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_slide, parent, false)
    )

    override fun getItemCount() = slides.count()

    override fun getItemId(position: Int) = slides[position].id

    override fun onBindViewHolder(holder: SlideHolder, position: Int) {
        holder.bind(slides[position], this::onItemClick)
    }

    private fun onItemClick(slide: Slide) {
        itemClickListener?.let { it(slide) }
    }

    fun setOnItemClickListener(listener: (slide: Slide) -> Unit) {
        itemClickListener = listener
    }

    fun removeOnItemClickListener() {
        itemClickListener = null
    }
}
