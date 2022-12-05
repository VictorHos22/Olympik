package com.example.olympik.common.model

import java.util.*

object Database {
    private val usersAuth = hashSetOf<UserAuth>()
    val userMeasurements = hashSetOf<UserMeasurements>()


    var sessionAuth: UserAuth? = null
    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(),"UserA", "Masculino",
            "10021924", "12345678910", "11912345678", "userA@gmail.com", "123456"))
    }
}