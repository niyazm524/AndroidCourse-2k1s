package ml.bimdev.baseapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_common.*
import ml.bimdev.baseapp.R
import ml.bimdev.baseapp.extensions.startActivityOnClick

class ActivityA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        tv_hello.text = this.localClassName
        btn_start.text = "Start Activity B"
        btn_start.startActivityOnClick(ActivityB::class.java)
    }
}
