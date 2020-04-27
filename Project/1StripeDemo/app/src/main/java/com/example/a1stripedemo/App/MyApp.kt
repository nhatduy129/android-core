package com.example.a1stripedemo.App

import android.app.Application
import com.stripe.android.PaymentConfiguration

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_mXQeonZBi7zN51PU6SZh8PAW00AkqAWOsa"
        )
    }
}