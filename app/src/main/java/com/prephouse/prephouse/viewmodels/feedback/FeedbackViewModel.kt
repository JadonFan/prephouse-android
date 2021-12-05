package com.prephouse.prephouse.viewmodels.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prephouse.prephouse.datasources.DataResult
import com.prephouse.prephouse.models.models.feedback.Feedback
import com.prephouse.prephouse.repositories.FeedbackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val repository: FeedbackRepository
) : ViewModel() {
    private val _feedbacks = MutableLiveData<DataResult<List<Feedback>>>()
    val feedbacks: LiveData<DataResult<List<Feedback>>>
        get() = _feedbacks

    fun loadFeedbacks() {
        viewModelScope.launch {
            repository.getFeedbacks()
                .onStart { _feedbacks.value = DataResult.Loading() }
                .catch { exception ->
                    _feedbacks.value = DataResult.Error(exception.localizedMessage)
                }
                .collect { response ->
                    _feedbacks.value = DataResult.Success(response)
                }
        }
    }
}
