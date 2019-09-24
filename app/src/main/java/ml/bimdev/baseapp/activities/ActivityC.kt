package ml.bimdev.baseapp.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_common.*
import ml.bimdev.baseapp.R
import ml.bimdev.baseapp.extensions.startActivityOnClick

class ActivityC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        tv_hello.text = this.localClassName
        btn_start.text = "Start Random activity"
        val activities = listOf<Class<out Activity>>(
            MainActivity::class.java,
            FullscreenActivity::class.java,
            ActivityA::class.java,
            ActivityB::class.java,
            ActivityC::class.java
        )
        btn_start.startActivityOnClick(activities.random())
    }
}
