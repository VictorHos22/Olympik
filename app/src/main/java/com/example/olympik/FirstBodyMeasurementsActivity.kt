package com.example.olympik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olympik.registerStudent.view.RegisterStudentActivity
import kotlinx.android.synthetic.main.activity_register_first_measurements.*

class FirstBodyMeasurementsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_first_measurements)
        btnTurnBack.setOnClickListener{
            turnToRegister()
        }
    }
    private fun turnToRegister(){
        val intent = Intent(this, RegisterStudentActivity::class.java)
        startActivity(intent)
    }
}