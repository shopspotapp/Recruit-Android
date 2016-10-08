package com.oakkub.gankapitest.ui.views.recyclerviews.viewholders

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import com.oakkub.gankapitest.R
import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.ui.views.recyclerviews.adapter.OnItemClickListener

/**
 * Created by OaKKuB on 10/3/2016.
 */
class PhotoItemHolder(itemView: View, val onItemClickListener: OnItemClickListener? = null) :
        RecyclerView.ViewHolder(itemView) {

    val marginAtEvenPosition by lazy {
        itemView.resources.getDimensionPixelSize(R.dimen.size_normal)
    }

    init {
        itemView.setOnClickListener {
            onItemClickListener?.onItemClick(it, adapterPosition)
        }
        itemView.setOnLongClickListener {
            onItemClickListener?.onItemLongClick(it, adapterPosition)
            true
        }
    }

    fun bind(photo: Photo, position: Int, shouldPaddingEvenPosition: Boolean = false) {
        ViewCompat.setTransitionName(itemView, photo._id)

        if (shouldPaddingEvenPosition && position % 2 == 0) setMargin(marginAtEvenPosition)
        else setMargin(0)

        itemView as SimpleDraweeView
        itemView.setImageURI(photo.url)
    }

    fun setMargin(size: Int) {
        itemView.layoutParams.run {
            this as ViewGroup.MarginLayoutParams
            setMargins(size, size, size, size)
        }
    }

}