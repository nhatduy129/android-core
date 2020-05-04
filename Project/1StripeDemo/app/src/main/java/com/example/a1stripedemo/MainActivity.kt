package com.example.a1stripedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bacl.solution.data.NetworkBuilder
import com.stripe.android.*
import com.stripe.android.model.ShippingInformation
import com.stripe.android.view.PaymentMethodsActivity
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    var selectPaymentMethodButton: Button? = null
    var payButton: Button? = null
    private lateinit var paymentSession: PaymentSession
    private val defaultShippingInfo: ShippingInformation
        get() = ShippingInformation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Init CustomerSession by Ephemeral key
        CustomerSession.initCustomerSession(this, ExampleEphemeralKeyProvider())

        paymentSession = PaymentSession(
            this,
            PaymentSessionConfig.Builder()
                .setShippingMethodsRequired(false)
                .build()
        )

        setContentView(R.layout.activity_main)
        selectPaymentMethodButton = findViewById(R.id.selectPaymentMethodsButton)
        selectPaymentMethodButton?.setOnClickListener {
            println("selectPaymentMethodButton Click")
            paymentSession.presentPaymentMethodSelection()
        }

        payButton = findViewById(R.id.payButton)
        payButton?.setOnClickListener {
            println("payButton Click")
        }
    }
}