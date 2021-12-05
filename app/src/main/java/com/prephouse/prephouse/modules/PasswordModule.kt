package com.prephouse.prephouse.modules

import com.prephouse.prephouse.utils.ZxcvbnWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PasswordModule {
    @Provides
    fun providesPasswordStrengthMeasurer() = ZxcvbnWrapper()
}
