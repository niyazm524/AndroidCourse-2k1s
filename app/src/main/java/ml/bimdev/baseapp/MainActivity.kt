package ml.bimdev.baseapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ShareCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_send.setOnClickListener {
            startActivityForResult(
                ShareCompat.IntentBuilder
                    .from(this)
                    .addEmailTo(et_recipient.text.toString())
                    .setSubject("Домаха по онтроету")
                    .setText(et_body.text.toString())
                    .setType("text/plain")
                    .createChooserIntent(),
                REQUEST_CODE_SHARE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SHARE) {
            Toast.makeText(
                this,
                when (resultCode) {
                    Activity.RESULT_OK -> "всё окей"
                    Activity.RESULT_CANCELED -> "Письмо не отправилось (якобы)"
                    else -> "Результат: $resultCode"
                },
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val REQUEST_CODE_SHARE = 1
    }
}
