package com.example.olympik.registerStudent.data

import android.os.Handler
import android.os.Looper
import com.example.olympik.common.model.Database
import com.example.olympik.common.model.UserAuth
import java.util.*

class FakeRegisterStudentDataSource : RegisterStudentDataSource{

    override fun create(name: String, sex: String, birthDate: String, cpf: String, phoneNumber: String,
        email: String, password: String, callback: RegisterStudentCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth != null){
                callback.onFailure("Usuário já cadastrado")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(),name, sex, birthDate, cpf, phoneNumber, email, password)
                val created = Database.usersAuth.add(newUser)
                if (created){
                    Database.sessionAuth = newUser
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno no servidor")
                }
            }

        }, 1000)
    }

}