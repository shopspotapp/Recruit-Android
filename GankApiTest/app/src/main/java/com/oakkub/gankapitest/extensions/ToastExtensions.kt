@file:JvmName("ToastUtils")

package com.oakkub.gankapitest.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by OaKKuB on 8/13/2016.
 */
fun Context.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}