package com.prephouse.prephouse.utils

import androidx.lifecycle.SavedStateHandle

inline fun <reified T> SavedStateHandle.getOrCreate(key: String): T {
    return get<T>(key) ?: T::class.java.newInstance()
}
