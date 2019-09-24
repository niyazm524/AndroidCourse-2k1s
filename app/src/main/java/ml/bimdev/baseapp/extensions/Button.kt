package ml.bimdev.baseapp.extensions

import android.app.Activity
import android.content.Intent
import android.widget.Button

fun Button.startActivityOnClick(activity: Class<out Activity>) {
    this.setOnClickListener {
        val intent = Intent(this.context, activity)
        this.context.startActivity(intent)
    }
}