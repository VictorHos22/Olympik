package com.example.olympik.registerStudent.data

import java.util.*

interface RegisterStudentDataSource {
    fun create(name: String, sex: String, birthDate: String, cpf: String, phoneNumber: String, email: String, password: String, callback: RegisterStudentCallback)
}