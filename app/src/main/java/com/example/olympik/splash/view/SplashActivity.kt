package com.example.olympik.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olympik.main.MainActivity
import com.example.olympik.R
import com.example.olympik.common.base.DependencyInjector
import com.example.olympik.common.extension.animationEnd
import com.example.olympik.login.view.LoginActivity
import com.example.olympik.splash.Splash
import com.example.olympik.splash.presentation.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), Splash.View {

    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val repository = DependencyInjector.splashRepository()
        presenter = SplashPresenter(this,repository)

        splash_img.animate().apply {
            setListener(animationEnd {
                presenter.authenticated()
            })
            duration = 1000
            alpha(1.0f)
            start()
        }
    }

    override fun goToMainScreen() {
        splash_img.animate().apply {
            setListener(animationEnd {
                val intent = Intent(baseContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }
    }

    override fun goToLoginScreen() {
        splash_img.animate().apply {
            setListener(animationEnd {
                val intent = Intent(baseContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }
    }
    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}