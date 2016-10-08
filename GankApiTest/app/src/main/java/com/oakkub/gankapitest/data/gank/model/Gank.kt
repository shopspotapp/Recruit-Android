package com.oakkub.gankapitest.data.gank.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*


/**
 * Created by OaKKuB on 10/3/2016.
 */

data class PhotoResult(val error: Boolean,
                       val results: List<Photo>) : Displayable

data class Photo(val _id: String,
                 val createdAt: Date,
                 val desc: String,
                 val publishedAt: Date,
                 val source: String? = null,
                 val type: String,
                 val url: String,
                 val used: Boolean,
                 val who: String) : Displayable, Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Photo> = object : Parcelable.Creator<Photo> {
            override fun createFromParcel(source: Parcel): Photo = Photo(source)
            override fun newArray(size: Int): Array<Photo?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), Date(source.readLong()), source.readString(), Date(source.readLong()), source.readString(), source.readString(), source.readString(), 1 == source.readInt(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(_id)
        dest?.writeLong(createdAt.time)
        dest?.writeString(desc)
        dest?.writeLong(publishedAt.time)
        dest?.writeString(source)
        dest?.writeString(type)
        dest?.writeString(url)
        dest?.writeInt((if (used) 1 else 0))
        dest?.writeString(who)
    }
}
