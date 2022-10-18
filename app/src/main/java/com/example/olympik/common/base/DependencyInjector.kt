package com.example.olympik.common.base

import com.example.olympik.login.data.FakeDataSource
import com.example.olympik.login.data.LoginRepository
import com.example.olympik.registerStudent.data.FakeRegisterStudentDataSource
import com.example.olympik.registerStudent.data.RegisterStudentRepository
import com.example.olympik.splash.data.FakeLocalDataSource
import com.example.olympik.splash.data.SplashRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository{
        return LoginRepository(FakeDataSource())
    }
    fun splashRepository(): SplashRepository{
        return SplashRepository(FakeLocalDataSource())
    }
    fun registerStudentRepository(): RegisterStudentRepository{
        return RegisterStudentRepository(FakeRegisterStudentDataSource())
    }
}