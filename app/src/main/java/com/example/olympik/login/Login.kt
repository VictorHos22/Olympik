package com.example.olympik.login

import com.example.olympik.common.base.BasePresenter
import com.example.olympik.common.base.BaseView

interface Login {

    interface Presenter: BasePresenter{
        fun login(email: String, password: String)
    }
    interface View : BaseView<Presenter>{
        fun showProgress(enabled : Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message : String)
    }

}