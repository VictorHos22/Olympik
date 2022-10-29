package com.example.olympik.register.presentation

import android.util.Patterns
import com.example.olympik.R
import com.example.olympik.register.RegisterStudent
import com.example.olympik.register.data.RegisterCallback
import com.example.olympik.register.data.RegisterStudentRepository

class RegisterStudentPresenter(
    private var view: RegisterStudent.View?,
    private var repository: RegisterStudentRepository
): RegisterStudent.Presenter {
    override fun create( name: String, sex: String, birthDate: String, cpf: String,
        phoneNumber: String, email: String, password: String, confirmPass: String
    ) {
        val isNameValid = name.length >= 10
        val isSexValid = sex != "Sexo"
        val isBirthDateValid = birthDate.toString().isNotEmpty()
        val isCpfValid = cpf.length == 11
        val isPhoneNumberValid = phoneNumber.length == 11
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >=6
        val isConfirmValid = password == confirmPass

        if (!isNameValid){
            view?.displayNameFailure(R.string.invalid_name)
        } else {
            view?.displayNameFailure(null)
        }

        if (!isSexValid){
            view?.displaySexFailure(R.string.invalid_sex)
        } else {
            view?.displaySexFailure(null)
        }

        if (!isBirthDateValid){
            view?.displayBirthDateFailure(R.string.invalid_birth)
        } else {
            view?.displayBirthDateFailure(null)
        }

        if (!isCpfValid){
            view?.displayCpfFailure(R.string.invalid_cpf)
        } else {
            view?.displayCpfFailure(null)
        }

        if (!isPhoneNumberValid){
            view?.displayPhoneNumberFailure(R.string.invalid_phone)
        } else {
            view?.displayPhoneNumberFailure(null)
        }

        if(!isEmailValid){
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (!isConfirmValid){
            view?.displayRePasswordFailure(R.string.invalid_confirm)
        } else {
            if (!isPasswordValid){
                view?.displayPasswordFailure(R.string.invalid_password)
            } else {
                view?.displayPasswordFailure(null)
            }
        }


        if (isNameValid && isSexValid && isBirthDateValid && isCpfValid && isPhoneNumberValid &&
            isEmailValid && isPasswordValid && isConfirmValid){

            view?.showProgress(true)

            repository.create(name, sex, birthDate, cpf, phoneNumber, email, password, object: RegisterCallback {
                override fun onSuccess() {
                    view?.onCreateSuccess()
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
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