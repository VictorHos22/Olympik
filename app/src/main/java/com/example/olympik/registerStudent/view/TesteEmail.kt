package com.example.olympik.registerStudent.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.example.olympik.R
import kotlinx.android.synthetic.main.activity_teste_email.*

class TesteEmail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_email)
        btn_validar.setOnClickListener {
            validaEmail()
        }

    }

    fun validaEmail(){
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(edit_text.text.toString()).matches()

        if (!isEmailValid){
            Log.i("TAG", "onCreate: Email valido")
        } else {
            Log.i("TAG", "onCreate: Email invalido")
        }
    }
}