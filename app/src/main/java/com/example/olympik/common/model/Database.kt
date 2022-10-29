package com.example.olympik.common.model

import java.util.*

object Database {
    val usersAuth = hashSetOf<UserAuth>()
    val admAuth = hashSetOf<AdmAuth>()
    val userMeasurements = hashSetOf<UserMeasurements>()


    var sessionAuth: UserAuth? = null
    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(),"Paulo Minozzo", "Masculino",
            "10021924", "12345678910", "11912345678", "userA@gmail.com", "123456"))
    }

    var sessionAdmAuth: AdmAuth? = null
    init {
        admAuth.add(AdmAuth(UUID.randomUUID().toString(), "Tomin", "12312312312","11912341234",
        "tomin@gmail.com", "12345678910123", "Tomin Miados", "Tomin Miau",
        "12345678", "Rua dos felinos 666", "SP", "CatCity", "123123"))
    }
}