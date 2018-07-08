package com.javalong.retrofitmocker

import retrofit2.Retrofit
import java.lang.reflect.Proxy

/**
 * Created by javalong on 2018/7/8.
 */

fun <T> Retrofit.createMocker(cls:Class<T>,needMocker:Boolean):T{
    if(!needMocker) return this.create(cls)
    val api = this.create(cls)
    return Proxy.newProxyInstance(javaClass.classLoader, arrayOf(cls), MockerHandler(this, api)) as T
}

fun <T> Retrofit.createMocker(cls:Class<T>):T{
    return this.createMocker(cls,true)
}