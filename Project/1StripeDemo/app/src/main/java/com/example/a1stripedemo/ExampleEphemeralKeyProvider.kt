package com.example.a1stripedemo

import androidx.annotation.Size
import com.bacl.solution.data.NetworkBuilder
import com.example.a1stripedemo.Model.StripeRequest
import com.example.a1stripedemo.Model.StripeResponse
import com.example.a1stripedemo.NetWorkBuilder.NetWorkRequest
import com.google.gson.Gson
import com.stripe.android.EphemeralKeyProvider
import com.stripe.android.EphemeralKeyUpdateListener
import retrofit2.Response
import java.io.IOException

class ExampleEphemeralKeyProvider : EphemeralKeyProvider {
    companion object{
        private var instance: ExampleEphemeralKeyProvider = ExampleEphemeralKeyProvider()
        val shared: ExampleEphemeralKeyProvider
            get() {
                return instance
            }
    }
    override fun createEphemeralKey(
        @Size(min = 4) apiVersion: String,
        keyUpdateListener: EphemeralKeyUpdateListener
    ) {
        println(apiVersion)
        val header = HashMap<String, String>()
        header["Authorization"] = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMiwiZXhwIjoxNjE5MzUyOTgxfQ.Yg_G86kKq_1PHb0WMJLpnqcR8PxvYPgSYN35a5CbYIQ"
        NetWorkRequest.shared.doNetworkRequest(NetworkBuilder.api().createEphemeralKeys(header,
            StripeRequest(apiVersion)
        ), object :
            NetWorkRequest.OnNetworkRequest<Response<StripeResponse>?> {
            override fun onNetworkRequestError(throwable: Throwable?) {
                println("fetch error: ${throwable?.localizedMessage}")
            }
            override fun onNetworkRequestSuccess(response: Response<StripeResponse>?) {
                if (response!!.isSuccessful){
                    try {
                        println(Gson().toJson(response.body()))
                        val ephemeralKeyJson = Gson().toJson(response.body())
                        keyUpdateListener.onKeyUpdate(ephemeralKeyJson)
                    } catch (e: IOException) {
                        keyUpdateListener
                            .onKeyUpdateFailure(0, e.message ?: "")
                    }
                }else{
                    println("createEphemeralKey fail ${response.message()}")
                }
            }
        })
    }

}