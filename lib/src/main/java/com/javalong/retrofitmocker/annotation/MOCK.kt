package com.javalong.retrofitmocker.annotation

import javax.xml.transform.OutputKeys.METHOD

/**
 * Created by javalong on 2018/7/8.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MOCK(val value:String,val enable:Boolean = true)