package com.example.demo2023

import android.app.Application
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Copyright (c) 2023, Lollitech
 * All rights reserved
 * Author: fangzhiyuan@lollitech.com
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this)
    }

}