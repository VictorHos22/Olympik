package com.example.olympik.auth.login

import com.example.olympik.common.base.BasePresenter
import com.example.olympik.common.base.BaseView

interface Login {
        fun showProgress(enabled : Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message : String)
}