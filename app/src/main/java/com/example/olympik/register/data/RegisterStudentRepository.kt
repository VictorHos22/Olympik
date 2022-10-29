package com.example.olympik.register.data

class RegisterStudentRepository(private val dataSource: RegisterStudentDataSource) {
    fun create(name: String, sex: String, birthDate: String, cpf: String, phoneNumber: String, email: String, password: String, callback: RegisterCallback){
        dataSource.create(name, sex, birthDate, cpf, phoneNumber, email, password, callback)
    }

}