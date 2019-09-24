package ml.bimdev.baseapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_common.*
import ml.bimdev.baseapp.R
import ml.bimdev.baseapp.extensions.startActivityOnClick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        tv_hello.text = this.localClassName
        btn_start.text = "Start FullScreen"
        btn_start.startActivityOnClick(FullscreenActivity::class.java)
    }
}
