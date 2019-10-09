package ml.bimdev.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        et_login.addTextChangedListener {
            til_email.error = null
            til_email.isErrorEnabled = false
        }

        et_passwordOld.addTextChangedListener {
            til_passwordOld.error = null
            til_passwordOld.isErrorEnabled = false
        }

        btn_reset.setOnClickListener {
            if (!UserRepository.userExists(et_login.text.toString()))
                til_email.error = getString(R.string.err_no_such_user)
            else if (!UserRepository.validateSync(
                    et_login.text.toString(),
                    et_passwordOld.text.toString()
                )
            )
                til_passwordOld.error = getString(R.string.err_pass_incorrect)
            else {
                UserRepository.updatePassword(et_login.text.toString(), et_password.text.toString())
                onBackPressed()
            }
        }
    }
}
