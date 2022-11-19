package com.example.olympik.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.olympik.main.MainActivity
import com.example.olympik.R
import com.example.olympik.auth.AuthActivity
import com.example.olympik.common.util.TxtWatcher
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), Login {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_edit_email.addTextChangedListener(watcher)
        login_edit_email.addTextChangedListener(TxtWatcher{
            displayEmailFailure(null)
        })
        login_edit_password.addTextChangedListener(watcher)
        login_edit_password.addTextChangedListener(TxtWatcher{
            displayPasswordFailure(null)
        })

        login_btn_enter.setOnClickListener{
            TODO()
        }
        login_txt_click_to_register.setOnClickListener{
            goToChooseAccount()
        }
    }

    private fun goToChooseAccount(){
        val intent = Intent(activity, AuthActivity::class.java)
        activity?.startActivity(intent)
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
        val intent = Intent (activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        activity?.startActivity(intent)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
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

        }
    }
}