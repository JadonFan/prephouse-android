package com.prephouse.prephouse.models.models.feedback

import com.prephouse.annotationProcessor.room.Convertible
import com.prephouse.prephouse.models.serializers.FeedbackCategorySerializer
import com.prephouse.prephouse.utils.NumberedEnum
import kotlinx.serialization.Serializable

@Convertible
@Serializable(with = FeedbackCategorySerializer::class)
enum class FeedbackCategory(override val value: Int) : NumberedEnum {
    EMOTION(0),
    PAUSE(1),
    ;
}
