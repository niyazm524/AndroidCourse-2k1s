package ml.bimdev.baseapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_slide.*

class SlideHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(slide: Slide) {
        tv_title.text = slide.title
        tv_content.text = slide.content
        if (slide.url.isNotEmpty())
            Glide.with(containerView)
                .load(slide.url)
                .thumbnail(0.3f)
                .centerInside()
                .into(iv_preview)
    }
}
