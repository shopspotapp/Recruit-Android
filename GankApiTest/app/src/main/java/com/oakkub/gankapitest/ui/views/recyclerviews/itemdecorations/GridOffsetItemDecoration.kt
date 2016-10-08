package com.oakkub.gankapitest.ui.views.recyclerviews.itemdecorations

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

/**
 * Created by OaKKuB on 9/29/2015.
 */
class GridOffsetItemDecoration(val spaceBetweenCell: Int, val allSpace: Boolean = false) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        val spanCount = getSpanCount(parent.layoutManager)
        val itemPosition = parent.getChildAdapterPosition(view)

        if (allSpace) {
            outRect.left = spaceBetweenCell
            outRect.top = if (itemPosition < spanCount) spaceBetweenCell else 0
            outRect.right = if (itemPosition % spanCount == spanCount - 1) spaceBetweenCell else 0
            outRect.bottom = spaceBetweenCell
        } else {
            outRect.left = if (itemPosition % spanCount == 0) 0 else spaceBetweenCell
            outRect.top = if (itemPosition < spanCount) 0 else spaceBetweenCell
        }
    }

    private fun getSpanCount(layoutManager: RecyclerView.LayoutManager?) = when (layoutManager) {
        is GridLayoutManager -> layoutManager.spanCount
        is StaggeredGridLayoutManager -> layoutManager.spanCount
        else -> throw IllegalStateException("The layout manager must be either GridLayoutManager or StaggeredLayoutManager")
    }
}
