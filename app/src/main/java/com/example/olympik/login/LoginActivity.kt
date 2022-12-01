package com.example.olympik.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.olympik.main.MainActivity
import com.example.olympik.R
import com.example.olympik.common.util.TxtWatcher
import com.example.olympik.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), Login {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_login)

        login_edit_email.addTextChangedListener(watcher)
        login_edit_email.addTextChangedListener(TxtWatcher{
            displayEmailFailure(null)
        })
        login_edit_password.addTextChangedListener(watcher)
        login_edit_password.addTextChangedListener(TxtWatcher{
            displayPasswordFailure(null)
        })

        login_btn_enter.setOnClickListener{
            validateLogin()
        }
        login_txt_click_to_register.setOnClickListener{
            goToChooseAccount()
        }
    }
    private fun goToChooseAccount(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private val watcher = TxtWatcher{
        login_btn_enter.isEnabled = login_edit_email.text.toString().isNotEmpty()
                && login_edit_password.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        login_btn_enter.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        login_edit_email_input.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        login_edit_password_input.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        val intent = Intent (this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    private fun validateLogin(){
        val email = login_edit_email.text.toString()
        val password = login_edit_password.text.toString()

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >=6

        if(!isEmailValid){
           displayEmailFailure(R.string.invalid_email)
        } else {
            displayEmailFailure(null)
        }

        if (!isPasswordValid){
            displayPasswordFailure(R.string.invalid_password)
        } else {
            displayPasswordFailure(null)
        }

        if (isEmailValid && isPasswordValid){
            showProgress(true)
            onUserAuthenticated()
        } else{
            onUserUnauthorized("Login ou senha inválido")
        }
    }
}