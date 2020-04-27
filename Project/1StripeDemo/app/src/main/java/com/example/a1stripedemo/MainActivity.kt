package com.example.a1stripedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bacl.solution.data.NetworkBuilder
import com.stripe.android.EphemeralKeyUpdateListener
import com.stripe.android.Stripe
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    var selectPaymentMethodButton: Button? = null
    var payButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectPaymentMethodButton = findViewById(R.id.selectPaymentMethodsButton)
        selectPaymentMethodButton?.setOnClickListener {
            println("selectPaymentMethodButton Click")
            val header = HashMap<String, String>()
            header["Authorization"] = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMywiZXhwIjoxNjE4NzU5OTk2fQ.Z8wzF8U5p4omSHqJzgfm2nvBMYU7g1zRshJ8bZt25rA"
            ExampleEphemeralKeyProvider.shared.createEphemeralKey("2020-03-02", object : EphemeralKeyUpdateListener{
                override fun onKeyUpdate(stripeResponseJson: String) {
//                    TODO("Not yet implemented")
                }

                override fun onKeyUpdateFailure(responseCode: Int, message: String) {
//                    TODO("Not yet implemented")
                }

            })
        }

        payButton = findViewById(R.id.payButton)
        payButton?.setOnClickListener {
            println("payButton Click")
        }
    }


}