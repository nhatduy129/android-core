package com.example.a1stripedemo

import androidx.annotation.Size
import com.stripe.android.EphemeralKeyProvider
import com.stripe.android.EphemeralKeyUpdateListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException

class ExampleEphemeralKeyProvider : EphemeralKeyProvider {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun createEphemeralKey(
        @Size(min = 4) apiVersion: String,
        keyUpdateListener: EphemeralKeyUpdateListener
    ) {
        compositeDisposable.add(
            backendApi.createEphemeralKey(hashMapOf("api_version" to apiVersion))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { responseBody ->
                    try {
                        val ephemeralKeyJson = responseBody.string()
                        keyUpdateListener.onKeyUpdate(ephemeralKeyJson)
                    } catch (e: IOException) {
                        keyUpdateListener
                            .onKeyUpdateFailure(0, e.message ?: "")
                    }
                })
    }


}