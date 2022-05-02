package com.example.letscodetest.utils

import com.blankj.utilcode.util.GsonUtils
import com.example.letscodetest.models.ServiceKeys
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig


class LestsCodeUtils {

    fun getServiceKeysRemoteConfig(): ServiceKeys? {
        var remoteConfig = Firebase.remoteConfig
        return GsonUtils.fromJson(
            remoteConfig.getString("SERVICE_KEY"),
            ServiceKeys::class.java
        )
    }
}