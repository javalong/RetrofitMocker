package com.javalong.retrofitmocker

import retrofit2.Retrofit
import java.lang.reflect.Proxy

/**
 * Created by javalong on 2018/7/8.
 */
class MockerHelper {
    companion object {

        fun <T> createMocker(retrofit: Retrofit,cls:Class<T>, needMocker:Boolean):T{
            if(!needMocker) return retrofit.create(cls)
            val api = retrofit.create(cls)
            return Proxy.newProxyInstance(javaClass.classLoader, arrayOf(cls), MockerHandler(retrofit, api)) as T
        }

        fun <T> createMocker(retrofit:Retrofit,cls:Class<T>):T{
            return this.createMocker(retrofit,cls,true)
        }
    }
}