package com.example.olympik.login

interface Login {
        fun showProgress(enabled : Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message : String)
}