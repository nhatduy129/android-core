package com.example.a1stripedemo.NetWorkBuilder

import com.example.a1stripedemo.NetWorkBuilder.RxUtil.applySchedulers
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

open class NetWorkRequest {
    companion object{
        private val mCompositeDisposable = CompositeDisposable()
        private var instance: NetWorkRequest =
            NetWorkRequest()
        val shared: NetWorkRequest
            get() {
                return instance
            }
    }
    fun <T> doNetworkRequest(src: Observable<Response<T>?>?, onNetworkRequest: OnNetworkRequest<Response<T>?>?) {
        mCompositeDisposable.add(
            RxUtil.appleHandlerStartFinish(
                src
            ).compose(applySchedulers()).subscribe({ tResponse: Response<T> ->
            onNetworkRequest?.onNetworkRequestSuccess(tResponse)
        }
        ) { throwable: Throwable? -> onNetworkRequest?.onNetworkRequestError(throwable) })
    }

    interface OnNetworkRequest<T> {
        fun onNetworkRequestSuccess(response: T)
        fun onNetworkRequestError(throwable: Throwable?)
    }
}

object RxUtil {
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> appleHandlerStartFinish(src: Observable<T?>?): Observable<T> {
        return Observable.using({ null },
            { nothing: Any? -> src }
        ) { resource: Any? -> }
    }
}