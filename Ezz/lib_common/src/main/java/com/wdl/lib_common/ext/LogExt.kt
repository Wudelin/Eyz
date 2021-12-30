package com.wdl.lib_common.ext

import android.util.Log

const val TAG = "wdl"

fun String.logV(tag: String = TAG) {
    Log.v(tag, this)
}

fun String.logD(tag: String = TAG) {
    Log.d(tag, this)
}

fun String.logI(tag: String = TAG) {
    Log.i(tag, this)
}

fun String.logW(tag: String = TAG) {
    Log.w(tag, this)
}

fun String.logE(tag: String = TAG) {
    Log.e(tag, this)
}