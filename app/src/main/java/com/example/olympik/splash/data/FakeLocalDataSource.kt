package com.example.olympik.splash.data

import com.example.olympik.common.model.Database

    class FakeLocalDataSource: SplashDataSource {
        override fun session(callback: SplashCallback) {
            if(Database.sessionAuth != null){
                callback.onSuccess()
            } else {
                callback.onFailure()
            }
        }
    }