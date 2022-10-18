package com.example.olympik.registerStudent.data

interface RegisterStudentCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}