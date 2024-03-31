package com.autodata.api.integers

class IntegerRange(min: Int? = null, max: Int? = null) {
    val min = min ?: Int.MIN_VALUE
    val max = max ?: Int.MAX_VALUE

    init {
        if (this.min > this.max) {
            throw IllegalArgumentException(
                "Minimum value $min must be less than or equal to maximum value $max"
            )
        }
    }
}
