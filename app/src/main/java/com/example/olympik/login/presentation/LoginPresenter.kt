package com.example.olympik.login.presentation

import android.util.Patterns
import com.example.olympik.R
import com.example.olympik.common.model.UserAuth
import com.example.olympik.login.Login
import com.example.olympik.login.data.LoginCallback
import com.example.olympik.login.data.LoginRepository

class LoginPresenter(
    private var view: Login.View?,
    private var repository: LoginRepository
):Login.Presenter {
    override fun login(email: String, password: String) {

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >=6

        if(!isEmailValid){
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (!isPasswordValid){
            view?.displayPasswordFailure(R.string.invalid_password)
        } else {
            view?.displayPasswordFailure(null)
        }

        if (isEmailValid && isPasswordValid){
            view?.showProgress(true)

            repository.login(email, password, object : LoginCallback{
                override fun onSuccess(userAuth: UserAuth) {
                    view?.onUserAuthenticated()
                }

                override fun onFailure(message: String) {
                    view?.onUserUnauthorized(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }
    }

    override fun onDestroy() {
        view = null
    }

}