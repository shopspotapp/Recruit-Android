@file:JvmName("CollectionUtils")

package com.oakkub.gankapitest.extensions

/**
 * Created by OaKKuB on 10/5/2016.
 */

inline fun <T> List<T>.runIfNotEmpty(func: List<T>.() -> Unit) {
    if (isNotEmpty()) {
        func()
    }
}

inline fun <T> List<T>.runIfEmpty(func: List<T>.() -> Unit) {
    if (isEmpty()) {
        func()
    }
}