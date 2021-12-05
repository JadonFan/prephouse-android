package com.prephouse.prephouse.datasources.feedback

import androidx.room.Dao
import androidx.room.Query
import com.prephouse.prephouse.datasources.BaseDao
import com.prephouse.prephouse.models.models.feedback.Feedback

@Dao
interface FeedbackLocalDataSource : BaseDao<Feedback> {
    @Query("SELECT * FROM feedback")
    fun getAll(): List<Feedback>
}
