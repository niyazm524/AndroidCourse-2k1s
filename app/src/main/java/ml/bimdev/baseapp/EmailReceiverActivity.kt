package ml.bimdev.baseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_email_receiver.*

class EmailReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_receiver)
        if (intent.action == Intent.ACTION_SEND || intent.action == Intent.ACTION_SENDTO) {
            tv_right_to.text = intent.getStringArrayExtra(Intent.EXTRA_EMAIL)
                ?.joinToString("\n") ?: "No recipient"
            tv_right_subject.text = intent.getStringExtra(Intent.EXTRA_SUBJECT) ?: "No subject"
            tv_right_body.text = intent.getStringExtra(Intent.EXTRA_TEXT) ?: "No body text"
        }
    }
}
