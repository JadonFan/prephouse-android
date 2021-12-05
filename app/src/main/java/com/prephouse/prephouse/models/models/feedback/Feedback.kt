package com.prephouse.prephouse.models.models.feedback

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prephouse.prephouse.models.models.mediaupload.UploadId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "feedback")
data class Feedback(
    @PrimaryKey
    val id: String,

    @SerialName("upload_id")
    @ColumnInfo(name = "upload_id")
    val uploadId: String,

    @SerialName("type")
    val category: FeedbackCategory,

    val text: String,

    val score: Float,

    @SerialName("time_start")
    @ColumnInfo(name = "time_start")
    val timeStart: String,

    @SerialName("time_end")
    @ColumnInfo(name = "time_end")
    val timeEnd: String,
) {
    val typedId: FeedbackId
        get() = FeedbackId(id)

    val typedUploadId: UploadId
        get() = UploadId(uploadId)
}
