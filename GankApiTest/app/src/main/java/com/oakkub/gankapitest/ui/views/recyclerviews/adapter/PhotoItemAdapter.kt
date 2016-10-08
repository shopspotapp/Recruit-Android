package com.oakkub.gankapitest.ui.views.recyclerviews.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.oakkub.gankapitest.R
import com.oakkub.gankapitest.data.gank.model.Displayable
import com.oakkub.gankapitest.data.gank.model.Loading
import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.extensions.inflate
import com.oakkub.gankapitest.ui.views.recyclerviews.viewholders.LoadingHolder
import com.oakkub.gankapitest.ui.views.recyclerviews.viewholders.PhotoItemHolder

/**
 * Created by OaKKuB on 10/3/2016.
 */
class PhotoItemAdapter(val photoLayout: Int,
                       val shouldPaddingEvenPosition: Boolean = false,
                       val onItemClickListener: OnItemClickListener? = null) : BaseAdapter<Displayable, RecyclerView.ViewHolder>() {

    private companion object {
        val LOADING_VIEW_TYPE = 0
        val PHOTO_VIEW_TYPE = 1
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = when(itemList[position]) {
        is Loading -> LOADING_VIEW_TYPE
        is Photo -> PHOTO_VIEW_TYPE
        else -> throw IllegalStateException()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        LOADING_VIEW_TYPE -> LoadingHolder(parent.inflate(R.layout.item_progress_bar))
        PHOTO_VIEW_TYPE -> PhotoItemHolder(parent.inflate(photoLayout), onItemClickListener)
        else -> throw IllegalStateException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = itemList[position]

        when (item) {
            is Photo -> {
                holder as PhotoItemHolder
                holder.bind(item, position, shouldPaddingEvenPosition)
            }
            is Loading -> {
                holder as LoadingHolder
                holder.bind(position, itemCount)
            }
        }
    }
}