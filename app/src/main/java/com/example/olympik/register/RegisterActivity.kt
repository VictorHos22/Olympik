package com.example.olympik.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.common.extension.replaceFragment
import com.example.olympik.main.MainActivity

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val fragment = RegisterChooseAccountFragment()
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

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.register_fragment, fragment)
    }
}