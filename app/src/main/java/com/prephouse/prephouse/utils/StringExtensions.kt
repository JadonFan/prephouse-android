package com.prephouse.prephouse.utils

import java.util.UUID

fun String?.toUUID() = this?.let { UUID.fromString(it) }
