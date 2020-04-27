package com.bacl.solution.data

import com.example.a1stripedemo.Model.StripeRequest
import com.example.a1stripedemo.Model.StripeResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AppApi {
    @POST("payment/ephemeral_keys")
    fun createEphemeralKeys(@HeaderMap header: HashMap<String, String>, @Body body: StripeRequest): Observable<Response<StripeResponse>?>?
}