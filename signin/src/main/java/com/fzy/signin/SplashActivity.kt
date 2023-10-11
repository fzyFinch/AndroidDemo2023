package com.fzy.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.example.signin.R
import com.fzy.common.ARouterPath
import com.fzy.common.ext.go
import com.fzy.common.theme.Demo2023Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Copyright (c) 2023, Lollitech
 * All rights reserved
 * Author: fangzhiyuan@lollitech.com
 */

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val MIN_SHOW_TIME = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo2023Theme {
                ImmersePage {
                    Box(
                        modifier = Modifier.fillMaxSize().clickable {
                            go(ARouterPath.test1)
//                            startActivity(Intent(this@SplashActivity, EmptyActivity::class.java))
                        },
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
        goToNextWithMinTime()
    }

    override fun onResume() {
        super.onResume()
//        ARouter.getInstance()
//            .build(ARouterPath.MainActivity)
//            .navigation(this)
    }

    private fun goToNextWithMinTime() {
        //页面本身至少展示1000毫秒,太短过度不自然
        lifecycleScope.launch {
            delay(MIN_SHOW_TIME)
//            go(ARouterPath.MainActivity)
            ARouter.getInstance().build(ARouterPath.MainActivity).navigation()
//            finish()
        }
    }
}


@Composable
fun ImmersePage(
    isPreview: Boolean = false,
    color: Color = Color.Transparent,
    backgroundContent: (@Composable () -> Unit)? = null,
    foregroundContent: (@Composable () -> Unit)? = null,
    fixTitleBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    rememberSystemUiController().run {
        // 将状态栏和导航栏设置为color
        setSystemBarsColor(
            color = color,
            darkIcons = true,
            isNavigationBarContrastEnforced = false
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //背景区
        backgroundContent?.invoke()
        //内容区
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            //空出状态栏高度
            Spacer(
                modifier = Modifier
                    .windowInsetsTopHeight(WindowInsets.statusBars)
            )
            fixTitleBar?.invoke()
            //view内容
            Column(modifier = Modifier.weight(1f)) {
                content()
            }
            //空出导航栏高度
            Spacer(
                modifier = Modifier
                    .windowInsetsBottomHeight(WindowInsets.navigationBars)
            )
        }

        //前景区
        foregroundContent?.invoke()
    }
}