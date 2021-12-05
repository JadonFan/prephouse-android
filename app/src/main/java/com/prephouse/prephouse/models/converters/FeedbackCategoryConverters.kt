package com.prephouse.prephouse.models.converters

import androidx.room.TypeConverter
import com.prephouse.prephouse.models.models.feedback.FeedbackCategory
import kotlin.Int

object FeedbackCategoryConverters {
    @TypeConverter
    fun fromEnum(`value`: Int): FeedbackCategory = FeedbackCategory.values().single {
        it.value == value
    }

    @TypeConverter
    fun toValue(`enum`: FeedbackCategory): Int = enum.value
}
