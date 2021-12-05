package com.prephouse.prephouse.models.converters

import androidx.room.TypeConverter
import java.util.Date

object DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long) = Date(value)

    @TypeConverter
    fun toTimestamp(date: Date) = date.time
}
