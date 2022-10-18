package com.example.olympik.common.model

import java.util.*

data class UserAuth(
    val uuid: String,
    val name: String,
    val sex: String,
    val birthDate: String,
    val cpf: String,
    val phoneNumber: String,
    val email: String,
    val password: String
)
