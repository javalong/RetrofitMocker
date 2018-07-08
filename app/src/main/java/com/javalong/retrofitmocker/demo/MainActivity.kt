package com.javalong.retrofitmocker.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.javalong.retrofitmocker.BuildConfig
import com.javalong.retrofitmocker.MockerHelper
import com.javalong.retrofitmocker.R
import com.javalong.retrofitmocker.createMocker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var serviceApi:ServiceApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRetrofit()
        btMocker1.setOnClickListener({
            val call = serviceApi.test1()
            call.enqueue(object :Callback<String>{
                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    response.let {
                        tvHttpContent.text = response!!.body()
                        Log.e(TAG,response!!.body())
                    }
                }

                override fun onFailure(call: Call<String>?, t: Throwable?) {
                }
            })
        })
        btMocker2.setOnClickListener({
            val call = serviceApi.test2()
            call.enqueue(object :Callback<String>{
                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    response.let {
                        tvHttpContent.text = response!!.body()
                        Log.e(TAG,response!!.body())
                    }
                }

                override fun onFailure(call: Call<String>?, t: Throwable?) {
                }
            })
        })
        btMocker3.setOnClickListener({
            val call = serviceApi.test3()
            call.enqueue(object :Callback<String>{
                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    response.let {
                        tvHttpContent.text = response!!.body()
                        Log.e(TAG,response!!.body())
                    }
                }

                override fun onFailure(call: Call<String>?, t: Throwable?) {
                }
            })
        })

        btMocker4.setOnClickListener({
             serviceApi.test4()
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe({data->
                         tvHttpContent.text = data
                         Log.e(TAG,data)
                     },{error->})

        })


    }

    private fun initRetrofit() {
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        serviceApi = retrofit.createMocker(ServiceApi::class.java)
        //建议这样写，发布就不会忘了开关 serviceApi = retrofit.createMocker(ServiceApi::class.java,BuildConfig.DEBUG)
        //如果没有使用 kotlin 可以这么些 serviceApi = MockerHelper.createMocker(retrofit,ServiceApi::class.java,BuildConfig.DEBUG)
    }
}
