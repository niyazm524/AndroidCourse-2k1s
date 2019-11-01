package ml.bimdev.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_slide.*

class SlideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        intent.getParcelableExtra<Slide>("slide")?.let { slide ->
            tv_title.text = slide.title
            tv_content.text = slide.content
            Glide.with(this)
                .load(slide.url)
                .fitCenter()
                .thumbnail(0.5f)
                .into(iv_preview)
        }
    }
}
