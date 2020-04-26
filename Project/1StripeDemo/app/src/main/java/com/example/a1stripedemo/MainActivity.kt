package com.example.a1stripedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var selectPaymentMethodButton: Button? = null
    var payButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectPaymentMethodButton = findViewById(R.id.selectPaymentMethodsButton)
        selectPaymentMethodButton?.setOnClickListener {
            println("selectPaymentMethodButton Click")
        }

        payButton = findViewById(R.id.payButton)
        payButton?.setOnClickListener {
            println("payButton Click")
        }
    }


}