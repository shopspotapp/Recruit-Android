@file:JvmName("FragmentUtils")

package com.oakkub.gankapitest.extensions

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by OaKKuB on 7/30/2016.
 */
inline fun FragmentManager.doTransaction(func: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        func()
    }.commit()
}