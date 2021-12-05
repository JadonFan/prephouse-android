package com.prephouse.prephouse.components.dashboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.prephouse.prephouse.components.common.PageContainer
import com.prephouse.prephouse.viewmodels.feedback.FeedbackViewModel

@Composable
fun Dashboard(viewModel: FeedbackViewModel = hiltViewModel()) {
    val feedbackState = viewModel.feedbacks.observeAsState()
    viewModel.loadFeedbacks()

    PageContainer {
        Text(feedbackState.value?.message ?: "No message")
    }
}
