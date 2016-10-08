package com.oakkub.gankapitest.ui.views.recyclerviews.viewholders

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup

/**
 * Created by OaKKuB on 10/3/2016.
 */
class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int, itemCount: Int) {
        val paramSize = findParamSizeByPosition(position)
        val layoutParams = StaggeredGridLayoutManager.LayoutParams(paramSize, paramSize)

        layoutParams.isFullSpan = position == itemCount - 1
        itemView.layoutParams = layoutParams
    }

    fun findParamSizeByPosition(position: Int) = when(position) {
        0 -> ViewGroup.LayoutParams.MATCH_PARENT
        else -> ViewGroup.LayoutParams.WRAP_CONTENT
    }

}