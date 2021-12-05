package com.prephouse.prephouse.models.models.profile

import android.os.Parcelable
import androidx.annotation.IntRange
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRegistrationState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    @IntRange(from = 0, to = 4) var passwordStrength: Int = 0,
    var isPasswordValid: Boolean = false,
    var isValid: Boolean = false,
) : Parcelable
