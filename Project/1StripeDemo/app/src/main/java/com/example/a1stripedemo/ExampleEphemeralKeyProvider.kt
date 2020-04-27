package com.example.a1stripedemo

import com.bacl.solution.data.NetworkBuilder
import com.example.a1stripedemo.Model.StripeRequest
import com.example.a1stripedemo.Model.StripeResponse
import com.example.a1stripedemo.NetWorkBuilder.NetWorkRequest
import com.stripe.android.EphemeralKeyProvider
import com.stripe.android.EphemeralKeyUpdateListener
import retrofit2.Response

class ExampleEphemeralKeyProvider : EphemeralKeyProvider {
    companion object{
        private var instance: ExampleEphemeralKeyProvider = ExampleEphemeralKeyProvider()
        val shared: ExampleEphemeralKeyProvider
            get() {
                return instance
            }
    }
    override fun createEphemeralKey(
        apiVersion: String,
        keyUpdateListener: EphemeralKeyUpdateListener
    ) {
        val header = HashMap<String, String>()
        header["Authorization"] = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMywiZXhwIjoxNjE4NzU5OTk2fQ.Z8wzF8U5p4omSHqJzgfm2nvBMYU7g1zRshJ8bZt25rA"
        NetWorkRequest.shared.doNetworkRequest(NetworkBuilder.api().createEphemeralKeys(header,
            StripeRequest(apiVersion)
        ), object :
            NetWorkRequest.OnNetworkRequest<Response<StripeResponse>?> {
            override fun onNetworkRequestError(throwable: Throwable?) {
                println("fetch error: ${throwable?.localizedMessage}")
            }
            override fun onNetworkRequestSuccess(response: Response<StripeResponse>?) {
                if (response!!.isSuccessful){
                    println("response data: ${response.body()}")
                    println( "createEphemeralKey success")
                }else{
                    println("createEphemeralKey fail ${response.message()}")
                }
            }
        })
    }

}