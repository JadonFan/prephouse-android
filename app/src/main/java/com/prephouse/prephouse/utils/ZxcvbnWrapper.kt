package com.prephouse.prephouse.utils

import com.nulabinc.zxcvbn.Strength
import com.nulabinc.zxcvbn.Zxcvbn

class ZxcvbnWrapper : Zxcvbn() {
    private val sanitizedInputs: List<String> = listOf(
        "uwaterloo",
        "prephouse",
        "jadon",
    )

    override fun measure(password: CharSequence): Strength = measure(password, sanitizedInputs)

    override fun measure(password: CharSequence, sanitizedInputs: List<String>): Strength =
        super.measure(password, this.sanitizedInputs + sanitizedInputs)
}
