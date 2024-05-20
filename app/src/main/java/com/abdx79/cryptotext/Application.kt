package com.abdx79.cryptotext

import android.app.Application

class Application: Application() {
    companion object{
        var dataBase: AppDatabase? = null
    }
    override fun onCreate() {
        super.onCreate()
    }
}