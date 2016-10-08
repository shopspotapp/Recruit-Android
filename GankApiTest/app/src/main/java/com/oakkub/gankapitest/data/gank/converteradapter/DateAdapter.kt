package com.oakkub.gankapitest.data.gank.converteradapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by OaKKuB on 8/18/2016.
 */
class DateAdapter(val dateFormat: SimpleDateFormat) : JsonAdapter<Date>() {

    override fun toJson(writer: JsonWriter?, value: Date?) {
        value?.let {
            val date = dateFormat.format(value)
            writer?.value(date)
        }
    }

    override fun fromJson(reader: JsonReader?): Date? {
        reader?.let {
            val date = reader.nextString()
            return dateFormat.parse(date)
        }
        return null
    }

}
