package com.example.olympik.auth.register

interface RegisterStudent {
        fun showProgress(enabled : Boolean)
        fun displayNameFailure(nameError: Int?)
        fun displaySexFailure(sexError: Int?)
        fun displayBirthDateFailure(birthError: Int?)
        fun displayCpfFailure(cpfError: Int?)
        fun displayPhoneNumberFailure(phoneNumberError: Int?)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun displayRePasswordFailure(rePasswordError: Int?)
        fun onCreateSuccess()
        fun onCreateFailure(message : String)

}