package com.wdl.ezz.app

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.didichuxing.doraemonkit.DoraemonKit
import com.tencent.mmkv.MMKV
import com.wdl.ezz.BuildConfig

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
        }
        MMKV.initialize(context)
        DoraemonKit.install(context.applicationContext as Application, "")
        ARouter.init(context.applicationContext as Application)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}