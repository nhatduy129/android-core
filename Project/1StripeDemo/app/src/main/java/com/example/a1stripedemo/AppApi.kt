package com.bacl.solution.data

import com.nimbusds.jose.Header
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.*

interface AppApi {
    /*Sign up*/
    @POST("https://sshhhh.herokuapp.com/api/v1/payment/ephemeral_keys")
    fun signUp(@Body header: Header: SignUpRequest?): Observable<Response<SignUpResponse>?>?

}