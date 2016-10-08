package com.oakkub.gankapitest.ui.views.recyclerviews.adapter

import android.view.View

/**
 * Created by OaKKuB on 10/5/2016.
 */
interface OnItemClickListener {
    fun onItemClick(view: View, position: Int)
    fun onItemLongClick(view: View, position: Int)
}