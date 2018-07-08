package com.javalong.retrofitmocker.demo

import com.javalong.retrofitmocker.annotation.MOCK
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by javalong on 2018/7/8.
 */
interface ServiceApi {

    @GET(" ")
    fun test1():Call<String>

    @MOCK("test_1.json")
    @GET(" ")
    fun test2():Call<String>

    @MOCK("https://api.github.com/users")
    @GET(" ")
    fun test3():Call<String>

    @MOCK("test_1.json")
    @GET(" ")
    fun test4():Observable<String>
}