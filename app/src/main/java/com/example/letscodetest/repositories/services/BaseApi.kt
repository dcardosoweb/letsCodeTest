package com.example.letscodetest.repositories.services

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import com.example.letscodetest.utils.NoConnectivityException
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

object BaseApi {

    private const val CACHE_SIZE = (50 * 1024 * 1024).toLong()    // 50 MB
    private const val CONNECT_TIMEOUT = 300
    private const val WRITE_TIMEOUT = 60
    private const val TIMEOUT = 300
    lateinit var gson: Gson
    lateinit var context: Context
    var theMovieDBKey:String=""

    fun getMovieRetrofitInstance(context: Context): Retrofit {

        val okHttpClient = getHttpCommonBuild(context).build()

        gson = GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    fun getMusicRetrofitInstance(context: Context): Retrofit {

        val okHttpClient = getHttpCommonBuild(context).build()

        gson = GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://theaudiodb.com/api/v1/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    private fun getHttpCommonBuild(context: Context): OkHttpClient.Builder {

            val builder = OkHttpClient.Builder()
                .addInterceptor(ConnectivityInterceptor())
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .cache(Cache(context.cacheDir, CACHE_SIZE))
            return builder
    }

    class ConnectivityInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!NetworkUtils.isConnected() ) {
                throw NoConnectivityException()
            }

            val builder = chain.request().newBuilder()
            return chain.proceed(builder.build())
        }

    }
}