package com.javalong.retrofitmocker

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by javalong on 2018/7/8.
 */
class MockerCall<Any>(var data: Any) : Call<Any> {


    override fun isExecuted(): Boolean {
        return false
    }

    override fun isCanceled(): Boolean {
        return false
    }



    override fun execute(): Response<Any> {
        return  Response.success(data)
    }


    override fun cancel() {

    }

    override fun enqueue(callback: Callback<Any>) {
        callback.onResponse(null, Response.success(data))
    }

    override fun clone(): Call<Any> {
        return this
    }

    override fun request(): Request? {
        return null
    }
}