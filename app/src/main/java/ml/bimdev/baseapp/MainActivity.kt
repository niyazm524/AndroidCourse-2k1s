package ml.bimdev.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter = SlidesAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_slides.adapter = adapter
        adapter.slides = SlidesProvider.slides
    }
}
