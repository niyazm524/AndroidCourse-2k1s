package ml.bimdev.baseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_password.addTextChangedListener {
            setInputLayoutError(til_password, null)
        }
        et_login.addTextChangedListener {
            setInputLayoutError(til_email, null)
        }

        btn_login.setOnClickListener {
            doLogin(et_login.text.toString(), et_password.text.toString())
        }

        tv_resetPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }

    private fun setInputLayoutError(til: TextInputLayout, errorMessage: String?) = til.apply {
        if (errorMessage != null) {
            error = errorMessage
            isPasswordVisibilityToggleEnabled = false
        } else {
            error = null
            isErrorEnabled = false
            isPasswordVisibilityToggleEnabled = true
        }
    }

    private fun doLogin(login: String, password: String) = GlobalScope.launch(Dispatchers.Main) {
        progressBar.visibility = View.VISIBLE
        val validation = async(Dispatchers.Default) {
            return@async UserRepository.validate(login, password)
        }
        when (validation.await()) {
            ValidationStatus.WRONG_PASS -> setInputLayoutError(
                til_password,
                getString(R.string.err_pass_incorrect)
            )
            ValidationStatus.WRONG_LOGIN -> setInputLayoutError(
                til_email,
                getString(R.string.err_no_such_user)
            )
            ValidationStatus.SUCCESS -> {
                setInputLayoutError(til_email, null)
                setInputLayoutError(til_password, null)
                startActivity(Intent(this@LoginActivity, MainActivity::class.java).also {
                    it.putExtra("login", login)
                })
            }
        }
        progressBar.visibility = View.GONE
    }
}
