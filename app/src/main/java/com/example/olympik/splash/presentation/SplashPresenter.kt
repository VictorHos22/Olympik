package com.example.olympik.splash.presentation

import com.example.olympik.splash.Splash
import com.example.olympik.splash.data.SplashCallback
import com.example.olympik.splash.data.SplashRepository

class SplashPresenter(
    private var view: Splash.View?,
    private var repository: SplashRepository
): Splash.Presenter {
    override fun authenticated() {
        repository.session(object : SplashCallback {
            override fun onSuccess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}