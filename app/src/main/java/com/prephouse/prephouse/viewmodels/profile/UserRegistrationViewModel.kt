package com.prephouse.prephouse.viewmodels.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.prephouse.prephouse.models.models.profile.UserRegistrationState
import com.prephouse.prephouse.utils.ZxcvbnWrapper
import com.prephouse.prephouse.utils.getOrCreate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserRegistrationViewModel @Inject constructor(
    private val zxcvbnWrapper: ZxcvbnWrapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(savedStateHandle.getOrCreate<UserRegistrationState>(STATE_KEY))
    val stateFlow: StateFlow<UserRegistrationState> = _stateFlow

    fun updateFirstName(firstName: String) {
        val s = _stateFlow.value.copy(firstName = firstName)
        validateForm(s)
    }

    fun updateLastName(lastName: String) {
        val s = _stateFlow.value.copy(lastName = lastName)
        validateForm(s)
    }

    fun updateEmail(email: String) {
        val s = _stateFlow.value.copy(email = email)
        validateForm(s)
    }

    fun updatePassword(password: String) {
        val score = zxcvbnWrapper.measure(password).score
        val s = _stateFlow.value.copy(
            password = password,
            passwordStrength = score
        )
        validateForm(s)
    }

    private fun validateForm(newState: UserRegistrationState) {
        savedStateHandle[STATE_KEY] = newState

        val (firstName, lastname, email, _, _, isPasswordValid, _) = newState
        val hasNoBlankField = sequenceOf(firstName, lastname, email).all { it.isNotBlank() }
        val s = newState.copy(
            isValid = hasNoBlankField && isPasswordValid
        )

        _stateFlow.tryEmit(s)
    }

    companion object {
        private const val STATE_KEY = "state"
    }
}
