package ml.bimdev.baseapp.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import ml.bimdev.baseapp.R

import kotlinx.android.synthetic.main.activity_b.*

class ActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            Intent(this, ActivityC::class.java).also {
                startActivity(it)
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
