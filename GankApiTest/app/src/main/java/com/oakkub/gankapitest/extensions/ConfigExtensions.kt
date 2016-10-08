@file:JvmName("ConfigUtils")

package com.oakkub.gankapitest.extensions

import android.content.res.Configuration

/**
 * Created by OaKKuB on 10/4/2016.
 */
fun Configuration.isLandscape() = orientation == Configuration.ORIENTATION_LANDSCAPE

fun Configuration.isPortrait() = orientation == Configuration.ORIENTATION_PORTRAIT
