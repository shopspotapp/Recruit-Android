@file:JvmName("LogUtils")

package com.oakkub.gankapitest.extensions

import android.util.Log
import com.oakkub.gankapitest.BuildConfig

/**
 * Created by OaKKuB on 8/21/2016.
 */
fun Any.logV(message: Any) {
    if (BuildConfig.DEBUG) Log.v(this.javaClass.simpleName, message.toString())
}

fun Any.logD(message: Any) {
    if (BuildConfig.DEBUG) Log.d(this.javaClass.simpleName, message.toString())
}

fun Any.logI(message: Any) {
    if (BuildConfig.DEBUG) Log.i(this.javaClass.simpleName, message.toString())
}

fun Any.logW(message: Any) {
    if (BuildConfig.DEBUG) Log.w(this.javaClass.simpleName, message.toString())
}

fun Any.logE(message: Any) {
    if (BuildConfig.DEBUG) Log.e(this.javaClass.simpleName, message.toString())
}

fun Any.logWTF(message: Any) {
    if (BuildConfig.DEBUG) Log.wtf(this.javaClass.simpleName, message.toString())
}
