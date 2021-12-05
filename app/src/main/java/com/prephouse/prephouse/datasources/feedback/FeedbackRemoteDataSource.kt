package com.prephouse.prephouse.datasources.feedback

import com.prephouse.prephouse.models.models.feedback.Feedback
import retrofit2.Response
import retrofit2.http.GET

interface FeedbackRemoteDataSource {
    @GET("/feedback")
    suspend fun getFeedbacks(): Response<List<Feedback>>
}
