package com.example.olympik.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.olympik.main.MainActivity
import com.example.olympik.R
import com.example.olympik.common.base.DependencyInjector
import com.example.olympik.common.util.TxtWatcher
import com.example.olympik.login.Login
import com.example.olympik.login.presentation.LoginPresenter
import com.example.olympik.register.view.RegisterActivity
import com.example.olympik.register.view.RegisterChooseAccountFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), Login.View {

    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, DependencyInjector.loginRepository())

        login_edit_email.addTextChangedListener(watcher)
        login_edit_email.addTextChangedListener(TxtWatcher{
            displayEmailFailure(null)
        })
        login_edit_password.addTextChangedListener(watcher)
        login_edit_password.addTextChangedListener(TxtWatcher{
            displayPasswordFailure(null)
        })

        login_btn_enter.setOnClickListener{
            presenter.login(login_edit_email.text.toString(), login_edit_password.text.toString())
        }
        txtClickToRegister.setOnClickListener{
            goToChooseAccount()
        }
    }
    private fun goToChooseAccount(){
        intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
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
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}