package com.example.olympik.registerStudent.data

import java.util.*

class RegisterStudentRepository(private val dataSource: RegisterStudentDataSource) {
    fun create(name: String, sex: String, birthDate: String, cpf: String, phoneNumber: String, email: String, password: String, callback: RegisterStudentCallback){
        dataSource.create(name, sex, birthDate, cpf, phoneNumber, email, password, callback)
    }

}