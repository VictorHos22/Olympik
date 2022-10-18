package com.example.olympik.registerStudent

import com.example.olympik.common.base.BasePresenter
import com.example.olympik.common.base.BaseView
import java.util.*

interface RegisterStudent {
    interface Presenter: BasePresenter {
        fun create(name: String, sex: String, birthDate: String, cpf: String, phoneNumber: String, email: String, password: String, confirmPass: String)
    }
    interface View : BaseView<Presenter> {
        fun showProgress(enabled : Boolean)
        fun displayNameFailure(nameError: Int?)
        fun displaySexFailure(sexError: Int?)
        fun displayBirthDateFailure(birthError: Int?)
        fun displayCpfFailure(cpfError: Int?)
        fun displayPhoneNumber(phoneNumberError: Int?)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun displayRePasswordFailure(rePasswordError: Int?)
        fun onCreateSuccess()
        fun onCreateFailure(message : String)
    }
}