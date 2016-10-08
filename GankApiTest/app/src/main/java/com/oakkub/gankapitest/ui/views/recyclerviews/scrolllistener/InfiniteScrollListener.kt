package com.oakkub.gankapitest.ui.views.recyclerviews.scrolllistener

/**
 * Created by OaKKuB on 10/6/2016.
 */

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class InfiniteScrollListener(private val layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private var previousItemCount = 0
    private var isLoading: Boolean = false

    init {
        when(layoutManager) {
            is StaggeredGridLayoutManager -> visibleThreshold *= layoutManager.spanCount
            is GridLayoutManager -> visibleThreshold *= layoutManager.spanCount
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        val lastVisibleItemPosition = findLastVisibleItemPosition(layoutManager)
        val totalItemCount = layoutManager.itemCount

        if (totalItemCount < previousItemCount) {
            previousItemCount = totalItemCount

            if (totalItemCount == 0) {
                isLoading = true
            }
        }

        if (isLoading && totalItemCount > previousItemCount) {
            isLoading = false
            previousItemCount = totalItemCount
        }

        if (!isLoading && totalItemCount < lastVisibleItemPosition + visibleThreshold) {
            isLoading = true
            onLoadMore(totalItemCount)
        }
    }

    private fun findLastVisibleItemPosition(layoutManager: RecyclerView.LayoutManager) = when(layoutManager) {
        is StaggeredGridLayoutManager -> findStaggeredGridLastVisibleItemPosition(layoutManager)
        else -> {
            val linearLayoutManager = layoutManager as LinearLayoutManager
            linearLayoutManager.findLastVisibleItemPosition()
        }
    }

    private fun findStaggeredGridLastVisibleItemPosition(staggeredGridLayoutManager: StaggeredGridLayoutManager): Int {
        val lastVisibleItemPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(null)
        return lastVisibleItemPositions.max() ?: 0
    }

    protected abstract fun onLoadMore(totalItemCount: Int)

}

