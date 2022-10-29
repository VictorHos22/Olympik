package com.example.olympik.register.data

interface RegisterStudentDataSource {
    fun create(name: String, sex: String, birthDate: String, cpf: String, phoneNumber: String,
               email: String, password: String, callback: RegisterCallback)
}