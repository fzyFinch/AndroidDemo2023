package com.fzy.common.ext

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Copyright (c) 2023, Lollitech
 * All rights reserved
 * Author: fangzhiyuan@lollitech.com
 */
fun Activity.go(path: String, callback: ((Bundle) -> Unit)?=null, requestCode: Int = -1) {
    val bundle = if (callback != null) Bundle() else null
    if (callback != null) callback(bundle!!)

    ARouter.getInstance()
        .build(path)
        .with(bundle)
        .navigation(this, requestCode)
}