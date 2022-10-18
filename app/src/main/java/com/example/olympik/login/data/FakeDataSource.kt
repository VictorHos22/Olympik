package com.example.olympik.login.data

import android.os.Looper
import com.example.olympik.common.model.Database
import java.util.logging.Handler

class FakeDataSource : LoginDataSource{
    override fun login(email: String, password: String, callback: LoginCallback) {
        //pq ficou esse android.os. ??
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            when {
                userAuth == null -> {
                    callback.onFailure("Usuário não encontrado")
                }
                userAuth.password != password -> {
                    callback.onFailure("Senha incorreta")
                }
                else -> {
                    Database.sessionAuth = userAuth
                    callback.onSuccess(userAuth)
                }
            }
            callback.onComplete()
        }, 2000)
    }

}