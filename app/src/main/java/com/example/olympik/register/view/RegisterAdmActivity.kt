package com.example.olympik.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olympik.ChooseAccountActivity
import com.example.olympik.R
import kotlinx.android.synthetic.main.activity_register_adm.*


class RegisterAdmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_adm)
        btn_back_choose_account.setOnClickListener{
            turnBackToChooseAccount()
        }
    }
    private fun turnBackToChooseAccount(){
        val intent = Intent(this, ChooseAccountActivity::class.java)
        startActivity(intent)
    }
}