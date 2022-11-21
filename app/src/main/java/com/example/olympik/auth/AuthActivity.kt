package com.example.olympik.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.auth.login.LoginFragment
import com.example.olympik.auth.register.RegisterAdmFragment
import com.example.olympik.auth.register.RegisterFirstMeasurementsFragment
import com.example.olympik.auth.register.RegisterStudentFragment
import com.example.olympik.common.extension.replaceFragment
import com.example.olympik.main.MainActivity

class AuthActivity : AppCompatActivity(), FragmentAttachListener{

    private lateinit var loginFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        loginFragment = LoginFragment()
        val fragment = loginFragment
        replaceFragment(fragment)

    }
    override fun goToRegisterStudent() {
        val fragment = RegisterStudentFragment()
        replaceFragment(fragment)
    }

    override fun goToRegisterAdm() {
        val fragment = RegisterAdmFragment()
        replaceFragment(fragment)
    }

    override fun goToRegisterFirstMeasurements() {
        val fragment = RegisterFirstMeasurementsFragment()
        replaceFragment(fragment)
    }

    override fun goToMainScreen(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.auth_activity, fragment)
    }
}