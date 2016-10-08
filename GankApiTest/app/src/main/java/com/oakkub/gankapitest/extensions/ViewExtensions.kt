@file:JvmName("ViewUtils")

package com.oakkub.gankapitest.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

/**
 * Created by OaKKuB on 8/7/2016.
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ViewGroup.inflate(resource: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resource, this, attachToRoot)
}

inline fun <reified T : View> T.runOnClickListener(crossinline func: T.() -> Unit): T {
    setOnClickListener { func() }
    return this
}

inline fun <reified T : View> T.runOnPreDraw(crossinline func: T.() -> Unit) {
    // We cannot refer 'this' in lambda so we have to do this way :(
    if (viewTreeObserver.isAlive) {
        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                func()
                viewTreeObserver.removeOnPreDrawListener(this)
                return true
            }
        })
    }
}