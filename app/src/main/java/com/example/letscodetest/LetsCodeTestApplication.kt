package com.example.letscodetest

import android.app.Application
import com.example.letscodetest.di.apiModule
import com.example.letscodetest.di.dbModule
import com.example.letscodetest.di.repositoryModule
import com.example.letscodetest.di.viewModule
import com.example.letscodetest.models.ServiceKeys
import com.example.letscodetest.models.UserModel
import com.example.letscodetest.repositories.services.BaseApi
import com.example.letscodetest.utils.LestsCodeUtils
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LetsCodeTestApplication:Application() {

    var serviceKeys:ServiceKeys?=null


    override fun onCreate() {
        super.onCreate()
        BaseApi.context = applicationContext
        startKoin{
            androidContext(this@LetsCodeTestApplication)
            modules(listOf(
                viewModule,
                repositoryModule,
                apiModule,
                dbModule
            ))
        }

        serviceKeys = LestsCodeUtils().getServiceKeysRemoteConfig()
    }
}