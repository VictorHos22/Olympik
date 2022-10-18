package com.example.olympik.splash

import com.example.olympik.common.base.BasePresenter
import com.example.olympik.common.base.BaseView

interface Splash {
    interface Presenter: BasePresenter {
        fun authenticated()
    }
    interface View: BaseView<Presenter> {
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}