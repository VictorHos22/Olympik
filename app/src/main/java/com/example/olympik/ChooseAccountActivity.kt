package com.example.olympik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olympik.login.view.LoginActivity
import com.example.olympik.register.view.RegisterAdmActivity
import com.example.olympik.registerStudent.view.RegisterStudentActivity
import kotlinx.android.synthetic.main.activity_register_choose_account.*

class ChooseAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_choose_account)

        btnSouAluno.setOnClickListener {
            openRegisterAluno()
        }
        btnSouAdm.setOnClickListener{
            openRegisterAdm()
        }
        btnTurnBack.setOnClickListener{
            turnBackLogin()
        }

    }
    
    private fun turnBackLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun openRegisterAluno() {
        val intent = Intent(this, RegisterStudentActivity::class.java)
        startActivity(intent)
    }
    private fun openRegisterAdm(){
        val intent = Intent(this, RegisterAdmActivity::class.java)
        startActivity(intent)
    }


}