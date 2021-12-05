package com.prephouse.prephouse.repositories

import com.prephouse.prephouse.datasources.feedback.FeedbackLocalDataSource
import com.prephouse.prephouse.datasources.feedback.FeedbackRemoteDataSource
import com.prephouse.prephouse.models.models.feedback.Feedback
import com.prephouse.prephouse.utils.emitApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FeedbackRepository @Inject constructor(
    private val localSource: FeedbackLocalDataSource,
    private val remoteSource: FeedbackRemoteDataSource
) {
    fun getFeedbacks(): Flow<List<Feedback>> = flow {
        emitApiResponse(remoteSource.getFeedbacks())
    }.flowOn(Dispatchers.IO)
}
