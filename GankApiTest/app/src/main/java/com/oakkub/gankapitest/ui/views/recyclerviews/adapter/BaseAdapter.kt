package com.oakkub.gankapitest.ui.views.recyclerviews.adapter

import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Created by OaKKuB on 10/5/2016.
 */
abstract class BaseAdapter<E, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    val itemList: ArrayList<E> = ArrayList()

    fun getItem(position: Int) = itemList[position]

    fun add(item: E) {
        this.itemList.add(item)
        notifyItemInserted(itemCount)
    }

    fun addAll(itemList: List<E>) {
        this.itemList.addAll(itemList)
        notifyItemRangeInserted(itemCount, itemList.size)
    }

    fun removeLast(): E? {
        val lastPosition = getLastPosition()
        if (!isPositionValid(lastPosition)) return null

        val removedItem: E? = itemList.removeAt(lastPosition)
        removedItem?.let {
            notifyItemRemoved(lastPosition)
        }
        return removedItem
    }

    fun isPositionValid(position: Int) = position >= 0 && position < itemCount

    fun getLastPosition() = itemCount - 1

}