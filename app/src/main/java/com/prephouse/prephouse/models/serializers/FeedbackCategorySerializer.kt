package com.prephouse.prephouse.models.serializers

import com.prephouse.prephouse.models.models.feedback.FeedbackCategory

object FeedbackCategorySerializer : NumberedEnumSerializer<FeedbackCategory>(
    FeedbackCategory::class
)
