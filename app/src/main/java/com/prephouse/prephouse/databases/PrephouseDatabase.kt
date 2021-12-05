package com.prephouse.prephouse.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.prephouse.prephouse.datasources.feedback.FeedbackLocalDataSource
import com.prephouse.prephouse.datasources.mediadownload.MediaDownloadLocalDataSource
import com.prephouse.prephouse.models.converters.DateConverters
import com.prephouse.prephouse.models.converters.FeedbackCategoryConverters
import com.prephouse.prephouse.models.models.feedback.Feedback

@Database(entities = [Feedback::class], version = 1)
@TypeConverters(DateConverters::class, FeedbackCategoryConverters::class)
abstract class PrephouseDatabase : RoomDatabase() {
    abstract fun mediaDownloadDao(): MediaDownloadLocalDataSource

    abstract fun feedbackDao(): FeedbackLocalDataSource
}
